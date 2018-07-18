package com.coinbase.android.dashboard;

import rx.functions.Action1;

final /* synthetic */ class DashboardMainPresenter$$Lambda$8 implements Action1 {
    private final DashboardMainPresenter arg$1;

    private DashboardMainPresenter$$Lambda$8(DashboardMainPresenter dashboardMainPresenter) {
        this.arg$1 = dashboardMainPresenter;
    }

    public static Action1 lambdaFactory$(DashboardMainPresenter dashboardMainPresenter) {
        return new DashboardMainPresenter$$Lambda$8(dashboardMainPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Couldn't subscribe to DashboardBalanceUpdatedConnector, shouldn't happen", (Throwable) obj);
    }
}
