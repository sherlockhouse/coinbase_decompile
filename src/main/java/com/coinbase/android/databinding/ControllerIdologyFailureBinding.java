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
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerIdologyFailureBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btTryAgain;
    public final Toolbar cvCoinbaseToolbar;
    public final ImageView ivFailedIcon;
    public final ImageView ivFailedIconBackground;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final TextView tvFailureDescription;
    public final TextView tvFailureTitle;

    static {
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 1);
        sViewsWithIds.put(R.id.ivFailedIcon, 2);
        sViewsWithIds.put(R.id.ivFailedIconBackground, 3);
        sViewsWithIds.put(R.id.tvFailureTitle, 4);
        sViewsWithIds.put(R.id.tvFailureDescription, 5);
        sViewsWithIds.put(R.id.btTryAgain, 6);
    }

    public ControllerIdologyFailureBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.btTryAgain = (Button) bindings[6];
        this.cvCoinbaseToolbar = (Toolbar) bindings[1];
        this.ivFailedIcon = (ImageView) bindings[2];
        this.ivFailedIconBackground = (ImageView) bindings[3];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvFailureDescription = (TextView) bindings[5];
        this.tvFailureTitle = (TextView) bindings[4];
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

    public static ControllerIdologyFailureBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerIdologyFailureBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerIdologyFailureBinding) DataBindingUtil.inflate(inflater, R.layout.controller_idology_failure, root, attachToRoot, bindingComponent);
    }

    public static ControllerIdologyFailureBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerIdologyFailureBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_idology_failure, null, false), bindingComponent);
    }

    public static ControllerIdologyFailureBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerIdologyFailureBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_idology_failure_0".equals(view.getTag())) {
            return new ControllerIdologyFailureBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
