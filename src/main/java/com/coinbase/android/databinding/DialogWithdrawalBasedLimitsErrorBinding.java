package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class DialogWithdrawalBasedLimitsErrorBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final TextView tvDismiss;
    public final TextView tvLearnMore;
    public final TextView tvMessage;
    public final TextView tvTitle;

    static {
        sViewsWithIds.put(R.id.tvTitle, 1);
        sViewsWithIds.put(R.id.tvMessage, 2);
        sViewsWithIds.put(R.id.tvDismiss, 3);
        sViewsWithIds.put(R.id.tvLearnMore, 4);
    }

    public DialogWithdrawalBasedLimitsErrorBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvDismiss = (TextView) bindings[3];
        this.tvLearnMore = (TextView) bindings[4];
        this.tvMessage = (TextView) bindings[2];
        this.tvTitle = (TextView) bindings[1];
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

    public static DialogWithdrawalBasedLimitsErrorBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static DialogWithdrawalBasedLimitsErrorBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (DialogWithdrawalBasedLimitsErrorBinding) DataBindingUtil.inflate(inflater, R.layout.dialog_withdrawal_based_limits_error, root, attachToRoot, bindingComponent);
    }

    public static DialogWithdrawalBasedLimitsErrorBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static DialogWithdrawalBasedLimitsErrorBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.dialog_withdrawal_based_limits_error, null, false), bindingComponent);
    }

    public static DialogWithdrawalBasedLimitsErrorBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static DialogWithdrawalBasedLimitsErrorBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/dialog_withdrawal_based_limits_error_0".equals(view.getTag())) {
            return new DialogWithdrawalBasedLimitsErrorBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
