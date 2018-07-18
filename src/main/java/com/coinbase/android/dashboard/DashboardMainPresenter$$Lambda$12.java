package com.coinbase.android.dashboard;

import rx.functions.Action1;

final /* synthetic */ class DashboardMainPresenter$$Lambda$12 implements Action1 {
    private final DashboardMainPresenter arg$1;

    private DashboardMainPresenter$$Lambda$12(DashboardMainPresenter dashboardMainPresenter) {
        this.arg$1 = dashboardMainPresenter;
    }

    public static Action1 lambdaFactory$(DashboardMainPresenter dashboardMainPresenter) {
        return new DashboardMainPresenter$$Lambda$12(dashboardMainPresenter);
    }

    public void call(Object obj) {
        DashboardMainPresenter.lambda$fetchDashboardData$13(this.arg$1, (Throwable) obj);
    }
}
