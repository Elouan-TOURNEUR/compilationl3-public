public class Sa2c3a extends SaDepthFirstVisitor <C3aOperand> {

    private C3a c3a;
    private Ts table;
    
    public Sa2c3a(SaNode root, Ts table){
        c3a = newC3a();
        this.table = table;
        
        root.accept(this); 
    }

    public C3aOperand visit(SaProg node){
        
        C3aInstFBegin begin = new C3aInstFBegin(table, "entree fonction");
        c3a.addInst(begin);
        
        node.accept(this);

        C3aInstFEnd end = new C3aInstFEnd("fin fonction");
        c3a.addInst(end);

        return null ;

    }

    public C3aOperand visit(SaDecTab node){
        return null ;
    }

    public C3aOperand visit(SaDecFonc node){
        return null ;
    }


    public C3aOperand visit(SaInstAffect node){
	    C3aOperand op1 = (C3aOperand)node.getLhs().accept(this);
        C3aOperand result = (C3aOperand)node.getRhs().accept(this);
        C3aInstAffect affect = new C3aInstAffect(op1, result, "affectation");
        c3a.addInst(affect);
	    return null;
    }

    public C3aOperand visit(SaExpMult node){
	    C3aOperand op1 = (C3aOperand)node.getOp1().accept(this) ;
	    C3aOperand op2 = (C3aOperand)node.getOp2().accept(this) ;
        C3aOperand result = new C3aTemp(0) ;
        
        C3aInstMult mult = new C3aInstMult(op1, op2, result, "multiplication");
        c3a.addInst(mult);
     
	    return result ;
    }

    public C3aOperand visit(SaExpDiv node){
	    C3aOperand op1 = (C3aOperand)node.getOp1().accept(this) ;
	    C3aOperand op2 = (C3aOperand)node.getOp2().accept(this) ;
        C3aOperand result = new C3aTemp(0) ;
	    C3aInstDiv div = C3aInstDiv(op1, op2, result, "division") ;
        c3a.addInst(div);
	    return result ;
    }

    public C3aOperand visit(SaExpSub node){
	    C3aOperand op1 = (C3aOperand)node.getOp1().accept(this) ;
	    C3aOperand op2 = (C3aOperand)node.getOp2().accept(this) ;
        C3aOperand result = new C3aTemp(0) ;
	    C3aInstSub sub = C3aInstSub(op1, op2, result, "soustraction") ;
        c3a.addInst(sub);
	    return result ;
    }

    public C3aOperand visit(SaExpAdd node){
	    C3aOperand op1 = (C3aOperand)node.getOp1().accept(this) ;
	    C3aOperand op2 = (C3aOperand)node.getOp2().accept(this) ;
        C3aOperand result = new C3aTemp(0) ;
        
	    C3aInstAdd add = C3aInstAdd(op1, op2, result, "addition") ;
        c3a.addInst(add);
	    return result ;
    }

    public C3aOperand visit(SaExpInf node){
	    C3aOperand op1 = (C3aOperand)node.getOp1().accept(this) ;
	    C3aOperand op2 = (C3aOperand)node.getOp2().accept(this) ;
        C3aOperand result = new C3aTemp(0) ;
        
	    C3aInstJumpIfLess inf = C3aInstJumpIfLess(op1, op2, result, "inférieur") ;
        c3a.addInst(inf);
	    return result ;
    }

    public C3aOperand visit(SaExpEqual node){
	    C3aOperand op1 = (C3aOperand)node.getOp1().accept(this) ;
	    C3aOperand op2 = (C3aOperand)node.getOp2().accept(this) ;
        C3aOperand result = new C3aTemp(0) ;
        
	    C3aInstJumpIfNotEqual equal = C3aInstJumpIfNotEqual(op1, op2, result, "equal") ;
        c3a.addInst(equal);
	    return result ;
    }

    public C3aOperand visit(SaExpEqual node){
	    C3aOperand op1 = (C3aOperand)node.getOp1().accept(this) ;
	    C3aOperand op2 = (C3aOperand)node.getOp2().accept(this) ;
        C3aOperand result = new C3aTemp(0) ;
        
	    C3aInstJumpIfNotEqual equal = C3aInstJumpIfNotEqual(op1, op2, result, "equal") ;
        c3a.addInst(equal);
	    return result ;
    }

    public C3aOperand visit(SaExpComp node){


 
}
