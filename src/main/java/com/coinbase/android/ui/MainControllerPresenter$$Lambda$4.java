package com.coinbase.android.ui;

import com.coinbase.android.ui.ModalControllerLifeCycle.ModalDestination;
import rx.functions.Func1;

final /* synthetic */ class MainControllerPresenter$$Lambda$4 implements Func1 {
    private static final MainControllerPresenter$$Lambda$4 instance = new MainControllerPresenter$$Lambda$4();

    private MainControllerPresenter$$Lambda$4() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return MainControllerPresenter.lambda$subscribeModalController$3((ModalDestination) obj);
    }
}
