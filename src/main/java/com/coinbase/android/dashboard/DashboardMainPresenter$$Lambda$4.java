package com.coinbase.android.dashboard;

import rx.functions.Action1;

final /* synthetic */ class DashboardMainPresenter$$Lambda$4 implements Action1 {
    private final DashboardMainPresenter arg$1;

    private DashboardMainPresenter$$Lambda$4(DashboardMainPresenter dashboardMainPresenter) {
        this.arg$1 = dashboardMainPresenter;
    }

    public static Action1 lambdaFactory$(DashboardMainPresenter dashboardMainPresenter) {
        return new DashboardMainPresenter$$Lambda$4(dashboardMainPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Couldn't subscribe to DashboardRefreshConnector, shouldn't happen", (Throwable) obj);
    }
}
