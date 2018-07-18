package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerIdologyStateFailureBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView btTryAgain;
    public final ImageView ivFailedIcon;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final TextView tvFailureDescription;
    public final TextView tvFailureTitle;

    static {
        sViewsWithIds.put(R.id.ivFailedIcon, 1);
        sViewsWithIds.put(R.id.tvFailureTitle, 2);
        sViewsWithIds.put(R.id.tvFailureDescription, 3);
        sViewsWithIds.put(R.id.btTryAgain, 4);
    }

    public ControllerIdologyStateFailureBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.btTryAgain = (TextView) bindings[4];
        this.ivFailedIcon = (ImageView) bindings[1];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvFailureDescription = (TextView) bindings[3];
        this.tvFailureTitle = (TextView) bindings[2];
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

    public static ControllerIdologyStateFailureBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerIdologyStateFailureBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerIdologyStateFailureBinding) DataBindingUtil.inflate(inflater, R.layout.controller_idology_state_failure, root, attachToRoot, bindingComponent);
    }

    public static ControllerIdologyStateFailureBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerIdologyStateFailureBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_idology_state_failure, null, false), bindingComponent);
    }

    public static ControllerIdologyStateFailureBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerIdologyStateFailureBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_idology_state_failure_0".equals(view.getTag())) {
            return new ControllerIdologyStateFailureBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
