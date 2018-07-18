package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerStateLockedBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final View bottomLineView;
    public final Button btnContactSupport;
    public final Button btnGoBack;
    public final Toolbar cvCoinbaseToolbar;
    public final ImageView imageView4;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final View middleLineView;
    public final RelativeLayout stateSelectorContainer;
    public final TextView textView6;
    public final View topLineView;
    public final TextView tvWyomingLocked;
    public final TextView wyomingSupportText;

    static {
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 1);
        sViewsWithIds.put(R.id.stateSelectorContainer, 2);
        sViewsWithIds.put(R.id.imageView4, 3);
        sViewsWithIds.put(R.id.textView6, 4);
        sViewsWithIds.put(R.id.topLineView, 5);
        sViewsWithIds.put(R.id.tvWyomingLocked, 6);
        sViewsWithIds.put(R.id.wyoming_support_text, 7);
        sViewsWithIds.put(R.id.middleLineView, 8);
        sViewsWithIds.put(R.id.btnContactSupport, 9);
        sViewsWithIds.put(R.id.bottomLineView, 10);
        sViewsWithIds.put(R.id.btnGoBack, 11);
    }

    public ControllerStateLockedBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds);
        this.bottomLineView = (View) bindings[10];
        this.btnContactSupport = (Button) bindings[9];
        this.btnGoBack = (Button) bindings[11];
        this.cvCoinbaseToolbar = (Toolbar) bindings[1];
        this.imageView4 = (ImageView) bindings[3];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.middleLineView = (View) bindings[8];
        this.stateSelectorContainer = (RelativeLayout) bindings[2];
        this.textView6 = (TextView) bindings[4];
        this.topLineView = (View) bindings[5];
        this.tvWyomingLocked = (TextView) bindings[6];
        this.wyomingSupportText = (TextView) bindings[7];
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

    public static ControllerStateLockedBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerStateLockedBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerStateLockedBinding) DataBindingUtil.inflate(inflater, R.layout.controller_state_locked, root, attachToRoot, bindingComponent);
    }

    public static ControllerStateLockedBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerStateLockedBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_state_locked, null, false), bindingComponent);
    }

    public static ControllerStateLockedBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerStateLockedBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_state_locked_0".equals(view.getTag())) {
            return new ControllerStateLockedBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
