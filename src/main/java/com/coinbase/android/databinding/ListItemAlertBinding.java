package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.CardView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.coinbase.android.R;

public class ListItemAlertBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView btnDismiss;
    public final TextView btnLearnMore;
    public final CardView cvAlert;
    private long mDirtyFlags = -1;
    public final TextView tvDescription;

    static {
        sViewsWithIds.put(R.id.tvDescription, 1);
        sViewsWithIds.put(R.id.btnLearnMore, 2);
        sViewsWithIds.put(R.id.btnDismiss, 3);
    }

    public ListItemAlertBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.btnDismiss = (TextView) bindings[3];
        this.btnLearnMore = (TextView) bindings[2];
        this.cvAlert = (CardView) bindings[0];
        this.cvAlert.setTag(null);
        this.tvDescription = (TextView) bindings[1];
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

    public static ListItemAlertBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemAlertBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemAlertBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_alert, root, attachToRoot, bindingComponent);
    }

    public static ListItemAlertBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemAlertBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_alert, null, false), bindingComponent);
    }

    public static ListItemAlertBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemAlertBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_alert_0".equals(view.getTag())) {
            return new ListItemAlertBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
