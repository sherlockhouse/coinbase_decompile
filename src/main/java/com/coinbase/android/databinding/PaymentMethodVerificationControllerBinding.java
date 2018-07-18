package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class PaymentMethodVerificationControllerBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Toolbar cvCoinbaseToolbar;
    public final ImageView ivIdIcon;
    public final ImageView ivPaymentIcon;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final RelativeLayout relativeLayout4;
    public final RelativeLayout rlId;
    public final RelativeLayout rlPayment;
    public final TextView textView2;
    public final TextView textView3;
    public final TextView tvPaymentTitle;
    public final TextView tvVerificationDescription;
    public final TextView tvVerificationTitle;

    static {
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 1);
        sViewsWithIds.put(R.id.textView2, 2);
        sViewsWithIds.put(R.id.textView3, 3);
        sViewsWithIds.put(R.id.relativeLayout4, 4);
        sViewsWithIds.put(R.id.ivIdIcon, 5);
        sViewsWithIds.put(R.id.rlId, 6);
        sViewsWithIds.put(R.id.tvVerificationTitle, 7);
        sViewsWithIds.put(R.id.tvVerificationDescription, 8);
        sViewsWithIds.put(R.id.ivPaymentIcon, 9);
        sViewsWithIds.put(R.id.rlPayment, 10);
        sViewsWithIds.put(R.id.tvPaymentTitle, 11);
    }

    public PaymentMethodVerificationControllerBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds);
        this.cvCoinbaseToolbar = (Toolbar) bindings[1];
        this.ivIdIcon = (ImageView) bindings[5];
        this.ivPaymentIcon = (ImageView) bindings[9];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.relativeLayout4 = (RelativeLayout) bindings[4];
        this.rlId = (RelativeLayout) bindings[6];
        this.rlPayment = (RelativeLayout) bindings[10];
        this.textView2 = (TextView) bindings[2];
        this.textView3 = (TextView) bindings[3];
        this.tvPaymentTitle = (TextView) bindings[11];
        this.tvVerificationDescription = (TextView) bindings[8];
        this.tvVerificationTitle = (TextView) bindings[7];
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

    public static PaymentMethodVerificationControllerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static PaymentMethodVerificationControllerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (PaymentMethodVerificationControllerBinding) DataBindingUtil.inflate(inflater, R.layout.payment_method_verification_controller, root, attachToRoot, bindingComponent);
    }

    public static PaymentMethodVerificationControllerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static PaymentMethodVerificationControllerBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.payment_method_verification_controller, null, false), bindingComponent);
    }

    public static PaymentMethodVerificationControllerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static PaymentMethodVerificationControllerBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/payment_method_verification_controller_0".equals(view.getTag())) {
            return new PaymentMethodVerificationControllerBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
