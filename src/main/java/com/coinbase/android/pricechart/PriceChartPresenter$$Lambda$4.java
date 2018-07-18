package com.coinbase.android.pricechart;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class PriceChartPresenter$$Lambda$4 implements Func1 {
    private final PriceChartPresenter arg$1;

    private PriceChartPresenter$$Lambda$4(PriceChartPresenter priceChartPresenter) {
        this.arg$1 = priceChartPresenter;
    }

    public static Func1 lambdaFactory$(PriceChartPresenter priceChartPresenter) {
        return new PriceChartPresenter$$Lambda$4(priceChartPresenter);
    }

    public Object call(Object obj) {
        return PriceChartPresenter.lambda$fetchPriceChartData$3(this.arg$1, (Pair) obj);
    }
}
