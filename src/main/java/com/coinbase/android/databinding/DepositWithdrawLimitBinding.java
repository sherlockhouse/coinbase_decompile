package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.ui.AutoResizeTextView;

public class DepositWithdrawLimitBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivImage;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final AutoResizeTextView tvAmount;
    public final TextView tvDisplayName;

    static {
        sViewsWithIds.put(R.id.ivImage, 1);
        sViewsWithIds.put(R.id.tvDisplayName, 2);
        sViewsWithIds.put(R.id.tvAmount, 3);
    }

    public DepositWithdrawLimitBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.ivImage = (ImageView) bindings[1];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvAmount = (AutoResizeTextView) bindings[3];
        this.tvDisplayName = (TextView) bindings[2];
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

    public static DepositWithdrawLimitBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static DepositWithdrawLimitBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (DepositWithdrawLimitBinding) DataBindingUtil.inflate(inflater, R.layout.deposit_withdraw_limit, root, attachToRoot, bindingComponent);
    }

    public static DepositWithdrawLimitBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static DepositWithdrawLimitBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.deposit_withdraw_limit, null, false), bindingComponent);
    }

    public static DepositWithdrawLimitBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static DepositWithdrawLimitBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/deposit_withdraw_limit_0".equals(view.getTag())) {
            return new DepositWithdrawLimitBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
