package nasm;

import java.util.List;

import c3a.*;
import sa.*;
import ts.*;


public class C3a2nasm implements C3aVisitor <NasmOperand> {
    private C3a c3a;
    private Nasm nasm;
    private Ts tableGlobale;
    private TsItemFct currentFct;
    private boolean returned;


    public C3a2nasm(C3a c3a, Ts table) {
        tableGlobale = table ;
        this.c3a = c3a ;
        this.nasm = new Nasm(tableGlobale);
        
        /* Initialisation */
        init();
        
        List<C3aInst> insts = c3a.listeInst;
        returned = false;
        for(C3aInst inst : insts) {
        	inst.accept(this);
        }
    }
    
    private void init() {
        NasmLabel label = new NasmLabel("main");
        nasm.ajouteInst(new NasmCall(null, label, ""));
        
        NasmRegister ebx = nasm.newRegister();
        ebx.colorRegister(Nasm.REG_EBX);
        NasmOperand cst1 = new NasmConstant(0);
        nasm.ajouteInst(new NasmMov(null, ebx, cst1, " valeur de retour du programme"));
        
        NasmRegister eax = nasm.newRegister();
        eax.colorRegister(Nasm.REG_EAX);
        NasmOperand cst2 = new NasmConstant(1);
        nasm.ajouteInst(new NasmMov(null, eax, cst2, ""));
        
        nasm.ajouteInst(new NasmInt(null, ""));
    }


    public Nasm getNasm(){
       return nasm;
    }
    

    public NasmOperand visit(C3aInstAdd inst){
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null ;
		returned = false;
        
        NasmOperand oper1 = inst.op1.accept(this) ;
        NasmOperand oper2 = inst.op2.accept(this) ;
        NasmOperand dest = inst.result.accept(this);
        
        nasm.ajouteInst(new NasmMov(label, dest, oper1, ""));
        nasm.ajouteInst(new NasmAdd(null, dest, oper2, ""));
        
        return dest ;
    }

    public NasmOperand visit(C3aInstSub inst){
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null ;
		returned = false;
        
        NasmOperand oper1 = inst.op1.accept(this) ;
        NasmOperand oper2 = inst.op2.accept(this) ;
        NasmOperand dest = inst.result.accept(this) ;
        
        nasm.ajouteInst(new NasmMov(label, dest, oper1, "")) ;
        nasm.ajouteInst(new NasmSub(null, dest, oper2, "")) ;
        
        return dest ;
    }

    public NasmOperand visit(C3aInstMult inst){
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null ;
		returned = false;
        
        NasmOperand oper1 = inst.op1.accept(this) ;
        NasmOperand oper2 = inst.op2.accept(this) ;
        NasmOperand dest = inst.result.accept(this) ;
        
        nasm.ajouteInst(new NasmMov(label, dest, oper1, "")) ;
        nasm.ajouteInst(new NasmMul(null, dest, oper2, "")) ;
        
        return dest ;
    }

    public NasmOperand visit(C3aInstDiv inst){
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null ;
		returned = false;
        
        NasmOperand oper1 = inst.op1.accept(this) ;
        NasmOperand oper2 = inst.op2.accept(this) ;
        NasmOperand dest = inst.result.accept(this) ;
        
        NasmRegister eax = nasm.newRegister();
        eax.colorRegister(Nasm.REG_EAX);
        
        NasmRegister reg = nasm.newRegister();
        
        nasm.ajouteInst(new NasmMov(label, eax, oper1, ""));
        nasm.ajouteInst(new NasmMov(null, reg, oper2, ""));
        nasm.ajouteInst(new NasmDiv(null, reg, ""));
        nasm.ajouteInst(new NasmMov(null, dest, eax, ""));
        
        return dest ;
    }


    public NasmOperand visit(C3aInstFBegin inst){
        NasmRegister ebp = nasm.newRegister();
        ebp.colorRegister(Nasm.REG_EBP);
        NasmRegister esp = nasm.newRegister();
        esp.colorRegister(Nasm.REG_ESP);
        
        NasmLabel label = new NasmLabel(inst.val.identif);
        
        nasm.ajouteInst(new NasmPush(label, ebp, "sauvegarde la valeur de ebp")) ;
        nasm.ajouteInst(new NasmMov(null, ebp, esp, "nouvelle valeur de ebp")) ;
        currentFct = inst.val;
        SaLDec dec = currentFct.saDecFonc.getVariable();
        
        int length = dec!=null ? dec.length() : 0;
        
        NasmConstant nombre = new NasmConstant(length * 4);
        nasm.ajouteInst(new NasmSub(null, esp, nombre, "allocation des variables locales")) ;
       
        return null ;
    }


    public NasmOperand visit(C3aInstFEnd inst){
    	if(! returned){
			NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

			SaLDec dec = currentFct.saDecFonc.getVariable();
			int length = dec!=null ? dec.length() : 0;

			NasmRegister esp = nasm.newRegister();
			esp.colorRegister(Nasm.REG_ESP);
			NasmConstant nombre = new NasmConstant(length * 4);
			nasm.ajouteInst(new NasmAdd(label, esp, nombre, "désallocation des variables locales"));

			NasmRegister ebp = nasm.newRegister();
			ebp.colorRegister(Nasm.REG_EBP);

			nasm.ajouteInst(new NasmPop(null, ebp, "restaure la valeur de ebp")) ;
			nasm.ajouteInst(new NasmRet(null, "")) ;
		}

        return null ;
    }

    public NasmOperand visit(C3aInstWrite inst){
    	NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
    	returned = false;
    	
        NasmOperand oper1 = inst.op1.accept(this) ;
        NasmRegister eax = nasm.newRegister();
        eax.colorRegister(Nasm.REG_EAX);
        NasmLabel prinlf = new NasmLabel("iprintLF");
        nasm.ajouteInst(new NasmMov(label, eax, oper1, "Write 1")) ;
        nasm.ajouteInst(new NasmCall(null, prinlf, "Write 2")) ;

        return null ;
    }


	@Override
	public NasmOperand visit(C3aInstCall inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null ;
		returned = false;

		NasmOperand fct = new NasmLabel(inst.op1.val.identif);
		NasmOperand res = inst.result.accept(this);
		
		NasmRegister esp = nasm.newRegister();
        esp.colorRegister(Nasm.REG_ESP);
        NasmConstant nbr = new NasmConstant(4);
		
		nasm.ajouteInst(new NasmSub(label, esp, nbr, "allocation mémoire pour la valeur de retour"));
		nasm.ajouteInst(new NasmCall(null, fct, ""));
		nasm.ajouteInst(new NasmPop(null, res, "récupération de la valeur de retour"));
		
		int nb = inst.op1.val.getNbArgs()*4;
		if(nb != 0) {
			NasmConstant n = new NasmConstant(nb);
			nasm.ajouteInst(new NasmAdd(null, esp, n, "désallocation des arguments"));
		}

		return res;
	}


	@Override
	public NasmOperand visit(C3aInst inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

    	nasm.ajouteInst(new NasmEmpty(label, ""));
		return null;
	}


	@Override
	public NasmOperand visit(C3aInstJumpIfLess inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		returned = false;
		
		NasmOperand res = inst.result.accept(this);
		NasmOperand op1 = inst.op1.accept(this);
		NasmOperand op2 = inst.op2.accept(this);
		
		if(op1.isGeneralRegister()) {
			nasm.ajouteInst(new NasmCmp(label, op1, op2, "JumpIfLess 1"));
		} else {
			NasmRegister reg = nasm.newRegister();
			nasm.ajouteInst(new NasmMov(label, reg, op1, "JumpIfLess 1"));
			nasm.ajouteInst(new NasmCmp(null, reg, op2, "on passe par un registre temporaire"));
		}
		
		nasm.ajouteInst(new NasmJl(null, res, "JumpIfLess 2"));
		
		return null;
	}


	@Override
	public NasmOperand visit(C3aInstRead inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		returned = false;

		NasmOperand sinput = new NasmLabel("sinput");
		NasmOperand realine = new NasmLabel("readline");
		NasmOperand atoi = new NasmLabel("atoi");
		NasmRegister reg = nasm.newRegister();
		NasmRegister eax = nasm.newRegister();
		eax.colorRegister(Nasm.REG_EAX);

		nasm.ajouteInst(new NasmMov(label, eax, sinput, ""));
		nasm.ajouteInst(new NasmCall(null, realine, ""));
		nasm.ajouteInst(new NasmCall(null, atoi, ""));
		nasm.ajouteInst(new NasmMov(null, reg, eax, ""));

		return null;
	}


	@Override
	public NasmOperand visit(C3aInstAffect inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		returned = false;
		
		NasmOperand res = inst.result.accept(this);
		NasmOperand op = inst.op1.accept(this);
		
		nasm.ajouteInst(new NasmMov(label, res, op, "Affect"));
		
		return res;
	}


	@Override
	public NasmOperand visit(C3aInstJumpIfEqual inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		returned = false;
		
		NasmOperand res = inst.result.accept(this);
		NasmOperand op1 = inst.op1.accept(this);
		NasmOperand op2 = inst.op2.accept(this);
		
		if(op1.isGeneralRegister()) {
			nasm.ajouteInst(new NasmCmp(label, op1, op2, "JumpIfEqual 1"));
		} else {
			NasmRegister reg = nasm.newRegister();
			nasm.ajouteInst(new NasmMov(label, reg, op1, "JumpIfEqual 1"));
			nasm.ajouteInst(new NasmCmp(null, reg, op2, "on passe par un registre temporaire"));
		}
		
		nasm.ajouteInst(new NasmJe(null, res, "JumpIfEqual 2"));
		
		return null;
	}


	@Override
	public NasmOperand visit(C3aInstJumpIfNotEqual inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		returned = false;
		
		NasmOperand op1 = inst.op1.accept(this);
		NasmOperand op2 = inst.op2.accept(this);
		NasmOperand res = inst.result.accept(this);
		
		if(op1.isGeneralRegister()) {
			nasm.ajouteInst(new NasmCmp(label, op1, op2, "jumpIfNotEqual 1"));
		} else {
			NasmRegister reg = nasm.newRegister();
			nasm.ajouteInst(new NasmMov(label, reg, op1, "jumpIfNotEqual 1"));
			nasm.ajouteInst(new NasmCmp(null, reg, op2, "on passe par un registre temporaire"));
		}
		
		nasm.ajouteInst(new NasmJne(null, res, "jumpIfNotEqual 2"));
		
		return null;
	}
	
	@Override
	public NasmOperand visit(C3aInstJump inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		NasmOperand jmp = (inst.result != null) ? inst.result.accept(this) : null;
		returned = false;
		
		nasm.ajouteInst(new NasmJmp(label, jmp, "Jump"));
		
		return null;
	}


	@Override
	public NasmOperand visit(C3aInstParam inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		returned = false;
		NasmOperand param = inst.op1.accept(this);
		
		nasm.ajouteInst(new NasmPush(label, param, "Param"));
		return null;
	}


	@Override
	public NasmOperand visit(C3aInstReturn inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		returned = true;
		
		NasmRegister ebp = nasm.newRegister();
        ebp.colorRegister(Nasm.REG_EBP);
        NasmConstant n = new NasmConstant(2);
        
        NasmOperand op = inst.op1.accept(this);
        NasmAddress adr = new NasmAddress(ebp, '+', n);
        
		nasm.ajouteInst(new NasmMov(label, adr, op, "ecriture de la valeur de retour"));

		SaLDec dec = currentFct.saDecFonc.getVariable();
		int length = dec!=null ? dec.length() : 0;

		NasmRegister esp = nasm.newRegister();
		esp.colorRegister(Nasm.REG_ESP);
		NasmConstant nombre = new NasmConstant(length * 4);
		nasm.ajouteInst(new NasmAdd(null, esp, nombre, "désallocation des variables locales"));

		NasmRegister ebp2 = nasm.newRegister();
		ebp2.colorRegister(Nasm.REG_EBP);

		nasm.ajouteInst(new NasmPop(null, ebp2, "restaure la valeur de ebp")) ;
		nasm.ajouteInst(new NasmRet(null, "")) ;
		
		return null;
	}


	@Override
	public NasmOperand visit(C3aConstant oper) {
		return new NasmConstant(oper.val);
	}


	@Override
	public NasmOperand visit(C3aLabel oper) {
		return new NasmLabel(oper.toString());
	}


	@Override
	public NasmOperand visit(C3aTemp oper) {
		return new NasmRegister(oper.num);
	}


	@Override
	public NasmOperand visit(C3aVar oper) {
		NasmOperand adr;
        NasmOperand var;
        char signe = ' ';
        
        if(oper.index != null) {
            adr = nasm.newRegister();
        	NasmOperand op = oper.index.accept(this);
            nasm.ajouteInst(new NasmMov(null, adr, op, "on passe par un registre temporaire"));
        	signe = '+';
        } else if((! oper.item.isParam) && (currentFct.getTable().getVar(oper.item.getIdentif()) != null)){
        	adr = new NasmConstant(oper.item.adresse+1);
        	signe = '-';
        }else if(oper.item.isParam) {
        	adr = new NasmConstant(currentFct.getNbArgs()*2-oper.item.adresse-1);
        	signe = '+';
        } else {
        	var = new NasmLabel(oper.item.getIdentif());
        	return new NasmAddress(var, ' ', null);
        }
        
        if(oper.index != null) {
        	var = new NasmLabel(oper.item.getIdentif());
        } else {
        	NasmRegister ebp = nasm.newRegister();
            ebp.colorRegister(Nasm.REG_EBP);
            
            var = ebp;
        }
        
        return new NasmAddress(var, signe, adr);
	}


	@Override
	public NasmOperand visit(C3aFunction oper) {
		return new NasmLabel(oper.val.identif);
	}
}


