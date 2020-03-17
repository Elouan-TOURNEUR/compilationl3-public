package nasm;

import java.util.List;

import c3a.*;
import sa.*;
import ts.*;



/*
 * TODO :
 * - Mettre les commentaire pour chaque instructions (voir pre-nasm)
 * - Faire les autres Jump
 * - Voir à quoi sert les instructions nasm non-utilisées (NasmXor, NasmEmpty, NasmOr, ...)
 * - Valider tous les tests
 */




public class C3a2nasm implements C3aVisitor <NasmOperand> {
    private C3a c3a;
    private Nasm nasm;
    private Ts tableGlobale;
    private TsItemFct currentFct;


    public C3a2nasm(C3a c3a, Ts table) {
        tableGlobale = table ;
        this.c3a = c3a ;
        this.nasm = new Nasm(tableGlobale);
        
        /* Initialisation */
        init();
        
        List<C3aInst> insts = c3a.listeInst;
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
        NasmOperand oper1 = inst.op1.accept(this) ;
        NasmOperand oper2 = inst.op2.accept(this) ;
        NasmOperand dest = inst.result.accept(this);
        nasm.ajouteInst(new NasmMov(label, dest, oper1, ""));
        nasm.ajouteInst(new NasmAdd(null, dest, oper2, ""));
        
        return dest ;
    }

    public NasmOperand visit(C3aInstSub inst){
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null ;
        NasmOperand oper1 = inst.op1.accept(this) ;
        NasmOperand oper2 = inst.op2.accept(this) ;
        NasmOperand dest = inst.result.accept(this) ;
        nasm.ajouteInst(new NasmMov(label, dest, oper1, "")) ;
        nasm.ajouteInst(new NasmSub(null, dest, oper2, "")) ;
        
        return dest ;
    }

    public NasmOperand visit(C3aInstMult inst){
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null ;
        NasmOperand oper1 = inst.op1.accept(this) ;
        NasmOperand oper2 = inst.op2.accept(this) ;
        NasmOperand dest = inst.result.accept(this) ;
        nasm.ajouteInst(new NasmMov(label, dest, oper1, "")) ;
        nasm.ajouteInst(new NasmMul(null, dest, oper2, "")) ;
        
        return dest ;
    }

    public NasmOperand visit(C3aInstDiv inst){
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null ;
        NasmOperand oper1 = inst.op1.accept(this) ;
        NasmOperand oper2 = inst.op2.accept(this) ;
        NasmOperand dest = inst.result.accept(this) ;
        
        NasmRegister eax = nasm.newRegister();
        eax.colorRegister(Nasm.REG_EAX);
        
        nasm.ajouteInst(new NasmMov(label, eax, oper1, ""));
        nasm.ajouteInst(new NasmMov(null, dest, oper2, ""));
        nasm.ajouteInst(new NasmDiv(null, dest, ""));
        nasm.ajouteInst(new NasmMov(null, dest, eax, ""));
        
        return dest ;
    }


    public NasmOperand visit(C3aInstFBegin inst){
        NasmRegister ebp = nasm.newRegister();
        ebp.colorRegister(Nasm.REG_EBP);
        NasmRegister esp = nasm.newRegister();
        esp.colorRegister(Nasm.REG_ESP);
        
        NasmLabel label = new NasmLabel(inst.val.identif);
        
        nasm.ajouteInst(new NasmPush(label, ebp, "")) ;
        nasm.ajouteInst(new NasmMov(null, ebp, esp, "")) ;
        currentFct = inst.val;
        SaLDec dec = currentFct.saDecFonc.getVariable();
        
        int length = dec!=null ? dec.length() : 0;
        
        NasmConstant nombre = new NasmConstant(length * 4);
        nasm.ajouteInst(new NasmSub(null, esp, nombre, "")) ;
       
        return null ;
    }


    public NasmOperand visit(C3aInstFEnd inst){
    	NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
    	
        SaLDec dec = currentFct.saDecFonc.getVariable();
        int length = dec!=null ? dec.length() : 0;
        
        NasmRegister esp = nasm.newRegister();
        esp.colorRegister(Nasm.REG_ESP);
        NasmConstant nombre = new NasmConstant(length * 4);
        nasm.ajouteInst(new NasmAdd(label, esp, nombre, ""));
        
        NasmRegister ebp = nasm.newRegister();
        ebp.colorRegister(Nasm.REG_EBP);
        
        nasm.ajouteInst(new NasmPop(null, ebp, "")) ;
        nasm.ajouteInst(new NasmRet(null, "")) ;

        return null ;
    }

    public NasmOperand visit(C3aInstWrite inst){
        NasmOperand oper1 = inst.op1.accept(this) ;
        NasmRegister eax = nasm.newRegister();
        eax.colorRegister(Nasm.REG_EAX);
        NasmLabel prinlf = new NasmLabel("iprintLF");
        nasm.ajouteInst(new NasmMov(null, eax, oper1, "")) ;
        nasm.ajouteInst(new NasmCall(null, prinlf, "")) ;

        return null ;
    }


	@Override
	public NasmOperand visit(C3aInstCall inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null ;
		NasmOperand fct = new NasmLabel(inst.op1.val.identif);
		NasmOperand res = inst.result.accept(this);
		
		NasmRegister esp = nasm.newRegister();
        esp.colorRegister(Nasm.REG_ESP);
        NasmConstant nbr = new NasmConstant(4);
		
		nasm.ajouteInst(new NasmSub(label, esp, nbr, ""));
		nasm.ajouteInst(new NasmCall(null, fct, ""));
		nasm.ajouteInst(new NasmPop(null, res, ""));
		
		int nb = inst.op1.val.getNbArgs()*4;
		if(nb != 0) {
			NasmConstant n = new NasmConstant(nb);
			nasm.ajouteInst(new NasmAdd(null, esp, n, ""));
		}
		
		
		return res;
	}


	@Override
	public NasmOperand visit(C3aInst inst) {
		return null;
	}


	@Override
	public NasmOperand visit(C3aInstJumpIfLess inst) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public NasmOperand visit(C3aInstRead inst) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public NasmOperand visit(C3aInstAffect inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		NasmOperand op = inst.op1.accept(this);
		NasmOperand res = inst.result.accept(this);
		
		nasm.ajouteInst(new NasmMov(label, res, op, ""));
		
		return res;
	}


	@Override
	public NasmOperand visit(C3aInstJumpIfEqual inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		
		NasmRegister eax = nasm.newRegister();
        eax.colorRegister(Nasm.REG_EAX);
		
		NasmOperand res = inst.result.accept(this);
		NasmOperand op1 = inst.op1.accept(this);
		NasmOperand op2 = inst.op2.accept(this);
		
		nasm.ajouteInst(new NasmMov(label, eax, op1, ""));
		nasm.ajouteInst(new NasmCmp(label, eax, op2, ""));
		nasm.ajouteInst(new NasmJe(label, res, ""));
		
		return null;
	}


	@Override
	public NasmOperand visit(C3aInstJumpIfNotEqual inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		
		NasmRegister eax = nasm.newRegister();
        eax.colorRegister(Nasm.REG_EAX);
		
		NasmOperand res = inst.result.accept(this);
		NasmOperand op1 = inst.op1.accept(this);
		NasmOperand op2 = inst.op2.accept(this);
		
		nasm.ajouteInst(new NasmMov(label, eax, op1, ""));
		nasm.ajouteInst(new NasmCmp(label, eax, op2, ""));
		nasm.ajouteInst(new NasmJne(label, res, ""));
		
		return null;
	}
	
	@Override
	public NasmOperand visit(C3aInstJump inst) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public NasmOperand visit(C3aInstParam inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		NasmOperand param = inst.op1.accept(this);
		
		nasm.ajouteInst(new NasmPush(label, param, ""));
		return null;
	}


	@Override
	public NasmOperand visit(C3aInstReturn inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		
		NasmRegister ebp = nasm.newRegister();
        ebp.colorRegister(Nasm.REG_EBP);
        NasmConstant n = new NasmConstant(2);
        
        NasmOperand op = inst.op1.accept(this);
        NasmAddress adr = new NasmAddress(ebp, '+', n);
		nasm.ajouteInst(new NasmMov(label, adr, op, ""));
		
		return null;
	}


	@Override
	public NasmOperand visit(C3aConstant oper) {
		NasmOperand constant = new NasmConstant(oper.val);
		
		return constant;
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
        	adr = oper.index.accept(this);
        	signe = '+';
        } else if(currentFct.getTable().getVar(oper.item.getIdentif()) != null){
        	adr = new NasmConstant(oper.item.adresse+1);
        	signe = '-';
        }else if(oper.item.isParam) {
        	adr = new NasmConstant(currentFct.getNbArgs()*2-oper.item.adresse);
        	signe = '+';
        
        // TODO : à tester :
        } else {
        	return new NasmLabel(oper.item.getIdentif());
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


