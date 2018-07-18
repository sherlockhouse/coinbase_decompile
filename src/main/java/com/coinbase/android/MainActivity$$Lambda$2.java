package com.coinbase.android;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.functions.Func1;

final /* synthetic */ class MainActivity$$Lambda$2 implements Func1 {
    private static final MainActivity$$Lambda$2 instance = new MainActivity$$Lambda$2();

    private MainActivity$$Lambda$2() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return MainActivity.lambda$onCreate$1((ClassConsumableEvent) obj);
    }
}
