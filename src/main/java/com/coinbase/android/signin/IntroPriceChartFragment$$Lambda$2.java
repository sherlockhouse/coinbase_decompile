package com.coinbase.android.signin;

import com.coinbase.android.pricechart.PriceChartData.HighlightedPrice;
import rx.functions.Action1;

final /* synthetic */ class IntroPriceChartFragment$$Lambda$2 implements Action1 {
    private final IntroPriceChartFragment arg$1;

    private IntroPriceChartFragment$$Lambda$2(IntroPriceChartFragment introPriceChartFragment) {
        this.arg$1 = introPriceChartFragment;
    }

    public static Action1 lambdaFactory$(IntroPriceChartFragment introPriceChartFragment) {
        return new IntroPriceChartFragment$$Lambda$2(introPriceChartFragment);
    }

    public void call(Object obj) {
        this.arg$1.mPresenter.onPriceHighlighted((HighlightedPrice) obj);
    }
}
