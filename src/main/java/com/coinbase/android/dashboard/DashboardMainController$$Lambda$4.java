package com.coinbase.android.dashboard;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener;

final /* synthetic */ class DashboardMainController$$Lambda$4 implements OnOffsetChangedListener {
    private final DashboardMainController arg$1;

    private DashboardMainController$$Lambda$4(DashboardMainController dashboardMainController) {
        this.arg$1 = dashboardMainController;
    }

    public static OnOffsetChangedListener lambdaFactory$(DashboardMainController dashboardMainController) {
        return new DashboardMainController$$Lambda$4(dashboardMainController);
    }

    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        this.arg$1.handlePortfolioTitleHeaderVisibility(appBarLayout, i);
    }
}
