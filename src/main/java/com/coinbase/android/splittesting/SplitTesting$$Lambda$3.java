package com.coinbase.android.splittesting;

import java.util.List;
import rx.functions.Action1;

final /* synthetic */ class SplitTesting$$Lambda$3 implements Action1 {
    private final SplitTesting arg$1;

    private SplitTesting$$Lambda$3(SplitTesting splitTesting) {
        this.arg$1 = splitTesting;
    }

    public static Action1 lambdaFactory$(SplitTesting splitTesting) {
        return new SplitTesting$$Lambda$3(splitTesting);
    }

    public void call(Object obj) {
        this.arg$1.update((List) obj);
    }
}
