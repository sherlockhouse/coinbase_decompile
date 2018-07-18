package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.coinbase.android.R;

public class ControllerInvestmentTiersRequirementsBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final AppBarLayout appBarContainer;
    public final Button btnContinue;
    public final Toolbar cvCoinbaseToolbar;
    private long mDirtyFlags = -1;
    private final RelativeLayout mboundView0;
    public final RecyclerView rvRequirements;

    static {
        sViewsWithIds.put(R.id.appBarContainer, 1);
        sViewsWithIds.put(R.id.cvCoinbaseToolbar, 2);
        sViewsWithIds.put(R.id.rvRequirements, 3);
        sViewsWithIds.put(R.id.btnContinue, 4);
    }

    public ControllerInvestmentTiersRequirementsBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.appBarContainer = (AppBarLayout) bindings[1];
        this.btnContinue = (Button) bindings[4];
        this.cvCoinbaseToolbar = (Toolbar) bindings[2];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rvRequirements = (RecyclerView) bindings[3];
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

    public static ControllerInvestmentTiersRequirementsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerInvestmentTiersRequirementsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (ControllerInvestmentTiersRequirementsBinding) DataBindingUtil.inflate(inflater, R.layout.controller_investment_tiers_requirements, root, attachToRoot, bindingComponent);
    }

    public static ControllerInvestmentTiersRequirementsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerInvestmentTiersRequirementsBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.controller_investment_tiers_requirements, null, false), bindingComponent);
    }

    public static ControllerInvestmentTiersRequirementsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ControllerInvestmentTiersRequirementsBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/controller_investment_tiers_requirements_0".equals(view.getTag())) {
            return new ControllerInvestmentTiersRequirementsBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
