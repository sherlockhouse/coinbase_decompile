package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.coinbase.android.R;

public class ControllerUpfrontKycIdentityAcceptableDocumentsBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = new IncludedLayouts(4);
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ControllerUpfrontKycAcceptableDocumentsContentBinding acceptableDocumentsContent;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final FrameLayout nsvIdentityFormContainer;
    public final RelativeLayout progress;

    static {
        sIncludes.setIncludes(1, new String[]{"controller_upfront_kyc_acceptable_documents_content"}, new int[]{2}, new int[]{R.layout.controller_upfront_kyc_acceptable_documents_content});
        sViewsWithIds.put(R.id.progress, 3);
    }

    public ControllerUpfrontKycIdentityAcceptableDocumentsBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.acceptableDocumentsContent = (ControllerUpfrontKycAcceptableDocumentsContentBinding) bindings[2];
        setContainedBinding(this.acceptableDocumentsContent);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.nsvIdentityFormContainer = (FrameLayout) bindings[1];
        this.nsvIdentityFormContainer.setTag(null);
        this.progress = (RelativeLayout) bindings[3];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        this.acceptableDocumentsContent.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
        }
    }

    public boolean setVariable(int variableId, Object variable) {
        return false;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeAcceptableDocumentsContent((ControllerUpfrontKycAcceptableDocumentsContentBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeAcceptableDocumentsContent(ControllerUpfrontKycAcceptableDocumentsContentBinding AcceptableDocumentsContent, int fieldId) {
        switch (fieldId) {
            case 0:
                synchronized (this) {
                    this.mDirtyFlags |= 1;
                }
                return true;
            default:
                return false;
        }
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ViewDataBinding.executeBindingsOn(this.acceptableDocumentsContent);
    }

    public static ControllerUpfrontKycIdentityAcceptableDocumentsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycIdentityAcceptableDocumentsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerUpfrontKycIdentityAcceptableDocumentsBinding) DataBindingUtil.inflate(inflater, R.layout.controller_upfront_kyc_identity_acceptable_documents, root, attachToRoot, bindingComponent);
    }

    public static ControllerUpfrontKycIdentityAcceptableDocumentsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycIdentityAcceptableDocumentsBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_upfront_kyc_identity_acceptable_documents, null, false), bindingComponent);
    }

    public static ControllerUpfrontKycIdentityAcceptableDocumentsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycIdentityAcceptableDocumentsBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_upfront_kyc_identity_acceptable_documents_0".equals(view.getTag())) {
            return new ControllerUpfrontKycIdentityAcceptableDocumentsBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
