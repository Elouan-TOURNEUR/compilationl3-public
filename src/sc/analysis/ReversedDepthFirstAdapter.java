/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.analysis;

import java.util.*;
import sc.node.*;

public class ReversedDepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getEOF().apply(this);
        node.getPProg().apply(this);
        outStart(node);
    }

    public void inAPProg(APProg node)
    {
        defaultIn(node);
    }

    public void outAPProg(APProg node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPProg(APProg node)
    {
        inAPProg(node);
        if(node.getLdf() != null)
        {
            node.getLdf().apply(this);
        }
        if(node.getLdvo2() != null)
        {
            node.getLdvo2().apply(this);
        }
        outAPProg(node);
    }

    public void inALLdf(ALLdf node)
    {
        defaultIn(node);
    }

    public void outALLdf(ALLdf node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALLdf(ALLdf node)
    {
        inALLdf(node);
        if(node.getLdf() != null)
        {
            node.getLdf().apply(this);
        }
        if(node.getDf() != null)
        {
            node.getDf().apply(this);
        }
        outALLdf(node);
    }

    public void inAVideLdf(AVideLdf node)
    {
        defaultIn(node);
    }

    public void outAVideLdf(AVideLdf node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideLdf(AVideLdf node)
    {
        inAVideLdf(node);
        outAVideLdf(node);
    }

    public void inADfDf(ADfDf node)
    {
        defaultIn(node);
    }

    public void outADfDf(ADfDf node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADfDf(ADfDf node)
    {
        inADfDf(node);
        if(node.getIbloc() != null)
        {
            node.getIbloc().apply(this);
        }
        if(node.getLdvo2() != null)
        {
            node.getLdvo2().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getLdvo() != null)
        {
            node.getLdvo().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outADfDf(node);
    }

    public void inAVideLdvo2(AVideLdvo2 node)
    {
        defaultIn(node);
    }

    public void outAVideLdvo2(AVideLdvo2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideLdvo2(AVideLdvo2 node)
    {
        inAVideLdvo2(node);
        outAVideLdvo2(node);
    }

    public void inALLdvo2(ALLdvo2 node)
    {
        defaultIn(node);
    }

    public void outALLdvo2(ALLdvo2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALLdvo2(ALLdvo2 node)
    {
        inALLdvo2(node);
        if(node.getLdvo2() != null)
        {
            node.getLdvo2().apply(this);
        }
        if(node.getPvir() != null)
        {
            node.getPvir().apply(this);
        }
        if(node.getLdvo() != null)
        {
            node.getLdvo().apply(this);
        }
        outALLdvo2(node);
    }

    public void inAVideLdvo(AVideLdvo node)
    {
        defaultIn(node);
    }

    public void outAVideLdvo(AVideLdvo node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideLdvo(AVideLdvo node)
    {
        inAVideLdvo(node);
        outAVideLdvo(node);
    }

    public void inALLdvo(ALLdvo node)
    {
        defaultIn(node);
    }

    public void outALLdvo(ALLdvo node)
    {
        defaultOut(node);
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

    public void inALLdv(ALLdv node)
    {
        defaultIn(node);
    }

    public void outALLdv(ALLdv node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALLdv(ALLdv node)
    {
        inALLdv(node);
        if(node.getLdv() != null)
        {
            node.getLdv().apply(this);
        }
        if(node.getVir() != null)
        {
            node.getVir().apply(this);
        }
        if(node.getDv() != null)
        {
            node.getDv().apply(this);
        }
        outALLdv(node);
    }

    public void inADvLdv(ADvLdv node)
    {
        defaultIn(node);
    }

    public void outADvLdv(ADvLdv node)
    {
        defaultOut(node);
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

    public void inAVarDv(AVarDv node)
    {
        defaultIn(node);
    }

    public void outAVarDv(AVarDv node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarDv(AVarDv node)
    {
        inAVarDv(node);
        if(node.getDvar() != null)
        {
            node.getDvar().apply(this);
        }
        if(node.getEntier() != null)
        {
            node.getEntier().apply(this);
        }
        outAVarDv(node);
    }

    public void inAIdDvar(AIdDvar node)
    {
        defaultIn(node);
    }

    public void outAIdDvar(AIdDvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIdDvar(AIdDvar node)
    {
        inAIdDvar(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAIdDvar(node);
    }

    public void inATabDvar(ATabDvar node)
    {
        defaultIn(node);
    }

    public void outATabDvar(ATabDvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATabDvar(ATabDvar node)
    {
        inATabDvar(node);
        if(node.getRCroc() != null)
        {
            node.getRCroc().apply(this);
        }
        if(node.getNbr() != null)
        {
            node.getNbr().apply(this);
        }
        if(node.getLCroc() != null)
        {
            node.getLCroc().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outATabDvar(node);
    }

    public void inALexpLe(ALexpLe node)
    {
        defaultIn(node);
    }

    public void outALexpLe(ALexpLe node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALexpLe(ALexpLe node)
    {
        inALexpLe(node);
        if(node.getLe() != null)
        {
            node.getLe().apply(this);
        }
        if(node.getVir() != null)
        {
            node.getVir().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        outALexpLe(node);
    }

    public void inAExpLe(AExpLe node)
    {
        defaultIn(node);
    }

    public void outAExpLe(AExpLe node)
    {
        defaultOut(node);
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

    public void inAVideLe(AVideLe node)
    {
        defaultIn(node);
    }

    public void outAVideLe(AVideLe node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideLe(AVideLe node)
    {
        inAVideLe(node);
        outAVideLe(node);
    }

    public void inAOuE(AOuE node)
    {
        defaultIn(node);
    }

    public void outAOuE(AOuE node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOuE(AOuE node)
    {
        inAOuE(node);
        if(node.getE1() != null)
        {
            node.getE1().apply(this);
        }
        if(node.getOu() != null)
        {
            node.getOu().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        outAOuE(node);
    }

    public void inAEtE(AEtE node)
    {
        defaultIn(node);
    }

    public void outAEtE(AEtE node)
    {
        defaultOut(node);
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

    public void inAEtE1(AEtE1 node)
    {
        defaultIn(node);
    }

    public void outAEtE1(AEtE1 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEtE1(AEtE1 node)
    {
        inAEtE1(node);
        if(node.getE2() != null)
        {
            node.getE2().apply(this);
        }
        if(node.getEt() != null)
        {
            node.getEt().apply(this);
        }
        if(node.getE1() != null)
        {
            node.getE1().apply(this);
        }
        outAEtE1(node);
    }

    public void inAEgalE1(AEgalE1 node)
    {
        defaultIn(node);
    }

    public void outAEgalE1(AEgalE1 node)
    {
        defaultOut(node);
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

    public void inAEgalE2(AEgalE2 node)
    {
        defaultIn(node);
    }

    public void outAEgalE2(AEgalE2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEgalE2(AEgalE2 node)
    {
        inAEgalE2(node);
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
        }
        if(node.getEgal() != null)
        {
            node.getEgal().apply(this);
        }
        if(node.getE2() != null)
        {
            node.getE2().apply(this);
        }
        outAEgalE2(node);
    }

    public void inAInfE2(AInfE2 node)
    {
        defaultIn(node);
    }

    public void outAInfE2(AInfE2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInfE2(AInfE2 node)
    {
        inAInfE2(node);
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
        }
        if(node.getInf() != null)
        {
            node.getInf().apply(this);
        }
        if(node.getE2() != null)
        {
            node.getE2().apply(this);
        }
        outAInfE2(node);
    }

    public void inAPlusE2(APlusE2 node)
    {
        defaultIn(node);
    }

    public void outAPlusE2(APlusE2 node)
    {
        defaultOut(node);
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

    public void inAPlusE3(APlusE3 node)
    {
        defaultIn(node);
    }

    public void outAPlusE3(APlusE3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPlusE3(APlusE3 node)
    {
        inAPlusE3(node);
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
        }
        outAPlusE3(node);
    }

    public void inAMinusE3(AMinusE3 node)
    {
        defaultIn(node);
    }

    public void outAMinusE3(AMinusE3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMinusE3(AMinusE3 node)
    {
        inAMinusE3(node);
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
        }
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
        }
        outAMinusE3(node);
    }

    public void inAMultE3(AMultE3 node)
    {
        defaultIn(node);
    }

    public void outAMultE3(AMultE3 node)
    {
        defaultOut(node);
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

    public void inAMultE4(AMultE4 node)
    {
        defaultIn(node);
    }

    public void outAMultE4(AMultE4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultE4(AMultE4 node)
    {
        inAMultE4(node);
        if(node.getE5() != null)
        {
            node.getE5().apply(this);
        }
        if(node.getMult() != null)
        {
            node.getMult().apply(this);
        }
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
        }
        outAMultE4(node);
    }

    public void inADivE4(ADivE4 node)
    {
        defaultIn(node);
    }

    public void outADivE4(ADivE4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADivE4(ADivE4 node)
    {
        inADivE4(node);
        if(node.getE5() != null)
        {
            node.getE5().apply(this);
        }
        if(node.getDiv() != null)
        {
            node.getDiv().apply(this);
        }
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
        }
        outADivE4(node);
    }

    public void inANonE4(ANonE4 node)
    {
        defaultIn(node);
    }

    public void outANonE4(ANonE4 node)
    {
        defaultOut(node);
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

    public void inANonE5(ANonE5 node)
    {
        defaultIn(node);
    }

    public void outANonE5(ANonE5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANonE5(ANonE5 node)
    {
        inANonE5(node);
        if(node.getE5() != null)
        {
            node.getE5().apply(this);
        }
        if(node.getNon() != null)
        {
            node.getNon().apply(this);
        }
        outANonE5(node);
    }

    public void inAParE5(AParE5 node)
    {
        defaultIn(node);
    }

    public void outAParE5(AParE5 node)
    {
        defaultOut(node);
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

    public void inAParE6(AParE6 node)
    {
        defaultIn(node);
    }

    public void outAParE6(AParE6 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAParE6(AParE6 node)
    {
        inAParE6(node);
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        outAParE6(node);
    }

    public void inANbrE6(ANbrE6 node)
    {
        defaultIn(node);
    }

    public void outANbrE6(ANbrE6 node)
    {
        defaultOut(node);
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

    public void inAAppE6(AAppE6 node)
    {
        defaultIn(node);
    }

    public void outAAppE6(AAppE6 node)
    {
        defaultOut(node);
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

    public void inALireE6(ALireE6 node)
    {
        defaultIn(node);
    }

    public void outALireE6(ALireE6 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALireE6(ALireE6 node)
    {
        inALireE6(node);
        if(node.getElire() != null)
        {
            node.getElire().apply(this);
        }
        outALireE6(node);
    }

    public void inAVarE6(AVarE6 node)
    {
        defaultIn(node);
    }

    public void outAVarE6(AVarE6 node)
    {
        defaultOut(node);
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

    public void inALireElire(ALireElire node)
    {
        defaultIn(node);
    }

    public void outALireElire(ALireElire node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALireElire(ALireElire node)
    {
        inALireElire(node);
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getLire() != null)
        {
            node.getLire().apply(this);
        }
        outALireElire(node);
    }

    public void inAIaffI(AIaffI node)
    {
        defaultIn(node);
    }

    public void outAIaffI(AIaffI node)
    {
        defaultOut(node);
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

    public void inAIsiI(AIsiI node)
    {
        defaultIn(node);
    }

    public void outAIsiI(AIsiI node)
    {
        defaultOut(node);
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

    public void inAItqI(AItqI node)
    {
        defaultIn(node);
    }

    public void outAItqI(AItqI node)
    {
        defaultOut(node);
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

    public void inAIblocI(AIblocI node)
    {
        defaultIn(node);
    }

    public void outAIblocI(AIblocI node)
    {
        defaultOut(node);
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

    public void inAIretI(AIretI node)
    {
        defaultIn(node);
    }

    public void outAIretI(AIretI node)
    {
        defaultOut(node);
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

    public void inAIecrI(AIecrI node)
    {
        defaultIn(node);
    }

    public void outAIecrI(AIecrI node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIecrI(AIecrI node)
    {
        inAIecrI(node);
        if(node.getIecr() != null)
        {
            node.getIecr().apply(this);
        }
        outAIecrI(node);
    }

    public void inAIappI(AIappI node)
    {
        defaultIn(node);
    }

    public void outAIappI(AIappI node)
    {
        defaultOut(node);
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

    public void inALaffIaff(ALaffIaff node)
    {
        defaultIn(node);
    }

    public void outALaffIaff(ALaffIaff node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALaffIaff(ALaffIaff node)
    {
        inALaffIaff(node);
        if(node.getPvir() != null)
        {
            node.getPvir().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getEgal() != null)
        {
            node.getEgal().apply(this);
        }
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        outALaffIaff(node);
    }

    public void inAAppIapp(AAppIapp node)
    {
        defaultIn(node);
    }

    public void outAAppIapp(AAppIapp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAppIapp(AAppIapp node)
    {
        inAAppIapp(node);
        if(node.getPvir() != null)
        {
            node.getPvir().apply(this);
        }
        if(node.getApp() != null)
        {
            node.getApp().apply(this);
        }
        outAAppIapp(node);
    }

    public void inAClosIsi(AClosIsi node)
    {
        defaultIn(node);
    }

    public void outAClosIsi(AClosIsi node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAClosIsi(AClosIsi node)
    {
        inAClosIsi(node);
        if(node.getBloc2() != null)
        {
            node.getBloc2().apply(this);
        }
        if(node.getSinon() != null)
        {
            node.getSinon().apply(this);
        }
        if(node.getBloc1() != null)
        {
            node.getBloc1().apply(this);
        }
        if(node.getAlors() != null)
        {
            node.getAlors().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getSi() != null)
        {
            node.getSi().apply(this);
        }
        outAClosIsi(node);
    }

    public void inAOpenIsi(AOpenIsi node)
    {
        defaultIn(node);
    }

    public void outAOpenIsi(AOpenIsi node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOpenIsi(AOpenIsi node)
    {
        inAOpenIsi(node);
        if(node.getIbloc() != null)
        {
            node.getIbloc().apply(this);
        }
        if(node.getAlors() != null)
        {
            node.getAlors().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getSi() != null)
        {
            node.getSi().apply(this);
        }
        outAOpenIsi(node);
    }

    public void inALoopItq(ALoopItq node)
    {
        defaultIn(node);
    }

    public void outALoopItq(ALoopItq node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALoopItq(ALoopItq node)
    {
        inALoopItq(node);
        if(node.getIbloc() != null)
        {
            node.getIbloc().apply(this);
        }
        if(node.getFaire() != null)
        {
            node.getFaire().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getTantq() != null)
        {
            node.getTantq().apply(this);
        }
        outALoopItq(node);
    }

    public void inARetIret(ARetIret node)
    {
        defaultIn(node);
    }

    public void outARetIret(ARetIret node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARetIret(ARetIret node)
    {
        inARetIret(node);
        if(node.getPvir() != null)
        {
            node.getPvir().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getRet() != null)
        {
            node.getRet().apply(this);
        }
        outARetIret(node);
    }

    public void inAEcrIecr(AEcrIecr node)
    {
        defaultIn(node);
    }

    public void outAEcrIecr(AEcrIecr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEcrIecr(AEcrIecr node)
    {
        inAEcrIecr(node);
        if(node.getPvir() != null)
        {
            node.getPvir().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getEcrire() != null)
        {
            node.getEcrire().apply(this);
        }
        outAEcrIecr(node);
    }

    public void inABlocIbloc(ABlocIbloc node)
    {
        defaultIn(node);
    }

    public void outABlocIbloc(ABlocIbloc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABlocIbloc(ABlocIbloc node)
    {
        inABlocIbloc(node);
        if(node.getRAcc() != null)
        {
            node.getRAcc().apply(this);
        }
        if(node.getLi() != null)
        {
            node.getLi().apply(this);
        }
        if(node.getLAcc() != null)
        {
            node.getLAcc().apply(this);
        }
        outABlocIbloc(node);
    }

    public void inAIdVar(AIdVar node)
    {
        defaultIn(node);
    }

    public void outAIdVar(AIdVar node)
    {
        defaultOut(node);
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

    public void inATabVar(ATabVar node)
    {
        defaultIn(node);
    }

    public void outATabVar(ATabVar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATabVar(ATabVar node)
    {
        inATabVar(node);
        if(node.getRCroc() != null)
        {
            node.getRCroc().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getLCroc() != null)
        {
            node.getLCroc().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outATabVar(node);
    }

    public void inAILi(AILi node)
    {
        defaultIn(node);
    }

    public void outAILi(AILi node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAILi(AILi node)
    {
        inAILi(node);
        if(node.getLi() != null)
        {
            node.getLi().apply(this);
        }
        if(node.getI() != null)
        {
            node.getI().apply(this);
        }
        outAILi(node);
    }

    public void inAVideLi(AVideLi node)
    {
        defaultIn(node);
    }

    public void outAVideLi(AVideLi node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideLi(AVideLi node)
    {
        inAVideLi(node);
        outAVideLi(node);
    }

    public void inAFctApp(AFctApp node)
    {
        defaultIn(node);
    }

    public void outAFctApp(AFctApp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFctApp(AFctApp node)
    {
        inAFctApp(node);
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getLe() != null)
        {
            node.getLe().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAFctApp(node);
    }
}
