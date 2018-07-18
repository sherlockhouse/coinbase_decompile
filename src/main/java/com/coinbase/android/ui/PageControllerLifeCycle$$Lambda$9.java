package com.coinbase.android.ui;

import android.view.Menu;
import rx.functions.Action1;

final /* synthetic */ class PageControllerLifeCycle$$Lambda$9 implements Action1 {
    private final PageControllerLifeCycle arg$1;
    private final Menu arg$2;

    private PageControllerLifeCycle$$Lambda$9(PageControllerLifeCycle pageControllerLifeCycle, Menu menu) {
        this.arg$1 = pageControllerLifeCycle;
        this.arg$2 = menu;
    }

    public static Action1 lambdaFactory$(PageControllerLifeCycle pageControllerLifeCycle, Menu menu) {
        return new PageControllerLifeCycle$$Lambda$9(pageControllerLifeCycle, menu);
    }

    public void call(Object obj) {
        this.arg$1.onPagePrepareOptionsMenu(this.arg$2);
    }
}
