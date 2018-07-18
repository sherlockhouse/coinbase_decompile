package com.coinbase.android.pricechart;

import rx.functions.Action1;

final /* synthetic */ class PriceChartPresenter$$Lambda$8 implements Action1 {
    private static final PriceChartPresenter$$Lambda$8 instance = new PriceChartPresenter$$Lambda$8();

    private PriceChartPresenter$$Lambda$8() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        PriceChartPresenter.lambda$getPeriodicSpotPriceSubscription$9((Throwable) obj);
    }
}
