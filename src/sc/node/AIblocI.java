/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AIblocI extends PI
{
    private PIbloc _ibloc_;

    public AIblocI()
    {
        // Constructor
    }

    public AIblocI(
        @SuppressWarnings("hiding") PIbloc _ibloc_)
    {
        // Constructor
        setIbloc(_ibloc_);

    }

    @Override
    public Object clone()
    {
        return new AIblocI(
            cloneNode(this._ibloc_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIblocI(this);
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
            + toString(this._ibloc_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
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
        if(this._ibloc_ == oldChild)
        {
            setIbloc((PIbloc) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
