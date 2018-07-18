package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.coinbase.android.R;

public abstract class ControllerAcceptableDocumentsBinding extends ViewDataBinding {
    public final ControllerAcceptableDocumentsContentBinding acceptableDocumentsContent;
    public final Toolbar cvCoinbaseToolbar;
    public final ProgressBar progress;

    protected ControllerAcceptableDocumentsBinding(DataBindingComponent bindingComponent, View root_, int localFieldCount, ControllerAcceptableDocumentsContentBinding acceptableDocumentsContent, Toolbar cvCoinbaseToolbar, ProgressBar progress) {
        super(bindingComponent, root_, localFieldCount);
        this.acceptableDocumentsContent = acceptableDocumentsContent;
        setContainedBinding(this.acceptableDocumentsContent);
        this.cvCoinbaseToolbar = cvCoinbaseToolbar;
        this.progress = progress;
    }

    public static ControllerAcceptableDocumentsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerAcceptableDocumentsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerAcceptableDocumentsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerAcceptableDocumentsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerAcceptableDocumentsBinding) DataBindingUtil.inflate(inflater, R.layout.controller_acceptable_documents, root, attachToRoot, bindingComponent);
    }

    public static ControllerAcceptableDocumentsBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return (ControllerAcceptableDocumentsBinding) DataBindingUtil.inflate(inflater, R.layout.controller_acceptable_documents, null, false, bindingComponent);
    }

    public static ControllerAcceptableDocumentsBinding bind(View view, DataBindingComponent bindingComponent) {
        return (ControllerAcceptableDocumentsBinding) ViewDataBinding.bind(bindingComponent, view, R.layout.controller_acceptable_documents);
    }
}
