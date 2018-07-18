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

public class ListItemPaymentMethodBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivDivider;
    public final ImageView ivPaymentIcon;
    public final LinearLayout llPaymentMethodNameContainer;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final TextView tvPaymentMethodName;
    public final TextView tvPaymentMethodNameSuffix;
    public final TextView tvPaymentMethodRemove;
    public final TextView tvPaymentMethodVerify;

    static {
        sViewsWithIds.put(R.id.ivPaymentIcon, 1);
        sViewsWithIds.put(R.id.llPaymentMethodNameContainer, 2);
        sViewsWithIds.put(R.id.tvPaymentMethodName, 3);
        sViewsWithIds.put(R.id.tvPaymentMethodNameSuffix, 4);
        sViewsWithIds.put(R.id.tvPaymentMethodVerify, 5);
        sViewsWithIds.put(R.id.tvPaymentMethodRemove, 6);
        sViewsWithIds.put(R.id.ivDivider, 7);
    }

    public ListItemPaymentMethodBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.ivDivider = (ImageView) bindings[7];
        this.ivPaymentIcon = (ImageView) bindings[1];
        this.llPaymentMethodNameContainer = (LinearLayout) bindings[2];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvPaymentMethodName = (TextView) bindings[3];
        this.tvPaymentMethodNameSuffix = (TextView) bindings[4];
        this.tvPaymentMethodRemove = (TextView) bindings[6];
        this.tvPaymentMethodVerify = (TextView) bindings[5];
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

    public static ListItemPaymentMethodBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemPaymentMethodBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemPaymentMethodBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_payment_method, root, attachToRoot, bindingComponent);
    }

    public static ListItemPaymentMethodBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemPaymentMethodBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_payment_method, null, false), bindingComponent);
    }

    public static ListItemPaymentMethodBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemPaymentMethodBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_payment_method_0".equals(view.getTag())) {
            return new ListItemPaymentMethodBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
