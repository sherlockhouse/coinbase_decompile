package com.coinbase.android.dashboard;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class DashboardMainController$$Lambda$2 implements OnClickListener {
    private final DashboardMainController arg$1;

    private DashboardMainController$$Lambda$2(DashboardMainController dashboardMainController) {
        this.arg$1 = dashboardMainController;
    }

    public static OnClickListener lambdaFactory$(DashboardMainController dashboardMainController) {
        return new DashboardMainController$$Lambda$2(dashboardMainController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onFirstBuyAccountSetupClicked();
    }
}
