package com.coinbase.android.dashboard;

import java.util.List;
import rx.functions.Action1;

final /* synthetic */ class DashboardPriceChartListPresenter$$Lambda$1 implements Action1 {
    private final DashboardPriceChartListPresenter arg$1;

    private DashboardPriceChartListPresenter$$Lambda$1(DashboardPriceChartListPresenter dashboardPriceChartListPresenter) {
        this.arg$1 = dashboardPriceChartListPresenter;
    }

    public static Action1 lambdaFactory$(DashboardPriceChartListPresenter dashboardPriceChartListPresenter) {
        return new DashboardPriceChartListPresenter$$Lambda$1(dashboardPriceChartListPresenter);
    }

    public void call(Object obj) {
        DashboardPriceChartListPresenter.lambda$onResume$0(this.arg$1, (List) obj);
    }
}
