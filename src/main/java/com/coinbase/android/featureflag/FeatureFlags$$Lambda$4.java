package com.coinbase.android.featureflag;

import rx.functions.Action1;

final /* synthetic */ class FeatureFlags$$Lambda$4 implements Action1 {
    private final FeatureFlags arg$1;

    private FeatureFlags$$Lambda$4(FeatureFlags featureFlags) {
        this.arg$1 = featureFlags;
    }

    public static Action1 lambdaFactory$(FeatureFlags featureFlags) {
        return new FeatureFlags$$Lambda$4(featureFlags);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Error subscribing to feature flags", (Throwable) obj);
    }
}
