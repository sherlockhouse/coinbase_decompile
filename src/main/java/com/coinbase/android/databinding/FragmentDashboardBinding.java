package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.alerts.AlertsListLayout;
import com.coinbase.android.dashboard.DashboardPriceChartListLayout;

public class FragmentDashboardBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final AppBarLayout appBar;
    public final Button btFirstBuy;
    public final CollapsingToolbarLayout ctLayout;
    public final AlertsListLayout cvAlertsNewUser;
    public final AlertsListLayout cvAlertsPortfolio;
    public final Toolbar cvCoinbaseToolbar;
    public final FrameLayout flAlertsPortfolio;
    public final ImageView ivLogo;
    public final DashboardPriceChartListLayout layoutPriceChartList;
    public final LinearLayout llAlertsNewUser;
    public final LinearLayout llFirstBuyContent;
    public final LinearLayout llNewUserHeader;
    public final LinearLayout llPortfolioHeader;
    private long mDirtyFlags = -1;
    private final CoordinatorLayout mboundView0;
    public final RelativeLayout rlPortfolio;
    public final TextView tvHeader;
    public final View vAlertsNewUserPadding;
    public final View vAlertsPortfolioPadding;

    static {
        sViewsWithIds.put(R.id.appBar, 1);
        sViewsWithIds.put(R.id.ctLayout, 2);
        sViewsWithIds.put(R.id.llPortfolioHeader, 3);
        sViewsWithIds.put(R.id.rlPortfolio, 4);
        sViewsWithIds.put(R.id.tvHeader, 5);
        sViewsWithIds.put(R.id.flAlertsPortfolio, 6);
        sViewsWithIds.put(R.id.cvAlertsPortfolio, 7);
        sViewsWithIds.put(R.id.vAlertsPortfolioPadding, 8);
        sViewsWithIds.put(R.id.llNewUserHeader, 9);
        sViewsWithIds.put(R.id.llFirstBuyContent, 10);
        sViewsWithIds.put(R.id.btFirstBuy, 11);
        sViewsWithIds.put(R.id.llAlertsNewUser, 12);
        sViewsWithIds.put(R.id.cvAlertsNewUser, 13);
        sViewsWithIds.put(R.id.vAlertsNewUserPadding, 14);
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 15);
        sViewsWithIds.put(R.id.ivLogo, 16);
        sViewsWithIds.put(R.id.layoutPriceChartList, 17);
    }

    public FragmentDashboardBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 18, sIncludes, sViewsWithIds);
        this.appBar = (AppBarLayout) bindings[1];
        this.btFirstBuy = (Button) bindings[11];
        this.ctLayout = (CollapsingToolbarLayout) bindings[2];
        this.cvAlertsNewUser = (AlertsListLayout) bindings[13];
        this.cvAlertsPortfolio = (AlertsListLayout) bindings[7];
        this.cvCoinbaseToolbar = (Toolbar) bindings[15];
        this.flAlertsPortfolio = (FrameLayout) bindings[6];
        this.ivLogo = (ImageView) bindings[16];
        this.layoutPriceChartList = (DashboardPriceChartListLayout) bindings[17];
        this.llAlertsNewUser = (LinearLayout) bindings[12];
        this.llFirstBuyContent = (LinearLayout) bindings[10];
        this.llNewUserHeader = (LinearLayout) bindings[9];
        this.llPortfolioHeader = (LinearLayout) bindings[3];
        this.mboundView0 = (CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlPortfolio = (RelativeLayout) bindings[4];
        this.tvHeader = (TextView) bindings[5];
        this.vAlertsNewUserPadding = (View) bindings[14];
        this.vAlertsPortfolioPadding = (View) bindings[8];
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

    public static FragmentDashboardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentDashboardBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (FragmentDashboardBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, root, attachToRoot, bindingComponent);
    }

    public static FragmentDashboardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentDashboardBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.fragment_dashboard, null, false), bindingComponent);
    }

    public static FragmentDashboardBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentDashboardBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/fragment_dashboard_0".equals(view.getTag())) {
            return new FragmentDashboardBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
