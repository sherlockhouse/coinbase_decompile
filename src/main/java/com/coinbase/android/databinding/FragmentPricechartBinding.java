package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.pricechart.PriceChartLayout;

public class FragmentPricechartBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final LinearLayout currencyPicker;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final RelativeLayout multiCurrency;
    public final LinearLayout priceChangeContainer;
    public final LinearLayout priceContainer;
    public final RelativeLayout priceContainerLayout;
    public final LinearLayout priceHighlightContainer;
    public final View priceSeparator;
    public final LinearLayout rangePicker;
    public final RelativeLayout rangeSection;
    public final RelativeLayout rlPriceHighlightSection;
    public final LinearLayout rlPriceSection;
    public final TextView tvChangeScope;
    public final TextView tvCryptoPrice;
    public final TextView tvCurrencyBitcoin;
    public final TextView tvCurrencyEther;
    public final TextView tvCurrencyLitecoin;
    public final TextView tvCurrentPrice;
    public final TextView tvHighlightedDate;
    public final TextView tvHighlightedPrice;
    public final TextView tvHighlightedPriceCurrencySymbol;
    public final TextView tvPriceChange;
    public final TextView tvPriceChangeCurrencySymbol;
    public final TextView tvPriceCurrencySymbol;
    public final TextView tvRangeAll;
    public final TextView tvRangeDay;
    public final TextView tvRangeHour;
    public final TextView tvRangeMonth;
    public final TextView tvRangeWeek;
    public final TextView tvRangeYear;
    public final PriceChartLayout vPriceChart;

    static {
        sViewsWithIds.put(R.id.multi_currency, 1);
        sViewsWithIds.put(R.id.currency_picker, 2);
        sViewsWithIds.put(R.id.tvCurrencyBitcoin, 3);
        sViewsWithIds.put(R.id.tvCurrencyEther, 4);
        sViewsWithIds.put(R.id.tvCurrencyLitecoin, 5);
        sViewsWithIds.put(R.id.price_container_layout, 6);
        sViewsWithIds.put(R.id.rlPriceSection, 7);
        sViewsWithIds.put(R.id.price_container, 8);
        sViewsWithIds.put(R.id.tvPriceCurrencySymbol, 9);
        sViewsWithIds.put(R.id.tvCurrentPrice, 10);
        sViewsWithIds.put(R.id.tvCryptoPrice, 11);
        sViewsWithIds.put(R.id.price_separator, 12);
        sViewsWithIds.put(R.id.price_change_container, 13);
        sViewsWithIds.put(R.id.tvPriceChangeCurrencySymbol, 14);
        sViewsWithIds.put(R.id.tvPriceChange, 15);
        sViewsWithIds.put(R.id.tvChangeScope, 16);
        sViewsWithIds.put(R.id.rlPriceHighlightSection, 17);
        sViewsWithIds.put(R.id.price_highlight_container, 18);
        sViewsWithIds.put(R.id.tvHighlightedPriceCurrencySymbol, 19);
        sViewsWithIds.put(R.id.tvHighlightedPrice, 20);
        sViewsWithIds.put(R.id.tvHighlightedDate, 21);
        sViewsWithIds.put(R.id.range_section, 22);
        sViewsWithIds.put(R.id.range_picker, 23);
        sViewsWithIds.put(R.id.tvRangeHour, 24);
        sViewsWithIds.put(R.id.tvRangeDay, 25);
        sViewsWithIds.put(R.id.tvRangeWeek, 26);
        sViewsWithIds.put(R.id.tvRangeMonth, 27);
        sViewsWithIds.put(R.id.tvRangeYear, 28);
        sViewsWithIds.put(R.id.tvRangeAll, 29);
        sViewsWithIds.put(R.id.vPriceChart, 30);
    }

    public FragmentPricechartBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 31, sIncludes, sViewsWithIds);
        this.currencyPicker = (LinearLayout) bindings[2];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.multiCurrency = (RelativeLayout) bindings[1];
        this.priceChangeContainer = (LinearLayout) bindings[13];
        this.priceContainer = (LinearLayout) bindings[8];
        this.priceContainerLayout = (RelativeLayout) bindings[6];
        this.priceHighlightContainer = (LinearLayout) bindings[18];
        this.priceSeparator = (View) bindings[12];
        this.rangePicker = (LinearLayout) bindings[23];
        this.rangeSection = (RelativeLayout) bindings[22];
        this.rlPriceHighlightSection = (RelativeLayout) bindings[17];
        this.rlPriceSection = (LinearLayout) bindings[7];
        this.tvChangeScope = (TextView) bindings[16];
        this.tvCryptoPrice = (TextView) bindings[11];
        this.tvCurrencyBitcoin = (TextView) bindings[3];
        this.tvCurrencyEther = (TextView) bindings[4];
        this.tvCurrencyLitecoin = (TextView) bindings[5];
        this.tvCurrentPrice = (TextView) bindings[10];
        this.tvHighlightedDate = (TextView) bindings[21];
        this.tvHighlightedPrice = (TextView) bindings[20];
        this.tvHighlightedPriceCurrencySymbol = (TextView) bindings[19];
        this.tvPriceChange = (TextView) bindings[15];
        this.tvPriceChangeCurrencySymbol = (TextView) bindings[14];
        this.tvPriceCurrencySymbol = (TextView) bindings[9];
        this.tvRangeAll = (TextView) bindings[29];
        this.tvRangeDay = (TextView) bindings[25];
        this.tvRangeHour = (TextView) bindings[24];
        this.tvRangeMonth = (TextView) bindings[27];
        this.tvRangeWeek = (TextView) bindings[26];
        this.tvRangeYear = (TextView) bindings[28];
        this.vPriceChart = (PriceChartLayout) bindings[30];
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

    public static FragmentPricechartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentPricechartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (FragmentPricechartBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_pricechart, root, attachToRoot, bindingComponent);
    }

    public static FragmentPricechartBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentPricechartBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.fragment_pricechart, null, false), bindingComponent);
    }

    public static FragmentPricechartBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentPricechartBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/fragment_pricechart_0".equals(view.getTag())) {
            return new FragmentPricechartBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
