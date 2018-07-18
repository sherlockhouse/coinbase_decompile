package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.coinbase.android.R;

public class UpfrontKycAcceptableDocItemBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivIcon;
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;
    public final TextView tvText;

    static {
        sViewsWithIds.put(R.id.ivIcon, 1);
        sViewsWithIds.put(R.id.tvText, 2);
    }

    public UpfrontKycAcceptableDocItemBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.ivIcon = (ImageView) bindings[1];
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvText = (TextView) bindings[2];
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

    public static UpfrontKycAcceptableDocItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static UpfrontKycAcceptableDocItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (UpfrontKycAcceptableDocItemBinding) DataBindingUtil.inflate(inflater, R.layout.upfront_kyc_acceptable_doc_item, root, attachToRoot, bindingComponent);
    }

    public static UpfrontKycAcceptableDocItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static UpfrontKycAcceptableDocItemBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.upfront_kyc_acceptable_doc_item, null, false), bindingComponent);
    }

    public static UpfrontKycAcceptableDocItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static UpfrontKycAcceptableDocItemBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/upfront_kyc_acceptable_doc_item_0".equals(view.getTag())) {
            return new UpfrontKycAcceptableDocItemBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
