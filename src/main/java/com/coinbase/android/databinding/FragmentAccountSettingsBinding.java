package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.R;

public class FragmentAccountSettingsBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final AppBarLayout appBar;
    public final Button btnIncreaseYourLimits;
    public final CollapsingToolbarLayout ctLayout;
    public final Toolbar cvCoinbaseToolbar;
    public final LinearLayout llTiersHeader;
    public final LinearLayout llUserName;
    private long mDirtyFlags = -1;
    private final CoordinatorLayout mboundView0;
    public final RecyclerView rvSettings;
    public final TextView tvBody;
    public final TextView tvEmail;
    public final TextView tvHeader;
    public final TextView tvName;

    static {
        sViewsWithIds.put(R.id.appBar, 1);
        sViewsWithIds.put(R.id.ctLayout, 2);
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 3);
        sViewsWithIds.put(R.id.llUserName, 4);
        sViewsWithIds.put(R.id.tvName, 5);
        sViewsWithIds.put(R.id.tvEmail, 6);
        sViewsWithIds.put(R.id.llTiersHeader, 7);
        sViewsWithIds.put(R.id.tvHeader, 8);
        sViewsWithIds.put(R.id.tvBody, 9);
        sViewsWithIds.put(R.id.btnIncreaseYourLimits, 10);
        sViewsWithIds.put(R.id.rvSettings, 11);
    }

    public FragmentAccountSettingsBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds);
        this.appBar = (AppBarLayout) bindings[1];
        this.btnIncreaseYourLimits = (Button) bindings[10];
        this.ctLayout = (CollapsingToolbarLayout) bindings[2];
        this.cvCoinbaseToolbar = (Toolbar) bindings[3];
        this.llTiersHeader = (LinearLayout) bindings[7];
        this.llUserName = (LinearLayout) bindings[4];
        this.mboundView0 = (CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rvSettings = (RecyclerView) bindings[11];
        this.tvBody = (TextView) bindings[9];
        this.tvEmail = (TextView) bindings[6];
        this.tvHeader = (TextView) bindings[8];
        this.tvName = (TextView) bindings[5];
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

    public static FragmentAccountSettingsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentAccountSettingsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (FragmentAccountSettingsBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_account_settings, root, attachToRoot, bindingComponent);
    }

    public static FragmentAccountSettingsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentAccountSettingsBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.fragment_account_settings, null, false), bindingComponent);
    }

    public static FragmentAccountSettingsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static FragmentAccountSettingsBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/fragment_account_settings_0".equals(view.getTag())) {
            return new FragmentAccountSettingsBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
