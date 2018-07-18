package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.transactions.TransactionDetailLayout;
import com.coinbase.android.ui.EmptySupportRecyclerView;

public class LayoutTransactionsListBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final RelativeLayout rlEmpty;
    public final EmptySupportRecyclerView rvTransactions;
    public final TransactionDetailLayout transactionDetailLayout;
    public final TextView tvEmptyMessage;
    public final TextView tvEmptyTitle;
    public final SwipeRefreshLayout vTransactionRefreshLayout;

    static {
        sViewsWithIds.put(R.id.rlEmpty, 1);
        sViewsWithIds.put(R.id.tvEmptyTitle, 2);
        sViewsWithIds.put(R.id.tvEmptyMessage, 3);
        sViewsWithIds.put(R.id.vTransactionRefreshLayout, 4);
        sViewsWithIds.put(R.id.rvTransactions, 5);
        sViewsWithIds.put(R.id.transactionDetailLayout, 6);
    }

    public LayoutTransactionsListBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlEmpty = (RelativeLayout) bindings[1];
        this.rvTransactions = (EmptySupportRecyclerView) bindings[5];
        this.transactionDetailLayout = (TransactionDetailLayout) bindings[6];
        this.tvEmptyMessage = (TextView) bindings[3];
        this.tvEmptyTitle = (TextView) bindings[2];
        this.vTransactionRefreshLayout = (SwipeRefreshLayout) bindings[4];
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

    public static LayoutTransactionsListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutTransactionsListBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (LayoutTransactionsListBinding) DataBindingUtil.inflate(inflater, R.layout.layout_transactions_list, root, attachToRoot, bindingComponent);
    }

    public static LayoutTransactionsListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutTransactionsListBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_transactions_list, null, false), bindingComponent);
    }

    public static LayoutTransactionsListBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutTransactionsListBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/layout_transactions_list_0".equals(view.getTag())) {
            return new LayoutTransactionsListBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
