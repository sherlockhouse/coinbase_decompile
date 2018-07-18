package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.R;

public abstract class AddPlaidAccountControllerBinding extends ViewDataBinding {
    public final AddPlaidAccountControllerContentBinding addPlaidToAccountContent;
    public final Toolbar cvCoinbaseToolbar;

    protected AddPlaidAccountControllerBinding(DataBindingComponent bindingComponent, View root_, int localFieldCount, AddPlaidAccountControllerContentBinding addPlaidToAccountContent, Toolbar cvCoinbaseToolbar) {
        super(bindingComponent, root_, localFieldCount);
        this.addPlaidToAccountContent = addPlaidToAccountContent;
        setContainedBinding(this.addPlaidToAccountContent);
        this.cvCoinbaseToolbar = cvCoinbaseToolbar;
    }

    public static AddPlaidAccountControllerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static AddPlaidAccountControllerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static AddPlaidAccountControllerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static AddPlaidAccountControllerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (AddPlaidAccountControllerBinding) DataBindingUtil.inflate(inflater, R.layout.add_plaid_account_controller, root, attachToRoot, bindingComponent);
    }

    public static AddPlaidAccountControllerBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return (AddPlaidAccountControllerBinding) DataBindingUtil.inflate(inflater, R.layout.add_plaid_account_controller, null, false, bindingComponent);
    }

    public static AddPlaidAccountControllerBinding bind(View view, DataBindingComponent bindingComponent) {
        return (AddPlaidAccountControllerBinding) ViewDataBinding.bind(bindingComponent, view, R.layout.add_plaid_account_controller);
    }
}
