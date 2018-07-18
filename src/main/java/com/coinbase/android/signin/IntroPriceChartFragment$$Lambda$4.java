package com.coinbase.android.signin;

import android.view.View;
import java.util.List;

final /* synthetic */ class IntroPriceChartFragment$$Lambda$4 implements Runnable {
    private final View arg$1;
    private final List arg$2;

    private IntroPriceChartFragment$$Lambda$4(View view, List list) {
        this.arg$1 = view;
        this.arg$2 = list;
    }

    public static Runnable lambdaFactory$(View view, List list) {
        return new IntroPriceChartFragment$$Lambda$4(view, list);
    }

    public void run() {
        IntroPriceChartFragment.lambda$updateTouchHitArea$3(this.arg$1, this.arg$2);
    }
}
