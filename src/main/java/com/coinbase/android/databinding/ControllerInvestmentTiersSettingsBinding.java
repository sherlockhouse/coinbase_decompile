package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.coinbase.android.R;

public class ControllerInvestmentTiersSettingsBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button btCompleteAccountSetup;
    public final TextView btnContinue;
    public final Button btnIncreaseYourLimits;
    public final Toolbar cvCoinbaseToolbar;
    public final LinearLayout llBuyDepositContainer;
    public final LinearLayout llCompleteAccountContainer;
    public final LinearLayout llLifetimeLimitContainer;
    public final LinearLayout llLimitsContainer;
    public final LinearLayout llTiersContainer;
    public final FrameLayout llTiersSuccess;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final NestedScrollView nsvContainer;
    public final ProgressBar progLifetimeLimit;
    public final RecyclerView rvBuyDepositItems;
    public final RecyclerView rvTiers;
    public final TextView tvBuyDepositHeader;
    public final TextView tvLifetimeLimitAmount;
    public final TextView tvLifetimeLimitDescription;
    public final TextView tvLifetimeLimitName;
    public final TextView tvLifetimeLimitRemaining;
    public final TextView tvTiersHeader;
    public final TextView tvTiersLevelTitle;

    static {
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 1);
        sViewsWithIds.put(R.id.nsvContainer, 2);
        sViewsWithIds.put(R.id.llCompleteAccountContainer, 3);
        sViewsWithIds.put(R.id.btCompleteAccountSetup, 4);
        sViewsWithIds.put(R.id.llLimitsContainer, 5);
        sViewsWithIds.put(R.id.tvTiersLevelTitle, 6);
        sViewsWithIds.put(R.id.llLifetimeLimitContainer, 7);
        sViewsWithIds.put(R.id.tvLifetimeLimitName, 8);
        sViewsWithIds.put(R.id.tvLifetimeLimitAmount, 9);
        sViewsWithIds.put(R.id.tvLifetimeLimitRemaining, 10);
        sViewsWithIds.put(R.id.progLifetimeLimit, 11);
        sViewsWithIds.put(R.id.tvLifetimeLimitDescription, 12);
        sViewsWithIds.put(R.id.llBuyDepositContainer, 13);
        sViewsWithIds.put(R.id.tvBuyDepositHeader, 14);
        sViewsWithIds.put(R.id.rvBuyDepositItems, 15);
        sViewsWithIds.put(R.id.btnIncreaseYourLimits, 16);
        sViewsWithIds.put(R.id.llTiersContainer, 17);
        sViewsWithIds.put(R.id.tvTiersHeader, 18);
        sViewsWithIds.put(R.id.rvTiers, 19);
        sViewsWithIds.put(R.id.llTiersSuccess, 20);
        sViewsWithIds.put(R.id.btnContinue, 21);
    }

    public ControllerInvestmentTiersSettingsBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds);
        this.btCompleteAccountSetup = (Button) bindings[4];
        this.btnContinue = (TextView) bindings[21];
        this.btnIncreaseYourLimits = (Button) bindings[16];
        this.cvCoinbaseToolbar = (Toolbar) bindings[1];
        this.llBuyDepositContainer = (LinearLayout) bindings[13];
        this.llCompleteAccountContainer = (LinearLayout) bindings[3];
        this.llLifetimeLimitContainer = (LinearLayout) bindings[7];
        this.llLimitsContainer = (LinearLayout) bindings[5];
        this.llTiersContainer = (LinearLayout) bindings[17];
        this.llTiersSuccess = (FrameLayout) bindings[20];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.nsvContainer = (NestedScrollView) bindings[2];
        this.progLifetimeLimit = (ProgressBar) bindings[11];
        this.rvBuyDepositItems = (RecyclerView) bindings[15];
        this.rvTiers = (RecyclerView) bindings[19];
        this.tvBuyDepositHeader = (TextView) bindings[14];
        this.tvLifetimeLimitAmount = (TextView) bindings[9];
        this.tvLifetimeLimitDescription = (TextView) bindings[12];
        this.tvLifetimeLimitName = (TextView) bindings[8];
        this.tvLifetimeLimitRemaining = (TextView) bindings[10];
        this.tvTiersHeader = (TextView) bindings[18];
        this.tvTiersLevelTitle = (TextView) bindings[6];
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

    public static ControllerInvestmentTiersSettingsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerInvestmentTiersSettingsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerInvestmentTiersSettingsBinding) DataBindingUtil.inflate(inflater, R.layout.controller_investment_tiers_settings, root, attachToRoot, bindingComponent);
    }

    public static ControllerInvestmentTiersSettingsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerInvestmentTiersSettingsBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_investment_tiers_settings, null, false), bindingComponent);
    }

    public static ControllerInvestmentTiersSettingsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerInvestmentTiersSettingsBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_investment_tiers_settings_0".equals(view.getTag())) {
            return new ControllerInvestmentTiersSettingsBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
