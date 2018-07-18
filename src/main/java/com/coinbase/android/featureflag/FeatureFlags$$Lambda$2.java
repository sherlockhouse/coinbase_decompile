package com.coinbase.android.featureflag;

import com.coinbase.v2.models.user.Data;
import rx.functions.Func1;

final /* synthetic */ class FeatureFlags$$Lambda$2 implements Func1 {
    private static final FeatureFlags$$Lambda$2 instance = new FeatureFlags$$Lambda$2();

    private FeatureFlags$$Lambda$2() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return ((Data) obj).getFeatureFlags();
    }
}
