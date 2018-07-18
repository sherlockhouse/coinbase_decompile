package com.coinbase.android.currencies;

import rx.functions.Action1;

final /* synthetic */ class FetchCurrenciesOnCreateListener$$Lambda$2 implements Action1 {
    private static final FetchCurrenciesOnCreateListener$$Lambda$2 instance = new FetchCurrenciesOnCreateListener$$Lambda$2();

    private FetchCurrenciesOnCreateListener$$Lambda$2() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        FetchCurrenciesOnCreateListener.lambda$onCreate$1((Throwable) obj);
    }
}
