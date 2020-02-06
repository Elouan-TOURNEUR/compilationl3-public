package sa ;


import sc.analysis.DepthFirstAdapter;
import sc.node.*;


public class Sc2sa extends DepthFirstAdapter {
    private SaNode returnValue;


	public SaNode getRoot(){
		return this.returnValue;
	}

    @Override
    public void caseAPProg(APProg node)
    {
        SaLDec variables = null;
        SaLDec fonctions = null;

        inAPProg(node);
        if(node.getLdvo2() != null)
        {
            node.getLdvo2().apply(this);
            variables = (SaLDec) this.returnValue;
        }
        if(node.getLdf() != null)
        {
            node.getLdf().apply(this);
            fonctions = (SaLDec) this.returnValue;
        }
        outAPProg(node);
        
        this.returnValue = new SaProg(variables, fonctions);
    }

    @Override
    public void caseALLdf(ALLdf node)
    {
        SaLDec listeFonctions = null;
        SaDec fonction = null;

        inALLdf(node);
        if(node.getDf() != null)
        {
            node.getDf().apply(this);
            fonction = (SaDecFonc) this.returnValue;
        }
        if(node.getLdf() != null)
        {
            node.getLdf().apply(this);
            listeFonctions = (SaLDec) this.returnValue;
        }
        outALLdf(node);
        
        this.returnValue = new SaLDec(fonction, listeFonctions);
    }
	
	@Override
    public void caseAVideLdf(AVideLdf node)
    {
        inAVideLdf(node);
		this.returnValue = null;
        outAVideLdf(node);
    }

    
    @Override
    public void caseADfDf(ADfDf node)
    {
        String id = null;
        SaLDec parametres = null;
        SaLDec variables = null;
        SaInst iBloc = null;

        inADfDf(node);
        if(node.getId() != null)
        {
			id = node.getId().getText();
            node.getId().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getLdvo() != null)
        {
            node.getLdvo().apply(this);
            parametres = (SaLDec) this.returnValue;
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getLdvo2() != null)
        {
            node.getLdvo2().apply(this);
            variables = (SaLDec) this.returnValue;
        }
        if(node.getIbloc() != null)
        {
            node.getIbloc().apply(this);
            iBloc = (SaInst) this.returnValue;
        }
        outADfDf(node);
		
        this.returnValue = new SaDecFonc(id, parametres, variables, iBloc);
    }
	
	@Override
    public void caseAVideLdvo2(AVideLdvo2 node)
    {
        inAVideLdvo2(node);
		//this.returnValue = null;
        outAVideLdvo2(node);
    }

    @Override
    public void caseALLdvo2(ALLdvo2 node)
    {
        SaLDec declarations = null;
        SaLDec listeDeclarations = null;
        
        inALLdvo2(node);
        if(node.getLdvo() != null)
        {
            node.getLdvo().apply(this);
			declarations = (SaLDec) this.returnValue;
        }
        if(node.getPvir() != null)
        {
            node.getPvir().apply(this);
        }
        if(node.getLdvo2() != null)
        {
            node.getLdvo2().apply(this);
			listeDeclarations = (SaLDec) this.returnValue;
        }
        outALLdvo2(node);
		
		
		for(int i = 0; i < declarations.length()-1; i++){
			listeDeclarations = new SaLDec(declarations.getTete(), listeDeclarations);
			declarations = declarations.getQueue();
		}
		this.returnValue = listeDeclarations;
    }
	
	@Override
    public void caseAVideLdvo(AVideLdvo node)
    {
        inAVideLdvo(node);
		this.returnValue = null;
        outAVideLdvo(node);
    }

    @Override
    public void caseALLdvo(ALLdvo node)
    {
		SaLDec listeDeclarations = null;
		
        inALLdvo(node);
        if(node.getLdv() != null)
        {
            node.getLdv().apply(this);
			listeDeclarations = (SaLDec) this.returnValue;
        }
        outALLdvo(node);
		
		this.returnValue = listeDeclarations;
    }

    @Override
    public void caseALLdv(ALLdv node)
    {
		SaDec declaration = null;
		SaLDec listeDeclarations = null;
		
        inALLdv(node);
        if(node.getDv() != null)
        {
            node.getDv().apply(this);
			declaration = (SaDec) this.returnValue;
        }
        if(node.getVir() != null)
        {
            node.getVir().apply(this);
        }
        if(node.getLdv() != null)
        {
            node.getLdv().apply(this);
			listeDeclarations = (SaLDec) this.returnValue;
        }
        outALLdv(node);
		
		this.returnValue = new SaLDec(declaration, listeDeclarations);
    }

    @Override
    public void caseADvLdv(ADvLdv node)
    {
		SaDec declaration = null;
		
        inADvLdv(node);
        if(node.getDv() != null)
        {
            node.getDv().apply(this);
			declaration = (SaDec) this.returnValue;
        }
        outADvLdv(node);
		
		this.returnValue = new SaLDec(declaration, null);
    }

    @Override
    public void caseAVarDv(AVarDv node)
    {
		SaDec declaration = null;
		
        inAVarDv(node);
        if(node.getEntier() != null)
        {
            node.getEntier().apply(this);
        }
        if(node.getDvar() != null)
        {
            node.getDvar().apply(this);
			declaration = (SaDec) this.returnValue;
        }
        outAVarDv(node);
		
		this.returnValue = declaration;
    }
	
	@Override
    public void caseAIdDvar(AIdDvar node)
    {
		String id = null;
		
        inAIdDvar(node);
        if(node.getId() != null)
        {
			id = node.getId().getText();
            node.getId().apply(this);
        }
        outAIdDvar(node);
		
		this.returnValue = new SaDecVar(id);
    }

    @Override
    public void caseATabDvar(ATabDvar node)
    {
		String id = null;
		Integer taille = null;
		
        inATabDvar(node);
        if(node.getId() != null)
        {
			id = node.getId().getText();
            node.getId().apply(this);
        }
        if(node.getLCroc() != null)
        {
            node.getLCroc().apply(this);
        }
        if(node.getNbr() != null)
        {
			taille = Integer.parseInt(node.getNbr().getText());
            node.getNbr().apply(this);
        }
        if(node.getRCroc() != null)
        {
            node.getRCroc().apply(this);
        }
        outATabDvar(node);
		
		this.returnValue = new SaDecTab(id, taille);
    }

    @Override
    public void caseAIdVar(AIdVar node)
    {
		String id = null;
		
        inAIdVar(node);
        if(node.getId() != null)
        {
			id = node.getId().getText();
            node.getId().apply(this);
        }
        outAIdVar(node);
		
		this.returnValue = new SaVarSimple(id);
    }

    @Override
    public void caseATabVar(ATabVar node)
    {
		String id = null;
		SaExp indice = null;
		
        inATabVar(node);
        if(node.getId() != null)
        {
			id = node.getId().getText();
            node.getId().apply(this);
        }
        if(node.getLCroc() != null)
        {
            node.getLCroc().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
			indice = (SaExp) this.returnValue;
        }
        if(node.getRCroc() != null)
        {
            node.getRCroc().apply(this);
        }
        outATabVar(node);
		
		this.returnValue = new SaVarIndicee(id, indice);
    }

    @Override
    public void caseALexpLe(ALexpLe node)
    {
		SaExp expression = null;
		SaLExp listeExpressions = null;
		
        inALexpLe(node);
        if(node.getE() != null)
        {
            node.getE().apply(this);
			expression = (SaExp) this.returnValue;
        }
        if(node.getVir() != null)
        {
            node.getVir().apply(this);
        }
        if(node.getLe() != null)
        {
            node.getLe().apply(this);
			listeExpressions = (SaLExp) this.returnValue;
        }
        outALexpLe(node);
		
		this.returnValue = new SaLExp(expression, listeExpressions);
    }

    @Override
    public void caseAExpLe(AExpLe node)
    {
		SaExp expression = null;
		
        inAExpLe(node);
        if(node.getE() != null)
        {
            node.getE().apply(this);
			expression = (SaExp) this.returnValue;
        }
        outAExpLe(node);
		
		this.returnValue = new SaLExp(expression, null);
    }
	
	@Override
    public void caseAVideLe(AVideLe node)
    {
        inAVideLe(node);
		this.returnValue = null;
        outAVideLe(node);
    }

    @Override
    public void caseAOuE(AOuE node)
    {
		SaExp expressionG = null;
		SaExp expressionD = null;
		
        inAOuE(node);
        if(node.getE() != null)
        {
            node.getE().apply(this);
			expressionG = (SaExp) this.returnValue;
        }
        if(node.getOu() != null)
        {
            node.getOu().apply(this);
        }
        if(node.getE1() != null)
        {
            node.getE1().apply(this);
			expressionD = (SaExp) this.returnValue;
        }
        outAOuE(node);
		
		this.returnValue = new SaExpOr(expressionD, expressionG);
    }

    @Override
    public void caseAEtE(AEtE node)
    {
		SaExp expression = null;
		
        inAEtE(node);
        if(node.getE1() != null)
        {
            node.getE1().apply(this);
			expression = (SaExp) this.returnValue;
        }
        outAEtE(node);
		
		this.returnValue = expression;
    }

    @Override
    public void caseAEtE1(AEtE1 node)
    {
		SaExp expressionG = null;
		SaExp expressionD = null;
		
        inAEtE1(node);
        if(node.getE1() != null)
        {
            node.getE1().apply(this);
			expressionG = (SaExp) this.returnValue;
        }
        if(node.getEt() != null)
        {
            node.getEt().apply(this);
        }
        if(node.getE2() != null)
        {
            node.getE2().apply(this);
			expressionD = (SaExp) this.returnValue;
        }
        outAEtE1(node);
		
		this.returnValue = new SaExpAnd(expressionG, expressionD);
    }

    @Override
    public void caseAEgalE1(AEgalE1 node)
    {
		SaExp expression = null;
		
        inAEgalE1(node);
        if(node.getE2() != null)
        {
            node.getE2().apply(this);
			expression = (SaExp) this.returnValue;
        }
        outAEgalE1(node);
		
		this.returnValue = expression;
    }

    @Override
    public void caseAEgalE2(AEgalE2 node)
    {
		SaExp expressionG = null;
		SaExp expressionD = null;
		
        inAEgalE2(node);
        if(node.getE2() != null)
        {
            node.getE2().apply(this);
			expressionG = (SaExp) this.returnValue;
        }
        if(node.getEgal() != null)
        {
            node.getEgal().apply(this);
        }
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
			expressionD = (SaExp) this.returnValue;
        }
        outAEgalE2(node);
		
		this.returnValue = new SaExpEqual(expressionG, expressionD);
    }

    @Override
    public void caseAInfE2(AInfE2 node)
    {
		SaExp expressionG = null;
		SaExp expressionD = null;
		
        inAInfE2(node);
        if(node.getE2() != null)
        {
            node.getE2().apply(this);
			expressionG = (SaExp) this.returnValue;
        }
        if(node.getInf() != null)
        {
            node.getInf().apply(this);
        }
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
			expressionD = (SaExp) this.returnValue;
        }
        outAInfE2(node);
		
		this.returnValue = new SaExpInf(expressionG, expressionD);
    }

    @Override
    public void caseAPlusE2(APlusE2 node)
    {
		SaExp expression = null;
		
        inAPlusE2(node);
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
			expression = (SaExp) this.returnValue;
        }
        outAPlusE2(node);
		
		this.returnValue = expression;
    }

    @Override
    public void caseAPlusE3(APlusE3 node)
    {
		SaExp expressionG = null;
		SaExp expressionD = null;
		
        inAPlusE3(node);
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
			expressionG = (SaExp) this.returnValue;
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
			expressionD = (SaExp) this.returnValue;
        }
        outAPlusE3(node);
		
		this.returnValue = new SaExpAdd(expressionG, expressionD);
    }

    @Override
    public void caseAMinusE3(AMinusE3 node)
    {
		SaExp expressionG = null;
		SaExp expressionD = null;
		
        inAMinusE3(node);
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
			expressionG = (SaExp) this.returnValue;
        }
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
			expressionD = (SaExp) this.returnValue;
        }
        outAMinusE3(node);
		
		this.returnValue = new SaExpSub(expressionG, expressionD);
    }

    @Override
    public void caseAMultE3(AMultE3 node)
    {
		SaExp expression = null;
		
        inAMultE3(node);
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
			expression = (SaExp) this.returnValue;
        }
        outAMultE3(node);
		
		this.returnValue = expression;
    }

    @Override
    public void caseAMultE4(AMultE4 node)
    {
		SaExp expressionG = null;
		SaExp expressionD = null;
		
        inAMultE4(node);
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
			expressionG = (SaExp) this.returnValue;
        }
        if(node.getMult() != null)
        {
            node.getMult().apply(this);
        }
        if(node.getE5() != null)
        {
            node.getE5().apply(this);
			expressionD = (SaExp) this.returnValue;
        }
        outAMultE4(node);
		
		this.returnValue = new SaExpMult(expressionG, expressionD);
    }

    @Override
    public void caseADivE4(ADivE4 node)
    {
		SaExp expressionG = null;
		SaExp expressionD = null;
		
        inADivE4(node);
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
			expressionG = (SaExp) this.returnValue;
        }
        if(node.getDiv() != null)
        {
            node.getDiv().apply(this);
        }
        if(node.getE5() != null)
        {
            node.getE5().apply(this);
			expressionD = (SaExp) this.returnValue;
        }
        outADivE4(node);
		
		this.returnValue = new SaExpDiv(expressionG, expressionD);
    }

    @Override
    public void caseANonE4(ANonE4 node)
    {
		SaExp expression = null;
		
        inANonE4(node);
        if(node.getE5() != null)
        {
            node.getE5().apply(this);
			expression = (SaExp) this.returnValue;
        }
        outANonE4(node);
		
		this.returnValue = expression;
    }

    @Override
    public void caseANonE5(ANonE5 node)
    {
		SaExp expression = null;
		
        inANonE5(node);
        if(node.getNon() != null)
        {
            node.getNon().apply(this);
        }
        if(node.getE5() != null)
        {
            node.getE5().apply(this);
			expression = (SaExp) this.returnValue;
        }
        outANonE5(node);
		
		this.returnValue = new SaExpNot(expression);
    }

    @Override
    public void caseAParE5(AParE5 node)
    {
		SaExp expression = null;
		
        inAParE5(node);
        if(node.getE6() != null)
        {
            node.getE6().apply(this);
			expression = (SaExp) this.returnValue;
        }
        outAParE5(node);
		
		this.returnValue = expression;
    }

    @Override
    public void caseAParE6(AParE6 node)
    {
		SaExp expression = null;
		
        inAParE6(node);
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
			expression = (SaExp) this.returnValue;
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        outAParE6(node);
		
		this.returnValue = expression;
    }

    @Override
    public void caseANbrE6(ANbrE6 node)
    {
		Integer number = null;
		
        inANbrE6(node);
        if(node.getNbr() != null)
        {
			number = Integer.parseInt(node.getNbr().getText());
            node.getNbr().apply(this);
        }
        outANbrE6(node);
		
		this.returnValue = new SaExpInt(number);
    }

    @Override
    public void caseAAppE6(AAppE6 node)
    {
		SaAppel appel = null;
		
        inAAppE6(node);
        if(node.getApp() != null)
        {
            node.getApp().apply(this);
			appel = (SaAppel) this.returnValue;
        }
        outAAppE6(node);
		
		this.returnValue = new SaExpAppel(appel);
    }

    @Override
    public void caseAVarE6(AVarE6 node)
    {
		SaVar variable = null;
		
        inAVarE6(node);
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
			variable = (SaVar) this.returnValue;
        }
        outAVarE6(node);
		
		this.returnValue = new SaExpVar(variable);
    }
	
	@Override
    public void caseALireE6(ALireE6 node)
    {
		SaExpLire lire = null;
        inALireE6(node);
        if(node.getElire() != null)
        {
            node.getElire().apply(this);
			lire = (SaExpLire)this.returnValue;
        }
        outALireE6(node);
		
		this.returnValue = lire;
    }
	
	@Override
    public void caseALireElire(ALireElire node)
    {
        inALireElire(node);
        if(node.getLire() != null)
        {
            node.getLire().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        outALireElire(node);
		
		this.returnValue = new SaExpLire();
    }

    @Override
    public void caseAIaffI(AIaffI node)
    {
		SaInst instruction = null;
		
        inAIaffI(node);
        if(node.getIaff() != null)
        {
            node.getIaff().apply(this);
			instruction = (SaInst) this.returnValue;
        }
        outAIaffI(node);
		
		this.returnValue = instruction;
    }

    @Override
    public void caseAIsiI(AIsiI node)
    {
		SaInstSi instructionSi = null;
		
        inAIsiI(node);
        if(node.getIsi() != null)
        {
            node.getIsi().apply(this);
			instructionSi = (SaInstSi) this.returnValue;
        }
        outAIsiI(node);
		
		this.returnValue = instructionSi;
    }

    @Override
    public void caseAItqI(AItqI node)
    {
		SaInstTantQue instructionTq = null;
		
        inAItqI(node);
        if(node.getItq() != null)
        {
            node.getItq().apply(this);
			instructionTq = (SaInstTantQue) this.returnValue;
        }
        outAItqI(node);
		
		this.returnValue = instructionTq;
    }

	@Override
    public void caseAIappI(AIappI node)
    {
		/*SaInst, SaExp*/
		SaAppel appel = null;
		
		inAIappI(node);
        if(node.getIapp() != null)
        {
            node.getIapp().apply(this);
			appel = (SaAppel) this.returnValue;
        }
        outAIappI(node);
		
		this.returnValue = appel;
    }

    @Override
    public void caseAIblocI(AIblocI node)
    {
		SaInstBloc instructions = null;
		
        inAIblocI(node);
        if(node.getIbloc() != null)
        {
            node.getIbloc().apply(this);
			instructions = (SaInstBloc) this.returnValue;
        }
        outAIblocI(node);
		
		this.returnValue = instructions;
    }

    @Override
    public void caseAIretI(AIretI node)
    {
		SaInstRetour instructionRet = null;
		
        inAIretI(node);
        if(node.getIret() != null)
        {
            node.getIret().apply(this);
			instructionRet = (SaInstRetour) this.returnValue;
        }
        outAIretI(node);
		
		this.returnValue = instructionRet;
    }

    @Override
    public void caseALaffIaff(ALaffIaff node)
    {
		SaVar variable = null;
		SaExp expression = null;
		
        inALaffIaff(node);
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
			variable = (SaVar) this.returnValue;
        }
        if(node.getEgal() != null)
        {
            node.getEgal().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
			expression = (SaExp) this.returnValue;
        }
        if(node.getPvir() != null)
        {
            node.getPvir().apply(this);
        }
        outALaffIaff(node);
		
		this.returnValue = new SaInstAffect(variable, expression);
    }

    @Override
    public void caseAAppIapp(AAppIapp node)
    {
		SaAppel appel = null;
		
        inAAppIapp(node);
        if(node.getApp() != null)
        {
            node.getApp().apply(this);
			appel = (SaAppel) this.returnValue;
        }
        if(node.getPvir() != null)
        {
            node.getPvir().apply(this);
        }
        outAAppIapp(node);
		
		this.returnValue = appel;
    }

    @Override
    public void caseAClosIsi(AClosIsi node)
    {
		SaExp expression = null;
		SaInst instruction1 = null;
		SaInst instruction2 = null;
		
        inAClosIsi(node);
        if(node.getSi() != null)
        {
            node.getSi().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
			expression = (SaExp) this.returnValue;
        }
        if(node.getAlors() != null)
        {
            node.getAlors().apply(this);
        }
        if(node.getBloc1() != null)
        {
            node.getBloc1().apply(this);
			instruction1 = (SaInst) this.returnValue;
        }
        if(node.getSinon() != null)
        {
            node.getSinon().apply(this);
        }
        if(node.getBloc2() != null)
        {
            node.getBloc2().apply(this);
			instruction2 = (SaInst) this.returnValue;
        }
        outAClosIsi(node);
		
		this.returnValue = new SaInstSi(expression, instruction1, instruction2);
    }

    @Override
    public void caseAOpenIsi(AOpenIsi node)
    {
		SaExp expression = null;
		SaInst instruction1 = null;
		
        inAOpenIsi(node);
        if(node.getSi() != null)
        {
            node.getSi().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
			expression = (SaExp) this.returnValue;
        }
        if(node.getAlors() != null)
        {
            node.getAlors().apply(this);
        }
        if(node.getIbloc() != null)
        {
            node.getIbloc().apply(this);
			instruction1 = (SaInst) this.returnValue;
        }
        outAOpenIsi(node);
		
		this.returnValue = new SaInstSi(expression, instruction1, null);
    }

    @Override
    public void caseALoopItq(ALoopItq node)
    {
		SaExp expression = null;
		SaInst instruction = null;
		
        inALoopItq(node);
        if(node.getTantq() != null)
        {
            node.getTantq().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
			expression = (SaExp) this.returnValue;
        }
        if(node.getFaire() != null)
        {
            node.getFaire().apply(this);
        }
        if(node.getIbloc() != null)
        {
            node.getIbloc().apply(this);
			instruction = (SaInst) this.returnValue;
        }
        outALoopItq(node);
		
		this.returnValue = new SaInstTantQue(expression, instruction);
    }

    @Override
    public void caseARetIret(ARetIret node)
    {
		SaExp expression = null;
		
        inARetIret(node);
        if(node.getRet() != null)
        {
            node.getRet().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
			expression = (SaExp) this.returnValue;
        }
        if(node.getPvir() != null)
        {
            node.getPvir().apply(this);
        }
        outARetIret(node);
		
		this.returnValue = new SaInstRetour(expression);
    }
	
    @Override
    public void caseABlocIbloc(ABlocIbloc node)
    {
		SaLInst instructions = null;
		
        inABlocIbloc(node);
        if(node.getLAcc() != null)
        {
            node.getLAcc().apply(this);
        }
        if(node.getLi() != null)
        {
            node.getLi().apply(this);
			instructions = (SaLInst) this.returnValue;
        }
        if(node.getRAcc() != null)
        {
            node.getRAcc().apply(this);
        }
        outABlocIbloc(node);
		
		this.returnValue = new SaInstBloc(instructions);
    }

    @Override
    public void caseAILi(AILi node)
    {
		SaInst instruction = null;
		SaLInst instructions = null;
		
        inAILi(node);
        if(node.getI() != null)
        {
            node.getI().apply(this);
			instruction = (SaInst)this.returnValue;
        }
        if(node.getLi() != null)
        {
            node.getLi().apply(this);
			instructions = (SaLInst)this.returnValue;
        }
        outAILi(node);
		
		this.returnValue = new SaLInst(instruction, instructions);
    }
	
	@Override
    public void caseAVideLi(AVideLi node)
    {
        inAVideLi(node);
		this.returnValue = null;
        outAVideLi(node);
    }
	
	@Override
    public void caseAEcrIecr(AEcrIecr node)
    {
		SaExp expression = null;
		
        inAEcrIecr(node);
        if(node.getEcrire() != null)
        {
            node.getEcrire().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
			expression = (SaExp)this.returnValue;
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getPvir() != null)
        {
            node.getPvir().apply(this);
        }
        outAEcrIecr(node);
		
		this.returnValue = new SaInstEcriture(expression);
    }

    @Override
    public void caseAFctApp(AFctApp node)
    {
		String id = null;
		SaLExp arguments = null;
		
        inAFctApp(node);
        if(node.getId() != null)
        {
			id = node.getId().getText();
            node.getId().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getLe() != null)
        {
            node.getLe().apply(this);
			arguments = (SaLExp)this.returnValue;
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        outAFctApp(node);
		
		this.returnValue = new SaAppel(id, arguments);
    }

}


