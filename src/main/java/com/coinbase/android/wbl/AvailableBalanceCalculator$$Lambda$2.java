package com.coinbase.android.wbl;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class AvailableBalanceCalculator$$Lambda$2 implements Func1 {
    private final AvailableBalanceCalculator arg$1;

    private AvailableBalanceCalculator$$Lambda$2(AvailableBalanceCalculator availableBalanceCalculator) {
        this.arg$1 = availableBalanceCalculator;
    }

    public static Func1 lambdaFactory$(AvailableBalanceCalculator availableBalanceCalculator) {
        return new AvailableBalanceCalculator$$Lambda$2(availableBalanceCalculator);
    }

    public Object call(Object obj) {
        return AvailableBalanceCalculator.lambda$get$1(this.arg$1, (Pair) obj);
    }
}
