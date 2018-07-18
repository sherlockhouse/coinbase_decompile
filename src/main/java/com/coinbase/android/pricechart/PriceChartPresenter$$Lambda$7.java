package com.coinbase.android.pricechart;

import rx.functions.Action1;

final /* synthetic */ class PriceChartPresenter$$Lambda$7 implements Action1 {
    private final PriceChartPresenter arg$1;
    private final String arg$2;

    private PriceChartPresenter$$Lambda$7(PriceChartPresenter priceChartPresenter, String str) {
        this.arg$1 = priceChartPresenter;
        this.arg$2 = str;
    }

    public static Action1 lambdaFactory$(PriceChartPresenter priceChartPresenter, String str) {
        return new PriceChartPresenter$$Lambda$7(priceChartPresenter, str);
    }

    public void call(Object obj) {
        PriceChartPresenter.lambda$getPeriodicSpotPriceSubscription$8(this.arg$1, this.arg$2, (Long) obj);
    }
}
