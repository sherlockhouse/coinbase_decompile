package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class BottomItemModalBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final FrameLayout flContainer;
    public final ImageView ivIcon;
    public final LinearLayout llItemContainer;
    private long mDirtyFlags = -1;
    public final TextView tvText;

    static {
        sViewsWithIds.put(R.id.llItemContainer, 1);
        sViewsWithIds.put(R.id.ivIcon, 2);
        sViewsWithIds.put(R.id.tvText, 3);
    }

    public BottomItemModalBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.flContainer = (FrameLayout) bindings[0];
        this.flContainer.setTag(null);
        this.ivIcon = (ImageView) bindings[2];
        this.llItemContainer = (LinearLayout) bindings[1];
        this.tvText = (TextView) bindings[3];
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

    public static BottomItemModalBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static BottomItemModalBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (BottomItemModalBinding) DataBindingUtil.inflate(inflater, R.layout.bottom_item_modal, root, attachToRoot, bindingComponent);
    }

    public static BottomItemModalBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static BottomItemModalBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.bottom_item_modal, null, false), bindingComponent);
    }

    public static BottomItemModalBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static BottomItemModalBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/bottom_item_modal_0".equals(view.getTag())) {
            return new BottomItemModalBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
