package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.R;

public abstract class FragmentPlaidAccountLoginBinding extends ViewDataBinding {
    public final FragmentPlaidAccountLoginContentBinding plaidAccountLoginContent;

    protected FragmentPlaidAccountLoginBinding(DataBindingComponent bindingComponent, View root_, int localFieldCount, FragmentPlaidAccountLoginContentBinding plaidAccountLoginContent) {
        super(bindingComponent, root_, localFieldCount);
        this.plaidAccountLoginContent = plaidAccountLoginContent;
        setContainedBinding(this.plaidAccountLoginContent);
    }

    public static FragmentPlaidAccountLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentPlaidAccountLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentPlaidAccountLoginBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentPlaidAccountLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (FragmentPlaidAccountLoginBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_plaid_account_login, root, attachToRoot, bindingComponent);
    }

    public static FragmentPlaidAccountLoginBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return (FragmentPlaidAccountLoginBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_plaid_account_login, null, false, bindingComponent);
    }

    public static FragmentPlaidAccountLoginBinding bind(View view, DataBindingComponent bindingComponent) {
        return (FragmentPlaidAccountLoginBinding) ViewDataBinding.bind(bindingComponent, view, R.layout.fragment_plaid_account_login);
    }
}
