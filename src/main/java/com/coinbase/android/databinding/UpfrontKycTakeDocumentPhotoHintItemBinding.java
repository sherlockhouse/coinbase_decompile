package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class UpfrontKycTakeDocumentPhotoHintItemBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;
    public final TextView tvHint;

    static {
        sViewsWithIds.put(R.id.tvHint, 1);
    }

    public UpfrontKycTakeDocumentPhotoHintItemBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds);
        this.mboundView0 = (FrameLayout) bindings[0];
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

    public static UpfrontKycTakeDocumentPhotoHintItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static UpfrontKycTakeDocumentPhotoHintItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (UpfrontKycTakeDocumentPhotoHintItemBinding) DataBindingUtil.inflate(inflater, R.layout.upfront_kyc_take_document_photo_hint_item, root, attachToRoot, bindingComponent);
    }

    public static UpfrontKycTakeDocumentPhotoHintItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static UpfrontKycTakeDocumentPhotoHintItemBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.upfront_kyc_take_document_photo_hint_item, null, false), bindingComponent);
    }

    public static UpfrontKycTakeDocumentPhotoHintItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static UpfrontKycTakeDocumentPhotoHintItemBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/upfront_kyc_take_document_photo_hint_item_0".equals(view.getTag())) {
            return new UpfrontKycTakeDocumentPhotoHintItemBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
