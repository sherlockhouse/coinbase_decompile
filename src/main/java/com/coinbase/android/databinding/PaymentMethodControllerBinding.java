package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.coinbase.android.R;

public class PaymentMethodControllerBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final CoordinatorLayout clContainer;
    public final Toolbar cvCoinbaseToolbar;
    public final FrameLayout flCoinbaseOverlay;
    public final LinearLayout llRemovePaymentMethodConfirmation;
    public final LinearLayout lvAvailablePaymentMethods;
    public final RecyclerView lvList;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final ProgressBar progress;
    public final RecyclerView rvAvailablePaymentMethods;
    public final TextView tvRemovePaymentMethodConfirmation;
    public final TextView tvRemovePaymentMethodConfirmationCancel;
    public final TextView tvRemovePaymentMethodDescriptionConfirmation;
    public final TextView tvRemovePaymentMethodNameConfirmation;
    public final TextView tvRemovePaymentMethodNameConfirmationSuffix;

    static {
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 1);
        sViewsWithIds.put(R.id.clContainer, 2);
        sViewsWithIds.put(R.id.lvList, 3);
        sViewsWithIds.put(R.id.lvAvailablePaymentMethods, 4);
        sViewsWithIds.put(R.id.rvAvailablePaymentMethods, 5);
        sViewsWithIds.put(R.id.progress, 6);
        sViewsWithIds.put(R.id.flCoinbaseOverlay, 7);
        sViewsWithIds.put(R.id.llRemovePaymentMethodConfirmation, 8);
        sViewsWithIds.put(R.id.tvRemovePaymentMethodNameConfirmation, 9);
        sViewsWithIds.put(R.id.tvRemovePaymentMethodNameConfirmationSuffix, 10);
        sViewsWithIds.put(R.id.tvRemovePaymentMethodDescriptionConfirmation, 11);
        sViewsWithIds.put(R.id.tvRemovePaymentMethodConfirmation, 12);
        sViewsWithIds.put(R.id.tvRemovePaymentMethodConfirmationCancel, 13);
    }

    public PaymentMethodControllerBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds);
        this.clContainer = (CoordinatorLayout) bindings[2];
        this.cvCoinbaseToolbar = (Toolbar) bindings[1];
        this.flCoinbaseOverlay = (FrameLayout) bindings[7];
        this.llRemovePaymentMethodConfirmation = (LinearLayout) bindings[8];
        this.lvAvailablePaymentMethods = (LinearLayout) bindings[4];
        this.lvList = (RecyclerView) bindings[3];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progress = (ProgressBar) bindings[6];
        this.rvAvailablePaymentMethods = (RecyclerView) bindings[5];
        this.tvRemovePaymentMethodConfirmation = (TextView) bindings[12];
        this.tvRemovePaymentMethodConfirmationCancel = (TextView) bindings[13];
        this.tvRemovePaymentMethodDescriptionConfirmation = (TextView) bindings[11];
        this.tvRemovePaymentMethodNameConfirmation = (TextView) bindings[9];
        this.tvRemovePaymentMethodNameConfirmationSuffix = (TextView) bindings[10];
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

    public static PaymentMethodControllerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static PaymentMethodControllerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (PaymentMethodControllerBinding) DataBindingUtil.inflate(inflater, R.layout.payment_method_controller, root, attachToRoot, bindingComponent);
    }

    public static PaymentMethodControllerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static PaymentMethodControllerBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.payment_method_controller, null, false), bindingComponent);
    }

    public static PaymentMethodControllerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static PaymentMethodControllerBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/payment_method_controller_0".equals(view.getTag())) {
            return new PaymentMethodControllerBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
