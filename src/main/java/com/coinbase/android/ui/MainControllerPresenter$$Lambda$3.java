package com.coinbase.android.ui;

import com.coinbase.android.ui.BottomNavigationItem.Type;
import rx.functions.Action1;

final /* synthetic */ class MainControllerPresenter$$Lambda$3 implements Action1 {
    private final MainControllerPresenter arg$1;

    private MainControllerPresenter$$Lambda$3(MainControllerPresenter mainControllerPresenter) {
        this.arg$1 = mainControllerPresenter;
    }

    public static Action1 lambdaFactory$(MainControllerPresenter mainControllerPresenter) {
        return new MainControllerPresenter$$Lambda$3(mainControllerPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mMainScreen.switchTo((Type) obj);
    }
}
