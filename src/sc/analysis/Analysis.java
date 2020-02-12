/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.analysis;

import sc.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseAPProg(APProg node);
    void caseALLdf(ALLdf node);
    void caseAVideLdf(AVideLdf node);
    void caseADfDf(ADfDf node);
    void caseAVideLdvo2(AVideLdvo2 node);
    void caseALLdvo2(ALLdvo2 node);
    void caseAVideLdvo(AVideLdvo node);
    void caseALLdvo(ALLdvo node);
    void caseALLdv(ALLdv node);
    void caseADvLdv(ADvLdv node);
    void caseAVarDv(AVarDv node);
    void caseAIdDvar(AIdDvar node);
    void caseATabDvar(ATabDvar node);
    void caseALexpLe(ALexpLe node);
    void caseAExpLe(AExpLe node);
    void caseAVideLe(AVideLe node);
    void caseAOuE(AOuE node);
    void caseAEtE(AEtE node);
    void caseAEtE1(AEtE1 node);
    void caseAEgalE1(AEgalE1 node);
    void caseAEgalE2(AEgalE2 node);
    void caseAInfE2(AInfE2 node);
    void caseAPlusE2(APlusE2 node);
    void caseAPlusE3(APlusE3 node);
    void caseAMinusE3(AMinusE3 node);
    void caseAMultE3(AMultE3 node);
    void caseAMultE4(AMultE4 node);
    void caseADivE4(ADivE4 node);
    void caseANonE4(ANonE4 node);
    void caseANonE5(ANonE5 node);
    void caseAParE5(AParE5 node);
    void caseAParE6(AParE6 node);
    void caseANbrE6(ANbrE6 node);
    void caseAAppE6(AAppE6 node);
    void caseALireE6(ALireE6 node);
    void caseAVarE6(AVarE6 node);
    void caseALireElire(ALireElire node);
    void caseAIaffI(AIaffI node);
    void caseAIsiI(AIsiI node);
    void caseAItqI(AItqI node);
    void caseAIblocI(AIblocI node);
    void caseAIretI(AIretI node);
    void caseAIecrI(AIecrI node);
    void caseAIappI(AIappI node);
    void caseALaffIaff(ALaffIaff node);
    void caseAAppIapp(AAppIapp node);
    void caseAClosIsi(AClosIsi node);
    void caseAOpenIsi(AOpenIsi node);
    void caseALoopItq(ALoopItq node);
    void caseARetIret(ARetIret node);
    void caseAEcrIecr(AEcrIecr node);
    void caseABlocIbloc(ABlocIbloc node);
    void caseAIdVar(AIdVar node);
    void caseATabVar(ATabVar node);
    void caseAILi(AILi node);
    void caseAVideLi(AVideLi node);
    void caseAFctApp(AFctApp node);

    void caseTNbr(TNbr node);
    void caseTPlus(TPlus node);
    void caseTMinus(TMinus node);
    void caseTMult(TMult node);
    void caseTDiv(TDiv node);
    void caseTInf(TInf node);
    void caseTEgal(TEgal node);
    void caseTNon(TNon node);
    void caseTEt(TEt node);
    void caseTOu(TOu node);
    void caseTLPar(TLPar node);
    void caseTRPar(TRPar node);
    void caseTLAcc(TLAcc node);
    void caseTRAcc(TRAcc node);
    void caseTLCroc(TLCroc node);
    void caseTRCroc(TRCroc node);
    void caseTPvir(TPvir node);
    void caseTVir(TVir node);
    void caseTRet(TRet node);
    void caseTSi(TSi node);
    void caseTAlors(TAlors node);
    void caseTSinon(TSinon node);
    void caseTTantq(TTantq node);
    void caseTFaire(TFaire node);
    void caseTEcrire(TEcrire node);
    void caseTLire(TLire node);
    void caseTEntier(TEntier node);
    void caseTId(TId node);
    void caseTEspaces(TEspaces node);
    void caseTCommentaire(TCommentaire node);
    void caseEOF(EOF node);
    void caseInvalidToken(InvalidToken node);
}
