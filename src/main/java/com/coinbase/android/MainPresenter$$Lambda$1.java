package com.coinbase.android;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class MainPresenter$$Lambda$1 implements Action1 {
    private final MainPresenter arg$1;

    private MainPresenter$$Lambda$1(MainPresenter mainPresenter) {
        this.arg$1 = mainPresenter;
    }

    public static Action1 lambdaFactory$(MainPresenter mainPresenter) {
        return new MainPresenter$$Lambda$1(mainPresenter);
    }

    public void call(Object obj) {
        MainPresenter.lambda$refreshCurrencyList$0(this.arg$1, (Pair) obj);
    }
}
