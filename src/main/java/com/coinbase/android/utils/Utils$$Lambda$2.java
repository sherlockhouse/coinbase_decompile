package com.coinbase.android.utils;

import com.coinbase.android.utils.Utils.URLSpanUnderlineWithColor;
import rx.functions.Func1;

final /* synthetic */ class Utils$$Lambda$2 implements Func1 {
    private final int arg$1;

    private Utils$$Lambda$2(int i) {
        this.arg$1 = i;
    }

    public static Func1 lambdaFactory$(int i) {
        return new Utils$$Lambda$2(i);
    }

    public Object call(Object obj) {
        return new URLSpanUnderlineWithColor((String) obj, this.arg$1);
    }
}
