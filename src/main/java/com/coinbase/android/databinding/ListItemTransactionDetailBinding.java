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

public class ListItemTransactionDetailBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final View statusView;
    public final TextView tvHeader;
    public final TextView tvValue;
    public final View vDottedLine;

    static {
        sViewsWithIds.put(R.id.tvHeader, 1);
        sViewsWithIds.put(R.id.statusView, 2);
        sViewsWithIds.put(R.id.tvValue, 3);
        sViewsWithIds.put(R.id.vDottedLine, 4);
    }

    public ListItemTransactionDetailBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.statusView = (View) bindings[2];
        this.tvHeader = (TextView) bindings[1];
        this.tvValue = (TextView) bindings[3];
        this.vDottedLine = (View) bindings[4];
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

    public static ListItemTransactionDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemTransactionDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemTransactionDetailBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_transaction_detail, root, attachToRoot, bindingComponent);
    }

    public static ListItemTransactionDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemTransactionDetailBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_transaction_detail, null, false), bindingComponent);
    }

    public static ListItemTransactionDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemTransactionDetailBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_transaction_detail_0".equals(view.getTag())) {
            return new ListItemTransactionDetailBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
