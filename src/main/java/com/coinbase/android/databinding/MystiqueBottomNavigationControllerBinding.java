package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.design.widget.CoordinatorLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.coinbase.android.R;
import com.coinbase.android.ui.MystiqueBottomNavigationLayout;
import com.coinbase.android.ui.NoSwipeViewPager;

public class MystiqueBottomNavigationControllerBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final CoordinatorLayout clControllerContainer;
    public final MystiqueBottomNavigationLayout cvBottom;
    public final FrameLayout flModalContainer;
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;
    public final NoSwipeViewPager vpControllerPager;

    static {
        sViewsWithIds.put(R.id.clControllerContainer, 1);
        sViewsWithIds.put(R.id.vpControllerPager, 2);
        sViewsWithIds.put(R.id.cvBottom, 3);
        sViewsWithIds.put(R.id.flModalContainer, 4);
    }

    public MystiqueBottomNavigationControllerBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.clControllerContainer = (CoordinatorLayout) bindings[1];
        this.cvBottom = (MystiqueBottomNavigationLayout) bindings[3];
        this.flModalContainer = (FrameLayout) bindings[4];
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.vpControllerPager = (NoSwipeViewPager) bindings[2];
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

    public static MystiqueBottomNavigationControllerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static MystiqueBottomNavigationControllerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (MystiqueBottomNavigationControllerBinding) DataBindingUtil.inflate(inflater, R.layout.mystique_bottom_navigation_controller, root, attachToRoot, bindingComponent);
    }

    public static MystiqueBottomNavigationControllerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static MystiqueBottomNavigationControllerBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.mystique_bottom_navigation_controller, null, false), bindingComponent);
    }

    public static MystiqueBottomNavigationControllerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static MystiqueBottomNavigationControllerBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/mystique_bottom_navigation_controller_0".equals(view.getTag())) {
            return new MystiqueBottomNavigationControllerBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
