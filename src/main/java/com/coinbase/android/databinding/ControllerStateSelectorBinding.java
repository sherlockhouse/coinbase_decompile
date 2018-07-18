package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerStateSelectorBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final View bottomLineView;
    public final TextView btnContinue;
    public final Toolbar cvCoinbaseToolbar;
    public final ImageView ivIcon;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final ProgressBar pbLoading;
    public final RelativeLayout rlStateSelector;
    public final RelativeLayout stateSelectorContainer;
    public final View topLineView;
    public final TextView tvDescription;
    public final TextView tvState;
    public final TextView tvTitle;

    static {
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 1);
        sViewsWithIds.put(R.id.stateSelectorContainer, 2);
        sViewsWithIds.put(R.id.pbLoading, 3);
        sViewsWithIds.put(R.id.ivIcon, 4);
        sViewsWithIds.put(R.id.tvTitle, 5);
        sViewsWithIds.put(R.id.tvDescription, 6);
        sViewsWithIds.put(R.id.topLineView, 7);
        sViewsWithIds.put(R.id.rlStateSelector, 8);
        sViewsWithIds.put(R.id.tvState, 9);
        sViewsWithIds.put(R.id.bottomLineView, 10);
        sViewsWithIds.put(R.id.btnContinue, 11);
    }

    public ControllerStateSelectorBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds);
        this.bottomLineView = (View) bindings[10];
        this.btnContinue = (TextView) bindings[11];
        this.cvCoinbaseToolbar = (Toolbar) bindings[1];
        this.ivIcon = (ImageView) bindings[4];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.pbLoading = (ProgressBar) bindings[3];
        this.rlStateSelector = (RelativeLayout) bindings[8];
        this.stateSelectorContainer = (RelativeLayout) bindings[2];
        this.topLineView = (View) bindings[7];
        this.tvDescription = (TextView) bindings[6];
        this.tvState = (TextView) bindings[9];
        this.tvTitle = (TextView) bindings[5];
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

    public static ControllerStateSelectorBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerStateSelectorBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerStateSelectorBinding) DataBindingUtil.inflate(inflater, R.layout.controller_state_selector, root, attachToRoot, bindingComponent);
    }

    public static ControllerStateSelectorBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerStateSelectorBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_state_selector, null, false), bindingComponent);
    }

    public static ControllerStateSelectorBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerStateSelectorBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_state_selector_0".equals(view.getTag())) {
            return new ControllerStateSelectorBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
