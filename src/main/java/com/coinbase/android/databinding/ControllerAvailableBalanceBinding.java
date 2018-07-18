package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.coinbase.android.R;
import com.coinbase.android.ui.CenteredAppBarLayout;
import com.coinbase.android.wbl.AvailableBalanceLayout;

public class ControllerAvailableBalanceBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final CenteredAppBarLayout apbLayout;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final AvailableBalanceLayout vAvailableBalanceLayout;

    static {
        sViewsWithIds.put(R.id.apbLayout, 1);
        sViewsWithIds.put(R.id.vAvailableBalanceLayout, 2);
    }

    public ControllerAvailableBalanceBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.apbLayout = (CenteredAppBarLayout) bindings[1];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.vAvailableBalanceLayout = (AvailableBalanceLayout) bindings[2];
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

    public static ControllerAvailableBalanceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerAvailableBalanceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerAvailableBalanceBinding) DataBindingUtil.inflate(inflater, R.layout.controller_available_balance, root, attachToRoot, bindingComponent);
    }

    public static ControllerAvailableBalanceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerAvailableBalanceBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_available_balance, null, false), bindingComponent);
    }

    public static ControllerAvailableBalanceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerAvailableBalanceBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_available_balance_0".equals(view.getTag())) {
            return new ControllerAvailableBalanceBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
