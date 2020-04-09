package ig;

import fg.*;
import nasm.*;
import util.graph.*;
import util.intset.*;
import java.util.*;
import java.io.*;

public class Ig {
	public Graph graph;
	public FgSolution fgs;
	public int regNb;
	public Nasm nasm;
	public Node int2Node[];
	private ColorGraph colorGraph;


	public Ig(FgSolution fgs){
		this.fgs = fgs;
		this.graph = new Graph();
		this.nasm = fgs.nasm;
		this.regNb = fgs.fg.temp2Num.keySet().size();//this.nasm.getTempCounter();
		this.int2Node = new Node[regNb];

		this.construction();
		colorGraph = new ColorGraph(this.graph, 4, this.getPrecoloredTemporaries(), fgs.fg.temp2Num.keySet().size());
		colorGraph.coloration();
		this.allocateRegisters();
	}


	private void generateNOEdge(Collection<IntSet> sets){
		for(IntSet inSet : sets){
			if(!inSet.isEmpty()){
				for(int i = 0; i < inSet.getSize()-1; i++){
					if(inSet.isMember(i)){
						for(int j = i+1; j < inSet.getSize(); j++){
							if(inSet.isMember(j)){
								this.graph.addNOEdge(int2Node[i], int2Node[j]);
							}
						}
					}
				}
			}
		}
	}


	public void construction(){
		for(int i = 0; i < regNb; i++)
			int2Node[i] = this.graph.newNode();

		generateNOEdge(fgs.in.values());
		generateNOEdge(fgs.out.values());
	}

	public int[] getPrecoloredTemporaries() {
		int[] res = new int[fgs.fg.temp2Num.keySet().size()];
		for(NasmRegister reg : fgs.fg.temp2Num.keySet()){
			res[fgs.fg.temp2Num.get(reg)] = reg.color;
		}

		return res;
	}


	public void allocateRegisters(){
		for(NasmInst inst : this.nasm.listeInst){
			for(NasmRegister reg : fgs.fg.temp2Num.keySet()){
				if(reg.equals(inst.destination)){
					((NasmRegister) inst.destination).colorRegister(colorGraph.couleur[fgs.fg.temp2Num.get(reg)]);
				}
				if(reg.equals(inst.source)){
					((NasmRegister) inst.source).colorRegister(colorGraph.couleur[fgs.fg.temp2Num.get(reg)]);
				}

				if((inst.source instanceof NasmAddress) && (reg.equals(((NasmAddress) inst.source).base))){
					((NasmRegister) ((NasmAddress) inst.source).base).colorRegister(colorGraph.couleur[fgs.fg.temp2Num.get(reg)]);
				}
				if((inst.source instanceof NasmAddress) && (reg.equals(((NasmAddress) inst.source).offset))){
					((NasmRegister) ((NasmAddress) inst.source).offset).colorRegister(colorGraph.couleur[fgs.fg.temp2Num.get(reg)]);
				}

				if((inst.destination instanceof NasmAddress) && (reg.equals(((NasmAddress) inst.destination).base))){
					((NasmRegister) ((NasmAddress) inst.destination).base).colorRegister(colorGraph.couleur[fgs.fg.temp2Num.get(reg)]);
				}
				if((inst.destination instanceof NasmAddress) && (reg.equals(((NasmAddress) inst.destination).offset))){
					((NasmRegister) ((NasmAddress) inst.destination).offset).colorRegister(colorGraph.couleur[fgs.fg.temp2Num.get(reg)]);
				}
			}
		}
	}


	public void affiche(String baseFileName){
		String fileName;
		PrintStream out = System.out;

		if (baseFileName != null){
			try {
				baseFileName = baseFileName;
				fileName = baseFileName + ".ig";
				out = new PrintStream(fileName);
			}

			catch (IOException e) {
				System.err.println("Error: " + e.getMessage());
			}
		}

		for(int i = 0; i < regNb; i++){
			Node n = this.int2Node[i];
			out.print(n + " : ( ");
			for(NodeList q=n.succ(); q!=null; q=q.tail) {
				out.print(q.head.toString());
				out.print(" ");
			}
			out.println(")");
		}
	}
}
	    
    

    
    
