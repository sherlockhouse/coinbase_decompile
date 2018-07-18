package com.coinbase.android;

import rx.functions.Action1;

final /* synthetic */ class MainPresenter$$Lambda$2 implements Action1 {
    private final MainPresenter arg$1;

    private MainPresenter$$Lambda$2(MainPresenter mainPresenter) {
        this.arg$1 = mainPresenter;
    }

    public static Action1 lambdaFactory$(MainPresenter mainPresenter) {
        return new MainPresenter$$Lambda$2(mainPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Couldn't subscribe to getCryptoCurrenciesObservable, shouldn't happen", (Throwable) obj);
    }
}
