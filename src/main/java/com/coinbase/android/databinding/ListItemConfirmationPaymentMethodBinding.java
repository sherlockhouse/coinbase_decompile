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

public class ListItemConfirmationPaymentMethodBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivArrow;
    public final ImageView ivPaymentMethodIcon;
    public final LinearLayout llValue;
    private long mDirtyFlags = -1;
    public final RelativeLayout rlPaymentMethod;
    public final TextView tvHeader;
    public final TextView tvValue;

    static {
        sViewsWithIds.put(R.id.tvHeader, 1);
        sViewsWithIds.put(R.id.llValue, 2);
        sViewsWithIds.put(R.id.ivPaymentMethodIcon, 3);
        sViewsWithIds.put(R.id.tvValue, 4);
        sViewsWithIds.put(R.id.ivArrow, 5);
    }

    public ListItemConfirmationPaymentMethodBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.ivArrow = (ImageView) bindings[5];
        this.ivPaymentMethodIcon = (ImageView) bindings[3];
        this.llValue = (LinearLayout) bindings[2];
        this.rlPaymentMethod = (RelativeLayout) bindings[0];
        this.rlPaymentMethod.setTag(null);
        this.tvHeader = (TextView) bindings[1];
        this.tvValue = (TextView) bindings[4];
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

    public static ListItemConfirmationPaymentMethodBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemConfirmationPaymentMethodBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemConfirmationPaymentMethodBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_confirmation_payment_method, root, attachToRoot, bindingComponent);
    }

    public static ListItemConfirmationPaymentMethodBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemConfirmationPaymentMethodBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_confirmation_payment_method, null, false), bindingComponent);
    }

    public static ListItemConfirmationPaymentMethodBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemConfirmationPaymentMethodBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_confirmation_payment_method_0".equals(view.getTag())) {
            return new ListItemConfirmationPaymentMethodBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
