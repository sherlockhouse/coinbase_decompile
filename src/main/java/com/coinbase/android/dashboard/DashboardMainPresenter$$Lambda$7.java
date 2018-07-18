package com.coinbase.android.dashboard;

import rx.functions.Action1;

final /* synthetic */ class DashboardMainPresenter$$Lambda$7 implements Action1 {
    private final DashboardMainPresenter arg$1;

    private DashboardMainPresenter$$Lambda$7(DashboardMainPresenter dashboardMainPresenter) {
        this.arg$1 = dashboardMainPresenter;
    }

    public static Action1 lambdaFactory$(DashboardMainPresenter dashboardMainPresenter) {
        return new DashboardMainPresenter$$Lambda$7(dashboardMainPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mScreen.updatePortfolioBalanceHeader((String) obj);
    }
}
