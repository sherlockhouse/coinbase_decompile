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
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerGdprIntroBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView btnNext;
    public final FrameLayout flContainer;
    public final ImageView ivGdprIcon;
    public final FrameLayout llWhiteBoxContainer;
    private long mDirtyFlags = -1;

    static {
        sViewsWithIds.put(R.id.llWhiteBoxContainer, 1);
        sViewsWithIds.put(R.id.ivGdprIcon, 2);
        sViewsWithIds.put(R.id.btnNext, 3);
    }

    public ControllerGdprIntroBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.btnNext = (TextView) bindings[3];
        this.flContainer = (FrameLayout) bindings[0];
        this.flContainer.setTag(null);
        this.ivGdprIcon = (ImageView) bindings[2];
        this.llWhiteBoxContainer = (FrameLayout) bindings[1];
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

    public static ControllerGdprIntroBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerGdprIntroBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerGdprIntroBinding) DataBindingUtil.inflate(inflater, R.layout.controller_gdpr_intro, root, attachToRoot, bindingComponent);
    }

    public static ControllerGdprIntroBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerGdprIntroBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_gdpr_intro, null, false), bindingComponent);
    }

    public static ControllerGdprIntroBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerGdprIntroBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_gdpr_intro_0".equals(view.getTag())) {
            return new ControllerGdprIntroBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
