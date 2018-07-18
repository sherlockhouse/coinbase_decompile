package com.coinbase.android.dashboard;

import rx.functions.Action1;

final /* synthetic */ class DashboardMainPresenter$$Lambda$3 implements Action1 {
    private final DashboardMainPresenter arg$1;

    private DashboardMainPresenter$$Lambda$3(DashboardMainPresenter dashboardMainPresenter) {
        this.arg$1 = dashboardMainPresenter;
    }

    public static Action1 lambdaFactory$(DashboardMainPresenter dashboardMainPresenter) {
        return new DashboardMainPresenter$$Lambda$3(dashboardMainPresenter);
    }

    public void call(Object obj) {
        DashboardMainPresenter.lambda$onResume$2(this.arg$1, (Void) obj);
    }
}
