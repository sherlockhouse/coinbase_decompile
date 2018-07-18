package com.coinbase.android;

import com.coinbase.android.event.ClassConsumableEvent;
import java.util.HashMap;
import rx.functions.Func1;

final /* synthetic */ class MainActivity$$Lambda$3 implements Func1 {
    private static final MainActivity$$Lambda$3 instance = new MainActivity$$Lambda$3();

    private MainActivity$$Lambda$3() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return ((HashMap) ((ClassConsumableEvent) obj).get());
    }
}
