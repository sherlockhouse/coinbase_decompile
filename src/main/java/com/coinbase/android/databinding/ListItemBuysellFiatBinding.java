package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ListItemBuysellFiatBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivFiatBackground;
    private long mDirtyFlags = -1;
    public final RelativeLayout rlFiatIcon;
    public final LinearLayout rlPaymentMethod;
    public final View selectedView;
    public final TextView tvAccountBalance;
    public final TextView tvAccountName;
    public final TextView tvFiatSymbol;

    static {
        sViewsWithIds.put(R.id.selectedView, 1);
        sViewsWithIds.put(R.id.rlFiatIcon, 2);
        sViewsWithIds.put(R.id.ivFiatBackground, 3);
        sViewsWithIds.put(R.id.tvFiatSymbol, 4);
        sViewsWithIds.put(R.id.tvAccountName, 5);
        sViewsWithIds.put(R.id.tvAccountBalance, 6);
    }

    public ListItemBuysellFiatBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.ivFiatBackground = (ImageView) bindings[3];
        this.rlFiatIcon = (RelativeLayout) bindings[2];
        this.rlPaymentMethod = (LinearLayout) bindings[0];
        this.rlPaymentMethod.setTag(null);
        this.selectedView = (View) bindings[1];
        this.tvAccountBalance = (TextView) bindings[6];
        this.tvAccountName = (TextView) bindings[5];
        this.tvFiatSymbol = (TextView) bindings[4];
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

    public static ListItemBuysellFiatBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemBuysellFiatBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemBuysellFiatBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_buysell_fiat, root, attachToRoot, bindingComponent);
    }

    public static ListItemBuysellFiatBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemBuysellFiatBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_buysell_fiat, null, false), bindingComponent);
    }

    public static ListItemBuysellFiatBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemBuysellFiatBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_buysell_fiat_0".equals(view.getTag())) {
            return new ListItemBuysellFiatBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
