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
import com.coinbase.android.R;

public class DialogVerifyPhoneBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final EditText etInput2fa;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.etInput2fa, 1);
    }

    public DialogVerifyPhoneBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds);
        this.etInput2fa = (EditText) bindings[1];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
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

    public static DialogVerifyPhoneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static DialogVerifyPhoneBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (DialogVerifyPhoneBinding) DataBindingUtil.inflate(inflater, R.layout.dialog_verify_phone, root, attachToRoot, bindingComponent);
    }

    public static DialogVerifyPhoneBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static DialogVerifyPhoneBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.dialog_verify_phone, null, false), bindingComponent);
    }

    public static DialogVerifyPhoneBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static DialogVerifyPhoneBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/dialog_verify_phone_0".equals(view.getTag())) {
            return new DialogVerifyPhoneBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
