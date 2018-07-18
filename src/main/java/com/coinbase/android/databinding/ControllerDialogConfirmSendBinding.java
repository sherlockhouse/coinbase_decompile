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
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerDialogConfirmSendBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivConfirmTransferProfile;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final TextView tvConfirm;
    public final TextView tvConfirmTransferText;
    public final TextView tvDismiss;

    static {
        sViewsWithIds.put(R.id.ivConfirmTransferProfile, 1);
        sViewsWithIds.put(R.id.tvConfirmTransferText, 2);
        sViewsWithIds.put(R.id.tvDismiss, 3);
        sViewsWithIds.put(R.id.tvConfirm, 4);
    }

    public ControllerDialogConfirmSendBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.ivConfirmTransferProfile = (ImageView) bindings[1];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvConfirm = (TextView) bindings[4];
        this.tvConfirmTransferText = (TextView) bindings[2];
        this.tvDismiss = (TextView) bindings[3];
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

    public static ControllerDialogConfirmSendBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerDialogConfirmSendBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerDialogConfirmSendBinding) DataBindingUtil.inflate(inflater, R.layout.controller_dialog_confirm_send, root, attachToRoot, bindingComponent);
    }

    public static ControllerDialogConfirmSendBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerDialogConfirmSendBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_dialog_confirm_send, null, false), bindingComponent);
    }

    public static ControllerDialogConfirmSendBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerDialogConfirmSendBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_dialog_confirm_send_0".equals(view.getTag())) {
            return new ControllerDialogConfirmSendBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
