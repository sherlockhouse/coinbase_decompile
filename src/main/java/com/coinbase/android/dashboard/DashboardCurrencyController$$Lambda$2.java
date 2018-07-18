package com.coinbase.android.dashboard;

import com.coinbase.android.pricechart.PriceChartData.HighlightedPrice;
import rx.functions.Action1;

final /* synthetic */ class DashboardCurrencyController$$Lambda$2 implements Action1 {
    private final DashboardCurrencyController arg$1;

    private DashboardCurrencyController$$Lambda$2(DashboardCurrencyController dashboardCurrencyController) {
        this.arg$1 = dashboardCurrencyController;
    }

    public static Action1 lambdaFactory$(DashboardCurrencyController dashboardCurrencyController) {
        return new DashboardCurrencyController$$Lambda$2(dashboardCurrencyController);
    }

    public void call(Object obj) {
        this.arg$1.mPresenter.onPriceHighlighted((HighlightedPrice) obj);
    }
}
