package com.coinbase.android.currencies;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class FetchCurrenciesOnCreateListener$$Lambda$1 implements Action1 {
    private final FetchCurrenciesOnCreateListener arg$1;

    private FetchCurrenciesOnCreateListener$$Lambda$1(FetchCurrenciesOnCreateListener fetchCurrenciesOnCreateListener) {
        this.arg$1 = fetchCurrenciesOnCreateListener;
    }

    public static Action1 lambdaFactory$(FetchCurrenciesOnCreateListener fetchCurrenciesOnCreateListener) {
        return new FetchCurrenciesOnCreateListener$$Lambda$1(fetchCurrenciesOnCreateListener);
    }

    public void call(Object obj) {
        FetchCurrenciesOnCreateListener.lambda$onCreate$0(this.arg$1, (Pair) obj);
    }
}
