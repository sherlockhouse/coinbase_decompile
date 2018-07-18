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

public class ListItemTransactionBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivTransactionProfile;
    private long mDirtyFlags = -1;
    public final RelativeLayout rlTransactionsItem;
    public final View transactionStatusView;
    public final TextView tvTransactionAmount;
    public final TextView tvTransactionStatus;
    public final TextView tvTransactionSummary;
    public final TextView tvTransactionTitle;

    static {
        sViewsWithIds.put(R.id.ivTransactionProfile, 1);
        sViewsWithIds.put(R.id.tvTransactionTitle, 2);
        sViewsWithIds.put(R.id.tvTransactionAmount, 3);
        sViewsWithIds.put(R.id.tvTransactionSummary, 4);
        sViewsWithIds.put(R.id.transactionStatusView, 5);
        sViewsWithIds.put(R.id.tvTransactionStatus, 6);
    }

    public ListItemTransactionBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.ivTransactionProfile = (ImageView) bindings[1];
        this.rlTransactionsItem = (RelativeLayout) bindings[0];
        this.rlTransactionsItem.setTag(null);
        this.transactionStatusView = (View) bindings[5];
        this.tvTransactionAmount = (TextView) bindings[3];
        this.tvTransactionStatus = (TextView) bindings[6];
        this.tvTransactionSummary = (TextView) bindings[4];
        this.tvTransactionTitle = (TextView) bindings[2];
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

    public static ListItemTransactionBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemTransactionBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemTransactionBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_transaction, root, attachToRoot, bindingComponent);
    }

    public static ListItemTransactionBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemTransactionBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_transaction, null, false), bindingComponent);
    }

    public static ListItemTransactionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemTransactionBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_transaction_0".equals(view.getTag())) {
            return new ListItemTransactionBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
