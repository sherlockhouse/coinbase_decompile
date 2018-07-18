package com.coinbase.android.ui;

import com.coinbase.android.ui.ModalControllerLifeCycle.ModalDestination;
import rx.functions.Action1;

final /* synthetic */ class MainControllerPresenter$$Lambda$5 implements Action1 {
    private final MainControllerPresenter arg$1;

    private MainControllerPresenter$$Lambda$5(MainControllerPresenter mainControllerPresenter) {
        this.arg$1 = mainControllerPresenter;
    }

    public static Action1 lambdaFactory$(MainControllerPresenter mainControllerPresenter) {
        return new MainControllerPresenter$$Lambda$5(mainControllerPresenter);
    }

    public void call(Object obj) {
        MainControllerPresenter.lambda$subscribeModalController$4(this.arg$1, (ModalDestination) obj);
    }
}
