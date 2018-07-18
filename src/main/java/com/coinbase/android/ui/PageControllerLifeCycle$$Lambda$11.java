package com.coinbase.android.ui;

import rx.functions.Action1;

final /* synthetic */ class PageControllerLifeCycle$$Lambda$11 implements Action1 {
    private final PageControllerLifeCycle arg$1;

    private PageControllerLifeCycle$$Lambda$11(PageControllerLifeCycle pageControllerLifeCycle) {
        this.arg$1 = pageControllerLifeCycle;
    }

    public static Action1 lambdaFactory$(PageControllerLifeCycle pageControllerLifeCycle) {
        return new PageControllerLifeCycle$$Lambda$11(pageControllerLifeCycle);
    }

    public void call(Object obj) {
        this.arg$1.mController.enableBackSubscription();
    }
}
