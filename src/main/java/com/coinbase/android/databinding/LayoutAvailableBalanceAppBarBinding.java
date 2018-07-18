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
import android.widget.ImageView;
import android.widget.TextView;
import com.coinbase.android.R;

public class LayoutAvailableBalanceAppBarBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final AppBarLayout apbLayout;
    public final Toolbar cvCoinbaseToolbar;
    public final ImageView ivAvailableBalance;
    public final ImageView ivAvailableBalanceInvisible;
    private long mDirtyFlags = -1;
    public final View shadowView;
    public final TextView tvAvailableBalance;

    static {
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 1);
        sViewsWithIds.put(R.id.ivAvailableBalanceInvisible, 2);
        sViewsWithIds.put(R.id.tvAvailableBalance, 3);
        sViewsWithIds.put(R.id.ivAvailableBalance, 4);
        sViewsWithIds.put(R.id.shadow_view, 5);
    }

    public LayoutAvailableBalanceAppBarBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.apbLayout = (AppBarLayout) bindings[0];
        this.apbLayout.setTag(null);
        this.cvCoinbaseToolbar = (Toolbar) bindings[1];
        this.ivAvailableBalance = (ImageView) bindings[4];
        this.ivAvailableBalanceInvisible = (ImageView) bindings[2];
        this.shadowView = (View) bindings[5];
        this.tvAvailableBalance = (TextView) bindings[3];
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

    public static LayoutAvailableBalanceAppBarBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutAvailableBalanceAppBarBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (LayoutAvailableBalanceAppBarBinding) DataBindingUtil.inflate(inflater, R.layout.layout_available_balance_app_bar, root, attachToRoot, bindingComponent);
    }

    public static LayoutAvailableBalanceAppBarBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutAvailableBalanceAppBarBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_available_balance_app_bar, null, false), bindingComponent);
    }

    public static LayoutAvailableBalanceAppBarBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutAvailableBalanceAppBarBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/layout_available_balance_app_bar_0".equals(view.getTag())) {
            return new LayoutAvailableBalanceAppBarBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
