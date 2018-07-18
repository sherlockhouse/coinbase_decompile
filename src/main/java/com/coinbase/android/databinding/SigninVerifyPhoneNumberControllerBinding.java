package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.ui.MaterialProgressBar;

public class SigninVerifyPhoneNumberControllerBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView btnBack;
    public final TextView btnResendToken;
    public final EditText etInput2fa;
    public final FrameLayout flPhoneLogoContainer;
    public final ImageView ivEmailSpinnerBackground;
    public final LinearLayout llVerifyPhoneContainer;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final MaterialProgressBar progress;
    public final ScrollView svVerifyPhoneNumberContainer;
    public final TextView tvVerifyText;

    static {
        sViewsWithIds.put(R.id.progress, 1);
        sViewsWithIds.put(R.id.svVerifyPhoneNumberContainer, 2);
        sViewsWithIds.put(R.id.llVerifyPhoneContainer, 3);
        sViewsWithIds.put(R.id.flPhoneLogoContainer, 4);
        sViewsWithIds.put(R.id.ivEmailSpinnerBackground, 5);
        sViewsWithIds.put(R.id.tvVerifyText, 6);
        sViewsWithIds.put(R.id.etInput2fa, 7);
        sViewsWithIds.put(R.id.btnBack, 8);
        sViewsWithIds.put(R.id.btnResendToken, 9);
    }

    public SigninVerifyPhoneNumberControllerBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.btnBack = (TextView) bindings[8];
        this.btnResendToken = (TextView) bindings[9];
        this.etInput2fa = (EditText) bindings[7];
        this.flPhoneLogoContainer = (FrameLayout) bindings[4];
        this.ivEmailSpinnerBackground = (ImageView) bindings[5];
        this.llVerifyPhoneContainer = (LinearLayout) bindings[3];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progress = (MaterialProgressBar) bindings[1];
        this.svVerifyPhoneNumberContainer = (ScrollView) bindings[2];
        this.tvVerifyText = (TextView) bindings[6];
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

    public static SigninVerifyPhoneNumberControllerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static SigninVerifyPhoneNumberControllerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (SigninVerifyPhoneNumberControllerBinding) DataBindingUtil.inflate(inflater, R.layout.signin_verify_phone_number_controller, root, attachToRoot, bindingComponent);
    }

    public static SigninVerifyPhoneNumberControllerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static SigninVerifyPhoneNumberControllerBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.signin_verify_phone_number_controller, null, false), bindingComponent);
    }

    public static SigninVerifyPhoneNumberControllerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static SigninVerifyPhoneNumberControllerBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/signin_verify_phone_number_controller_0".equals(view.getTag())) {
            return new SigninVerifyPhoneNumberControllerBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
