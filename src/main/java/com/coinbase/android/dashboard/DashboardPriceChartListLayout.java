package com.coinbase.android.dashboard;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.LayoutDashboardPricechartListBinding;
import javax.inject.Inject;

@ControllerScope
public class DashboardPriceChartListLayout extends LinearLayout implements DashboardPriceChartListScreen {
    private LayoutDashboardPricechartListBinding mBinding;
    @Inject
    DashboardPriceChartListPresenter mPresenter;
    private DashboardPriceChartListAdapter mPriceChartListAdapter;

    public DashboardPriceChartListLayout(Context context) {
        this(context, null);
    }

    public DashboardPriceChartListLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashboardPriceChartListLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mBinding = LayoutDashboardPricechartListBinding.inflate(LayoutInflater.from(context), this, true);
        ((MainActivitySubcomponentProvider) context).mainActivitySubcomponent().addDashboardPriceChartListLayoutSubcomponent(new DashboardPriceChartListPresenterModule(this)).inject(this);
        this.mPriceChartListAdapter = new DashboardPriceChartListAdapter(context, this.mPresenter);
        this.mBinding.rvPriceCharts.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.mBinding.vPriceChartsRefreshLayout.setColorSchemeResources(R.color.coinbase_blue);
        this.mBinding.vPriceChartsRefreshLayout.setOnRefreshListener(DashboardPriceChartListLayout$$Lambda$1.lambdaFactory$(this));
    }

    void onResume() {
        this.mBinding.rvPriceCharts.setAdapter(this.mPriceChartListAdapter);
        this.mPresenter.onResume();
    }

    void onDestroy() {
        this.mBinding.rvPriceCharts.setAdapter(null);
        this.mPresenter.onDestroy();
    }

    public void notifyDataSetChanged() {
        this.mPriceChartListAdapter.notifyDataSetChanged();
        if (this.mBinding.vPriceChartsRefreshLayout.isRefreshing()) {
            this.mBinding.vPriceChartsRefreshLayout.setRefreshing(false);
        }
    }
}
