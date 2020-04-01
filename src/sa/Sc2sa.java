package sa;


import sc.analysis.DepthFirstAdapter;
import sc.node.*;


public class Sc2sa extends DepthFirstAdapter {
    private SaNode returnValue;


    public SaNode getRoot() {
        return this.returnValue;
    }

    @Override
    public void caseAPProg(APProg node) {
        SaLDec variables = null;
        SaLDec fonctions = null;

        node.getLdvo2().apply(this);
        variables = (SaLDec) this.returnValue;
        node.getLdf().apply(this);
        fonctions = (SaLDec) this.returnValue;

        this.returnValue = new SaProg(variables, fonctions);
    }

    @Override
    public void caseALLdf(ALLdf node) {
        SaLDec listeFonctions = null;
        SaDec fonction = null;

        node.getDf().apply(this);
        fonction = (SaDecFonc) this.returnValue;
        node.getLdf().apply(this);
        listeFonctions = (SaLDec) this.returnValue;

        this.returnValue = new SaLDec(fonction, listeFonctions);
    }

    @Override
    public void caseAVideLdf(AVideLdf node) {
        this.returnValue = null;
    }


    @Override
    public void caseADfDf(ADfDf node) {
        String id = null;
        SaLDec parametres = null;
        SaLDec variables = null;
        SaInst iBloc = null;

        id = node.getId().getText();
        node.getLdvo().apply(this);
        parametres = (SaLDec) this.returnValue;
        node.getLdvo2().apply(this);
        variables = (SaLDec) this.returnValue;
        node.getIbloc().apply(this);
        iBloc = (SaInst) this.returnValue;
        this.returnValue = new SaDecFonc(id, parametres, variables, iBloc);
    }

    @Override
    public void caseAVideLdvo2(AVideLdvo2 node) {
        this.returnValue = null;
    }

    @Override
    public void caseALLdvo2(ALLdvo2 node) {
        SaLDec declarations = null;
        SaLDec listeDeclarations = null;

        node.getLdvo().apply(this);
        declarations = (SaLDec) this.returnValue;
        node.getLdvo2().apply(this);
        listeDeclarations = (SaLDec) this.returnValue;

        while(listeDeclarations != null){
            SaDec declaration = listeDeclarations.getTete();
            declarations = new SaLDec(declaration, declarations);
            listeDeclarations = listeDeclarations.getQueue();
        }

        this.returnValue = declarations;
    }

    @Override
    public void caseAVideLdvo(AVideLdvo node) {
        this.returnValue = null;
    }

    @Override
    public void caseALLdvo(ALLdvo node) {
        SaLDec listeDeclarations = null;

        node.getLdv().apply(this);
        listeDeclarations = (SaLDec) this.returnValue;

        this.returnValue = listeDeclarations;
    }

    @Override
    public void caseALLdv(ALLdv node) {
        SaDec declaration = null;
        SaLDec listeDeclarations = null;

        node.getDv().apply(this);
        declaration = (SaDec) this.returnValue;

        node.getLdv().apply(this);
        listeDeclarations = (SaLDec) this.returnValue;

        this.returnValue = new SaLDec(declaration, listeDeclarations);
    }

    @Override
    public void caseADvLdv(ADvLdv node) {
        SaDec declaration = null;
        node.getDv().apply(this);
        declaration = (SaDec) this.returnValue;

        this.returnValue = new SaLDec(declaration, null);
    }

    @Override
    public void caseAVarDv(AVarDv node) {
        SaDec declaration = null;

        node.getDvar().apply(this);
        declaration = (SaDec) this.returnValue;

        this.returnValue = declaration;
    }

    @Override
    public void caseAIdDvar(AIdDvar node) {
        String id = null;

        id = node.getId().getText();

        this.returnValue = new SaDecVar(id);
    }

    @Override
    public void caseATabDvar(ATabDvar node) {
        String id = null;
        Integer taille = null;

        id = node.getId().getText();
        taille = Integer.parseInt(node.getNbr().getText());
        this.returnValue = new SaDecTab(id, taille);
    }

    @Override
    public void caseAIdVar(AIdVar node) {
        String id = null;
        id = node.getId().getText();

        this.returnValue = new SaVarSimple(id);
    }

    @Override
    public void caseATabVar(ATabVar node) {
        String id = null;
        SaExp indice = null;

        id = node.getId().getText();

        node.getE().apply(this);
        indice = (SaExp) this.returnValue;

        this.returnValue = new SaVarIndicee(id, indice);
    }

    @Override
    public void caseALexpLe(ALexpLe node) {
        SaExp expression = null;
        SaLExp listeExpressions = null;

        node.getE().apply(this);
        expression = (SaExp) this.returnValue;

        node.getLe().apply(this);
        listeExpressions = (SaLExp) this.returnValue;
        this.returnValue = new SaLExp(expression, listeExpressions);
    }

    @Override
    public void caseAExpLe(AExpLe node) {
        SaExp expression = null;

        node.getE().apply(this);
        expression = (SaExp) this.returnValue;

        this.returnValue = new SaLExp(expression, null);
    }

    @Override
    public void caseAVideLe(AVideLe node) {

        this.returnValue = null;
    }

    @Override
    public void caseAOuE(AOuE node) {
        SaExp expressionG = null;
        SaExp expressionD = null;

        node.getE().apply(this);
        expressionG = (SaExp) this.returnValue;
        node.getE1().apply(this);
        expressionD = (SaExp) this.returnValue;
        this.returnValue = new SaExpOr(expressionD, expressionG);
    }

    @Override
    public void caseAEtE(AEtE node) {
        SaExp expression = null;

        node.getE1().apply(this);
        expression = (SaExp) this.returnValue;

        this.returnValue = expression;
    }

    @Override
    public void caseAEtE1(AEtE1 node) {
        SaExp expressionG = null;
        SaExp expressionD = null;

        node.getE1().apply(this);
        expressionG = (SaExp) this.returnValue;

        node.getE2().apply(this);
        expressionD = (SaExp) this.returnValue;

        this.returnValue = new SaExpAnd(expressionG, expressionD);
    }

    @Override
    public void caseAEgalE1(AEgalE1 node) {
        SaExp expression = null;

        node.getE2().apply(this);
        expression = (SaExp) this.returnValue;

        this.returnValue = expression;
    }

    @Override
    public void caseAEgalE2(AEgalE2 node) {
        SaExp expressionG = null;
        SaExp expressionD = null;

        node.getE2().apply(this);
        expressionG = (SaExp) this.returnValue;
        node.getE3().apply(this);
        expressionD = (SaExp) this.returnValue;

        this.returnValue = new SaExpEqual(expressionG, expressionD);
    }

    @Override
    public void caseAInfE2(AInfE2 node) {
        SaExp expressionG = null;
        SaExp expressionD = null;

        node.getE2().apply(this);
        expressionG = (SaExp) this.returnValue;
        node.getE3().apply(this);
        expressionD = (SaExp) this.returnValue;

        this.returnValue = new SaExpInf(expressionG, expressionD);
    }

    @Override
    public void caseAPlusE2(APlusE2 node) {
        SaExp expression = null;

        node.getE3().apply(this);
        expression = (SaExp) this.returnValue;

        this.returnValue = expression;
    }

    @Override
    public void caseAPlusE3(APlusE3 node) {
        SaExp expressionG = null;
        SaExp expressionD = null;

        node.getE3().apply(this);
        expressionG = (SaExp) this.returnValue;
        node.getE4().apply(this);
        expressionD = (SaExp) this.returnValue;

        this.returnValue = new SaExpAdd(expressionG, expressionD);
    }

    @Override
    public void caseAMinusE3(AMinusE3 node) {
        SaExp expressionG = null;
        SaExp expressionD = null;

        node.getE3().apply(this);
        expressionG = (SaExp) this.returnValue;
        node.getE4().apply(this);
        expressionD = (SaExp) this.returnValue;

        this.returnValue = new SaExpSub(expressionG, expressionD);
    }

    @Override
    public void caseAMultE3(AMultE3 node) {
        SaExp expression = null;

        node.getE4().apply(this);
        expression = (SaExp) this.returnValue;

        this.returnValue = expression;
    }

    @Override
    public void caseAMultE4(AMultE4 node) {
        SaExp expressionG = null;
        SaExp expressionD = null;

        node.getE4().apply(this);
        expressionG = (SaExp) this.returnValue;
        node.getE5().apply(this);
        expressionD = (SaExp) this.returnValue;

        this.returnValue = new SaExpMult(expressionG, expressionD);
    }

    @Override
    public void caseADivE4(ADivE4 node) {
        SaExp expressionG = null;
        SaExp expressionD = null;

        node.getE4().apply(this);
        expressionG = (SaExp) this.returnValue;
        node.getE5().apply(this);
        expressionD = (SaExp) this.returnValue;

        this.returnValue = new SaExpDiv(expressionG, expressionD);
    }

    @Override
    public void caseANonE4(ANonE4 node) {
        SaExp expression = null;

        node.getE5().apply(this);
        expression = (SaExp) this.returnValue;

        this.returnValue = expression;
    }

    @Override
    public void caseANonE5(ANonE5 node) {
        SaExp expression = null;

        node.getE5().apply(this);
        expression = (SaExp) this.returnValue;

        this.returnValue = new SaExpNot(expression);
    }

    @Override
    public void caseAParE5(AParE5 node) {
        SaExp expression = null;

        node.getE6().apply(this);
        expression = (SaExp) this.returnValue;

        this.returnValue = expression;
    }

    @Override
    public void caseAParE6(AParE6 node) {
        SaExp expression = null;

        node.getE().apply(this);
        expression = (SaExp) this.returnValue;

        this.returnValue = expression;
    }

    @Override
    public void caseANbrE6(ANbrE6 node) {
        Integer number = null;

        number = Integer.parseInt(node.getNbr().getText());

        this.returnValue = new SaExpInt(number);
    }

    @Override
    public void caseAAppE6(AAppE6 node) {
        SaAppel appel = null;

        node.getApp().apply(this);
        appel = (SaAppel) this.returnValue;

        this.returnValue = new SaExpAppel(appel);
    }

    @Override
    public void caseAVarE6(AVarE6 node) {
        SaVar variable = null;

        node.getVar().apply(this);
        variable = (SaVar) this.returnValue;

        this.returnValue = new SaExpVar(variable);
    }

    @Override
    public void caseALireE6(ALireE6 node) {
        SaExpLire lire = null;

        node.getElire().apply(this);
        lire = (SaExpLire) this.returnValue;

        this.returnValue = lire;
    }

    @Override
    public void caseALireElire(ALireElire node) {
        this.returnValue = new SaExpLire();
    }

    @Override
    public void caseAIaffI(AIaffI node) {
        SaInst instruction = null;

        node.getIaff().apply(this);
        instruction = (SaInst) this.returnValue;

        this.returnValue = instruction;
    }

    @Override
    public void caseAIsiI(AIsiI node) {
        SaInstSi instructionSi = null;

        node.getIsi().apply(this);
        instructionSi = (SaInstSi) this.returnValue;

        this.returnValue = instructionSi;
    }

    @Override
    public void caseAItqI(AItqI node) {
        SaInstTantQue instructionTq = null;

        node.getItq().apply(this);
        instructionTq = (SaInstTantQue) this.returnValue;

        this.returnValue = instructionTq;
    }

    @Override
    public void caseAIappI(AIappI node) {
        /*SaInst, SaExp*/
        SaAppel appel = null;

        node.getIapp().apply(this);
        appel = (SaAppel) this.returnValue;

        this.returnValue = appel;
    }

    @Override
    public void caseAIblocI(AIblocI node) {
        SaInstBloc instructions = null;

        node.getIbloc().apply(this);
        instructions = (SaInstBloc) this.returnValue;

        this.returnValue = instructions;
    }

    @Override
    public void caseAIretI(AIretI node) {
        SaInstRetour instructionRet = null;

        node.getIret().apply(this);
        instructionRet = (SaInstRetour) this.returnValue;

        this.returnValue = instructionRet;
    }

    @Override
    public void caseALaffIaff(ALaffIaff node) {
        SaVar variable = null;
        SaExp expression = null;

        node.getVar().apply(this);
        variable = (SaVar) this.returnValue;
        node.getE().apply(this);
        expression = (SaExp) this.returnValue;

        this.returnValue = new SaInstAffect(variable, expression);
    }

    @Override
    public void caseAAppIapp(AAppIapp node) {
        SaAppel appel = null;

        node.getApp().apply(this);
        appel = (SaAppel) this.returnValue;

        this.returnValue = appel;
    }

    @Override
    public void caseAClosIsi(AClosIsi node) {
        SaExp expression = null;
        SaInst instruction1 = null;
        SaInst instruction2 = null;

        node.getE().apply(this);
        expression = (SaExp) this.returnValue;
        node.getBloc1().apply(this);
        instruction1 = (SaInst) this.returnValue;
        node.getBloc2().apply(this);
        instruction2 = (SaInst) this.returnValue;

        this.returnValue = new SaInstSi(expression, instruction1, instruction2);
    }

    @Override
    public void caseAOpenIsi(AOpenIsi node) {
        SaExp expression = null;
        SaInst instruction1 = null;

        node.getE().apply(this);
        expression = (SaExp) this.returnValue;
        node.getIbloc().apply(this);
        instruction1 = (SaInst) this.returnValue;

        this.returnValue = new SaInstSi(expression, instruction1, null);
    }

    @Override
    public void caseALoopItq(ALoopItq node) {
        SaExp expression = null;
        SaInst instruction = null;

        node.getE().apply(this);
        expression = (SaExp) this.returnValue;
        node.getIbloc().apply(this);
        instruction = (SaInst) this.returnValue;

        this.returnValue = new SaInstTantQue(expression, instruction);
    }

    @Override
    public void caseARetIret(ARetIret node) {
        SaExp expression = null;

        node.getE().apply(this);
        expression = (SaExp) this.returnValue;

        this.returnValue = new SaInstRetour(expression);
    }

    @Override
    public void caseABlocIbloc(ABlocIbloc node) {
        SaLInst instructions = null;

        node.getLi().apply(this);
        instructions = (SaLInst) this.returnValue;

        this.returnValue = new SaInstBloc(instructions);
    }

    @Override
    public void caseAILi(AILi node) {
        SaInst instruction = null;
        SaLInst instructions = null;

        node.getI().apply(this);
        instruction = (SaInst) this.returnValue;
        node.getLi().apply(this);
        instructions = (SaLInst) this.returnValue;

        this.returnValue = new SaLInst(instruction, instructions);
    }

    @Override
    public void caseAVideLi(AVideLi node) {
        this.returnValue = null;
    }

    @Override
    public void caseAEcrIecr(AEcrIecr node) {
        SaExp expression = null;

        node.getE().apply(this);
        expression = (SaExp) this.returnValue;

        this.returnValue = new SaInstEcriture(expression);
    }

    @Override
    public void caseAFctApp(AFctApp node) {
        String id = null;
        SaLExp arguments = null;

        id = node.getId().getText();
        node.getLe().apply(this);
        arguments = (SaLExp) this.returnValue;

        this.returnValue = new SaAppel(id, arguments);
    }

}


