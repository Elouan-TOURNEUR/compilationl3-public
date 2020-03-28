package fg;
import nasm.*;
import util.graph.*;
import java.util.*;
import java.io.*;

public class Fg implements NasmVisitor <Void> {
    public Nasm nasm;
    public Graph graph;
    Map< NasmInst, Node> inst2Node;
    Map< Node, NasmInst> node2Inst;
    Map< String, NasmInst> label2Inst;

    private boolean firstCourse;

    public Fg(Nasm nasm){
        this.nasm = nasm;
        this.inst2Node = new HashMap< NasmInst, Node>();
        this.node2Inst = new HashMap< Node, NasmInst>();
        this.label2Inst = new HashMap< String, NasmInst>();
        this.graph = new Graph();

        this.firstCourse = true;
        for(NasmInst inst : nasm.listeInst){
            inst.accept(this);
        }

        this.firstCourse = false;
        for(NasmInst inst : nasm.listeInst){
            inst.accept(this);
        }
    }

    public void affiche(String baseFileName){
        String fileName;
        PrintStream out = System.out;

        if (baseFileName != null){
            try {
                //baseFileName = baseFileName;
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


    private void addEdge(Node node){
        Node[] nodes = graph.nodeArray();
        int idx = nodes.length-2;
        if(idx < 0) return;

        Node prev = nodes[idx];
        if(prev != null){
            graph.addEdge(prev, node);
        }
    }


    private Node createNode(NasmInst inst){
        Node node = graph.newNode();
        inst2Node.put(inst, node);
        node2Inst.put(node, inst);

        if((inst.label != null) && (label2Inst.get(inst.label.toString()) == null))
            label2Inst.put(inst.label.toString(), inst);

        return node;
    }







    public Void visit(NasmAdd inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));

        return null;
    }
    public Void visit(NasmCall inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));
        else{
            Node node = inst2Node.get(inst);
            if(node.succ().head != null)
                graph.rmEdge(node, node.succ().head);

            if((inst.address != null) && (label2Inst.get(inst.address.toString()) != null)){
                Node succ = inst2Node.get(label2Inst.get(inst.address.toString()));
                graph.addEdge(inst2Node.get(inst), succ);
            }
        }

        return null;
    }
    public Void visit(NasmDiv inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));

        return null;
    }
    public Void visit(NasmJe inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));
        else{
            if((inst.address != null) && (label2Inst.get(inst.address.toString()) != null)){
                Node succ = inst2Node.get(label2Inst.get(inst.address.toString()));
                graph.addEdge(inst2Node.get(inst), succ);
            }
        }

        return null;
    }
    public Void visit(NasmJle inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));
        else{
            if((inst.address != null) && (label2Inst.get(inst.address.toString()) != null)){
                Node succ = inst2Node.get(label2Inst.get(inst.address.toString()));
                graph.addEdge(inst2Node.get(inst), succ);
            }
        }

        return null;
    }
    public Void visit(NasmJne inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));
        else{
            if((inst.address != null) && (label2Inst.get(inst.address.toString()) != null)){
                Node succ = inst2Node.get(label2Inst.get(inst.address.toString()));
                graph.addEdge(inst2Node.get(inst), succ);
            }
        }

        return null;
    }
    public Void visit(NasmMul inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));

        return null;
    }
    public Void visit(NasmOr inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));

        return null;
    }
    public Void visit(NasmCmp inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));

        return null;
    }
    public Void visit(NasmInst inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));

        return null;
    }
    public Void visit(NasmJge inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));
        else{
            if((inst.address != null) && (label2Inst.get(inst.address.toString()) != null)){
                Node succ = inst2Node.get(label2Inst.get(inst.address.toString()));
                graph.addEdge(inst2Node.get(inst), succ);
            }
        }

        return null;
    }
    public Void visit(NasmJl inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));
        else{
            if((inst.address != null) && (label2Inst.get(inst.address.toString()) != null)){
                Node succ = inst2Node.get(label2Inst.get(inst.address.toString()));
                graph.addEdge(inst2Node.get(inst), succ);
            }
        }

        return null;
    }
    public Void visit(NasmNot inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));

        return null;
    }
    public Void visit(NasmPop inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));

        return null;
    }
    public Void visit(NasmRet inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));

        return null;
    }
    public Void visit(NasmXor inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));

        return null;
    }
    public Void visit(NasmAnd inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));

        return null;
    }
    public Void visit(NasmJg inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));
        else{
            if((inst.address != null) && (label2Inst.get(inst.address.toString()) != null)){
                Node succ = inst2Node.get(label2Inst.get(inst.address.toString()));
                graph.addEdge(inst2Node.get(inst), succ);
            }
        }

        return null;
    }
    public Void visit(NasmJmp inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));
        else{
            Node node = inst2Node.get(inst);
            if(node.succ().head != null)
                graph.rmEdge(node, node.succ().head);

            if((inst.address != null) && (label2Inst.get(inst.address.toString()) != null)){
                Node succ = inst2Node.get(label2Inst.get(inst.address.toString()));
                graph.addEdge(inst2Node.get(inst), succ);
            }
        }

        return null;
    }
    public Void visit(NasmMov inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));

        return null;
    }
    public Void visit(NasmPush inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));

        return null;
    }
    public Void visit(NasmSub inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));

        return null;
    }
    public Void visit(NasmEmpty inst){
        if(this.firstCourse)
            this.addEdge(this.createNode(inst));

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
