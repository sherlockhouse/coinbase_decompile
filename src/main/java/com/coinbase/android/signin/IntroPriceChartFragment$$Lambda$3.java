package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class IntroPriceChartFragment$$Lambda$3 implements Action1 {
    private final IntroPriceChartFragment arg$1;

    private IntroPriceChartFragment$$Lambda$3(IntroPriceChartFragment introPriceChartFragment) {
        this.arg$1 = introPriceChartFragment;
    }

    public static Action1 lambdaFactory$(IntroPriceChartFragment introPriceChartFragment) {
        return new IntroPriceChartFragment$$Lambda$3(introPriceChartFragment);
    }

    public void call(Object obj) {
        this.arg$1.mPresenter.onPriceChartMotionEvent(((Boolean) obj).booleanValue());
    }
}
