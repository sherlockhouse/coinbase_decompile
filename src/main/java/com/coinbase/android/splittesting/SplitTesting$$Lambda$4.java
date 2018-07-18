package com.coinbase.android.splittesting;

import rx.functions.Action1;

final /* synthetic */ class SplitTesting$$Lambda$4 implements Action1 {
    private final SplitTesting arg$1;

    private SplitTesting$$Lambda$4(SplitTesting splitTesting) {
        this.arg$1 = splitTesting;
    }

    public static Action1 lambdaFactory$(SplitTesting splitTesting) {
        return new SplitTesting$$Lambda$4(splitTesting);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Error subscribing to split tests", (Throwable) obj);
    }
}
