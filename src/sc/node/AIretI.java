/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AIretI extends PI
{
    private PIret _iret_;

    public AIretI()
    {
        // Constructor
    }

    public AIretI(
        @SuppressWarnings("hiding") PIret _iret_)
    {
        // Constructor
        setIret(_iret_);

    }

    @Override
    public Object clone()
    {
        return new AIretI(
            cloneNode(this._iret_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIretI(this);
    }

    public PIret getIret()
    {
        return this._iret_;
    }

    public void setIret(PIret node)
    {
        if(this._iret_ != null)
        {
            this._iret_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._iret_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._iret_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._iret_ == child)
        {
            this._iret_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._iret_ == oldChild)
        {
            setIret((PIret) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
