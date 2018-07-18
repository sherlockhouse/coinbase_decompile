package com.coinbase.android.idology;

import android.util.Pair;
import java.util.Arrays;
import rx.functions.Func3;

final /* synthetic */ class IdologyFormPresenter$$Lambda$11 implements Func3 {
    private static final IdologyFormPresenter$$Lambda$11 instance = new IdologyFormPresenter$$Lambda$11();

    private IdologyFormPresenter$$Lambda$11() {
    }

    public static Func3 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj, Object obj2, Object obj3) {
        return Arrays.asList(new Pair[]{(Pair) obj, (Pair) obj2, (Pair) obj3});
    }
}
