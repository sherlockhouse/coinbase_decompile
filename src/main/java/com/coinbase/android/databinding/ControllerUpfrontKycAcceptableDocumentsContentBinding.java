package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerUpfrontKycAcceptableDocumentsContentBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView empty;
    public final ListView lvList;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.lvList, 1);
        sViewsWithIds.put(16908292, 2);
    }

    public ControllerUpfrontKycAcceptableDocumentsContentBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.empty = (TextView) bindings[2];
        this.lvList = (ListView) bindings[1];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
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

    public static ControllerUpfrontKycAcceptableDocumentsContentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycAcceptableDocumentsContentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerUpfrontKycAcceptableDocumentsContentBinding) DataBindingUtil.inflate(inflater, R.layout.controller_upfront_kyc_acceptable_documents_content, root, attachToRoot, bindingComponent);
    }

    public static ControllerUpfrontKycAcceptableDocumentsContentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycAcceptableDocumentsContentBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_upfront_kyc_acceptable_documents_content, null, false), bindingComponent);
    }

    public static ControllerUpfrontKycAcceptableDocumentsContentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycAcceptableDocumentsContentBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_upfront_kyc_acceptable_documents_content_0".equals(view.getTag())) {
            return new ControllerUpfrontKycAcceptableDocumentsContentBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
