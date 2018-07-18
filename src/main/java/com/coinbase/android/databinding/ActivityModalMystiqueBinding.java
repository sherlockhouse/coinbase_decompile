package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.coinbase.android.R;

public class ActivityModalMystiqueBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Toolbar cvCoinbaseToolbar;
    public final FrameLayout flContainer;
    public final FrameLayout flDropShadow;
    public final RelativeLayout flOverlay;
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;
    public final ProgressBar progress;

    static {
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 1);
        sViewsWithIds.put(R.id.flDropShadow, 2);
        sViewsWithIds.put(R.id.flContainer, 3);
        sViewsWithIds.put(R.id.progress, 4);
        sViewsWithIds.put(R.id.flOverlay, 5);
    }

    public ActivityModalMystiqueBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.cvCoinbaseToolbar = (Toolbar) bindings[1];
        this.flContainer = (FrameLayout) bindings[3];
        this.flDropShadow = (FrameLayout) bindings[2];
        this.flOverlay = (RelativeLayout) bindings[5];
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progress = (ProgressBar) bindings[4];
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

    public static ActivityModalMystiqueBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityModalMystiqueBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ActivityModalMystiqueBinding) DataBindingUtil.inflate(inflater, R.layout.activity_modal_mystique, root, attachToRoot, bindingComponent);
    }

    public static ActivityModalMystiqueBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityModalMystiqueBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_modal_mystique, null, false), bindingComponent);
    }

    public static ActivityModalMystiqueBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityModalMystiqueBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/activity_modal_mystique_0".equals(view.getTag())) {
            return new ActivityModalMystiqueBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
