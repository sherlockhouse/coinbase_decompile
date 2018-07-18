package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.coinbase.android.R;

public class DialogAddPhoneBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btnVerifyPhoneSkip;
    public final Button btnVerifyPhoneVerify;
    public final EditText etVerifyPhone2faInput;
    public final EditText etVerifyPhoneInput;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final Spinner spinnerVerifyPhoneCountry;
    public final TextView tvVerifyPhoneCountryCode;

    static {
        sViewsWithIds.put(R.id.spinnerVerifyPhoneCountry, 1);
        sViewsWithIds.put(R.id.tvVerifyPhoneCountryCode, 2);
        sViewsWithIds.put(R.id.etVerifyPhoneInput, 3);
        sViewsWithIds.put(R.id.etVerifyPhone2faInput, 4);
        sViewsWithIds.put(R.id.btnVerifyPhoneSkip, 5);
        sViewsWithIds.put(R.id.btnVerifyPhoneVerify, 6);
    }

    public DialogAddPhoneBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.btnVerifyPhoneSkip = (Button) bindings[5];
        this.btnVerifyPhoneVerify = (Button) bindings[6];
        this.etVerifyPhone2faInput = (EditText) bindings[4];
        this.etVerifyPhoneInput = (EditText) bindings[3];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.spinnerVerifyPhoneCountry = (Spinner) bindings[1];
        this.tvVerifyPhoneCountryCode = (TextView) bindings[2];
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

    public static DialogAddPhoneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static DialogAddPhoneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (DialogAddPhoneBinding) DataBindingUtil.inflate(inflater, R.layout.dialog_add_phone, root, attachToRoot, bindingComponent);
    }

    public static DialogAddPhoneBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static DialogAddPhoneBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.dialog_add_phone, null, false), bindingComponent);
    }

    public static DialogAddPhoneBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static DialogAddPhoneBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/dialog_add_phone_0".equals(view.getTag())) {
            return new DialogAddPhoneBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
