package com.coinbase.android;

import java.util.ArrayList;
import rx.functions.Func0;

final /* synthetic */ class NoOpDebugModule$$Lambda$1 implements Func0 {
    private static final NoOpDebugModule$$Lambda$1 instance = new NoOpDebugModule$$Lambda$1();

    private NoOpDebugModule$$Lambda$1() {
    }

    public static Func0 lambdaFactory$() {
        return instance;
    }

    public Object call() {
        return new ArrayList();
    }
}
