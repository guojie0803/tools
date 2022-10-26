package com.guojie.tools.flowLayout;

import android.view.View;
import android.view.ViewGroup;

/**
 *
 */
public abstract class FlowAdapt {

    public abstract int getCount();

    public abstract View getView(ViewGroup viewGroup,int position);

}
