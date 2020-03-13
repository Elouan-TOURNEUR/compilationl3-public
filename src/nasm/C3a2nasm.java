package nasm;

import c3a.*;
import sa.*;
import ts.*;

public class C3a2nasm implements C3aVisitor <NasmOperand> {
    private C3a c3a;
    private Nasm nasm;
    private Ts tableGlobale;
    private TsItemFct currentFct;


    public C3a2nasm(C3a c3a, Ts table) {
        tableGlobale = table ;
        this.c3a = c3a ;
    }


    public Nasm getNasm(){
       return nasm;
    }
    

    public NasmOperand visit(C3aInstAdd inst){
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null ;
        NasmOperand oper1 = inst.op1.accept(this) ;
        NasmOperand oper2 = inst.op2.accept(this) ;
        NasmOperand dest = inst.result.accept(this) ;
        nasm.ajouteInst(new NasmMov(label, dest, oper1, "")) ;
        nasm.ajouteInst(new NasmAdd(null, dest, oper2, "")) ;
        return null ;
    }

    public NasmOperand visit(C3aInstSub inst){
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null ;
        NasmOperand oper1 = inst.op1.accept(this) ;
        NasmOperand oper2 = inst.op2.accept(this) ;
        NasmOperand dest = inst.result.accept(this) ;
        nasm.ajouteInst(new NasmMov(label, dest, oper1, "")) ;
        nasm.ajouteInst(new NasmSub(null, dest, oper2, "")) ;
        return null ;
    }

    public NasmOperand visit(C3aInstMult inst){
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null ;
        NasmOperand oper1 = inst.op1.accept(this) ;
        NasmOperand oper2 = inst.op2.accept(this) ;
        NasmOperand dest = inst.result.accept(this) ;
        nasm.ajouteInst(new NasmMov(label, dest, oper1, "")) ;
        nasm.ajouteInst(new NasmMul(null, dest, oper2, "")) ;
        return null ;
    }

    public NasmOperand visit(C3aInstDiv inst){
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null ;
        NasmOperand oper1 = inst.op1.accept(this) ;
        NasmOperand oper2 = inst.op2.accept(this) ;
        NasmOperand dest = inst.result.accept(this) ;
        nasm.ajouteInst(new NasmMov(label, dest, oper1, "")) ;
        nasm.ajouteInst(new NasmDiv(null, oper2, "")) ;
        return null ;
    }


    public NasmOperand visit(C3aInstFBegin inst){
        //NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null ;
        NasmRegister ebp = nasm.newRegister();
        ebp.colorRegister(Nasm.REG_EBP);
        NasmRegister esp = nasm.newRegister();
        esp.colorRegister(Nasm.REG_ESP);
        
        nasm.ajouteInst(new NasmPush(null, ebp, "")) ;
        nasm.ajouteInst(new NasmMov(null, ebp, esp, "")) ;
        
        currentFct = inst.val;
        SaLDec dec = currentFct.saDecFonc.getVariable();
        
        if(dec != null){
            NasmConstant nombre = new NasmConstant(dec.length() * 4);
            nasm.ajouteInst(new NasmSub(null, esp, nombre, "")) ;
        }
       
        return null ;
    }


    public NasmOperand visit(C3aInstFEnd inst){
        SaLDec dec = currentFct.saDecFonc.getVariable();
        
        if(dec != null){
            NasmRegister esp = nasm.newRegister();
            esp.colorRegister(Nasm.REG_ESP);
            NasmConstant nombre = new NasmConstant(dec.length() * 4);
            nasm.ajouteInst(new NasmAdd(null, esp, nombre, ""));
        }
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
        nasm.ajouteInst(new NasmMov(null, eax, oper1, "")) ;
        nasm.ajouteInst(new NasmCall(null, eax, "")) ;

        return null ;
    }


	@Override
	public NasmOperand visit(C3aInstCall inst) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public NasmOperand visit(C3aInst inst) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public NasmOperand visit(C3aInstJumpIfEqual inst) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public NasmOperand visit(C3aInstJumpIfNotEqual inst) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public NasmOperand visit(C3aInstJump inst) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public NasmOperand visit(C3aInstParam inst) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public NasmOperand visit(C3aInstReturn inst) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public NasmOperand visit(C3aConstant oper) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public NasmOperand visit(C3aLabel oper) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public NasmOperand visit(C3aTemp oper) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public NasmOperand visit(C3aVar oper) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public NasmOperand visit(C3aFunction oper) {
		// TODO Auto-generated method stub
		return null;
	}


}


