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
import android.widget.TextView;
import com.coinbase.android.R;

public class ListItemCreatePriceAlertBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final FrameLayout flBottomFirst;
    public final FrameLayout flBottomFourth;
    public final FrameLayout flBottomSecond;
    public final FrameLayout flBottomThird;
    public final FrameLayout flTopFirst;
    public final FrameLayout flTopFourth;
    public final FrameLayout flTopSecond;
    public final FrameLayout flTopThird;
    private long mDirtyFlags = -1;
    public final RelativeLayout rlParent;
    public final TextView tvPriceLabel;

    static {
        sViewsWithIds.put(R.id.tvPriceLabel, 1);
        sViewsWithIds.put(R.id.flTopFirst, 2);
        sViewsWithIds.put(R.id.flTopSecond, 3);
        sViewsWithIds.put(R.id.flTopThird, 4);
        sViewsWithIds.put(R.id.flTopFourth, 5);
        sViewsWithIds.put(R.id.flBottomFirst, 6);
        sViewsWithIds.put(R.id.flBottomSecond, 7);
        sViewsWithIds.put(R.id.flBottomThird, 8);
        sViewsWithIds.put(R.id.flBottomFourth, 9);
    }

    public ListItemCreatePriceAlertBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.flBottomFirst = (FrameLayout) bindings[6];
        this.flBottomFourth = (FrameLayout) bindings[9];
        this.flBottomSecond = (FrameLayout) bindings[7];
        this.flBottomThird = (FrameLayout) bindings[8];
        this.flTopFirst = (FrameLayout) bindings[2];
        this.flTopFourth = (FrameLayout) bindings[5];
        this.flTopSecond = (FrameLayout) bindings[3];
        this.flTopThird = (FrameLayout) bindings[4];
        this.rlParent = (RelativeLayout) bindings[0];
        this.rlParent.setTag(null);
        this.tvPriceLabel = (TextView) bindings[1];
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

    public static ListItemCreatePriceAlertBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemCreatePriceAlertBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemCreatePriceAlertBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_create_price_alert, root, attachToRoot, bindingComponent);
    }

    public static ListItemCreatePriceAlertBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemCreatePriceAlertBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_create_price_alert, null, false), bindingComponent);
    }

    public static ListItemCreatePriceAlertBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemCreatePriceAlertBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_create_price_alert_0".equals(view.getTag())) {
            return new ListItemCreatePriceAlertBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
