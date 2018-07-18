package com.coinbase.android.idology;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class IdologyFormPresenter$$Lambda$3 implements Func1 {
    private static final IdologyFormPresenter$$Lambda$3 instance = new IdologyFormPresenter$$Lambda$3();

    private IdologyFormPresenter$$Lambda$3() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return IdologyFormPresenter.lambda$onShow$2((Pair) obj);
    }
}
