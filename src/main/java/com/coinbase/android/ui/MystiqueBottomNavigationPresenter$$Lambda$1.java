package com.coinbase.android.ui;

import com.coinbase.android.ui.PageControllerLifeCycle.PageDestination;
import rx.functions.Func1;

final /* synthetic */ class MystiqueBottomNavigationPresenter$$Lambda$1 implements Func1 {
    private final MystiqueBottomNavigationPresenter arg$1;

    private MystiqueBottomNavigationPresenter$$Lambda$1(MystiqueBottomNavigationPresenter mystiqueBottomNavigationPresenter) {
        this.arg$1 = mystiqueBottomNavigationPresenter;
    }

    public static Func1 lambdaFactory$(MystiqueBottomNavigationPresenter mystiqueBottomNavigationPresenter) {
        return new MystiqueBottomNavigationPresenter$$Lambda$1(mystiqueBottomNavigationPresenter);
    }

    public Object call(Object obj) {
        return ((BottomNavigationItem) this.arg$1.mBottomNavigationItemMap.get(((PageDestination) obj).getBottomNavigationItem()));
    }
}
