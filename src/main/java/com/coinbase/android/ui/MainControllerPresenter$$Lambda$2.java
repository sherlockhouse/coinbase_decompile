package com.coinbase.android.ui;

import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import rx.functions.Func1;

final /* synthetic */ class MainControllerPresenter$$Lambda$2 implements Func1 {
    private static final MainControllerPresenter$$Lambda$2 instance = new MainControllerPresenter$$Lambda$2();

    private MainControllerPresenter$$Lambda$2() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return ((PageDestination) obj).getBottomNavigationItem();
    }
}
