package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class LayoutConnectedAccountListBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final View btDivider;
    public final View horizontalLine;
    public final ImageView ivPaymentMethodIcon;
    public final LinearLayout linearLayout5;
    public final LinearLayout llRemoveConfirmation;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final RecyclerView rlConnectedAccounts;
    public final RelativeLayout rlLoading;
    public final RelativeLayout rlRemovePaymentView;
    public final TextView tvCancel;
    public final TextView tvConfirmRemove;
    public final TextView tvLoading;
    public final TextView tvPaymentMethodName;
    public final TextView tvPaymetMethodNumber;
    public final TextView tvRemove;
    public final TextView tvRemoveWarning;
    public final TextView tvVerify;

    static {
        sViewsWithIds.put(R.id.rlLoading, 1);
        sViewsWithIds.put(R.id.tvLoading, 2);
        sViewsWithIds.put(R.id.rlConnectedAccounts, 3);
        sViewsWithIds.put(R.id.rlRemovePaymentView, 4);
        sViewsWithIds.put(R.id.ivPaymentMethodIcon, 5);
        sViewsWithIds.put(R.id.tvPaymentMethodName, 6);
        sViewsWithIds.put(R.id.tvPaymetMethodNumber, 7);
        sViewsWithIds.put(R.id.horizontalLine, 8);
        sViewsWithIds.put(R.id.linearLayout5, 9);
        sViewsWithIds.put(R.id.tvVerify, 10);
        sViewsWithIds.put(R.id.btDivider, 11);
        sViewsWithIds.put(R.id.tvRemove, 12);
        sViewsWithIds.put(R.id.llRemoveConfirmation, 13);
        sViewsWithIds.put(R.id.tvRemoveWarning, 14);
        sViewsWithIds.put(R.id.tvCancel, 15);
        sViewsWithIds.put(R.id.tvConfirmRemove, 16);
    }

    public LayoutConnectedAccountListBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds);
        this.btDivider = (View) bindings[11];
        this.horizontalLine = (View) bindings[8];
        this.ivPaymentMethodIcon = (ImageView) bindings[5];
        this.linearLayout5 = (LinearLayout) bindings[9];
        this.llRemoveConfirmation = (LinearLayout) bindings[13];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlConnectedAccounts = (RecyclerView) bindings[3];
        this.rlLoading = (RelativeLayout) bindings[1];
        this.rlRemovePaymentView = (RelativeLayout) bindings[4];
        this.tvCancel = (TextView) bindings[15];
        this.tvConfirmRemove = (TextView) bindings[16];
        this.tvLoading = (TextView) bindings[2];
        this.tvPaymentMethodName = (TextView) bindings[6];
        this.tvPaymetMethodNumber = (TextView) bindings[7];
        this.tvRemove = (TextView) bindings[12];
        this.tvRemoveWarning = (TextView) bindings[14];
        this.tvVerify = (TextView) bindings[10];
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

    public static LayoutConnectedAccountListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutConnectedAccountListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (LayoutConnectedAccountListBinding) DataBindingUtil.inflate(inflater, R.layout.layout_connected_account_list, root, attachToRoot, bindingComponent);
    }

    public static LayoutConnectedAccountListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutConnectedAccountListBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_connected_account_list, null, false), bindingComponent);
    }

    public static LayoutConnectedAccountListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutConnectedAccountListBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/layout_connected_account_list_0".equals(view.getTag())) {
            return new LayoutConnectedAccountListBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
