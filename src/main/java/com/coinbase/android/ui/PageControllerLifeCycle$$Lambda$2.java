package com.coinbase.android.ui;

import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import rx.functions.Func1;

final /* synthetic */ class PageControllerLifeCycle$$Lambda$2 implements Func1 {
    private final PageControllerLifeCycle arg$1;

    private PageControllerLifeCycle$$Lambda$2(PageControllerLifeCycle pageControllerLifeCycle) {
        this.arg$1 = pageControllerLifeCycle;
    }

    public static Func1 lambdaFactory$(PageControllerLifeCycle pageControllerLifeCycle) {
        return new PageControllerLifeCycle$$Lambda$2(pageControllerLifeCycle);
    }

    public Object call(Object obj) {
        return Boolean.valueOf(this.arg$1.filterThisPageShow((PageDestination) obj));
    }
}
