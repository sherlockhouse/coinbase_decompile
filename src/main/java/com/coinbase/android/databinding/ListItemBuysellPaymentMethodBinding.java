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
import android.widget.TextView;
import com.coinbase.android.R;

public class ListItemBuysellPaymentMethodBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivPaymentMethodIcon;
    private long mDirtyFlags = -1;
    public final LinearLayout rlPaymentMethod;
    public final View selectedView;
    public final TextView tvPaymentMethodName;
    public final TextView tvPaymentMethodNumber;

    static {
        sViewsWithIds.put(R.id.selectedView, 1);
        sViewsWithIds.put(R.id.ivPaymentMethodIcon, 2);
        sViewsWithIds.put(R.id.tvPaymentMethodName, 3);
        sViewsWithIds.put(R.id.tvPaymentMethodNumber, 4);
    }

    public ListItemBuysellPaymentMethodBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.ivPaymentMethodIcon = (ImageView) bindings[2];
        this.rlPaymentMethod = (LinearLayout) bindings[0];
        this.rlPaymentMethod.setTag(null);
        this.selectedView = (View) bindings[1];
        this.tvPaymentMethodName = (TextView) bindings[3];
        this.tvPaymentMethodNumber = (TextView) bindings[4];
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

    public static ListItemBuysellPaymentMethodBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemBuysellPaymentMethodBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemBuysellPaymentMethodBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_buysell_payment_method, root, attachToRoot, bindingComponent);
    }

    public static ListItemBuysellPaymentMethodBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemBuysellPaymentMethodBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_buysell_payment_method, null, false), bindingComponent);
    }

    public static ListItemBuysellPaymentMethodBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemBuysellPaymentMethodBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_buysell_payment_method_0".equals(view.getTag())) {
            return new ListItemBuysellPaymentMethodBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
