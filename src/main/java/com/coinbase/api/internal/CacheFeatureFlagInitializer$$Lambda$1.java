package com.coinbase.api.internal;

import rx.functions.Action1;

final /* synthetic */ class CacheFeatureFlagInitializer$$Lambda$1 implements Action1 {
    private final CacheFeatureFlagInitializer arg$1;

    private CacheFeatureFlagInitializer$$Lambda$1(CacheFeatureFlagInitializer cacheFeatureFlagInitializer) {
        this.arg$1 = cacheFeatureFlagInitializer;
    }

    public static Action1 lambdaFactory$(CacheFeatureFlagInitializer cacheFeatureFlagInitializer) {
        return new CacheFeatureFlagInitializer$$Lambda$1(cacheFeatureFlagInitializer);
    }

    public void call(Object obj) {
        this.arg$1.mClient.setForcedCacheEnabled(((Boolean) obj).booleanValue());
    }
}
