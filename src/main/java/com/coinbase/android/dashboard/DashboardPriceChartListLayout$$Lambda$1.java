package com.coinbase.android.dashboard;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

final /* synthetic */ class DashboardPriceChartListLayout$$Lambda$1 implements OnRefreshListener {
    private final DashboardPriceChartListLayout arg$1;

    private DashboardPriceChartListLayout$$Lambda$1(DashboardPriceChartListLayout dashboardPriceChartListLayout) {
        this.arg$1 = dashboardPriceChartListLayout;
    }

    public static OnRefreshListener lambdaFactory$(DashboardPriceChartListLayout dashboardPriceChartListLayout) {
        return new DashboardPriceChartListLayout$$Lambda$1(dashboardPriceChartListLayout);
    }

    public void onRefresh() {
        this.arg$1.mPresenter.onRefresh();
    }
}
