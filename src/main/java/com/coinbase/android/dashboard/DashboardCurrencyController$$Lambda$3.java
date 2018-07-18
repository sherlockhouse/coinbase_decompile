package com.coinbase.android.dashboard;

import rx.functions.Action1;

final /* synthetic */ class DashboardCurrencyController$$Lambda$3 implements Action1 {
    private final DashboardCurrencyController arg$1;

    private DashboardCurrencyController$$Lambda$3(DashboardCurrencyController dashboardCurrencyController) {
        this.arg$1 = dashboardCurrencyController;
    }

    public static Action1 lambdaFactory$(DashboardCurrencyController dashboardCurrencyController) {
        return new DashboardCurrencyController$$Lambda$3(dashboardCurrencyController);
    }

    public void call(Object obj) {
        this.arg$1.mPresenter.onPriceChartMotionEvent((Boolean) obj);
    }
}
