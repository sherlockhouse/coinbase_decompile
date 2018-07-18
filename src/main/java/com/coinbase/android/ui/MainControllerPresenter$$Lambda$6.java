package com.coinbase.android.ui;

import rx.functions.Action1;

final /* synthetic */ class MainControllerPresenter$$Lambda$6 implements Action1 {
    private final MainControllerPresenter arg$1;

    private MainControllerPresenter$$Lambda$6(MainControllerPresenter mainControllerPresenter) {
        this.arg$1 = mainControllerPresenter;
    }

    public static Action1 lambdaFactory$(MainControllerPresenter mainControllerPresenter) {
        return new MainControllerPresenter$$Lambda$6(mainControllerPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Failed switching to modal controller, shouldn't happen", (Throwable) obj);
    }
}
