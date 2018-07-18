package com.coinbase.android.dashboard;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class DashboardMainPresenter$$Lambda$11 implements Action1 {
    private final DashboardMainPresenter arg$1;

    private DashboardMainPresenter$$Lambda$11(DashboardMainPresenter dashboardMainPresenter) {
        this.arg$1 = dashboardMainPresenter;
    }

    public static Action1 lambdaFactory$(DashboardMainPresenter dashboardMainPresenter) {
        return new DashboardMainPresenter$$Lambda$11(dashboardMainPresenter);
    }

    public void call(Object obj) {
        DashboardMainPresenter.lambda$fetchDashboardData$12(this.arg$1, (Pair) obj);
    }
}
