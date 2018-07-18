package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class DialogLaunchMessageBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btnUpdate;
    public final ImageView ivImage;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final TextView tvDescription;
    public final TextView tvTitle;

    static {
        sViewsWithIds.put(R.id.ivImage, 1);
        sViewsWithIds.put(R.id.tvTitle, 2);
        sViewsWithIds.put(R.id.tvDescription, 3);
        sViewsWithIds.put(R.id.btnUpdate, 4);
    }

    public DialogLaunchMessageBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.btnUpdate = (Button) bindings[4];
        this.ivImage = (ImageView) bindings[1];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvDescription = (TextView) bindings[3];
        this.tvTitle = (TextView) bindings[2];
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

    public static DialogLaunchMessageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static DialogLaunchMessageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (DialogLaunchMessageBinding) DataBindingUtil.inflate(inflater, R.layout.dialog_launch_message, root, attachToRoot, bindingComponent);
    }

    public static DialogLaunchMessageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static DialogLaunchMessageBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.dialog_launch_message, null, false), bindingComponent);
    }

    public static DialogLaunchMessageBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static DialogLaunchMessageBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/dialog_launch_message_0".equals(view.getTag())) {
            return new DialogLaunchMessageBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
