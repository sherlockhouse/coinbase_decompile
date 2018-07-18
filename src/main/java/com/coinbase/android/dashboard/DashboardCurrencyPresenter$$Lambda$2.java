package com.coinbase.android.dashboard;

import com.coinbase.v2.models.account.Data;
import rx.functions.Action1;

final /* synthetic */ class DashboardCurrencyPresenter$$Lambda$2 implements Action1 {
    private final DashboardCurrencyPresenter arg$1;

    private DashboardCurrencyPresenter$$Lambda$2(DashboardCurrencyPresenter dashboardCurrencyPresenter) {
        this.arg$1 = dashboardCurrencyPresenter;
    }

    public static Action1 lambdaFactory$(DashboardCurrencyPresenter dashboardCurrencyPresenter) {
        return new DashboardCurrencyPresenter$$Lambda$2(dashboardCurrencyPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mScreen.gotoAccountTransactions((Data) obj, this.arg$1.mSelectedCurrency);
    }
}
