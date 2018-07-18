package com.coinbase.android.dashboard;

import org.joda.money.CurrencyUnit;
import rx.functions.Action1;

final /* synthetic */ class DashboardCurrencyController$$Lambda$4 implements Action1 {
    private final DashboardCurrencyController arg$1;

    private DashboardCurrencyController$$Lambda$4(DashboardCurrencyController dashboardCurrencyController) {
        this.arg$1 = dashboardCurrencyController;
    }

    public static Action1 lambdaFactory$(DashboardCurrencyController dashboardCurrencyController) {
        return new DashboardCurrencyController$$Lambda$4(dashboardCurrencyController);
    }

    public void call(Object obj) {
        this.arg$1.mPresenter.onCurrencyUpdated((CurrencyUnit) obj);
    }
}
