/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ALaffIaff extends PIaff
{
    private PVar _var_;
    private TEgal _egal_;
    private PE _e_;
    private TPvir _pvir_;

    public ALaffIaff()
    {
        // Constructor
    }

    public ALaffIaff(
        @SuppressWarnings("hiding") PVar _var_,
        @SuppressWarnings("hiding") TEgal _egal_,
        @SuppressWarnings("hiding") PE _e_,
        @SuppressWarnings("hiding") TPvir _pvir_)
    {
        // Constructor
        setVar(_var_);

        setEgal(_egal_);

        setE(_e_);

        setPvir(_pvir_);

    }

    @Override
    public Object clone()
    {
        return new ALaffIaff(
            cloneNode(this._var_),
            cloneNode(this._egal_),
            cloneNode(this._e_),
            cloneNode(this._pvir_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseALaffIaff(this);
    }

    public PVar getVar()
    {
        return this._var_;
    }

    public void setVar(PVar node)
    {
        if(this._var_ != null)
        {
            this._var_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._var_ = node;
    }

    public TEgal getEgal()
    {
        return this._egal_;
    }

    public void setEgal(TEgal node)
    {
        if(this._egal_ != null)
        {
            this._egal_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._egal_ = node;
    }

    public PE getE()
    {
        return this._e_;
    }

    public void setE(PE node)
    {
        if(this._e_ != null)
        {
            this._e_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._e_ = node;
    }

    public TPvir getPvir()
    {
        return this._pvir_;
    }

    public void setPvir(TPvir node)
    {
        if(this._pvir_ != null)
        {
            this._pvir_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._pvir_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._var_)
            + toString(this._egal_)
            + toString(this._e_)
            + toString(this._pvir_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._var_ == child)
        {
            this._var_ = null;
            return;
        }

        if(this._egal_ == child)
        {
            this._egal_ = null;
            return;
        }

        if(this._e_ == child)
        {
            this._e_ = null;
            return;
        }

        if(this._pvir_ == child)
        {
            this._pvir_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._var_ == oldChild)
        {
            setVar((PVar) newChild);
            return;
        }

        if(this._egal_ == oldChild)
        {
            setEgal((TEgal) newChild);
            return;
        }

        if(this._e_ == oldChild)
        {
            setE((PE) newChild);
            return;
        }

        if(this._pvir_ == oldChild)
        {
            setPvir((TPvir) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
