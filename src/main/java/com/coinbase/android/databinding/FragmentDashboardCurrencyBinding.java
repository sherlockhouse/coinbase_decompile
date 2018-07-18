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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.accounts.AccountFilteredListLayout;
import com.coinbase.android.dashboard.DashboardTabPeriodLayout;
import com.coinbase.android.pricechart.LockableNestedScrollView;
import com.coinbase.android.pricechart.PriceChartLayout;

public class FragmentDashboardCurrencyBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btBuy;
    public final Button btSell;
    public final Toolbar cvCoinbaseToolbar;
    public final AccountFilteredListLayout layoutAccountList;
    public final DashboardTabPeriodLayout layoutTabPeriod;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final LockableNestedScrollView nestedScrollView;
    public final LinearLayout priceContainer;
    public final RelativeLayout priceContainerLayout;
    public final LinearLayout priceHighlightContainer;
    public final RelativeLayout rlPriceHighlightSection;
    public final RelativeLayout rlPriceSection;
    public final TextView tvCurrentPrice;
    public final TextView tvHighlightedDate;
    public final TextView tvHighlightedPrice;
    public final TextView tvHighlightedPriceCurrencySymbol;
    public final TextView tvPriceChange;
    public final TextView tvPriceCurrencySymbol;
    public final PriceChartLayout vPriceChart;

    static {
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 1);
        sViewsWithIds.put(R.id.nestedScrollView, 2);
        sViewsWithIds.put(R.id.layoutTabPeriod, 3);
        sViewsWithIds.put(R.id.price_container_layout, 4);
        sViewsWithIds.put(R.id.rlPriceSection, 5);
        sViewsWithIds.put(R.id.price_container, 6);
        sViewsWithIds.put(R.id.tvPriceCurrencySymbol, 7);
        sViewsWithIds.put(R.id.tvCurrentPrice, 8);
        sViewsWithIds.put(R.id.tvPriceChange, 9);
        sViewsWithIds.put(R.id.rlPriceHighlightSection, 10);
        sViewsWithIds.put(R.id.price_highlight_container, 11);
        sViewsWithIds.put(R.id.tvHighlightedPriceCurrencySymbol, 12);
        sViewsWithIds.put(R.id.tvHighlightedPrice, 13);
        sViewsWithIds.put(R.id.tvHighlightedDate, 14);
        sViewsWithIds.put(R.id.vPriceChart, 15);
        sViewsWithIds.put(R.id.btBuy, 16);
        sViewsWithIds.put(R.id.btSell, 17);
        sViewsWithIds.put(R.id.layoutAccountList, 18);
    }

    public FragmentDashboardCurrencyBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds);
        this.btBuy = (Button) bindings[16];
        this.btSell = (Button) bindings[17];
        this.cvCoinbaseToolbar = (Toolbar) bindings[1];
        this.layoutAccountList = (AccountFilteredListLayout) bindings[18];
        this.layoutTabPeriod = (DashboardTabPeriodLayout) bindings[3];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.nestedScrollView = (LockableNestedScrollView) bindings[2];
        this.priceContainer = (LinearLayout) bindings[6];
        this.priceContainerLayout = (RelativeLayout) bindings[4];
        this.priceHighlightContainer = (LinearLayout) bindings[11];
        this.rlPriceHighlightSection = (RelativeLayout) bindings[10];
        this.rlPriceSection = (RelativeLayout) bindings[5];
        this.tvCurrentPrice = (TextView) bindings[8];
        this.tvHighlightedDate = (TextView) bindings[14];
        this.tvHighlightedPrice = (TextView) bindings[13];
        this.tvHighlightedPriceCurrencySymbol = (TextView) bindings[12];
        this.tvPriceChange = (TextView) bindings[9];
        this.tvPriceCurrencySymbol = (TextView) bindings[7];
        this.vPriceChart = (PriceChartLayout) bindings[15];
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

    public static FragmentDashboardCurrencyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentDashboardCurrencyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (FragmentDashboardCurrencyBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard_currency, root, attachToRoot, bindingComponent);
    }

    public static FragmentDashboardCurrencyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentDashboardCurrencyBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.fragment_dashboard_currency, null, false), bindingComponent);
    }

    public static FragmentDashboardCurrencyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentDashboardCurrencyBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/fragment_dashboard_currency_0".equals(view.getTag())) {
            return new FragmentDashboardCurrencyBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
