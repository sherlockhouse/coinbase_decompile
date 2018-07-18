package com.coinbase.android.dashboard;

import android.view.View;
import android.view.View.OnClickListener;
import com.coinbase.api.internal.models.currency.Data;

final /* synthetic */ class DashboardPriceChartListAdapter$$Lambda$1 implements OnClickListener {
    private final DashboardPriceChartListAdapter arg$1;
    private final Data arg$2;

    private DashboardPriceChartListAdapter$$Lambda$1(DashboardPriceChartListAdapter dashboardPriceChartListAdapter, Data data) {
        this.arg$1 = dashboardPriceChartListAdapter;
        this.arg$2 = data;
    }

    public static OnClickListener lambdaFactory$(DashboardPriceChartListAdapter dashboardPriceChartListAdapter, Data data) {
        return new DashboardPriceChartListAdapter$$Lambda$1(dashboardPriceChartListAdapter, data);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onPriceChartItemClicked(this.arg$2);
    }
}
