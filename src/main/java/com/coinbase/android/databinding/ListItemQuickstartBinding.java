package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ListItemQuickstartBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageButton ibtnQuickstartItemDismiss;
    public final ImageView ivQuickstartItemIcon;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final TextView tvQuickstartItemTitle;

    static {
        sViewsWithIds.put(R.id.ivQuickstartItemIcon, 1);
        sViewsWithIds.put(R.id.tvQuickstartItemTitle, 2);
        sViewsWithIds.put(R.id.ibtnQuickstartItemDismiss, 3);
    }

    public ListItemQuickstartBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.ibtnQuickstartItemDismiss = (ImageButton) bindings[3];
        this.ivQuickstartItemIcon = (ImageView) bindings[1];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvQuickstartItemTitle = (TextView) bindings[2];
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

    public static ListItemQuickstartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemQuickstartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemQuickstartBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_quickstart, root, attachToRoot, bindingComponent);
    }

    public static ListItemQuickstartBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemQuickstartBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_quickstart, null, false), bindingComponent);
    }

    public static ListItemQuickstartBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemQuickstartBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_quickstart_0".equals(view.getTag())) {
            return new ListItemQuickstartBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
