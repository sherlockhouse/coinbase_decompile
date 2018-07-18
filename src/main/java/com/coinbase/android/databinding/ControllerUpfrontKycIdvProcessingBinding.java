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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.ui.MaterialProgressBar;

public class ControllerUpfrontKycIdvProcessingBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView allDone;
    public final FrameLayout flIdvLogoContainer;
    public final ImageView ivIdvSpinnerBackground;
    public final TextView jumioSuccessMessage;
    public final LinearLayout llIdvContainer;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final MaterialProgressBar progress;

    static {
        sViewsWithIds.put(R.id.progress, 1);
        sViewsWithIds.put(R.id.llIdvContainer, 2);
        sViewsWithIds.put(R.id.flIdvLogoContainer, 3);
        sViewsWithIds.put(R.id.ivIdvSpinnerBackground, 4);
        sViewsWithIds.put(R.id.all_done, 5);
        sViewsWithIds.put(R.id.jumio_success_message, 6);
    }

    public ControllerUpfrontKycIdvProcessingBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.allDone = (TextView) bindings[5];
        this.flIdvLogoContainer = (FrameLayout) bindings[3];
        this.ivIdvSpinnerBackground = (ImageView) bindings[4];
        this.jumioSuccessMessage = (TextView) bindings[6];
        this.llIdvContainer = (LinearLayout) bindings[2];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progress = (MaterialProgressBar) bindings[1];
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

    public static ControllerUpfrontKycIdvProcessingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycIdvProcessingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerUpfrontKycIdvProcessingBinding) DataBindingUtil.inflate(inflater, R.layout.controller_upfront_kyc_idv_processing, root, attachToRoot, bindingComponent);
    }

    public static ControllerUpfrontKycIdvProcessingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycIdvProcessingBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_upfront_kyc_idv_processing, null, false), bindingComponent);
    }

    public static ControllerUpfrontKycIdvProcessingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerUpfrontKycIdvProcessingBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_upfront_kyc_idv_processing_0".equals(view.getTag())) {
            return new ControllerUpfrontKycIdvProcessingBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
