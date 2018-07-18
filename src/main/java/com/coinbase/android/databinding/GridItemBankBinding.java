package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.CardView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.coinbase.android.R;

public class GridItemBankBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final CardView cvBankContainer;
    public final ImageView ivBankLogo;
    private long mDirtyFlags = -1;
    public final TextView tvBankName;

    static {
        sViewsWithIds.put(R.id.ivBankLogo, 1);
        sViewsWithIds.put(R.id.tvBankName, 2);
    }

    public GridItemBankBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.cvBankContainer = (CardView) bindings[0];
        this.cvBankContainer.setTag(null);
        this.ivBankLogo = (ImageView) bindings[1];
        this.tvBankName = (TextView) bindings[2];
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

    public static GridItemBankBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static GridItemBankBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (GridItemBankBinding) DataBindingUtil.inflate(inflater, R.layout.grid_item_bank, root, attachToRoot, bindingComponent);
    }

    public static GridItemBankBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static GridItemBankBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.grid_item_bank, null, false), bindingComponent);
    }

    public static GridItemBankBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static GridItemBankBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/grid_item_bank_0".equals(view.getTag())) {
            return new GridItemBankBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
