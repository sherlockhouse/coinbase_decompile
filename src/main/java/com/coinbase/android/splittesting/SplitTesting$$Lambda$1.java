package com.coinbase.android.splittesting;

import com.coinbase.v2.models.user.Data;
import rx.functions.Func1;

final /* synthetic */ class SplitTesting$$Lambda$1 implements Func1 {
    private static final SplitTesting$$Lambda$1 instance = new SplitTesting$$Lambda$1();

    private SplitTesting$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return SplitTesting.lambda$onCreate$0((Data) obj);
    }
}
