package com.coinbase.android.dashboard;

import com.coinbase.api.internal.models.alerts.Alerts;
import rx.functions.Action1;

final /* synthetic */ class DashboardMainPresenter$$Lambda$13 implements Action1 {
    private final DashboardMainPresenter arg$1;

    private DashboardMainPresenter$$Lambda$13(DashboardMainPresenter dashboardMainPresenter) {
        this.arg$1 = dashboardMainPresenter;
    }

    public static Action1 lambdaFactory$(DashboardMainPresenter dashboardMainPresenter) {
        return new DashboardMainPresenter$$Lambda$13(dashboardMainPresenter);
    }

    public void call(Object obj) {
        DashboardMainPresenter.lambda$null$4(this.arg$1, (Alerts) obj);
    }
}
