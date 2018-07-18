package com.coinbase.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.coinbase.android.R;
import com.coinbase.android.pricechart.ZeroMarginLineChart;
import com.coinbase.android.ui.MaterialProgressBar;

public class LayoutPriceChartBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final ZeroMarginLineChart cvPriceChart;
    public final ImageView ivPriceChart;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final MaterialProgressBar progressPriceChart;
    public final TextView tvPriceChartFailure;

    static {
        sViewsWithIds.put(R.id.progressPriceChart, 1);
        sViewsWithIds.put(R.id.cvPriceChart, 2);
        sViewsWithIds.put(R.id.ivPriceChart, 3);
        sViewsWithIds.put(R.id.tvPriceChartFailure, 4);
    }

    public LayoutPriceChartBinding(DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.cvPriceChart = (ZeroMarginLineChart) bindings[2];
        this.ivPriceChart = (ImageView) bindings[3];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progressPriceChart = (MaterialProgressBar) bindings[1];
        this.tvPriceChartFailure = (TextView) bindings[4];
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

    public static LayoutPriceChartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutPriceChartBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, DataBindingComponent bindingComponent) {
        return (LayoutPriceChartBinding) DataBindingUtil.inflate(inflater, R.layout.layout_price_chart, root, attachToRoot, bindingComponent);
    }

    public static LayoutPriceChartBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutPriceChartBinding inflate(LayoutInflater inflater, DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_price_chart, null, false), bindingComponent);
    }

    public static LayoutPriceChartBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static LayoutPriceChartBinding bind(View view, DataBindingComponent bindingComponent) {
        if ("layout/layout_price_chart_0".equals(view.getTag())) {
            return new LayoutPriceChartBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
