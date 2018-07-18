package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class LayoutAvailableBalanceBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;
    public final NestedScrollView nsvContainer;
    public final RelativeLayout progress;
    public final RelativeLayout rlPendingHoldsHeader;
    public final RecyclerView rvFundsOnHold;
    public final TextView tvAvailableBalance;
    public final TextView tvAvailableBalanceHeader;
    public final TextView tvAvailableBalanceSum;
    public final TextView tvFundsOnHoldExplanation;
    public final TextView tvPortfolioBalance;
    public final TextView tvPortfolioBalanceHeader;
    public final TextView tvTotalPendingFunds;
    public final TextView tvTotalPendingFundsHeader;

    static {
        sViewsWithIds.put(R.id.progress, 1);
        sViewsWithIds.put(R.id.nsvContainer, 2);
        sViewsWithIds.put(R.id.tvAvailableBalance, 3);
        sViewsWithIds.put(R.id.tvPortfolioBalanceHeader, 4);
        sViewsWithIds.put(R.id.tvPortfolioBalance, 5);
        sViewsWithIds.put(R.id.tvTotalPendingFundsHeader, 6);
        sViewsWithIds.put(R.id.tvTotalPendingFunds, 7);
        sViewsWithIds.put(R.id.tvAvailableBalanceHeader, 8);
        sViewsWithIds.put(R.id.tvAvailableBalanceSum, 9);
        sViewsWithIds.put(R.id.rlPendingHoldsHeader, 10);
        sViewsWithIds.put(R.id.tvFundsOnHoldExplanation, 11);
        sViewsWithIds.put(R.id.rvFundsOnHold, 12);
    }

    public LayoutAvailableBalanceBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds);
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.nsvContainer = (NestedScrollView) bindings[2];
        this.progress = (RelativeLayout) bindings[1];
        this.rlPendingHoldsHeader = (RelativeLayout) bindings[10];
        this.rvFundsOnHold = (RecyclerView) bindings[12];
        this.tvAvailableBalance = (TextView) bindings[3];
        this.tvAvailableBalanceHeader = (TextView) bindings[8];
        this.tvAvailableBalanceSum = (TextView) bindings[9];
        this.tvFundsOnHoldExplanation = (TextView) bindings[11];
        this.tvPortfolioBalance = (TextView) bindings[5];
        this.tvPortfolioBalanceHeader = (TextView) bindings[4];
        this.tvTotalPendingFunds = (TextView) bindings[7];
        this.tvTotalPendingFundsHeader = (TextView) bindings[6];
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

    public static LayoutAvailableBalanceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutAvailableBalanceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (LayoutAvailableBalanceBinding) DataBindingUtil.inflate(inflater, R.layout.layout_available_balance, root, attachToRoot, bindingComponent);
    }

    public static LayoutAvailableBalanceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutAvailableBalanceBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_available_balance, null, false), bindingComponent);
    }

    public static LayoutAvailableBalanceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutAvailableBalanceBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/layout_available_balance_0".equals(view.getTag())) {
            return new LayoutAvailableBalanceBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
