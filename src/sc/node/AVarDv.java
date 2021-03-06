/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AVarDv extends PDv
{
    private TEntier _entier_;
    private PDvar _dvar_;

    public AVarDv()
    {
        // Constructor
    }

    public AVarDv(
        @SuppressWarnings("hiding") TEntier _entier_,
        @SuppressWarnings("hiding") PDvar _dvar_)
    {
        // Constructor
        setEntier(_entier_);

        setDvar(_dvar_);

    }

    @Override
    public Object clone()
    {
        return new AVarDv(
            cloneNode(this._entier_),
            cloneNode(this._dvar_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAVarDv(this);
    }

    public TEntier getEntier()
    {
        return this._entier_;
    }

    public void setEntier(TEntier node)
    {
        if(this._entier_ != null)
        {
            this._entier_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._entier_ = node;
    }

    public PDvar getDvar()
    {
        return this._dvar_;
    }

    public void setDvar(PDvar node)
    {
        if(this._dvar_ != null)
        {
            this._dvar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._dvar_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._entier_)
            + toString(this._dvar_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._entier_ == child)
        {
            this._entier_ = null;
            return;
        }

        if(this._dvar_ == child)
        {
            this._dvar_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._entier_ == oldChild)
        {
            setEntier((TEntier) newChild);
            return;
        }

        if(this._dvar_ == oldChild)
        {
            setDvar((PDvar) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
