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
import com.coinbase.android.ui.CurrencyTabLayout;
import com.coinbase.android.ui.EmptySupportRecyclerView;

public class FragmentPriceAlertsBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btCreatePriceAlert;
    public final Toolbar cvCoinbaseToolbar;
    public final ImageView imageView5;
    public final ImageView ivCurrencyIcon;
    public final CurrencyTabLayout layoutTabCurrency;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final RelativeLayout relativeLayout2;
    public final RelativeLayout rlEmptyState;
    public final EmptySupportRecyclerView rvNotifications;
    public final TextView textView4;
    public final TextView tvCurrencyCode;
    public final TextView tvCurrentPrice;
    public final TextView tvEmptyStateDescription;
    public final View vDivider;

    static {
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 1);
        sViewsWithIds.put(R.id.layoutTabCurrency, 2);
        sViewsWithIds.put(R.id.relativeLayout2, 3);
        sViewsWithIds.put(R.id.ivCurrencyIcon, 4);
        sViewsWithIds.put(R.id.tvCurrentPrice, 5);
        sViewsWithIds.put(R.id.tvCurrencyCode, 6);
        sViewsWithIds.put(R.id.vDivider, 7);
        sViewsWithIds.put(R.id.rlEmptyState, 8);
        sViewsWithIds.put(R.id.imageView5, 9);
        sViewsWithIds.put(R.id.textView4, 10);
        sViewsWithIds.put(R.id.tvEmptyStateDescription, 11);
        sViewsWithIds.put(R.id.btCreatePriceAlert, 12);
        sViewsWithIds.put(R.id.rvNotifications, 13);
    }

    public FragmentPriceAlertsBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds);
        this.btCreatePriceAlert = (Button) bindings[12];
        this.cvCoinbaseToolbar = (Toolbar) bindings[1];
        this.imageView5 = (ImageView) bindings[9];
        this.ivCurrencyIcon = (ImageView) bindings[4];
        this.layoutTabCurrency = (CurrencyTabLayout) bindings[2];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.relativeLayout2 = (RelativeLayout) bindings[3];
        this.rlEmptyState = (RelativeLayout) bindings[8];
        this.rvNotifications = (EmptySupportRecyclerView) bindings[13];
        this.textView4 = (TextView) bindings[10];
        this.tvCurrencyCode = (TextView) bindings[6];
        this.tvCurrentPrice = (TextView) bindings[5];
        this.tvEmptyStateDescription = (TextView) bindings[11];
        this.vDivider = (View) bindings[7];
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

    public static FragmentPriceAlertsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentPriceAlertsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (FragmentPriceAlertsBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_price_alerts, root, attachToRoot, bindingComponent);
    }

    public static FragmentPriceAlertsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentPriceAlertsBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.fragment_price_alerts, null, false), bindingComponent);
    }

    public static FragmentPriceAlertsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentPriceAlertsBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/fragment_price_alerts_0".equals(view.getTag())) {
            return new FragmentPriceAlertsBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
