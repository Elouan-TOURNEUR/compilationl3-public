package fg;
import nasm.*;
import util.graph.*;
import util.intset.IntSet;

import java.util.*;
import java.io.*;

public class Fg implements NasmVisitor <Void> {
    public Nasm nasm;
    public Graph graph;
    private FgSolution fgs;
    public Map<NasmInst, Node> inst2Node;
    Map<Node, NasmInst> node2Inst;
    Map<String, NasmInst> label2Inst;
    public Map<NasmRegister, Integer> temp2Num;

    private boolean firstCourse;
    private boolean construction;
    private boolean changed;
    private int sizeArray;
    private int counter;

    public Fg(Nasm nasm){
        this.nasm = nasm;
        this.fgs = new FgSolution(nasm, this);
        this.inst2Node = new HashMap<>();
        this.node2Inst = new HashMap<>();
        this.label2Inst = new HashMap<>();
        this.temp2Num = new HashMap<>();
        this.graph = new Graph();

        /* Construction */
        this.construction = true;
        this.firstCourse = true;
        for(NasmInst inst : nasm.listeInst){
            inst.accept(this);
        }

        this.firstCourse = false;
        for(NasmInst inst : nasm.listeInst){
            inst.accept(this);
        }

        /* Resolution */
        this.counter = 0;
        this.sizeArray = inst2Node.size();
        this.construction = false;
        this.firstCourse = true;
        for(NasmInst inst : nasm.listeInst){
            inst.accept(this);
        }

        this.firstCourse = false;
        this.changed = true;
        while(this.changed) {
            this.changed = false;
            this.fgs.iterNum++;

            for (NasmInst inst : nasm.listeInst) {
                inst.accept(this);
            }
        }

    }

    public void affiche(String baseFileName){
        String fileName;
        PrintStream out = System.out;

        if (baseFileName != null){
            try {
                baseFileName = baseFileName;
                fileName = baseFileName + ".fg";
                out = new PrintStream(fileName);
            }

            catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        for(NasmInst nasmInst : nasm.listeInst){
            Node n = this.inst2Node.get(nasmInst);
            out.print(n + " : ( ");
            for(NodeList q=n.succ(); q!=null; q=q.tail) {
                out.print(q.head.toString());
                out.print(" ");
            }
            out.println(")\t" + nasmInst);
        }
    }

    public FgSolution getFgs(){
        return this.fgs;
    }


    private void addEdge(Node node){
        Node[] nodes = graph.nodeArray();
        int idx = nodes.length-2;
        if(idx < 0) return;

        Node prev = nodes[idx];
        if(prev != null){
            graph.addEdge(prev, node);
        }
    }


    private void addEdgeLabel(NasmInst inst){
        if((inst.address != null) && (label2Inst.get(inst.address.toString()) != null)){
            Node succ = inst2Node.get(label2Inst.get(inst.address.toString()));
            graph.addEdge(inst2Node.get(inst), succ);
        }
    }

    private void rmEdge(NasmInst inst){
        Node node = inst2Node.get(inst);
        if(node.succ().head != null)
            graph.rmEdge(node, node.succ().head);
    }


    private Node createNode(NasmInst inst){
        Node node = graph.newNode();
        inst2Node.put(inst, node);
        node2Inst.put(node, inst);

        if((inst.label != null) && (label2Inst.get(inst.label.toString()) == null))
            label2Inst.put(inst.label.toString(), inst);

        return node;
    }


    private void initMapFgs(NasmInst inst){
        fgs.def.put(inst, new IntSet(this.sizeArray));
        fgs.use.put(inst, new IntSet(this.sizeArray));
        fgs.in.put(inst, new IntSet(this.sizeArray));
        fgs.out.put(inst, new IntSet(this.sizeArray));
    }

    private void computeInOut(NasmInst inst){
        IntSet prevIn = fgs.in.get(inst);
        IntSet minusSet = fgs.out.get(inst).copy();
        IntSet useSet = fgs.use.get(inst).copy();
        IntSet inRes = useSet.union(minusSet.minus(fgs.def.get(inst)));

        IntSet prevOut = fgs.out.get(inst).copy();
        IntSet outRes = new IntSet(this.sizeArray);
        NodeList list = inst2Node.get(inst).succ();
        while(list != null){
            Node head = list.head;
            outRes.union(fgs.in.get(node2Inst.get(head)));
            list = list.tail;
        }

        if(!prevIn.equal(inRes)) fgs.in.put(inst, inRes);
        if(!prevOut.equal(outRes)) fgs.out.put(inst, outRes);

        this.changed = this.changed || (!prevIn.equal(inRes)) || (!prevOut.equal(outRes));
    }


    private void computeDef(NasmInst inst, NasmOperand operand){
        if((operand instanceof NasmRegister) && (((NasmRegister) operand).color != Nasm.REG_ESP) && (((NasmRegister) operand).color != Nasm.REG_EBP)){
            int temp;
            if(temp2Num.containsKey(operand)){
                temp = temp2Num.get(operand);
            } else{
                temp = this.counter++;
            }

            this.temp2Num.put(new NasmRegister((NasmRegister) operand), temp);
            fgs.def.get(inst).add(temp);
        } else if(operand instanceof  NasmAddress){
            this.computeUse(inst, ((NasmAddress) operand).base);
            this.computeUse(inst, ((NasmAddress) operand).offset);
        }
    }

    private void computeUse(NasmInst inst, NasmOperand operand){
        if((operand instanceof NasmRegister) && (((NasmRegister) operand).color != Nasm.REG_ESP) && (((NasmRegister) operand).color != Nasm.REG_EBP)){
            int temp = this.temp2Num.get(operand);
            fgs.use.get(inst).add(temp);
        } else if(operand instanceof  NasmAddress){
            this.computeUse(inst, ((NasmAddress) operand).base);
            this.computeUse(inst, ((NasmAddress) operand).offset);
        }
    }





    public Void visit(NasmAdd inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
        } else{
            if(this.firstCourse){
                this.initMapFgs(inst);
                this.computeDef(inst, inst.destination);
                this.computeUse(inst, inst.source);
                this.computeUse(inst, inst.destination);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmCall inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
            else{
                this.rmEdge(inst);
                this.addEdgeLabel(inst);
            }
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmDiv inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
                this.computeUse(inst, inst.source);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmJe inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
            else{
                this.addEdgeLabel(inst);
            }
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmJle inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
            else{
                this.addEdgeLabel(inst);
            }
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmJne inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
            else{
                this.addEdgeLabel(inst);
            }
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmMul inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
                this.computeDef(inst, inst.destination);
                this.computeUse(inst, inst.source);
                this.computeUse(inst, inst.destination);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmOr inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmCmp inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
                this.computeUse(inst, inst.destination);
                this.computeUse(inst, inst.source);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmInst inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmJge inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
            else{
                this.addEdgeLabel(inst);
            }
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmJl inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
            else{
                this.addEdgeLabel(inst);
            }
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmNot inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmPop inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
                this.computeDef(inst, inst.destination);
            } else {
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmRet inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
            }
        }

        return null;
    }
    public Void visit(NasmXor inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmAnd inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmJg inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
            else{
                this.addEdgeLabel(inst);
            }
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmJmp inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
            else{
                this.rmEdge(inst);
                this.addEdgeLabel(inst);
            }
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmMov inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
        } else{
            if(this.firstCourse){
                this.initMapFgs(inst);
                this.computeDef(inst, inst.destination);
                //this.computeUse(inst, inst.destination);
                this.computeUse(inst, inst.source);
            } else {
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmPush inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmSub inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
        } else{
            if(this.firstCourse){
                this.initMapFgs(inst);
                this.computeDef(inst, inst.destination);
                this.computeUse(inst, inst.source);
                this.computeUse(inst, inst.destination);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }
    public Void visit(NasmEmpty inst){
        if(this.construction){
            if(this.firstCourse)
                this.addEdge(this.createNode(inst));
        } else{
            if(this.firstCourse) {
                this.initMapFgs(inst);
            } else{
                this.computeInOut(inst);
            }
        }

        return null;
    }

    public Void visit(NasmAddress operand){

        return null;
    }

    public Void visit(NasmConstant operand){

        return null;
    }

    public Void visit(NasmLabel operand){

        return null;
    }

    public Void visit(NasmRegister operand){

        return null;
    }


}
