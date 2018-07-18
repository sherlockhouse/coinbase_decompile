package com.coinbase.android;

import rx.functions.Action1;

final /* synthetic */ class FetchAdvertisingIdOnCreateListener$$Lambda$2 implements Action1 {
    private final FetchAdvertisingIdOnCreateListener arg$1;

    private FetchAdvertisingIdOnCreateListener$$Lambda$2(FetchAdvertisingIdOnCreateListener fetchAdvertisingIdOnCreateListener) {
        this.arg$1 = fetchAdvertisingIdOnCreateListener;
    }

    public static Action1 lambdaFactory$(FetchAdvertisingIdOnCreateListener fetchAdvertisingIdOnCreateListener) {
        return new FetchAdvertisingIdOnCreateListener$$Lambda$2(fetchAdvertisingIdOnCreateListener);
    }

    public void call(Object obj) {
        this.arg$1.mCoinbaseInternal.setAdvertisingId((String) obj);
    }
}
