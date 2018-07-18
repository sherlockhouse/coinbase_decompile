package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerUpfrontKycIdentityFailedBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView btnTryAgain;
    public final ImageView ivDocument;
    public final LinearLayout llIdentityFailedContainer;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final TextView tvHeader;
    public final TextView tvHint;

    static {
        sViewsWithIds.put(R.id.llIdentityFailedContainer, 1);
        sViewsWithIds.put(R.id.ivDocument, 2);
        sViewsWithIds.put(R.id.tvHeader, 3);
        sViewsWithIds.put(R.id.tvHint, 4);
        sViewsWithIds.put(R.id.btnTryAgain, 5);
    }

    public ControllerUpfrontKycIdentityFailedBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.btnTryAgain = (TextView) bindings[5];
        this.ivDocument = (ImageView) bindings[2];
        this.llIdentityFailedContainer = (LinearLayout) bindings[1];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvHeader = (TextView) bindings[3];
        this.tvHint = (TextView) bindings[4];
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

    public static ControllerUpfrontKycIdentityFailedBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycIdentityFailedBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerUpfrontKycIdentityFailedBinding) DataBindingUtil.inflate(inflater, R.layout.controller_upfront_kyc_identity_failed, root, attachToRoot, bindingComponent);
    }

    public static ControllerUpfrontKycIdentityFailedBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycIdentityFailedBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_upfront_kyc_identity_failed, null, false), bindingComponent);
    }

    public static ControllerUpfrontKycIdentityFailedBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycIdentityFailedBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_upfront_kyc_identity_failed_0".equals(view.getTag())) {
            return new ControllerUpfrontKycIdentityFailedBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
