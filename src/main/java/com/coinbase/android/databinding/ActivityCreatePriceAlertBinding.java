package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.ui.MaterialProgressBar;

public class ActivityCreatePriceAlertBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btnAbove;
    public final Button btnBelow;
    public final Button btnCreateNotification;
    public final RelativeLayout createAlertLayout;
    public final LinearLayout currencyLayout;
    public final ImageButton ibtnCanel;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final RelativeLayout priceLayout;
    public final MaterialProgressBar progressBar;
    public final MaterialProgressBar progressCreatePriceAlert;
    public final RelativeLayout relativeLayout3;
    public final RelativeLayout rlScrollLayout;
    public final RecyclerView rvPrice;
    public final TextView tvCent;
    public final TextView tvCurrency;
    public final TextView tvDollar;
    public final View vLeftDivider;
    public final View vRightDivider;

    static {
        sViewsWithIds.put(R.id.relativeLayout3, 1);
        sViewsWithIds.put(R.id.ibtnCanel, 2);
        sViewsWithIds.put(R.id.price_layout, 3);
        sViewsWithIds.put(R.id.vLeftDivider, 4);
        sViewsWithIds.put(R.id.vRightDivider, 5);
        sViewsWithIds.put(R.id.currency_layout, 6);
        sViewsWithIds.put(R.id.tvCurrency, 7);
        sViewsWithIds.put(R.id.tvDollar, 8);
        sViewsWithIds.put(R.id.tvCent, 9);
        sViewsWithIds.put(R.id.btnBelow, 10);
        sViewsWithIds.put(R.id.btnAbove, 11);
        sViewsWithIds.put(R.id.rlScrollLayout, 12);
        sViewsWithIds.put(R.id.rvPrice, 13);
        sViewsWithIds.put(R.id.progressBar, 14);
        sViewsWithIds.put(R.id.create_alert_layout, 15);
        sViewsWithIds.put(R.id.btnCreateNotification, 16);
        sViewsWithIds.put(R.id.progressCreatePriceAlert, 17);
    }

    public ActivityCreatePriceAlertBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds);
        this.btnAbove = (Button) bindings[11];
        this.btnBelow = (Button) bindings[10];
        this.btnCreateNotification = (Button) bindings[16];
        this.createAlertLayout = (RelativeLayout) bindings[15];
        this.currencyLayout = (LinearLayout) bindings[6];
        this.ibtnCanel = (ImageButton) bindings[2];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.priceLayout = (RelativeLayout) bindings[3];
        this.progressBar = (MaterialProgressBar) bindings[14];
        this.progressCreatePriceAlert = (MaterialProgressBar) bindings[17];
        this.relativeLayout3 = (RelativeLayout) bindings[1];
        this.rlScrollLayout = (RelativeLayout) bindings[12];
        this.rvPrice = (RecyclerView) bindings[13];
        this.tvCent = (TextView) bindings[9];
        this.tvCurrency = (TextView) bindings[7];
        this.tvDollar = (TextView) bindings[8];
        this.vLeftDivider = (View) bindings[4];
        this.vRightDivider = (View) bindings[5];
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

    public static ActivityCreatePriceAlertBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityCreatePriceAlertBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ActivityCreatePriceAlertBinding) DataBindingUtil.inflate(inflater, R.layout.activity_create_price_alert, root, attachToRoot, bindingComponent);
    }

    public static ActivityCreatePriceAlertBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityCreatePriceAlertBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_create_price_alert, null, false), bindingComponent);
    }

    public static ActivityCreatePriceAlertBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityCreatePriceAlertBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/activity_create_price_alert_0".equals(view.getTag())) {
            return new ActivityCreatePriceAlertBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
