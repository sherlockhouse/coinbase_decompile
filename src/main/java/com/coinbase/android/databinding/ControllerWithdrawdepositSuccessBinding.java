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
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerWithdrawdepositSuccessBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btGotoAccount;
    public final Toolbar cvCoinbaseToolbar;
    public final ImageView ivLogo;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final TextView tvAmount;
    public final TextView tvSuccessText;

    static {
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 1);
        sViewsWithIds.put(R.id.ivLogo, 2);
        sViewsWithIds.put(R.id.tvSuccessText, 3);
        sViewsWithIds.put(R.id.tvAmount, 4);
        sViewsWithIds.put(R.id.btGotoAccount, 5);
    }

    public ControllerWithdrawdepositSuccessBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.btGotoAccount = (Button) bindings[5];
        this.cvCoinbaseToolbar = (Toolbar) bindings[1];
        this.ivLogo = (ImageView) bindings[2];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvAmount = (TextView) bindings[4];
        this.tvSuccessText = (TextView) bindings[3];
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

    public static ControllerWithdrawdepositSuccessBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerWithdrawdepositSuccessBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerWithdrawdepositSuccessBinding) DataBindingUtil.inflate(inflater, R.layout.controller_withdrawdeposit_success, root, attachToRoot, bindingComponent);
    }

    public static ControllerWithdrawdepositSuccessBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerWithdrawdepositSuccessBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_withdrawdeposit_success, null, false), bindingComponent);
    }

    public static ControllerWithdrawdepositSuccessBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerWithdrawdepositSuccessBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_withdrawdeposit_success_0".equals(view.getTag())) {
            return new ControllerWithdrawdepositSuccessBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
