package com.coinbase.android.splittesting;

import com.coinbase.v2.models.user.Data;
import rx.functions.Func1;

final /* synthetic */ class SplitTesting$$Lambda$2 implements Func1 {
    private static final SplitTesting$$Lambda$2 instance = new SplitTesting$$Lambda$2();

    private SplitTesting$$Lambda$2() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return ((Data) obj).getSplitTestGroups();
    }
}
