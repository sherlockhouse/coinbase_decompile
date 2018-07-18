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
import com.coinbase.android.ui.MaterialProgressBar;

public class ControllerEmailVerifyBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivEmailSpinnerBackground;
    public final LinearLayout llEmailContainer;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final MaterialProgressBar progress;
    public final TextView tvSignupConfirmEmail;
    public final TextView tvSignupIncorrectEmail;
    public final TextView tvVerifyEmailAddress;

    static {
        sViewsWithIds.put(R.id.progress, 1);
        sViewsWithIds.put(R.id.llEmailContainer, 2);
        sViewsWithIds.put(R.id.ivEmailSpinnerBackground, 3);
        sViewsWithIds.put(R.id.tvVerifyEmailAddress, 4);
        sViewsWithIds.put(R.id.tvSignupConfirmEmail, 5);
        sViewsWithIds.put(R.id.tvSignupIncorrectEmail, 6);
    }

    public ControllerEmailVerifyBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.ivEmailSpinnerBackground = (ImageView) bindings[3];
        this.llEmailContainer = (LinearLayout) bindings[2];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progress = (MaterialProgressBar) bindings[1];
        this.tvSignupConfirmEmail = (TextView) bindings[5];
        this.tvSignupIncorrectEmail = (TextView) bindings[6];
        this.tvVerifyEmailAddress = (TextView) bindings[4];
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

    public static ControllerEmailVerifyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerEmailVerifyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerEmailVerifyBinding) DataBindingUtil.inflate(inflater, R.layout.controller_email_verify, root, attachToRoot, bindingComponent);
    }

    public static ControllerEmailVerifyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerEmailVerifyBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_email_verify, null, false), bindingComponent);
    }

    public static ControllerEmailVerifyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerEmailVerifyBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_email_verify_0".equals(view.getTag())) {
            return new ControllerEmailVerifyBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
