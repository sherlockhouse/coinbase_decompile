package com.coinbase.android.dashboard;

import com.coinbase.api.internal.models.currency.Data;
import rx.functions.Action1;

final /* synthetic */ class DashboardMainPresenter$$Lambda$1 implements Action1 {
    private final DashboardMainPresenter arg$1;

    private DashboardMainPresenter$$Lambda$1(DashboardMainPresenter dashboardMainPresenter) {
        this.arg$1 = dashboardMainPresenter;
    }

    public static Action1 lambdaFactory$(DashboardMainPresenter dashboardMainPresenter) {
        return new DashboardMainPresenter$$Lambda$1(dashboardMainPresenter);
    }

    public void call(Object obj) {
        DashboardMainPresenter.lambda$onResume$0(this.arg$1, (Data) obj);
    }
}
