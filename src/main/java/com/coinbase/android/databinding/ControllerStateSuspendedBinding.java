package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerStateSuspendedBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final View bottomLineView;
    public final Button btnGoBack;
    public final Toolbar cvCoinbaseToolbar;
    public final ImageView imageView4;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final RelativeLayout stateSelectorContainer;
    public final TextView textView6;
    public final View topLineView;
    public final TextView tvWyoming;

    static {
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 1);
        sViewsWithIds.put(R.id.stateSelectorContainer, 2);
        sViewsWithIds.put(R.id.imageView4, 3);
        sViewsWithIds.put(R.id.textView6, 4);
        sViewsWithIds.put(R.id.topLineView, 5);
        sViewsWithIds.put(R.id.tvWyoming, 6);
        sViewsWithIds.put(R.id.bottomLineView, 7);
        sViewsWithIds.put(R.id.btnGoBack, 8);
    }

    public ControllerStateSuspendedBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.bottomLineView = (View) bindings[7];
        this.btnGoBack = (Button) bindings[8];
        this.cvCoinbaseToolbar = (Toolbar) bindings[1];
        this.imageView4 = (ImageView) bindings[3];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.stateSelectorContainer = (RelativeLayout) bindings[2];
        this.textView6 = (TextView) bindings[4];
        this.topLineView = (View) bindings[5];
        this.tvWyoming = (TextView) bindings[6];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int variableId, Object variable) {
        return false;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }

    public static ControllerStateSuspendedBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerStateSuspendedBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerStateSuspendedBinding) DataBindingUtil.inflate(inflater, R.layout.controller_state_suspended, root, attachToRoot, bindingComponent);
    }

    public static ControllerStateSuspendedBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerStateSuspendedBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_state_suspended, null, false), bindingComponent);
    }

    public static ControllerStateSuspendedBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerStateSuspendedBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_state_suspended_0".equals(view.getTag())) {
            return new ControllerStateSuspendedBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
