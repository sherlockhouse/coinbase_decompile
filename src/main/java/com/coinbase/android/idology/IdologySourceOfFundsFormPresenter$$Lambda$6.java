package com.coinbase.android.idology;

import android.util.Pair;
import java.util.Arrays;
import rx.functions.Func3;

final /* synthetic */ class IdologySourceOfFundsFormPresenter$$Lambda$6 implements Func3 {
    private static final IdologySourceOfFundsFormPresenter$$Lambda$6 instance = new IdologySourceOfFundsFormPresenter$$Lambda$6();

    private IdologySourceOfFundsFormPresenter$$Lambda$6() {
    }

    public static Func3 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj, Object obj2, Object obj3) {
        return Arrays.asList(new Pair[]{(Pair) obj, (Pair) obj2, (Pair) obj3});
    }
}
