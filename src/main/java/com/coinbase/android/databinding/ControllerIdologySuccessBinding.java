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

public class ControllerIdologySuccessBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btGotoSettings;
    public final Toolbar cvCoinbaseToolbar;
    public final ImageView ivSuccessIcon;
    public final ImageView ivSuccessIconBackground;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final TextView tvSuccessDescription;
    public final TextView tvSuccessTitle;

    static {
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 1);
        sViewsWithIds.put(R.id.ivSuccessIcon, 2);
        sViewsWithIds.put(R.id.ivSuccessIconBackground, 3);
        sViewsWithIds.put(R.id.tvSuccessTitle, 4);
        sViewsWithIds.put(R.id.tvSuccessDescription, 5);
        sViewsWithIds.put(R.id.btGotoSettings, 6);
    }

    public ControllerIdologySuccessBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.btGotoSettings = (Button) bindings[6];
        this.cvCoinbaseToolbar = (Toolbar) bindings[1];
        this.ivSuccessIcon = (ImageView) bindings[2];
        this.ivSuccessIconBackground = (ImageView) bindings[3];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvSuccessDescription = (TextView) bindings[5];
        this.tvSuccessTitle = (TextView) bindings[4];
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

    public static ControllerIdologySuccessBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerIdologySuccessBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerIdologySuccessBinding) DataBindingUtil.inflate(inflater, R.layout.controller_idology_success, root, attachToRoot, bindingComponent);
    }

    public static ControllerIdologySuccessBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerIdologySuccessBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_idology_success, null, false), bindingComponent);
    }

    public static ControllerIdologySuccessBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerIdologySuccessBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_idology_success_0".equals(view.getTag())) {
            return new ControllerIdologySuccessBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
