/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AOpenIsi extends PIsi
{
    private TSi _si_;
    private PE _e_;
    private TAlors _alors_;
    private PIbloc _ibloc_;

    public AOpenIsi()
    {
        // Constructor
    }

    public AOpenIsi(
        @SuppressWarnings("hiding") TSi _si_,
        @SuppressWarnings("hiding") PE _e_,
        @SuppressWarnings("hiding") TAlors _alors_,
        @SuppressWarnings("hiding") PIbloc _ibloc_)
    {
        // Constructor
        setSi(_si_);

        setE(_e_);

        setAlors(_alors_);

        setIbloc(_ibloc_);

    }

    @Override
    public Object clone()
    {
        return new AOpenIsi(
            cloneNode(this._si_),
            cloneNode(this._e_),
            cloneNode(this._alors_),
            cloneNode(this._ibloc_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAOpenIsi(this);
    }

    public TSi getSi()
    {
        return this._si_;
    }

    public void setSi(TSi node)
    {
        if(this._si_ != null)
        {
            this._si_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._si_ = node;
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

    public TAlors getAlors()
    {
        return this._alors_;
    }

    public void setAlors(TAlors node)
    {
        if(this._alors_ != null)
        {
            this._alors_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._alors_ = node;
    }

    public PIbloc getIbloc()
    {
        return this._ibloc_;
    }

    public void setIbloc(PIbloc node)
    {
        if(this._ibloc_ != null)
        {
            this._ibloc_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._ibloc_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._si_)
            + toString(this._e_)
            + toString(this._alors_)
            + toString(this._ibloc_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._si_ == child)
        {
            this._si_ = null;
            return;
        }

        if(this._e_ == child)
        {
            this._e_ = null;
            return;
        }

        if(this._alors_ == child)
        {
            this._alors_ = null;
            return;
        }

        if(this._ibloc_ == child)
        {
            this._ibloc_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._si_ == oldChild)
        {
            setSi((TSi) newChild);
            return;
        }

        if(this._e_ == oldChild)
        {
            setE((PE) newChild);
            return;
        }

        if(this._alors_ == oldChild)
        {
            setAlors((TAlors) newChild);
            return;
        }

        if(this._ibloc_ == oldChild)
        {
            setIbloc((PIbloc) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
