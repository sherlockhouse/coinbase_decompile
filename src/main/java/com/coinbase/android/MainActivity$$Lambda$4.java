package com.coinbase.android;

import java.util.HashMap;
import rx.functions.Action1;

final /* synthetic */ class MainActivity$$Lambda$4 implements Action1 {
    private final MainActivity arg$1;

    private MainActivity$$Lambda$4(MainActivity mainActivity) {
        this.arg$1 = mainActivity;
    }

    public static Action1 lambdaFactory$(MainActivity mainActivity) {
        return new MainActivity$$Lambda$4(mainActivity);
    }

    public void call(Object obj) {
        this.arg$1.stateDisclosureFinished((HashMap) obj);
    }
}
