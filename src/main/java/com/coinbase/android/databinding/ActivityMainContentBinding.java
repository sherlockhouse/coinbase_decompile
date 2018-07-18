package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout;
import com.coinbase.android.R;

public class ActivityMainContentBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ChangeHandlerFrameLayout cvControllerContainer;
    public final FrameLayout flModalContainer;
    public final RelativeLayout flOverlay;
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;

    static {
        sViewsWithIds.put(R.id.cvControllerContainer, 1);
        sViewsWithIds.put(R.id.flOverlay, 2);
        sViewsWithIds.put(R.id.flModalContainer, 3);
    }

    public ActivityMainContentBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.cvControllerContainer = (ChangeHandlerFrameLayout) bindings[1];
        this.flModalContainer = (FrameLayout) bindings[3];
        this.flOverlay = (RelativeLayout) bindings[2];
        this.mboundView0 = (FrameLayout) bindings[0];
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

    public static ActivityMainContentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityMainContentBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ActivityMainContentBinding) DataBindingUtil.inflate(inflater, R.layout.activity_main_content, root, attachToRoot, bindingComponent);
    }

    public static ActivityMainContentBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityMainContentBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_main_content, null, false), bindingComponent);
    }

    public static ActivityMainContentBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityMainContentBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/activity_main_content_0".equals(view.getTag())) {
            return new ActivityMainContentBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
