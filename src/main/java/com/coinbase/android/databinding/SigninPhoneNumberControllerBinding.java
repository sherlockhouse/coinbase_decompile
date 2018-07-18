package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.ui.MaterialProgressBar;

public class SigninPhoneNumberControllerBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView btnSkip;
    public final TextView btnVerifyPhoneVerify;
    public final EditText etVerifyPhoneInput;
    public final ImageView ivPhoneLogo;
    public final LinearLayout llAddPhoneContainer;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final MaterialProgressBar progress;
    public final Spinner spinnerVerifyPhoneCountry;
    public final TextView tvVerifyPhoneCountryCode;
    public final View vButtonDivider;

    static {
        sViewsWithIds.put(R.id.progress, 1);
        sViewsWithIds.put(R.id.llAddPhoneContainer, 2);
        sViewsWithIds.put(R.id.ivPhoneLogo, 3);
        sViewsWithIds.put(R.id.spinnerVerifyPhoneCountry, 4);
        sViewsWithIds.put(R.id.tvVerifyPhoneCountryCode, 5);
        sViewsWithIds.put(R.id.etVerifyPhoneInput, 6);
        sViewsWithIds.put(R.id.btnSkip, 7);
        sViewsWithIds.put(R.id.vButtonDivider, 8);
        sViewsWithIds.put(R.id.btnVerifyPhoneVerify, 9);
    }

    public SigninPhoneNumberControllerBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.btnSkip = (TextView) bindings[7];
        this.btnVerifyPhoneVerify = (TextView) bindings[9];
        this.etVerifyPhoneInput = (EditText) bindings[6];
        this.ivPhoneLogo = (ImageView) bindings[3];
        this.llAddPhoneContainer = (LinearLayout) bindings[2];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progress = (MaterialProgressBar) bindings[1];
        this.spinnerVerifyPhoneCountry = (Spinner) bindings[4];
        this.tvVerifyPhoneCountryCode = (TextView) bindings[5];
        this.vButtonDivider = (View) bindings[8];
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

    public static SigninPhoneNumberControllerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static SigninPhoneNumberControllerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (SigninPhoneNumberControllerBinding) DataBindingUtil.inflate(inflater, R.layout.signin_phone_number_controller, root, attachToRoot, bindingComponent);
    }

    public static SigninPhoneNumberControllerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static SigninPhoneNumberControllerBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.signin_phone_number_controller, null, false), bindingComponent);
    }

    public static SigninPhoneNumberControllerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static SigninPhoneNumberControllerBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/signin_phone_number_controller_0".equals(view.getTag())) {
            return new SigninPhoneNumberControllerBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
