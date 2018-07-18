package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coinbase.android.R;
import com.coinbase.android.buysell.D3SView;

public class ActivityCardBuyVerifyBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags = -1;
    public final D3SView webview;

    public ActivityCardBuyVerifyBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        this.webview = (D3SView) ViewDataBinding.mapBindings(bindingComponent, root, 1, sIncludes, sViewsWithIds)[0];
        this.webview.setTag(null);
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

    public static ActivityCardBuyVerifyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityCardBuyVerifyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ActivityCardBuyVerifyBinding) DataBindingUtil.inflate(inflater, R.layout.activity_card_buy_verify, root, attachToRoot, bindingComponent);
    }

    public static ActivityCardBuyVerifyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityCardBuyVerifyBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_card_buy_verify, null, false), bindingComponent);
    }

    public static ActivityCardBuyVerifyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityCardBuyVerifyBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/activity_card_buy_verify_0".equals(view.getTag())) {
            return new ActivityCardBuyVerifyBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
