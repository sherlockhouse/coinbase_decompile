package com.coinbase.android.utils;

import com.coinbase.android.utils.Utils.URLSpanNoUnderline;
import rx.functions.Func1;

final /* synthetic */ class Utils$$Lambda$1 implements Func1 {
    private static final Utils$$Lambda$1 instance = new Utils$$Lambda$1();

    private Utils$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return new URLSpanNoUnderline((String) obj);
    }
}
