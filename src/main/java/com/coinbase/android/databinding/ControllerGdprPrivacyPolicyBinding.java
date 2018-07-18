package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerGdprPrivacyPolicyBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView btnAcknowledge;
    public final View dividerAboveAcknowledge;
    public final View dividerAboveScrollAcknowledge;
    public final FrameLayout flContainer;
    public final ImageView ivGdprIcon;
    public final ConstraintLayout llWhiteBoxContainer;
    private long mDirtyFlags = -1;
    public final ProgressBar progressBar;
    public final ScrollView svPrivacyPolicyText;
    public final TextView tvNoticeParagraph;
    public final TextView tvScrollToAcknowledge;

    static {
        sViewsWithIds.put(R.id.llWhiteBoxContainer, 1);
        sViewsWithIds.put(R.id.svPrivacyPolicyText, 2);
        sViewsWithIds.put(R.id.ivGdprIcon, 3);
        sViewsWithIds.put(R.id.tvNoticeParagraph, 4);
        sViewsWithIds.put(R.id.dividerAboveScrollAcknowledge, 5);
        sViewsWithIds.put(R.id.tvScrollToAcknowledge, 6);
        sViewsWithIds.put(R.id.dividerAboveAcknowledge, 7);
        sViewsWithIds.put(R.id.btnAcknowledge, 8);
        sViewsWithIds.put(R.id.progressBar, 9);
    }

    public ControllerGdprPrivacyPolicyBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.btnAcknowledge = (TextView) bindings[8];
        this.dividerAboveAcknowledge = (View) bindings[7];
        this.dividerAboveScrollAcknowledge = (View) bindings[5];
        this.flContainer = (FrameLayout) bindings[0];
        this.flContainer.setTag(null);
        this.ivGdprIcon = (ImageView) bindings[3];
        this.llWhiteBoxContainer = (ConstraintLayout) bindings[1];
        this.progressBar = (ProgressBar) bindings[9];
        this.svPrivacyPolicyText = (ScrollView) bindings[2];
        this.tvNoticeParagraph = (TextView) bindings[4];
        this.tvScrollToAcknowledge = (TextView) bindings[6];
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

    public static ControllerGdprPrivacyPolicyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerGdprPrivacyPolicyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerGdprPrivacyPolicyBinding) DataBindingUtil.inflate(inflater, R.layout.controller_gdpr_privacy_policy, root, attachToRoot, bindingComponent);
    }

    public static ControllerGdprPrivacyPolicyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerGdprPrivacyPolicyBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_gdpr_privacy_policy, null, false), bindingComponent);
    }

    public static ControllerGdprPrivacyPolicyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerGdprPrivacyPolicyBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_gdpr_privacy_policy_0".equals(view.getTag())) {
            return new ControllerGdprPrivacyPolicyBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
