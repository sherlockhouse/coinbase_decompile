package com.coinbase.android.idology;

import java.util.List;
import rx.functions.Func1;

final /* synthetic */ class IdologyFormPresenter$$Lambda$12 implements Func1 {
    private static final IdologyFormPresenter$$Lambda$12 instance = new IdologyFormPresenter$$Lambda$12();

    private IdologyFormPresenter$$Lambda$12() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return IdologyFormPresenter.lambda$fetchIdologyOptions$11((List) obj);
    }
}
