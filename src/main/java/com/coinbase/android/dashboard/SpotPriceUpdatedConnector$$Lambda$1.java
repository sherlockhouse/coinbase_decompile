package com.coinbase.android.dashboard;

import android.util.Pair;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

final /* synthetic */ class SpotPriceUpdatedConnector$$Lambda$1 implements Action1 {
    private final SpotPriceUpdatedConnector arg$1;
    private final String arg$2;
    private final String arg$3;
    private final BehaviorSubject arg$4;

    private SpotPriceUpdatedConnector$$Lambda$1(SpotPriceUpdatedConnector spotPriceUpdatedConnector, String str, String str2, BehaviorSubject behaviorSubject) {
        this.arg$1 = spotPriceUpdatedConnector;
        this.arg$2 = str;
        this.arg$3 = str2;
        this.arg$4 = behaviorSubject;
    }

    public static Action1 lambdaFactory$(SpotPriceUpdatedConnector spotPriceUpdatedConnector, String str, String str2, BehaviorSubject behaviorSubject) {
        return new SpotPriceUpdatedConnector$$Lambda$1(spotPriceUpdatedConnector, str, str2, behaviorSubject);
    }

    public void call(Object obj) {
        SpotPriceUpdatedConnector.lambda$fetch$0(this.arg$1, this.arg$2, this.arg$3, this.arg$4, (Pair) obj);
    }
}
