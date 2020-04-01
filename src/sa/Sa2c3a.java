package sa;

import ts.*;
import c3a.*;


public class Sa2c3a extends SaDepthFirstVisitor<C3aOperand> {

    private C3a c3a;
    private Ts table;
	private Ts tableCourante;
    
    public Sa2c3a(SaNode root, Ts table){
        c3a = new C3a();
        this.table = table;
		this.tableCourante = this.table;
        
        root.accept(this); 
    }
	
	public C3a getC3a(){
		return this.c3a;
	}
	
	private TsItemVar getVar(String name){
		
		if(this.tableCourante.getVar(name) != null){
			return this.tableCourante.getVar(name);
		} else{
			return this.table.getVar(name);
		}
	}

    public C3aOperand visit(SaProg node){
        node.getFonctions().accept(this);
        return null ;
    }

    public C3aOperand visit(SaDecTab node){
        return null ;
    }

    public C3aOperand visit(SaDecFonc node){
		TsItemFct fct = this.table.getFct(node.getNom());
		C3aInstFBegin begin = new C3aInstFBegin(fct, "entree fonction");
        c3a.ajouteInst(begin);
		
		this.tableCourante = this.table.getTableLocale(node.getNom());
        
        if(node.getCorps() != null)
    		node.getCorps().accept(this);
		
		C3aInstFEnd end = new C3aInstFEnd("fin fonction");
        c3a.ajouteInst(end);
		
		
        return null ;
    }
	
	
	public C3aOperand visit(SaInstSi node) {
		C3aLabel label1 = c3a.newAutoLabel();
		
		C3aOperand test = node.getTest().accept(this);
		C3aInstJumpIfEqual jumpIf = new C3aInstJumpIfEqual(test, c3a.False, label1, "");
		c3a.ajouteInst(jumpIf);
		
		node.getAlors().accept(this);
		
		if(node.getSinon() != null){
			C3aLabel label2 = c3a.newAutoLabel();
			C3aInstJump jump = new C3aInstJump(label2, "");
			c3a.ajouteInst(jump);
			c3a.addLabelToNextInst(label1);
			
			node.getSinon().accept(this);
			
			c3a.addLabelToNextInst(label2);
			
		} else{
			c3a.addLabelToNextInst(label1);
		}
		
		return null;
    }
	
	
	public C3aOperand visit(SaInstTantQue node) {
		C3aLabel label1 = c3a.newAutoLabel();
		C3aLabel label2 = c3a.newAutoLabel();
		
		c3a.addLabelToNextInst(label1);
		C3aOperand test = node.getTest().accept(this);
		C3aInstJumpIfEqual jumpIf = new C3aInstJumpIfEqual(test, c3a.False, label2, "");
		c3a.ajouteInst(jumpIf);
		
		node.getFaire().accept(this);
		
		C3aInstJump jump = new C3aInstJump(label1, "");
		c3a.ajouteInst(jump);
		
		c3a.addLabelToNextInst(label2);
		
		return null;
    }


    public C3aOperand visit(SaInstAffect node){
	    C3aOperand op1 = node.getLhs().accept(this);
        C3aOperand result = node.getRhs().accept(this);
        C3aInstAffect affect = new C3aInstAffect(result, op1, "");
        c3a.ajouteInst(affect);
		
	    return null;
    }

    public C3aOperand visit(SaExpMult node){
	    C3aOperand op1 = node.getOp1().accept(this) ;
	    C3aOperand op2 = node.getOp2().accept(this) ;
        C3aOperand result = c3a.newTemp();
        
        C3aInstMult mult = new C3aInstMult(op1, op2, result, "");
        c3a.ajouteInst(mult);
     
	    return result ;
    }

    public C3aOperand visit(SaExpDiv node){
	    C3aOperand op1 = node.getOp1().accept(this) ;
	    C3aOperand op2 = node.getOp2().accept(this) ;
        C3aOperand result = c3a.newTemp();
	    C3aInstDiv div = new C3aInstDiv(op1, op2, result, "");
        c3a.ajouteInst(div);
		
	    return result ;
    }

    public C3aOperand visit(SaExpSub node){
	    C3aOperand op1 = node.getOp1().accept(this) ;
	    C3aOperand op2 = node.getOp2().accept(this) ;
        C3aOperand result = c3a.newTemp();
	    C3aInstSub sub = new C3aInstSub(op1, op2, result, "");
        c3a.ajouteInst(sub);
		
	    return result ;
    }

    public C3aOperand visit(SaExpAdd node){
	    C3aOperand op1 = node.getOp1().accept(this) ;
	    C3aOperand op2 = node.getOp2().accept(this) ;
        C3aOperand result = c3a.newTemp();
        
	    C3aInstAdd add = new C3aInstAdd(op1, op2, result, "");
        c3a.ajouteInst(add);
		
	    return result ;
    }

    public C3aOperand visit(SaExpInf node){
		C3aLabel label = c3a.newAutoLabel();
		C3aOperand tmp = c3a.newTemp();
	    C3aOperand op1 = node.getOp1().accept(this);
	    C3aOperand op2 = node.getOp2().accept(this);
		
		C3aInstAffect affect = new C3aInstAffect(c3a.True, tmp, "");
		c3a.ajouteInst(affect);
	    C3aInstJumpIfLess inf = new C3aInstJumpIfLess(op1, op2, label, "");
        c3a.ajouteInst(inf);
		affect = new C3aInstAffect(c3a.False, tmp, "");
		c3a.ajouteInst(affect);
		
		c3a.addLabelToNextInst(label);
		
	    return tmp;
    }

    public C3aOperand visit(SaExpEqual node){
        C3aLabel label = c3a.newAutoLabel();
		C3aOperand tmp = c3a.newTemp();
		C3aOperand op1 = node.getOp1().accept(this);
	    C3aOperand op2 = node.getOp2().accept(this);
		
		C3aInstAffect affect = new C3aInstAffect(c3a.True, tmp, "");
		c3a.ajouteInst(affect);
	    C3aInstJumpIfEqual equal = new C3aInstJumpIfEqual(op1, op2, label, "");
        c3a.ajouteInst(equal);
		affect = new C3aInstAffect(c3a.False, tmp, "");
		c3a.ajouteInst(affect);
		
		c3a.addLabelToNextInst(label);
		
	    return tmp;
    }
	
	public C3aOperand visit(SaExpAnd node) {
		C3aLabel label1 = c3a.newAutoLabel();
		C3aLabel label2 = c3a.newAutoLabel();
		C3aOperand tmp = c3a.newTemp();
		C3aOperand op1 = node.getOp1().accept(this);
		C3aOperand op2 = node.getOp2().accept(this);
		
		C3aInstJumpIfEqual jump1 = new C3aInstJumpIfEqual(op1, c3a.False, label2, "");
		C3aInstJumpIfEqual jump2 = new C3aInstJumpIfEqual(op2, c3a.False, label2, "");
		c3a.ajouteInst(jump1);
		c3a.ajouteInst(jump2);
		
		C3aInstAffect affect = new C3aInstAffect(c3a.True, tmp, "");
		c3a.ajouteInst(affect);
		C3aInstJump jump = new C3aInstJump(label1, "");
		c3a.ajouteInst(jump);

		c3a.addLabelToNextInst(label2);
		
		affect = new C3aInstAffect(c3a.False, tmp, "");
		c3a.ajouteInst(affect);
		
		c3a.addLabelToNextInst(label1);
		
		return tmp;
    }
	
	
	public C3aOperand visit(SaExpOr node) {
		C3aLabel label1 = c3a.newAutoLabel();
		C3aLabel label2 = c3a.newAutoLabel();
		C3aOperand tmp = c3a.newTemp();
		C3aOperand op1 = node.getOp1().accept(this);
		C3aOperand op2 = node.getOp2().accept(this);
		
		C3aInstJumpIfNotEqual jump1 = new C3aInstJumpIfNotEqual(op1, c3a.False, label2, "");
		C3aInstJumpIfNotEqual jump2 = new C3aInstJumpIfNotEqual(op2, c3a.False, label2, "");
		c3a.ajouteInst(jump2);
		c3a.ajouteInst(jump1);
		
		C3aInstAffect affect = new C3aInstAffect(c3a.False, tmp, "");
		c3a.ajouteInst(affect);
		C3aInstJump jump = new C3aInstJump(label1, "");
		c3a.ajouteInst(jump);
		
		c3a.addLabelToNextInst(label2);
		
		affect = new C3aInstAffect(c3a.True, tmp, "");
		c3a.ajouteInst(affect);
		
		c3a.addLabelToNextInst(label1);
		
		return tmp;
    }
	
	public C3aOperand visit(SaExpNot node) {
		node.getOp1().accept(this);
		return null;
    }
	
	
	public C3aOperand visit(SaExpLire node) {
		C3aOperand result = c3a.newTemp();
		C3aInstRead read = new C3aInstRead(result, "");
		
		c3a.ajouteInst(read);
		
		return result;
    }
	
	
	public C3aOperand visit(SaInstEcriture node) {
		C3aOperand op = node.getArg().accept(this);
		C3aInstWrite write = new C3aInstWrite(op, "");
		
		c3a.ajouteInst(write);
		
		return null;
    }
	
	
	public C3aOperand visit(SaVarIndicee node) {
		C3aOperand id = node.getIndice().accept(this);
		TsItemVar item = this.getVar(node.getNom());

		return new C3aVar(item, id);
    }
	
	public C3aOperand visit(SaVarSimple node) {
		TsItemVar item = this.getVar(node.getNom());

		return new C3aVar(item, null);
    }
	
	
	public C3aOperand visit(SaExpInt node) {
		return new C3aConstant(node.getVal());
    }
	
	
	public C3aOperand visit(SaExpVar node) {
		return node.getVar().accept(this);
    }
	
	public C3aOperand visit(SaExpAppel node) {
		return node.getVal().accept(this);
    }
	
	
	public C3aOperand visit(SaAppel node) {
		if(node.getArguments() != null){
			SaLExp arguments = node.getArguments();
			SaExp argument;
			
			while(arguments != null){
				argument = arguments.getTete();
				
				C3aOperand op = argument.accept(this);
				C3aInstParam param = new C3aInstParam(op, "");
				c3a.ajouteInst(param);
				
				arguments = arguments.getQueue();
			}
		}
		
		C3aFunction function = new C3aFunction(this.table.getFct(node.getNom()));
		C3aOperand result = c3a.newTemp();
		C3aInstCall call = new C3aInstCall(function, result, "");
		
		c3a.ajouteInst(call);
		
		return result;
    }
	
	
	public C3aOperand visit(SaInstRetour node) {
		C3aOperand val = node.getVal().accept(this);
		C3aInstReturn ret = new C3aInstReturn(val, "");
		
		c3a.ajouteInst(ret);
		
		return null;
    }
 
}
