package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.coinbase.android.R;

public class FragmentSepaDepositWithdrawBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    public final ProgressBar progress;
    public final RelativeLayout rlSepaInfo;
    public final RecyclerView rvSepaDepositInfo;

    static {
        sViewsWithIds.put(R.id.rvSepaDepositInfo, 1);
        sViewsWithIds.put(R.id.progress, 2);
    }

    public FragmentSepaDepositWithdrawBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.progress = (ProgressBar) bindings[2];
        this.rlSepaInfo = (RelativeLayout) bindings[0];
        this.rlSepaInfo.setTag(null);
        this.rvSepaDepositInfo = (RecyclerView) bindings[1];
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

    public static FragmentSepaDepositWithdrawBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentSepaDepositWithdrawBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (FragmentSepaDepositWithdrawBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_sepa_deposit_withdraw, root, attachToRoot, bindingComponent);
    }

    public static FragmentSepaDepositWithdrawBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentSepaDepositWithdrawBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.fragment_sepa_deposit_withdraw, null, false), bindingComponent);
    }

    public static FragmentSepaDepositWithdrawBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentSepaDepositWithdrawBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/fragment_sepa_deposit_withdraw_0".equals(view.getTag())) {
            return new FragmentSepaDepositWithdrawBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
