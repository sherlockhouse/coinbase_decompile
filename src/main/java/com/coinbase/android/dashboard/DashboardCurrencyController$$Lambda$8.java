package com.coinbase.android.dashboard;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class DashboardCurrencyController$$Lambda$8 implements OnClickListener {
    private final DashboardCurrencyController arg$1;

    private DashboardCurrencyController$$Lambda$8(DashboardCurrencyController dashboardCurrencyController) {
        this.arg$1 = dashboardCurrencyController;
    }

    public static OnClickListener lambdaFactory$(DashboardCurrencyController dashboardCurrencyController) {
        return new DashboardCurrencyController$$Lambda$8(dashboardCurrencyController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onSellButtonClicked();
    }
}
