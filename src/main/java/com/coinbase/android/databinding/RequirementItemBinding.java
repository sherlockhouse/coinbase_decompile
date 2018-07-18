package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class RequirementItemBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivChevron;
    private long mDirtyFlags = -1;
    public final RelativeLayout rlContainer;
    public final TextView tvName;
    public final TextView tvStatus;

    static {
        sViewsWithIds.put(R.id.tvName, 1);
        sViewsWithIds.put(R.id.tvStatus, 2);
        sViewsWithIds.put(R.id.ivChevron, 3);
    }

    public RequirementItemBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.ivChevron = (ImageView) bindings[3];
        this.rlContainer = (RelativeLayout) bindings[0];
        this.rlContainer.setTag(null);
        this.tvName = (TextView) bindings[1];
        this.tvStatus = (TextView) bindings[2];
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

    public static RequirementItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static RequirementItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (RequirementItemBinding) DataBindingUtil.inflate(inflater, R.layout.requirement_item, root, attachToRoot, bindingComponent);
    }

    public static RequirementItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static RequirementItemBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.requirement_item, null, false), bindingComponent);
    }

    public static RequirementItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static RequirementItemBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/requirement_item_0".equals(view.getTag())) {
            return new RequirementItemBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
