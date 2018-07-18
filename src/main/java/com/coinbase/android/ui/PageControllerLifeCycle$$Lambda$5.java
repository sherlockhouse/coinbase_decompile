package com.coinbase.android.ui;

import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import rx.functions.Action1;

final /* synthetic */ class PageControllerLifeCycle$$Lambda$5 implements Action1 {
    private final PageControllerLifeCycle arg$1;

    private PageControllerLifeCycle$$Lambda$5(PageControllerLifeCycle pageControllerLifeCycle) {
        this.arg$1 = pageControllerLifeCycle;
    }

    public static Action1 lambdaFactory$(PageControllerLifeCycle pageControllerLifeCycle) {
        return new PageControllerLifeCycle$$Lambda$5(pageControllerLifeCycle);
    }

    public void call(Object obj) {
        this.arg$1.pushPage((PageDestination) obj);
    }
}
