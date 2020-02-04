package sa ;


import sc.analysis.DepthFirstAdapter;


public class Sc2sa extendsDepthFirstAdapter {
    privateSaNode returnValue;

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
    public void caseADfDf(ADfDf node)
    {
        String id = null;
        SaLDec parametres = null;
        SaLDec variables = null;
        SaInst iBloc = null;

        inADfDf(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
            id = (String) this.returnValue;
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
        this.returnValue = new SaLDec(id, parametres, variables, iBloc);
    }

    @Override
    public void caseALLdvo2(ALLdvo2 node)
    {
        inALLdvo2(node);
        if(node.getLdvo() != null)
        {
            node.getLdvo().apply(this);
        }
        if(node.getPvir() != null)
        {
            node.getPvir().apply(this);
        }
        if(node.getLdvo2() != null)
        {
            node.getLdvo2().apply(this);
        }
        outALLdvo2(node);
    }

    @Override
    public void caseAVideLdvo(AVideLdvo node)
    {
        inAVideLdvo(node);
        outAVideLdvo(node);
    }

    @Override
    public void caseALLdvo(ALLdvo node)
    {
        inALLdvo(node);
        if(node.getLdv() != null)
        {
            node.getLdv().apply(this);
        }
        outALLdvo(node);
    }

    @Override
    public void caseALLdv(ALLdv node)
    {
        inALLdv(node);
        if(node.getDv() != null)
        {
            node.getDv().apply(this);
        }
        if(node.getVir() != null)
        {
            node.getVir().apply(this);
        }
        if(node.getLdv() != null)
        {
            node.getLdv().apply(this);
        }
        outALLdv(node);
    }

    @Override
    public void caseADvLdv(ADvLdv node)
    {
        inADvLdv(node);
        if(node.getDv() != null)
        {
            node.getDv().apply(this);
        }
        outADvLdv(node);
    }

    @Override
    public void caseAVarDv(AVarDv node)
    {
        inAVarDv(node);
        if(node.getEntier() != null)
        {
            node.getEntier().apply(this);
        }
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        outAVarDv(node);
    }

    @Override
    public void caseAIdVar(AIdVar node)
    {
        inAIdVar(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAIdVar(node);
    }

    @Override
    public void caseATabVar(ATabVar node)
    {
        inATabVar(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getLCroc() != null)
        {
            node.getLCroc().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getRCroc() != null)
        {
            node.getRCroc().apply(this);
        }
        outATabVar(node);
    }

    @Override
    public void caseALexpLe(ALexpLe node)
    {
        inALexpLe(node);
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getVir() != null)
        {
            node.getVir().apply(this);
        }
        if(node.getLe() != null)
        {
            node.getLe().apply(this);
        }
        outALexpLe(node);
    }

    @Override
    public void caseAExpLe(AExpLe node)
    {
        inAExpLe(node);
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        outAExpLe(node);
    }

    @Override
    public void caseAVideLe(AVideLe node)
    {
        inAVideLe(node);
        outAVideLe(node);
    }

    @Override
    public void caseAOuE(AOuE node)
    {
        inAOuE(node);
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getOu() != null)
        {
            node.getOu().apply(this);
        }
        if(node.getE1() != null)
        {
            node.getE1().apply(this);
        }
        outAOuE(node);
    }

    @Override
    public void caseAEtE(AEtE node)
    {
        inAEtE(node);
        if(node.getE1() != null)
        {
            node.getE1().apply(this);
        }
        outAEtE(node);
    }

    @Override
    public void caseAEtE1(AEtE1 node)
    {
        inAEtE1(node);
        if(node.getE1() != null)
        {
            node.getE1().apply(this);
        }
        if(node.getEt() != null)
        {
            node.getEt().apply(this);
        }
        if(node.getE2() != null)
        {
            node.getE2().apply(this);
        }
        outAEtE1(node);
    }

    @Override
    public void caseAEgalE1(AEgalE1 node)
    {
        inAEgalE1(node);
        if(node.getE2() != null)
        {
            node.getE2().apply(this);
        }
        outAEgalE1(node);
    }

    @Override
    public void caseAEgalE2(AEgalE2 node)
    {
        inAEgalE2(node);
        if(node.getE2() != null)
        {
            node.getE2().apply(this);
        }
        if(node.getEgal() != null)
        {
            node.getEgal().apply(this);
        }
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
        }
        outAEgalE2(node);
    }

    @Override
    public void caseAInfE2(AInfE2 node)
    {
        inAInfE2(node);
        if(node.getE2() != null)
        {
            node.getE2().apply(this);
        }
        if(node.getInf() != null)
        {
            node.getInf().apply(this);
        }
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
        }
        outAInfE2(node);
    }

    @Override
    public void caseAPlusE2(APlusE2 node)
    {
        inAPlusE2(node);
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
        }
        outAPlusE2(node);
    }

    @Override
    public void caseAPlusE3(APlusE3 node)
    {
        inAPlusE3(node);
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
        }
        outAPlusE3(node);
    }

    @Override
    public void caseAMinusE3(AMinusE3 node)
    {
        inAMinusE3(node);
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
        }
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
        }
        outAMinusE3(node);
    }

    @Override
    public void caseAMultE3(AMultE3 node)
    {
        inAMultE3(node);
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
        }
        outAMultE3(node);
    }

    @Override
    public void caseAMultE4(AMultE4 node)
    {
        inAMultE4(node);
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
        }
        if(node.getMult() != null)
        {
            node.getMult().apply(this);
        }
        if(node.getE5() != null)
        {
            node.getE5().apply(this);
        }
        outAMultE4(node);
    }

    @Override
    public void caseADivE4(ADivE4 node)
    {
        inADivE4(node);
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
        }
        if(node.getDiv() != null)
        {
            node.getDiv().apply(this);
        }
        if(node.getE5() != null)
        {
            node.getE5().apply(this);
        }
        outADivE4(node);
    }

    @Override
    public void caseANonE4(ANonE4 node)
    {
        inANonE4(node);
        if(node.getE5() != null)
        {
            node.getE5().apply(this);
        }
        outANonE4(node);
    }

    @Override
    public void caseANonE5(ANonE5 node)
    {
        inANonE5(node);
        if(node.getNon() != null)
        {
            node.getNon().apply(this);
        }
        if(node.getE5() != null)
        {
            node.getE5().apply(this);
        }
        outANonE5(node);
    }

    @Override
    public void caseAParE5(AParE5 node)
    {
        inAParE5(node);
        if(node.getE6() != null)
        {
            node.getE6().apply(this);
        }
        outAParE5(node);
    }

    @Override
    public void caseAParE6(AParE6 node)
    {
        inAParE6(node);
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        outAParE6(node);
    }

    @Override
    public void caseANbrE6(ANbrE6 node)
    {
        inANbrE6(node);
        if(node.getNbr() != null)
        {
            node.getNbr().apply(this);
        }
        outANbrE6(node);
    }

    @Override
    public void caseAAppE6(AAppE6 node)
    {
        inAAppE6(node);
        if(node.getApp() != null)
        {
            node.getApp().apply(this);
        }
        outAAppE6(node);
    }

    @Override
    public void caseAVarE6(AVarE6 node)
    {
        inAVarE6(node);
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        outAVarE6(node);
    }

    @Override
    public void caseAIaffI(AIaffI node)
    {
        inAIaffI(node);
        if(node.getIaff() != null)
        {
            node.getIaff().apply(this);
        }
        outAIaffI(node);
    }

    @Override
    public void caseAIsiI(AIsiI node)
    {
        inAIsiI(node);
        if(node.getIsi() != null)
        {
            node.getIsi().apply(this);
        }
        outAIsiI(node);
    }

    @Override
    public void caseAItqI(AItqI node)
    {
        inAItqI(node);
        if(node.getItq() != null)
        {
            node.getItq().apply(this);
        }
        outAItqI(node);
    }

    @Override
    public void caseAIappI(AIappI node)
    {
        inAIappI(node);
        if(node.getIapp() != null)
        {
            node.getIapp().apply(this);
        }
        outAIappI(node);
    }

    @Override
    public void caseAIblocI(AIblocI node)
    {
        inAIblocI(node);
        if(node.getIbloc() != null)
        {
            node.getIbloc().apply(this);
        }
        outAIblocI(node);
    }

    @Override
    public void caseAIretI(AIretI node)
    {
        inAIretI(node);
        if(node.getIret() != null)
        {
            node.getIret().apply(this);
        }
        outAIretI(node);
    }

    @Override
    public void caseALaffIaff(ALaffIaff node)
    {
        inALaffIaff(node);
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        if(node.getEgal() != null)
        {
            node.getEgal().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getPvir() != null)
        {
            node.getPvir().apply(this);
        }
        outALaffIaff(node);
    }

    @Override
    public void caseAAppIapp(AAppIapp node)
    {
        inAAppIapp(node);
        if(node.getApp() != null)
        {
            node.getApp().apply(this);
        }
        if(node.getPvir() != null)
        {
            node.getPvir().apply(this);
        }
        outAAppIapp(node);
    }

    @Override
    public void caseAClosIsi(AClosIsi node)
    {
        inAClosIsi(node);
        if(node.getSi() != null)
        {
            node.getSi().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getAlors() != null)
        {
            node.getAlors().apply(this);
        }
        if(node.getBloc1() != null)
        {
            node.getBloc1().apply(this);
        }
        if(node.getSinon() != null)
        {
            node.getSinon().apply(this);
        }
        if(node.getBloc2() != null)
        {
            node.getBloc2().apply(this);
        }
        outAClosIsi(node);
    }

    @Override
    public void caseAOpenIsi(AOpenIsi node)
    {
        inAOpenIsi(node);
        if(node.getSi() != null)
        {
            node.getSi().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getAlors() != null)
        {
            node.getAlors().apply(this);
        }
        if(node.getIbloc() != null)
        {
            node.getIbloc().apply(this);
        }
        outAOpenIsi(node);
    }

    @Override
    public void caseALoopItq(ALoopItq node)
    {
        inALoopItq(node);
        if(node.getTantq() != null)
        {
            node.getTantq().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getFaire() != null)
        {
            node.getFaire().apply(this);
        }
        if(node.getIbloc() != null)
        {
            node.getIbloc().apply(this);
        }
        outALoopItq(node);
    }

    @Override
    public void caseARetIret(ARetIret node)
    {
        inARetIret(node);
        if(node.getRet() != null)
        {
            node.getRet().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getPvir() != null)
        {
            node.getPvir().apply(this);
        }
        outARetIret(node);
    }
    @Override
    public void caseABlocIbloc(ABlocIbloc node)
    {
        inABlocIbloc(node);
        if(node.getLAcc() != null)
        {
            node.getLAcc().apply(this);
        }
        if(node.getLi() != null)
        {
            node.getLi().apply(this);
        }
        if(node.getRAcc() != null)
        {
            node.getRAcc().apply(this);
        }
        outABlocIbloc(node);
    }

    @Override
    public void caseAILi(AILi node)
    {
        inAILi(node);
        if(node.getI() != null)
        {
            node.getI().apply(this);
        }
        if(node.getLi() != null)
        {
            node.getLi().apply(this);
        }
        outAILi(node);
    }

    @Override
    public void caseAVideLi(AVideLi node)
    {
        inAVideLi(node);
        outAVideLi(node);
    }

    @Override
    public void caseAFctApp(AFctApp node)
    {
        inAFctApp(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getLe() != null)
        {
            node.getLe().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        outAFctApp(node);
    }

}


