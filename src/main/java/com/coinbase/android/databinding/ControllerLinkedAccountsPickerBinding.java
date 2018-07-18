package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.coinbase.android.R;
import com.coinbase.android.ui.CenteredAppBarLayout;

public class ControllerLinkedAccountsPickerBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final CenteredAppBarLayout apbLayout;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final RecyclerView rlPaymentMethods;

    static {
        sViewsWithIds.put(R.id.apbLayout, 1);
        sViewsWithIds.put(R.id.rlPaymentMethods, 2);
    }

    public ControllerLinkedAccountsPickerBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.apbLayout = (CenteredAppBarLayout) bindings[1];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlPaymentMethods = (RecyclerView) bindings[2];
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

    public static ControllerLinkedAccountsPickerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerLinkedAccountsPickerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerLinkedAccountsPickerBinding) DataBindingUtil.inflate(inflater, R.layout.controller_linked_accounts_picker, root, attachToRoot, bindingComponent);
    }

    public static ControllerLinkedAccountsPickerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerLinkedAccountsPickerBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_linked_accounts_picker, null, false), bindingComponent);
    }

    public static ControllerLinkedAccountsPickerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerLinkedAccountsPickerBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_linked_accounts_picker_0".equals(view.getTag())) {
            return new ControllerLinkedAccountsPickerBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
