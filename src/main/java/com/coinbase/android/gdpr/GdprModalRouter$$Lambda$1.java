package com.coinbase.android.gdpr;

import java.util.List;
import rx.functions.Func1;

final /* synthetic */ class GdprModalRouter$$Lambda$1 implements Func1 {
    private final GdprModalRouter arg$1;

    private GdprModalRouter$$Lambda$1(GdprModalRouter gdprModalRouter) {
        this.arg$1 = gdprModalRouter;
    }

    public static Func1 lambdaFactory$(GdprModalRouter gdprModalRouter) {
        return new GdprModalRouter$$Lambda$1(gdprModalRouter);
    }

    public Object call(Object obj) {
        return GdprModalRouter.lambda$route$1(this.arg$1, (List) obj);
    }
}
