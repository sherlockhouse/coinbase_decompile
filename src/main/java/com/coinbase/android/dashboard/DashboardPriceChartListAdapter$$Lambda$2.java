package com.coinbase.android.dashboard;

import com.coinbase.android.databinding.ListItemDashboardPricechartBinding;
import com.coinbase.android.pricechart.PriceChartData.SpotPrice;
import rx.functions.Action1;

final /* synthetic */ class DashboardPriceChartListAdapter$$Lambda$2 implements Action1 {
    private final DashboardPriceChartListAdapter arg$1;
    private final ListItemDashboardPricechartBinding arg$2;

    private DashboardPriceChartListAdapter$$Lambda$2(DashboardPriceChartListAdapter dashboardPriceChartListAdapter, ListItemDashboardPricechartBinding listItemDashboardPricechartBinding) {
        this.arg$1 = dashboardPriceChartListAdapter;
        this.arg$2 = listItemDashboardPricechartBinding;
    }

    public static Action1 lambdaFactory$(DashboardPriceChartListAdapter dashboardPriceChartListAdapter, ListItemDashboardPricechartBinding listItemDashboardPricechartBinding) {
        return new DashboardPriceChartListAdapter$$Lambda$2(dashboardPriceChartListAdapter, listItemDashboardPricechartBinding);
    }

    public void call(Object obj) {
        this.arg$1.updateSpotPriceView(this.arg$2, (SpotPrice) obj);
    }
}
