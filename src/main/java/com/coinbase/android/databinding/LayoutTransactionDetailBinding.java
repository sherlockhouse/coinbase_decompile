package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class LayoutTransactionDetailBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btnAction;
    public final Button btnClose;
    public final ImageView ivAccountIcon;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final RecyclerView rvDetails;
    public final TextView tvCryptoPrice;
    public final TextView tvNativePrice;

    static {
        sViewsWithIds.put(R.id.ivAccountIcon, 1);
        sViewsWithIds.put(R.id.tvCryptoPrice, 2);
        sViewsWithIds.put(R.id.tvNativePrice, 3);
        sViewsWithIds.put(R.id.rvDetails, 4);
        sViewsWithIds.put(R.id.btnClose, 5);
        sViewsWithIds.put(R.id.btnAction, 6);
    }

    public LayoutTransactionDetailBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.btnAction = (Button) bindings[6];
        this.btnClose = (Button) bindings[5];
        this.ivAccountIcon = (ImageView) bindings[1];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rvDetails = (RecyclerView) bindings[4];
        this.tvCryptoPrice = (TextView) bindings[2];
        this.tvNativePrice = (TextView) bindings[3];
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

    public static LayoutTransactionDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutTransactionDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (LayoutTransactionDetailBinding) DataBindingUtil.inflate(inflater, R.layout.layout_transaction_detail, root, attachToRoot, bindingComponent);
    }

    public static LayoutTransactionDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutTransactionDetailBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_transaction_detail, null, false), bindingComponent);
    }

    public static LayoutTransactionDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutTransactionDetailBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/layout_transaction_detail_0".equals(view.getTag())) {
            return new LayoutTransactionDetailBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
