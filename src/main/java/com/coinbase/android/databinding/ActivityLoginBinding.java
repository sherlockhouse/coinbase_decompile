package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.ui.MaterialProgressBar;

public class ActivityLoginBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView btnLoginSubmit;
    public final EditText etLoginEmailField;
    public final EditText etLoginPasswordField;
    public final LinearLayout llLoginForm;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final MaterialProgressBar progressDeviceConfirmation;
    public final TextView tvLoggingIn;
    public final TextView tvLoginForgotPassword;
    public final TextView tvLoginPrivacyPolicy;

    static {
        sViewsWithIds.put(R.id.llLoginForm, 1);
        sViewsWithIds.put(R.id.etLoginEmailField, 2);
        sViewsWithIds.put(R.id.etLoginPasswordField, 3);
        sViewsWithIds.put(R.id.btnLoginSubmit, 4);
        sViewsWithIds.put(R.id.tvLoginForgotPassword, 5);
        sViewsWithIds.put(R.id.tvLoginPrivacyPolicy, 6);
        sViewsWithIds.put(R.id.progressDeviceConfirmation, 7);
        sViewsWithIds.put(R.id.tvLoggingIn, 8);
    }

    public ActivityLoginBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.btnLoginSubmit = (TextView) bindings[4];
        this.etLoginEmailField = (EditText) bindings[2];
        this.etLoginPasswordField = (EditText) bindings[3];
        this.llLoginForm = (LinearLayout) bindings[1];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progressDeviceConfirmation = (MaterialProgressBar) bindings[7];
        this.tvLoggingIn = (TextView) bindings[8];
        this.tvLoginForgotPassword = (TextView) bindings[5];
        this.tvLoginPrivacyPolicy = (TextView) bindings[6];
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

    public static ActivityLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ActivityLoginBinding) DataBindingUtil.inflate(inflater, R.layout.activity_login, root, attachToRoot, bindingComponent);
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_login, null, false), bindingComponent);
    }

    public static ActivityLoginBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityLoginBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/activity_login_0".equals(view.getTag())) {
            return new ActivityLoginBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
