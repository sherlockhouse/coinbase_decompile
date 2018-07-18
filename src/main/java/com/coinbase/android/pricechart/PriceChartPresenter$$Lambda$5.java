package com.coinbase.android.pricechart;

import retrofit2.Response;
import rx.functions.Action1;

final /* synthetic */ class PriceChartPresenter$$Lambda$5 implements Action1 {
    private final PriceChartPresenter arg$1;

    private PriceChartPresenter$$Lambda$5(PriceChartPresenter priceChartPresenter) {
        this.arg$1 = priceChartPresenter;
    }

    public static Action1 lambdaFactory$(PriceChartPresenter priceChartPresenter) {
        return new PriceChartPresenter$$Lambda$5(priceChartPresenter);
    }

    public void call(Object obj) {
        PriceChartPresenter.lambda$fetchPriceChartData$4(this.arg$1, (Response) obj);
    }
}
