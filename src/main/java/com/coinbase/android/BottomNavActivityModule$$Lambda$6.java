package com.coinbase.android;

import android.os.Bundle;
import rx.functions.Func1;

final /* synthetic */ class BottomNavActivityModule$$Lambda$6 implements Func1 {
    private static final BottomNavActivityModule$$Lambda$6 instance = new BottomNavActivityModule$$Lambda$6();

    private BottomNavActivityModule$$Lambda$6() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return BottomNavActivityModule.lambda$providesModalControllerFuncs$5((Bundle) obj);
    }
}
