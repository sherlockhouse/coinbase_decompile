package com.coinbase.android.dashboard;

import android.util.Pair;
import java.util.Arrays;
import rx.functions.Func3;

final /* synthetic */ class DashboardMainPresenter$$Lambda$9 implements Func3 {
    private static final DashboardMainPresenter$$Lambda$9 instance = new DashboardMainPresenter$$Lambda$9();

    private DashboardMainPresenter$$Lambda$9() {
    }

    public static Func3 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj, Object obj2, Object obj3) {
        return Arrays.asList(new Pair[]{(Pair) obj, (Pair) obj2, (Pair) obj3});
    }
}
