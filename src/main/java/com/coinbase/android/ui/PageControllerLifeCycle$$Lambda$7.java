package com.coinbase.android.ui;

import rx.functions.Action1;

final /* synthetic */ class PageControllerLifeCycle$$Lambda$7 implements Action1 {
    private final PageControllerLifeCycle arg$1;

    private PageControllerLifeCycle$$Lambda$7(PageControllerLifeCycle pageControllerLifeCycle) {
        this.arg$1 = pageControllerLifeCycle;
    }

    public static Action1 lambdaFactory$(PageControllerLifeCycle pageControllerLifeCycle) {
        return new PageControllerLifeCycle$$Lambda$7(pageControllerLifeCycle);
    }

    public void call(Object obj) {
        this.arg$1.onPageHide();
    }
}
