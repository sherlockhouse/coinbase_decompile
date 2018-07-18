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

public class DialogConfirmationBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;
    public final TextView tvCancel;
    public final TextView tvOk;
    public final TextView tvText;
    public final TextView tvTitle;

    static {
        sViewsWithIds.put(R.id.tvTitle, 1);
        sViewsWithIds.put(R.id.tvText, 2);
        sViewsWithIds.put(R.id.tvCancel, 3);
        sViewsWithIds.put(R.id.tvOk, 4);
    }

    public DialogConfirmationBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvCancel = (TextView) bindings[3];
        this.tvOk = (TextView) bindings[4];
        this.tvText = (TextView) bindings[2];
        this.tvTitle = (TextView) bindings[1];
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

    public static DialogConfirmationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static DialogConfirmationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (DialogConfirmationBinding) DataBindingUtil.inflate(inflater, R.layout.dialog_confirmation, root, attachToRoot, bindingComponent);
    }

    public static DialogConfirmationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static DialogConfirmationBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.dialog_confirmation, null, false), bindingComponent);
    }

    public static DialogConfirmationBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static DialogConfirmationBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/dialog_confirmation_0".equals(view.getTag())) {
            return new DialogConfirmationBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
