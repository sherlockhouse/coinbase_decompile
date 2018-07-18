package com.coinbase.android.ui;

import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import rx.functions.Action1;

final /* synthetic */ class PageControllerLifeCycle$$Lambda$3 implements Action1 {
    private final PageControllerLifeCycle arg$1;

    private PageControllerLifeCycle$$Lambda$3(PageControllerLifeCycle pageControllerLifeCycle) {
        this.arg$1 = pageControllerLifeCycle;
    }

    public static Action1 lambdaFactory$(PageControllerLifeCycle pageControllerLifeCycle) {
        return new PageControllerLifeCycle$$Lambda$3(pageControllerLifeCycle);
    }

    public void call(Object obj) {
        PageControllerLifeCycle.lambda$onAttach$2(this.arg$1, (PageDestination) obj);
    }
}
