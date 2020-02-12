/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ALireElire extends PElire
{
    private TLire _lire_;
    private TLPar _lPar_;
    private TRPar _rPar_;

    public ALireElire()
    {
        // Constructor
    }

    public ALireElire(
        @SuppressWarnings("hiding") TLire _lire_,
        @SuppressWarnings("hiding") TLPar _lPar_,
        @SuppressWarnings("hiding") TRPar _rPar_)
    {
        // Constructor
        setLire(_lire_);

        setLPar(_lPar_);

        setRPar(_rPar_);

    }

    @Override
    public Object clone()
    {
        return new ALireElire(
            cloneNode(this._lire_),
            cloneNode(this._lPar_),
            cloneNode(this._rPar_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseALireElire(this);
    }

    public TLire getLire()
    {
        return this._lire_;
    }

    public void setLire(TLire node)
    {
        if(this._lire_ != null)
        {
            this._lire_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lire_ = node;
    }

    public TLPar getLPar()
    {
        return this._lPar_;
    }

    public void setLPar(TLPar node)
    {
        if(this._lPar_ != null)
        {
            this._lPar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lPar_ = node;
    }

    public TRPar getRPar()
    {
        return this._rPar_;
    }

    public void setRPar(TRPar node)
    {
        if(this._rPar_ != null)
        {
            this._rPar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rPar_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._lire_)
            + toString(this._lPar_)
            + toString(this._rPar_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._lire_ == child)
        {
            this._lire_ = null;
            return;
        }

        if(this._lPar_ == child)
        {
            this._lPar_ = null;
            return;
        }

        if(this._rPar_ == child)
        {
            this._rPar_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._lire_ == oldChild)
        {
            setLire((TLire) newChild);
            return;
        }

        if(this._lPar_ == oldChild)
        {
            setLPar((TLPar) newChild);
            return;
        }

        if(this._rPar_ == oldChild)
        {
            setRPar((TRPar) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
