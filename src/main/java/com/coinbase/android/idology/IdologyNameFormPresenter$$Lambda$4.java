package com.coinbase.android.idology;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class IdologyNameFormPresenter$$Lambda$4 implements Func1 {
    private static final IdologyNameFormPresenter$$Lambda$4 instance = new IdologyNameFormPresenter$$Lambda$4();

    private IdologyNameFormPresenter$$Lambda$4() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return IdologyNameFormPresenter.lambda$onShow$3((Pair) obj);
    }
}
