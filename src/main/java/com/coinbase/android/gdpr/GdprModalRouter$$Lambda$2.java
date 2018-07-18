package com.coinbase.android.gdpr;

import java.util.List;
import rx.functions.Action0;

final /* synthetic */ class GdprModalRouter$$Lambda$2 implements Action0 {
    private final GdprModalRouter arg$1;
    private final List arg$2;

    private GdprModalRouter$$Lambda$2(GdprModalRouter gdprModalRouter, List list) {
        this.arg$1 = gdprModalRouter;
        this.arg$2 = list;
    }

    public static Action0 lambdaFactory$(GdprModalRouter gdprModalRouter, List list) {
        return new GdprModalRouter$$Lambda$2(gdprModalRouter, list);
    }

    public void call() {
        this.arg$1.mOnboardingRouter.routeToNext(this.arg$2);
    }
}
