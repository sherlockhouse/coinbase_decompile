package com.coinbase.android.featureflag;

import com.coinbase.v2.models.user.Data;
import rx.functions.Func1;

final /* synthetic */ class FeatureFlags$$Lambda$1 implements Func1 {
    private static final FeatureFlags$$Lambda$1 instance = new FeatureFlags$$Lambda$1();

    private FeatureFlags$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return FeatureFlags.lambda$onCreate$0((Data) obj);
    }
}
