package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.AppCompatCheckBox;
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

public class ActivitySignupBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView btnSignupSubmit;
    public final AppCompatCheckBox checkboxCertifyAgreeAcknowledge;
    public final EditText etSignupEmailField;
    public final EditText etSignupFirstNameField;
    public final EditText etSignupLastNameField;
    public final EditText etSignupPasswordField;
    public final LinearLayout llSignupForm;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final MaterialProgressBar progressDeviceConfirmation;
    public final TextView tvCertifyAgreeAcknowledge;
    public final TextView tvDeviceConfirmation;

    static {
        sViewsWithIds.put(R.id.llSignupForm, 1);
        sViewsWithIds.put(R.id.etSignupFirstNameField, 2);
        sViewsWithIds.put(R.id.etSignupLastNameField, 3);
        sViewsWithIds.put(R.id.etSignupEmailField, 4);
        sViewsWithIds.put(R.id.etSignupPasswordField, 5);
        sViewsWithIds.put(R.id.checkboxCertifyAgreeAcknowledge, 6);
        sViewsWithIds.put(R.id.tvCertifyAgreeAcknowledge, 7);
        sViewsWithIds.put(R.id.btnSignupSubmit, 8);
        sViewsWithIds.put(R.id.progressDeviceConfirmation, 9);
        sViewsWithIds.put(R.id.tvDeviceConfirmation, 10);
    }

    public ActivitySignupBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.btnSignupSubmit = (TextView) bindings[8];
        this.checkboxCertifyAgreeAcknowledge = (AppCompatCheckBox) bindings[6];
        this.etSignupEmailField = (EditText) bindings[4];
        this.etSignupFirstNameField = (EditText) bindings[2];
        this.etSignupLastNameField = (EditText) bindings[3];
        this.etSignupPasswordField = (EditText) bindings[5];
        this.llSignupForm = (LinearLayout) bindings[1];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progressDeviceConfirmation = (MaterialProgressBar) bindings[9];
        this.tvCertifyAgreeAcknowledge = (TextView) bindings[7];
        this.tvDeviceConfirmation = (TextView) bindings[10];
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

    public static ActivitySignupBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ActivitySignupBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ActivitySignupBinding) DataBindingUtil.inflate(inflater, R.layout.activity_signup, root, attachToRoot, bindingComponent);
    }

    public static ActivitySignupBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ActivitySignupBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_signup, null, false), bindingComponent);
    }

    public static ActivitySignupBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivitySignupBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/activity_signup_0".equals(view.getTag())) {
            return new ActivitySignupBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
