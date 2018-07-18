package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.R;

public abstract class ControllerIdentityVerificationBinding extends ViewDataBinding {
    public final Toolbar cvCoinbaseToolbar;
    public final ControllerIdentityVerificationContentBinding identityVerificationContent;

    protected ControllerIdentityVerificationBinding(DataBindingComponent bindingComponent, View root_, int localFieldCount, Toolbar cvCoinbaseToolbar, ControllerIdentityVerificationContentBinding identityVerificationContent) {
        super(bindingComponent, root_, localFieldCount);
        this.cvCoinbaseToolbar = cvCoinbaseToolbar;
        this.identityVerificationContent = identityVerificationContent;
        setContainedBinding(this.identityVerificationContent);
    }

    public static ControllerIdentityVerificationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerIdentityVerificationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerIdentityVerificationBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerIdentityVerificationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerIdentityVerificationBinding) DataBindingUtil.inflate(inflater, R.layout.controller_identity_verification, root, attachToRoot, bindingComponent);
    }

    public static ControllerIdentityVerificationBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return (ControllerIdentityVerificationBinding) DataBindingUtil.inflate(inflater, R.layout.controller_identity_verification, null, false, bindingComponent);
    }

    public static ControllerIdentityVerificationBinding bind(View view, DataBindingComponent bindingComponent) {
        return (ControllerIdentityVerificationBinding) ViewDataBinding.bind(bindingComponent, view, R.layout.controller_identity_verification);
    }
}
