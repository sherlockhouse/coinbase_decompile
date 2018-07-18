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
import android.widget.Switch;
import android.widget.TextView;
import com.coinbase.android.R;

public class ListItemPriceAlertBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ImageView ivNotificationIcon;
    private long mDirtyFlags = -1;
    public final RelativeLayout rlPriceAlert;
    public final Switch switchNotification;
    public final TextView tvNotification;
    public final TextView tvNotificationTime;

    static {
        sViewsWithIds.put(R.id.ivNotificationIcon, 1);
        sViewsWithIds.put(R.id.tvNotification, 2);
        sViewsWithIds.put(R.id.tvNotificationTime, 3);
        sViewsWithIds.put(R.id.switchNotification, 4);
    }

    public ListItemPriceAlertBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.ivNotificationIcon = (ImageView) bindings[1];
        this.rlPriceAlert = (RelativeLayout) bindings[0];
        this.rlPriceAlert.setTag(null);
        this.switchNotification = (Switch) bindings[4];
        this.tvNotification = (TextView) bindings[2];
        this.tvNotificationTime = (TextView) bindings[3];
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

    public static ListItemPriceAlertBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemPriceAlertBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemPriceAlertBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_price_alert, root, attachToRoot, bindingComponent);
    }

    public static ListItemPriceAlertBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemPriceAlertBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_price_alert, null, false), bindingComponent);
    }

    public static ListItemPriceAlertBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemPriceAlertBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_price_alert_0".equals(view.getTag())) {
            return new ListItemPriceAlertBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
