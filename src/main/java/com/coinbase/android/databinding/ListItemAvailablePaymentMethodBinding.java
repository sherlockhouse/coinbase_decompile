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

public class ListItemAvailablePaymentMethodBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivPaymentMethodIcon;
    public final ImageView ivRightArrow;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final TextView tvAdditionalFees;
    public final TextView tvBuyingPower;
    public final TextView tvDescription;
    public final TextView tvPaymentMethodName;
    public final TextView tvRecommended;
    public final View vAdditionalFeesDivider;

    static {
        sViewsWithIds.put(R.id.ivPaymentMethodIcon, 1);
        sViewsWithIds.put(R.id.tvPaymentMethodName, 2);
        sViewsWithIds.put(R.id.tvBuyingPower, 3);
        sViewsWithIds.put(R.id.vAdditionalFeesDivider, 4);
        sViewsWithIds.put(R.id.tvAdditionalFees, 5);
        sViewsWithIds.put(R.id.tvDescription, 6);
        sViewsWithIds.put(R.id.tvRecommended, 7);
        sViewsWithIds.put(R.id.ivRightArrow, 8);
    }

    public ListItemAvailablePaymentMethodBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.ivPaymentMethodIcon = (ImageView) bindings[1];
        this.ivRightArrow = (ImageView) bindings[8];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvAdditionalFees = (TextView) bindings[5];
        this.tvBuyingPower = (TextView) bindings[3];
        this.tvDescription = (TextView) bindings[6];
        this.tvPaymentMethodName = (TextView) bindings[2];
        this.tvRecommended = (TextView) bindings[7];
        this.vAdditionalFeesDivider = (View) bindings[4];
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

    public static ListItemAvailablePaymentMethodBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemAvailablePaymentMethodBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemAvailablePaymentMethodBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_available_payment_method, root, attachToRoot, bindingComponent);
    }

    public static ListItemAvailablePaymentMethodBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemAvailablePaymentMethodBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_available_payment_method, null, false), bindingComponent);
    }

    public static ListItemAvailablePaymentMethodBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemAvailablePaymentMethodBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_available_payment_method_0".equals(view.getTag())) {
            return new ListItemAvailablePaymentMethodBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
