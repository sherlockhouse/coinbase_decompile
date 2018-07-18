package com.coinbase.android.dashboard;

import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

final /* synthetic */ class SpotPriceUpdatedConnector$$Lambda$2 implements Action1 {
    private final SpotPriceUpdatedConnector arg$1;
    private final BehaviorSubject arg$2;
    private final String arg$3;
    private final String arg$4;

    private SpotPriceUpdatedConnector$$Lambda$2(SpotPriceUpdatedConnector spotPriceUpdatedConnector, BehaviorSubject behaviorSubject, String str, String str2) {
        this.arg$1 = spotPriceUpdatedConnector;
        this.arg$2 = behaviorSubject;
        this.arg$3 = str;
        this.arg$4 = str2;
    }

    public static Action1 lambdaFactory$(SpotPriceUpdatedConnector spotPriceUpdatedConnector, BehaviorSubject behaviorSubject, String str, String str2) {
        return new SpotPriceUpdatedConnector$$Lambda$2(spotPriceUpdatedConnector, behaviorSubject, str, str2);
    }

    public void call(Object obj) {
        SpotPriceUpdatedConnector.lambda$fetch$1(this.arg$1, this.arg$2, this.arg$3, this.arg$4, (Throwable) obj);
    }
}
