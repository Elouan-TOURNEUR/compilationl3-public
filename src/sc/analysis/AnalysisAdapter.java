/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.analysis;

import java.util.*;
import sc.node.*;

public class AnalysisAdapter implements Analysis
{
    private Hashtable<Node,Object> in;
    private Hashtable<Node,Object> out;

    @Override
    public Object getIn(Node node)
    {
        if(this.in == null)
        {
            return null;
        }

        return this.in.get(node);
    }

    @Override
    public void setIn(Node node, Object o)
    {
        if(this.in == null)
        {
            this.in = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.in.put(node, o);
        }
        else
        {
            this.in.remove(node);
        }
    }

    @Override
    public Object getOut(Node node)
    {
        if(this.out == null)
        {
            return null;
        }

        return this.out.get(node);
    }

    @Override
    public void setOut(Node node, Object o)
    {
        if(this.out == null)
        {
            this.out = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.out.put(node, o);
        }
        else
        {
            this.out.remove(node);
        }
    }

    @Override
    public void caseStart(Start node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAPProg(APProg node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALLdf(ALLdf node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVideLdf(AVideLdf node)
    {
        defaultCase(node);
    }

    @Override
    public void caseADfDf(ADfDf node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVideLdvo2(AVideLdvo2 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALLdvo2(ALLdvo2 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVideLdvo(AVideLdvo node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALLdvo(ALLdvo node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALLdv(ALLdv node)
    {
        defaultCase(node);
    }

    @Override
    public void caseADvLdv(ADvLdv node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVarDv(AVarDv node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIdVar(AIdVar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseATabVar(ATabVar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALexpLe(ALexpLe node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExpLe(AExpLe node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVideLe(AVideLe node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAOuE(AOuE node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAEtE(AEtE node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAEtE1(AEtE1 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAEgalE1(AEgalE1 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAEgalE2(AEgalE2 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInfE2(AInfE2 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAPlusE2(APlusE2 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAPlusE3(APlusE3 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMinusE3(AMinusE3 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMultE3(AMultE3 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMultE4(AMultE4 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseADivE4(ADivE4 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANonE4(ANonE4 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANonE5(ANonE5 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAParE5(AParE5 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAParE6(AParE6 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANbrE6(ANbrE6 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAppE6(AAppE6 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVarE6(AVarE6 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIaffI(AIaffI node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIsiI(AIsiI node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAItqI(AItqI node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIappI(AIappI node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIblocI(AIblocI node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAIretI(AIretI node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALaffIaff(ALaffIaff node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAppIapp(AAppIapp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAClosIsi(AClosIsi node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAOpenIsi(AOpenIsi node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALoopItq(ALoopItq node)
    {
        defaultCase(node);
    }

    @Override
    public void caseARetIret(ARetIret node)
    {
        defaultCase(node);
    }

    @Override
    public void caseABlocIbloc(ABlocIbloc node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAILi(AILi node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVideLi(AVideLi node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFctApp(AFctApp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTNbr(TNbr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTPlus(TPlus node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMinus(TMinus node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMult(TMult node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTDiv(TDiv node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTInf(TInf node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTEgal(TEgal node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTNon(TNon node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTEt(TEt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTOu(TOu node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLPar(TLPar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTRPar(TRPar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLAcc(TLAcc node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTRAcc(TRAcc node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLCroc(TLCroc node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTRCroc(TRCroc node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTPvir(TPvir node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTVir(TVir node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTRet(TRet node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTSi(TSi node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTAlors(TAlors node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTSinon(TSinon node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTantq(TTantq node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTFaire(TFaire node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTEntier(TEntier node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTId(TId node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTEspaces(TEspaces node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTCommentaire(TCommentaire node)
    {
        defaultCase(node);
    }

    @Override
    public void caseEOF(EOF node)
    {
        defaultCase(node);
    }

    @Override
    public void caseInvalidToken(InvalidToken node)
    {
        defaultCase(node);
    }

    public void defaultCase(@SuppressWarnings("unused") Node node)
    {
        // do nothing
    }
}
