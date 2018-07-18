package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerUpfrontKycIdentityDocumentScanBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btnContinue;
    public final TextView btnRetake;
    public final Toolbar cvCoinbaseToolbar;
    public final ImageView ivJumioImagePreview;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final RecyclerView rvHints;
    public final TextView tvNoFaceDetected;
    public final TextView tvVerifyHeader;

    static {
        sViewsWithIds.put(R.id.ivJumioImagePreview, 1);
        sViewsWithIds.put(R.id.tvVerifyHeader, 2);
        sViewsWithIds.put(R.id.tvNoFaceDetected, 3);
        sViewsWithIds.put(R.id.rvHints, 4);
        sViewsWithIds.put(R.id.btnContinue, 5);
        sViewsWithIds.put(R.id.btnRetake, 6);
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 7);
    }

    public ControllerUpfrontKycIdentityDocumentScanBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.btnContinue = (Button) bindings[5];
        this.btnRetake = (TextView) bindings[6];
        this.cvCoinbaseToolbar = (Toolbar) bindings[7];
        this.ivJumioImagePreview = (ImageView) bindings[1];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rvHints = (RecyclerView) bindings[4];
        this.tvNoFaceDetected = (TextView) bindings[3];
        this.tvVerifyHeader = (TextView) bindings[2];
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

    public static ControllerUpfrontKycIdentityDocumentScanBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycIdentityDocumentScanBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerUpfrontKycIdentityDocumentScanBinding) DataBindingUtil.inflate(inflater, R.layout.controller_upfront_kyc_identity_document_scan, root, attachToRoot, bindingComponent);
    }

    public static ControllerUpfrontKycIdentityDocumentScanBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycIdentityDocumentScanBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_upfront_kyc_identity_document_scan, null, false), bindingComponent);
    }

    public static ControllerUpfrontKycIdentityDocumentScanBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycIdentityDocumentScanBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_upfront_kyc_identity_document_scan_0".equals(view.getTag())) {
            return new ControllerUpfrontKycIdentityDocumentScanBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
