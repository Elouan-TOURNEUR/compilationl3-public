/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AEtE1 extends PE1
{
    private PE1 _e1_;
    private TEt _et_;
    private PE2 _e2_;

    public AEtE1()
    {
        // Constructor
    }

    public AEtE1(
        @SuppressWarnings("hiding") PE1 _e1_,
        @SuppressWarnings("hiding") TEt _et_,
        @SuppressWarnings("hiding") PE2 _e2_)
    {
        // Constructor
        setE1(_e1_);

        setEt(_et_);

        setE2(_e2_);

    }

    @Override
    public Object clone()
    {
        return new AEtE1(
            cloneNode(this._e1_),
            cloneNode(this._et_),
            cloneNode(this._e2_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAEtE1(this);
    }

    public PE1 getE1()
    {
        return this._e1_;
    }

    public void setE1(PE1 node)
    {
        if(this._e1_ != null)
        {
            this._e1_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._e1_ = node;
    }

    public TEt getEt()
    {
        return this._et_;
    }

    public void setEt(TEt node)
    {
        if(this._et_ != null)
        {
            this._et_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._et_ = node;
    }

    public PE2 getE2()
    {
        return this._e2_;
    }

    public void setE2(PE2 node)
    {
        if(this._e2_ != null)
        {
            this._e2_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._e2_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._e1_)
            + toString(this._et_)
            + toString(this._e2_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._e1_ == child)
        {
            this._e1_ = null;
            return;
        }

        if(this._et_ == child)
        {
            this._et_ = null;
            return;
        }

        if(this._e2_ == child)
        {
            this._e2_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._e1_ == oldChild)
        {
            setE1((PE1) newChild);
            return;
        }

        if(this._et_ == oldChild)
        {
            setEt((TEt) newChild);
            return;
        }

        if(this._e2_ == oldChild)
        {
            setE2((PE2) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
