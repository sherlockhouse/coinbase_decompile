package com.coinbase.android.dashboard;

import rx.functions.Action1;

final /* synthetic */ class DashboardCurrencyPresenter$$Lambda$1 implements Action1 {
    private final DashboardCurrencyPresenter arg$1;

    private DashboardCurrencyPresenter$$Lambda$1(DashboardCurrencyPresenter dashboardCurrencyPresenter) {
        this.arg$1 = dashboardCurrencyPresenter;
    }

    public static Action1 lambdaFactory$(DashboardCurrencyPresenter dashboardCurrencyPresenter) {
        return new DashboardCurrencyPresenter$$Lambda$1(dashboardCurrencyPresenter);
    }

    public void call(Object obj) {
        DashboardCurrencyPresenter.lambda$onResume$0(this.arg$1, (TabPeriod) obj);
    }
}
