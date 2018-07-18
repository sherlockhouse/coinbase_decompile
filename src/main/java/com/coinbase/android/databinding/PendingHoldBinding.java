package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class PendingHoldBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    public final RelativeLayout rlSetting;
    public final TextView tvAmount;
    public final TextView tvAvailableInText;
    public final TextView tvHoldFromDate;

    static {
        sViewsWithIds.put(R.id.tvAvailableInText, 1);
        sViewsWithIds.put(R.id.tvHoldFromDate, 2);
        sViewsWithIds.put(R.id.tvAmount, 3);
    }

    public PendingHoldBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.rlSetting = (RelativeLayout) bindings[0];
        this.rlSetting.setTag(null);
        this.tvAmount = (TextView) bindings[3];
        this.tvAvailableInText = (TextView) bindings[1];
        this.tvHoldFromDate = (TextView) bindings[2];
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

    public static PendingHoldBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static PendingHoldBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (PendingHoldBinding) DataBindingUtil.inflate(inflater, R.layout.pending_hold, root, attachToRoot, bindingComponent);
    }

    public static PendingHoldBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static PendingHoldBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.pending_hold, null, false), bindingComponent);
    }

    public static PendingHoldBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static PendingHoldBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/pending_hold_0".equals(view.getTag())) {
            return new PendingHoldBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
