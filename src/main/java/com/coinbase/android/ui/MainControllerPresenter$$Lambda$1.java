package com.coinbase.android.ui;

import rx.functions.Action1;

final /* synthetic */ class MainControllerPresenter$$Lambda$1 implements Action1 {
    private final MainControllerPresenter arg$1;

    private MainControllerPresenter$$Lambda$1(MainControllerPresenter mainControllerPresenter) {
        this.arg$1 = mainControllerPresenter;
    }

    public static Action1 lambdaFactory$(MainControllerPresenter mainControllerPresenter) {
        return new MainControllerPresenter$$Lambda$1(mainControllerPresenter);
    }

    public void call(Object obj) {
        MainControllerPresenter.lambda$onCreateView$0(this.arg$1, (Boolean) obj);
    }
}
