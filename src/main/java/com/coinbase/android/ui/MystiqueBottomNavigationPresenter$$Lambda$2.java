package com.coinbase.android.ui;

import rx.functions.Action1;

final /* synthetic */ class MystiqueBottomNavigationPresenter$$Lambda$2 implements Action1 {
    private final MystiqueBottomNavigationPresenter arg$1;

    private MystiqueBottomNavigationPresenter$$Lambda$2(MystiqueBottomNavigationPresenter mystiqueBottomNavigationPresenter) {
        this.arg$1 = mystiqueBottomNavigationPresenter;
    }

    public static Action1 lambdaFactory$(MystiqueBottomNavigationPresenter mystiqueBottomNavigationPresenter) {
        return new MystiqueBottomNavigationPresenter$$Lambda$2(mystiqueBottomNavigationPresenter);
    }

    public void call(Object obj) {
        this.arg$1.setSelected((BottomNavigationItem) obj);
    }
}
