package com.coinbase.android.dashboard;

import java.util.List;
import rx.functions.Func1;

final /* synthetic */ class DashboardMainPresenter$$Lambda$10 implements Func1 {
    private final DashboardMainPresenter arg$1;

    private DashboardMainPresenter$$Lambda$10(DashboardMainPresenter dashboardMainPresenter) {
        this.arg$1 = dashboardMainPresenter;
    }

    public static Func1 lambdaFactory$(DashboardMainPresenter dashboardMainPresenter) {
        return new DashboardMainPresenter$$Lambda$10(dashboardMainPresenter);
    }

    public Object call(Object obj) {
        return DashboardMainPresenter.lambda$fetchDashboardData$11(this.arg$1, (List) obj);
    }
}
