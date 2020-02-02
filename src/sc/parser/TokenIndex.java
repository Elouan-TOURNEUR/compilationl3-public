/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.parser;

import sc.node.*;
import sc.analysis.*;

class TokenIndex extends AnalysisAdapter
{
    int index;

    @Override
    public void caseTNbr(@SuppressWarnings("unused") TNbr node)
    {
        this.index = 0;
    }

    @Override
    public void caseTPlus(@SuppressWarnings("unused") TPlus node)
    {
        this.index = 1;
    }

    @Override
    public void caseTMinus(@SuppressWarnings("unused") TMinus node)
    {
        this.index = 2;
    }

    @Override
    public void caseTMult(@SuppressWarnings("unused") TMult node)
    {
        this.index = 3;
    }

    @Override
    public void caseTDiv(@SuppressWarnings("unused") TDiv node)
    {
        this.index = 4;
    }

    @Override
    public void caseTInf(@SuppressWarnings("unused") TInf node)
    {
        this.index = 5;
    }

    @Override
    public void caseTEgal(@SuppressWarnings("unused") TEgal node)
    {
        this.index = 6;
    }

    @Override
    public void caseTNon(@SuppressWarnings("unused") TNon node)
    {
        this.index = 7;
    }

    @Override
    public void caseTEt(@SuppressWarnings("unused") TEt node)
    {
        this.index = 8;
    }

    @Override
    public void caseTOu(@SuppressWarnings("unused") TOu node)
    {
        this.index = 9;
    }

    @Override
    public void caseTLPar(@SuppressWarnings("unused") TLPar node)
    {
        this.index = 10;
    }

    @Override
    public void caseTRPar(@SuppressWarnings("unused") TRPar node)
    {
        this.index = 11;
    }

    @Override
    public void caseTLAcc(@SuppressWarnings("unused") TLAcc node)
    {
        this.index = 12;
    }

    @Override
    public void caseTRAcc(@SuppressWarnings("unused") TRAcc node)
    {
        this.index = 13;
    }

    @Override
    public void caseTLCroc(@SuppressWarnings("unused") TLCroc node)
    {
        this.index = 14;
    }

    @Override
    public void caseTRCroc(@SuppressWarnings("unused") TRCroc node)
    {
        this.index = 15;
    }

    @Override
    public void caseTPvir(@SuppressWarnings("unused") TPvir node)
    {
        this.index = 16;
    }

    @Override
    public void caseTVir(@SuppressWarnings("unused") TVir node)
    {
        this.index = 17;
    }

    @Override
    public void caseTRet(@SuppressWarnings("unused") TRet node)
    {
        this.index = 18;
    }

    @Override
    public void caseTSi(@SuppressWarnings("unused") TSi node)
    {
        this.index = 19;
    }

    @Override
    public void caseTAlors(@SuppressWarnings("unused") TAlors node)
    {
        this.index = 20;
    }

    @Override
    public void caseTSinon(@SuppressWarnings("unused") TSinon node)
    {
        this.index = 21;
    }

    @Override
    public void caseTTantq(@SuppressWarnings("unused") TTantq node)
    {
        this.index = 22;
    }

    @Override
    public void caseTFaire(@SuppressWarnings("unused") TFaire node)
    {
        this.index = 23;
    }

    @Override
    public void caseTEntier(@SuppressWarnings("unused") TEntier node)
    {
        this.index = 24;
    }

    @Override
    public void caseTId(@SuppressWarnings("unused") TId node)
    {
        this.index = 25;
    }

    @Override
    public void caseEOF(@SuppressWarnings("unused") EOF node)
    {
        this.index = 26;
    }
}
