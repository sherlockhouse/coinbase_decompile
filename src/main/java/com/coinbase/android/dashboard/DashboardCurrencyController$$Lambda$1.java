package com.coinbase.android.dashboard;

import com.coinbase.android.pricechart.PriceChartData.SpotPrice;
import rx.functions.Action1;

final /* synthetic */ class DashboardCurrencyController$$Lambda$1 implements Action1 {
    private final DashboardCurrencyController arg$1;

    private DashboardCurrencyController$$Lambda$1(DashboardCurrencyController dashboardCurrencyController) {
        this.arg$1 = dashboardCurrencyController;
    }

    public static Action1 lambdaFactory$(DashboardCurrencyController dashboardCurrencyController) {
        return new DashboardCurrencyController$$Lambda$1(dashboardCurrencyController);
    }

    public void call(Object obj) {
        this.arg$1.mPresenter.onSpotPriceUpdated((SpotPrice) obj);
    }
}
