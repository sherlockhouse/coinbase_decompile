package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class TiersItemBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivDividerBottom;
    public final ImageView ivTierImage;
    public final LinearLayout llRequirementsContainer;
    public final LinearLayout llTierProgressContainerTop;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final RecyclerView rvRequirements;
    public final TextView tvDescription;
    public final TextView tvName;
    public final TextView tvStatus;
    public final View vBottomDivider;
    public final View vBottomView;
    public final ImageView vConnectingVerticalLineTop;
    public final View vTopDividerPlaceholder;

    static {
        sViewsWithIds.put(R.id.tvStatus, 1);
        sViewsWithIds.put(R.id.ivDividerBottom, 2);
        sViewsWithIds.put(R.id.llTierProgressContainerTop, 3);
        sViewsWithIds.put(R.id.vConnectingVerticalLineTop, 4);
        sViewsWithIds.put(R.id.ivTierImage, 5);
        sViewsWithIds.put(R.id.vTopDividerPlaceholder, 6);
        sViewsWithIds.put(R.id.tvName, 7);
        sViewsWithIds.put(R.id.tvDescription, 8);
        sViewsWithIds.put(R.id.llRequirementsContainer, 9);
        sViewsWithIds.put(R.id.rvRequirements, 10);
        sViewsWithIds.put(R.id.vBottomDivider, 11);
        sViewsWithIds.put(R.id.vBottomView, 12);
    }

    public TiersItemBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds);
        this.ivDividerBottom = (ImageView) bindings[2];
        this.ivTierImage = (ImageView) bindings[5];
        this.llRequirementsContainer = (LinearLayout) bindings[9];
        this.llTierProgressContainerTop = (LinearLayout) bindings[3];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rvRequirements = (RecyclerView) bindings[10];
        this.tvDescription = (TextView) bindings[8];
        this.tvName = (TextView) bindings[7];
        this.tvStatus = (TextView) bindings[1];
        this.vBottomDivider = (View) bindings[11];
        this.vBottomView = (View) bindings[12];
        this.vConnectingVerticalLineTop = (ImageView) bindings[4];
        this.vTopDividerPlaceholder = (View) bindings[6];
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

    public static TiersItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static TiersItemBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (TiersItemBinding) DataBindingUtil.inflate(inflater, R.layout.tiers_item, root, attachToRoot, bindingComponent);
    }

    public static TiersItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static TiersItemBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.tiers_item, null, false), bindingComponent);
    }

    public static TiersItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static TiersItemBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/tiers_item_0".equals(view.getTag())) {
            return new TiersItemBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
