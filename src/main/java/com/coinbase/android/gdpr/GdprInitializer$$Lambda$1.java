package com.coinbase.android.gdpr;

import com.coinbase.v2.models.user.Data;
import rx.functions.Func2;

final /* synthetic */ class GdprInitializer$$Lambda$1 implements Func2 {
    private static final GdprInitializer$$Lambda$1 instance = new GdprInitializer$$Lambda$1();

    private GdprInitializer$$Lambda$1() {
    }

    public static Func2 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj, Object obj2) {
        return GdprInitializer.lambda$onCreate$0((Data) obj, (Boolean) obj2);
    }
}
