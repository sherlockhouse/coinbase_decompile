package com.coinbase.android.pricechart;

import rx.functions.Action1;

final /* synthetic */ class PriceChartPresenter$$Lambda$2 implements Action1 {
    private final PriceChartPresenter arg$1;

    private PriceChartPresenter$$Lambda$2(PriceChartPresenter priceChartPresenter) {
        this.arg$1 = priceChartPresenter;
    }

    public static Action1 lambdaFactory$(PriceChartPresenter priceChartPresenter) {
        return new PriceChartPresenter$$Lambda$2(priceChartPresenter);
    }

    public void call(Object obj) {
        PriceChartPresenter.lambda$displayChart$1(this.arg$1, (PriceChartData) obj);
    }
}
