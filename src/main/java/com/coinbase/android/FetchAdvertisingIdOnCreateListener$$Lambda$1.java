package com.coinbase.android;

import rx.Observable.OnSubscribe;
import rx.Subscriber;

final /* synthetic */ class FetchAdvertisingIdOnCreateListener$$Lambda$1 implements OnSubscribe {
    private final FetchAdvertisingIdOnCreateListener arg$1;

    private FetchAdvertisingIdOnCreateListener$$Lambda$1(FetchAdvertisingIdOnCreateListener fetchAdvertisingIdOnCreateListener) {
        this.arg$1 = fetchAdvertisingIdOnCreateListener;
    }

    public static OnSubscribe lambdaFactory$(FetchAdvertisingIdOnCreateListener fetchAdvertisingIdOnCreateListener) {
        return new FetchAdvertisingIdOnCreateListener$$Lambda$1(fetchAdvertisingIdOnCreateListener);
    }

    public void call(Object obj) {
        FetchAdvertisingIdOnCreateListener.lambda$onCreate$0(this.arg$1, (Subscriber) obj);
    }
}
