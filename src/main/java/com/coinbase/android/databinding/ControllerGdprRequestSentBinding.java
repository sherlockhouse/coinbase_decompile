package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.coinbase.android.R;

public class ControllerGdprRequestSentBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final AppBarLayout abLayout;
    public final Button btGotoSettings;
    public final Toolbar cvCoinbaseToolbar;
    private long mDirtyFlags = -1;
    public final RelativeLayout rlContainer;

    static {
        sViewsWithIds.put(R.id.abLayout, 1);
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 2);
        sViewsWithIds.put(R.id.btGotoSettings, 3);
    }

    public ControllerGdprRequestSentBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.abLayout = (AppBarLayout) bindings[1];
        this.btGotoSettings = (Button) bindings[3];
        this.cvCoinbaseToolbar = (Toolbar) bindings[2];
        this.rlContainer = (RelativeLayout) bindings[0];
        this.rlContainer.setTag(null);
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

    public static ControllerGdprRequestSentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerGdprRequestSentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerGdprRequestSentBinding) DataBindingUtil.inflate(inflater, R.layout.controller_gdpr_request_sent, root, attachToRoot, bindingComponent);
    }

    public static ControllerGdprRequestSentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerGdprRequestSentBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_gdpr_request_sent, null, false), bindingComponent);
    }

    public static ControllerGdprRequestSentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerGdprRequestSentBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_gdpr_request_sent_0".equals(view.getTag())) {
            return new ControllerGdprRequestSentBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
