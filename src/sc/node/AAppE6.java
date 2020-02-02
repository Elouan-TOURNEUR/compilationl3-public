/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AAppE6 extends PE6
{
    private PApp _app_;

    public AAppE6()
    {
        // Constructor
    }

    public AAppE6(
        @SuppressWarnings("hiding") PApp _app_)
    {
        // Constructor
        setApp(_app_);

    }

    @Override
    public Object clone()
    {
        return new AAppE6(
            cloneNode(this._app_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAppE6(this);
    }

    public PApp getApp()
    {
        return this._app_;
    }

    public void setApp(PApp node)
    {
        if(this._app_ != null)
        {
            this._app_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._app_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._app_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._app_ == child)
        {
            this._app_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._app_ == oldChild)
        {
            setApp((PApp) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
