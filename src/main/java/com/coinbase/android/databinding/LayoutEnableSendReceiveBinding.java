package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.coinbase.android.R;

public class LayoutEnableSendReceiveBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView btnClose;
    public final Button btnEnableSendReceive;
    public final ImageView ivEnableSendReceive;
    public final LinearLayout llEnableSendReceiveContainer;
    public final FrameLayout llProgressContainer;
    public final LinearLayout llWhiteBoxContainer;
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;
    public final ProgressBar progressTransparent;
    public final TextView tvMessage;
    public final TextView tvTitle;

    static {
        sViewsWithIds.put(R.id.llWhiteBoxContainer, 1);
        sViewsWithIds.put(R.id.llEnableSendReceiveContainer, 2);
        sViewsWithIds.put(R.id.ivEnableSendReceive, 3);
        sViewsWithIds.put(R.id.tvTitle, 4);
        sViewsWithIds.put(R.id.tvMessage, 5);
        sViewsWithIds.put(R.id.btnEnableSendReceive, 6);
        sViewsWithIds.put(R.id.btnClose, 7);
        sViewsWithIds.put(R.id.llProgressContainer, 8);
        sViewsWithIds.put(R.id.progressTransparent, 9);
    }

    public LayoutEnableSendReceiveBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.btnClose = (TextView) bindings[7];
        this.btnEnableSendReceive = (Button) bindings[6];
        this.ivEnableSendReceive = (ImageView) bindings[3];
        this.llEnableSendReceiveContainer = (LinearLayout) bindings[2];
        this.llProgressContainer = (FrameLayout) bindings[8];
        this.llWhiteBoxContainer = (LinearLayout) bindings[1];
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progressTransparent = (ProgressBar) bindings[9];
        this.tvMessage = (TextView) bindings[5];
        this.tvTitle = (TextView) bindings[4];
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

    public static LayoutEnableSendReceiveBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutEnableSendReceiveBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (LayoutEnableSendReceiveBinding) DataBindingUtil.inflate(inflater, R.layout.layout_enable_send_receive, root, attachToRoot, bindingComponent);
    }

    public static LayoutEnableSendReceiveBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutEnableSendReceiveBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_enable_send_receive, null, false), bindingComponent);
    }

    public static LayoutEnableSendReceiveBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutEnableSendReceiveBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/layout_enable_send_receive_0".equals(view.getTag())) {
            return new LayoutEnableSendReceiveBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
