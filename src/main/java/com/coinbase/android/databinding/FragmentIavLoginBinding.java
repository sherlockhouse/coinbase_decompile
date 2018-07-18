package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.R;

public abstract class FragmentIavLoginBinding extends ViewDataBinding {
    public final FragmentIavLoginContentBinding iavLoginContent;

    protected FragmentIavLoginBinding(DataBindingComponent bindingComponent, View root_, int localFieldCount, FragmentIavLoginContentBinding iavLoginContent) {
        super(bindingComponent, root_, localFieldCount);
        this.iavLoginContent = iavLoginContent;
        setContainedBinding(this.iavLoginContent);
    }

    public static FragmentIavLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentIavLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentIavLoginBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentIavLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (FragmentIavLoginBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_iav_login, root, attachToRoot, bindingComponent);
    }

    public static FragmentIavLoginBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return (FragmentIavLoginBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_iav_login, null, false, bindingComponent);
    }

    public static FragmentIavLoginBinding bind(View view, DataBindingComponent bindingComponent) {
        return (FragmentIavLoginBinding) ViewDataBinding.bind(bindingComponent, view, R.layout.fragment_iav_login);
    }
}
