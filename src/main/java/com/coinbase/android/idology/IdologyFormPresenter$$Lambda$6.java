package com.coinbase.android.idology;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class IdologyFormPresenter$$Lambda$6 implements Func1 {
    private static final IdologyFormPresenter$$Lambda$6 instance = new IdologyFormPresenter$$Lambda$6();

    private IdologyFormPresenter$$Lambda$6() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return IdologyFormPresenter.lambda$onShow$5((Pair) obj);
    }
}
