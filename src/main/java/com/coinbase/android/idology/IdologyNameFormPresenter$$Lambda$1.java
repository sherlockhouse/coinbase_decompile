package com.coinbase.android.idology;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class IdologyNameFormPresenter$$Lambda$1 implements Func1 {
    private static final IdologyNameFormPresenter$$Lambda$1 instance = new IdologyNameFormPresenter$$Lambda$1();

    private IdologyNameFormPresenter$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return IdologyNameFormPresenter.lambda$onShow$0((Pair) obj);
    }
}
