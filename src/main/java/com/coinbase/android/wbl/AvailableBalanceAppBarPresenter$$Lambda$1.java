package com.coinbase.android.wbl;

import android.os.Bundle;
import android.util.Pair;
import rx.functions.Func3;

final /* synthetic */ class AvailableBalanceAppBarPresenter$$Lambda$1 implements Func3 {
    private final AvailableBalanceAppBarPresenter arg$1;
    private final Bundle arg$2;

    private AvailableBalanceAppBarPresenter$$Lambda$1(AvailableBalanceAppBarPresenter availableBalanceAppBarPresenter, Bundle bundle) {
        this.arg$1 = availableBalanceAppBarPresenter;
        this.arg$2 = bundle;
    }

    public static Func3 lambdaFactory$(AvailableBalanceAppBarPresenter availableBalanceAppBarPresenter, Bundle bundle) {
        return new AvailableBalanceAppBarPresenter$$Lambda$1(availableBalanceAppBarPresenter, bundle);
    }

    public Object call(Object obj, Object obj2, Object obj3) {
        return this.arg$1.combineAvailableBalanceAndExchangeRates((AvailableBalance) obj, (Pair) obj2, (Pair) obj3, this.arg$2);
    }
}
