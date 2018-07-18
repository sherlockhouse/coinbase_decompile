package com.coinbase.android.ui;

import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import rx.functions.Func1;

final /* synthetic */ class PageControllerLifeCycle$$Lambda$6 implements Func1 {
    private final PageControllerLifeCycle arg$1;

    private PageControllerLifeCycle$$Lambda$6(PageControllerLifeCycle pageControllerLifeCycle) {
        this.arg$1 = pageControllerLifeCycle;
    }

    public static Func1 lambdaFactory$(PageControllerLifeCycle pageControllerLifeCycle) {
        return new PageControllerLifeCycle$$Lambda$6(pageControllerLifeCycle);
    }

    public Object call(Object obj) {
        return Boolean.valueOf(this.arg$1.filterThisPageHide((PageDestination) obj));
    }
}
