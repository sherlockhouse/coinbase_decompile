package com.coinbase.android.featureflag;

import java.util.List;
import rx.functions.Action1;

final /* synthetic */ class FeatureFlags$$Lambda$3 implements Action1 {
    private final FeatureFlags arg$1;

    private FeatureFlags$$Lambda$3(FeatureFlags featureFlags) {
        this.arg$1 = featureFlags;
    }

    public static Action1 lambdaFactory$(FeatureFlags featureFlags) {
        return new FeatureFlags$$Lambda$3(featureFlags);
    }

    public void call(Object obj) {
        this.arg$1.update((List) obj);
    }
}
