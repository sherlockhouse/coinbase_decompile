package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.ui.MaterialProgressBar;

public class ControllerTwoFactorBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView btnLogin2faVerify;
    public final EditText etLogin2faField;
    public final FrameLayout flPhoneLogo;
    public final ImageView ivEmailSpinnerBackground;
    public final LinearLayout llTwoFactorContainer;
    private long mDirtyFlags = -1;
    public final MaterialProgressBar progress;
    public final RelativeLayout rlContainer;
    public final TextView tvLogin2faType;

    static {
        sViewsWithIds.put(R.id.progress, 1);
        sViewsWithIds.put(R.id.llTwoFactorContainer, 2);
        sViewsWithIds.put(R.id.flPhoneLogo, 3);
        sViewsWithIds.put(R.id.ivEmailSpinnerBackground, 4);
        sViewsWithIds.put(R.id.etLogin2faField, 5);
        sViewsWithIds.put(R.id.btnLogin2faVerify, 6);
        sViewsWithIds.put(R.id.tvLogin2faType, 7);
    }

    public ControllerTwoFactorBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.btnLogin2faVerify = (TextView) bindings[6];
        this.etLogin2faField = (EditText) bindings[5];
        this.flPhoneLogo = (FrameLayout) bindings[3];
        this.ivEmailSpinnerBackground = (ImageView) bindings[4];
        this.llTwoFactorContainer = (LinearLayout) bindings[2];
        this.progress = (MaterialProgressBar) bindings[1];
        this.rlContainer = (RelativeLayout) bindings[0];
        this.rlContainer.setTag(null);
        this.tvLogin2faType = (TextView) bindings[7];
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

    public static ControllerTwoFactorBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerTwoFactorBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerTwoFactorBinding) DataBindingUtil.inflate(inflater, R.layout.controller_two_factor, root, attachToRoot, bindingComponent);
    }

    public static ControllerTwoFactorBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerTwoFactorBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_two_factor, null, false), bindingComponent);
    }

    public static ControllerTwoFactorBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerTwoFactorBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_two_factor_0".equals(view.getTag())) {
            return new ControllerTwoFactorBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
