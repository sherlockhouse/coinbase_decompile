package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.R;

public abstract class ControllerIdentityDocumentScanBinding extends ViewDataBinding {
    public final Toolbar cvCoinbaseToolbar;
    public final ControllerJumioDocumentScanContentBinding jumioDocumentScanContent;

    protected ControllerIdentityDocumentScanBinding(DataBindingComponent bindingComponent, View root_, int localFieldCount, Toolbar cvCoinbaseToolbar, ControllerJumioDocumentScanContentBinding jumioDocumentScanContent) {
        super(bindingComponent, root_, localFieldCount);
        this.cvCoinbaseToolbar = cvCoinbaseToolbar;
        this.jumioDocumentScanContent = jumioDocumentScanContent;
        setContainedBinding(this.jumioDocumentScanContent);
    }

    public static ControllerIdentityDocumentScanBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerIdentityDocumentScanBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerIdentityDocumentScanBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerIdentityDocumentScanBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerIdentityDocumentScanBinding) DataBindingUtil.inflate(inflater, R.layout.controller_identity_document_scan, root, attachToRoot, bindingComponent);
    }

    public static ControllerIdentityDocumentScanBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return (ControllerIdentityDocumentScanBinding) DataBindingUtil.inflate(inflater, R.layout.controller_identity_document_scan, null, false, bindingComponent);
    }

    public static ControllerIdentityDocumentScanBinding bind(View view, DataBindingComponent bindingComponent) {
        return (ControllerIdentityDocumentScanBinding) ViewDataBinding.bind(bindingComponent, view, R.layout.controller_identity_document_scan);
    }
}
