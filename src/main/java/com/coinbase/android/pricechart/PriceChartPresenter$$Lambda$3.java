package com.coinbase.android.pricechart;

import android.util.Pair;
import rx.functions.Func2;

final /* synthetic */ class PriceChartPresenter$$Lambda$3 implements Func2 {
    private static final PriceChartPresenter$$Lambda$3 instance = new PriceChartPresenter$$Lambda$3();

    private PriceChartPresenter$$Lambda$3() {
    }

    public static Func2 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj, Object obj2) {
        return new Pair((Pair) obj, (Pair) obj2);
    }
}
