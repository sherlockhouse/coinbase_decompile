package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class UpfrontKycVerifyDocumentPhotoHintItemBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final TextView tvHint;

    static {
        sViewsWithIds.put(R.id.tvHint, 1);
    }

    public UpfrontKycVerifyDocumentPhotoHintItemBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvHint = (TextView) bindings[1];
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

    public static UpfrontKycVerifyDocumentPhotoHintItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static UpfrontKycVerifyDocumentPhotoHintItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (UpfrontKycVerifyDocumentPhotoHintItemBinding) DataBindingUtil.inflate(inflater, R.layout.upfront_kyc_verify_document_photo_hint_item, root, attachToRoot, bindingComponent);
    }

    public static UpfrontKycVerifyDocumentPhotoHintItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static UpfrontKycVerifyDocumentPhotoHintItemBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.upfront_kyc_verify_document_photo_hint_item, null, false), bindingComponent);
    }

    public static UpfrontKycVerifyDocumentPhotoHintItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static UpfrontKycVerifyDocumentPhotoHintItemBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/upfront_kyc_verify_document_photo_hint_item_0".equals(view.getTag())) {
            return new UpfrontKycVerifyDocumentPhotoHintItemBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
