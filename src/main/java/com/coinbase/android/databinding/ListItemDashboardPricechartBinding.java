package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.CardView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.pricechart.PriceChartLayout;

public class ListItemDashboardPricechartBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final View currencyView;
    public final CardView cvDashboardCurrency;
    private long mDirtyFlags = -1;
    public final PriceChartLayout priceChartLayout;
    public final RelativeLayout rlHeader;
    public final TextView tvCurrencyCode;
    public final TextView tvCurrencyName;
    public final TextView tvCurrentPrice;
    public final TextView tvPriceChange;

    static {
        sViewsWithIds.put(R.id.rlHeader, 1);
        sViewsWithIds.put(R.id.tvCurrencyName, 2);
        sViewsWithIds.put(R.id.tvCurrentPrice, 3);
        sViewsWithIds.put(R.id.currencyView, 4);
        sViewsWithIds.put(R.id.tvCurrencyCode, 5);
        sViewsWithIds.put(R.id.tvPriceChange, 6);
        sViewsWithIds.put(R.id.priceChartLayout, 7);
    }

    public ListItemDashboardPricechartBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.currencyView = (View) bindings[4];
        this.cvDashboardCurrency = (CardView) bindings[0];
        this.cvDashboardCurrency.setTag(null);
        this.priceChartLayout = (PriceChartLayout) bindings[7];
        this.rlHeader = (RelativeLayout) bindings[1];
        this.tvCurrencyCode = (TextView) bindings[5];
        this.tvCurrencyName = (TextView) bindings[2];
        this.tvCurrentPrice = (TextView) bindings[3];
        this.tvPriceChange = (TextView) bindings[6];
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

    public static ListItemDashboardPricechartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemDashboardPricechartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ListItemDashboardPricechartBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_dashboard_pricechart, root, attachToRoot, bindingComponent);
    }

    public static ListItemDashboardPricechartBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemDashboardPricechartBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.list_item_dashboard_pricechart, null, false), bindingComponent);
    }

    public static ListItemDashboardPricechartBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ListItemDashboardPricechartBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/list_item_dashboard_pricechart_0".equals(view.getTag())) {
            return new ListItemDashboardPricechartBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
