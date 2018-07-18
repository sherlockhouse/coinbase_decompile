package com.coinbase.android.idology;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class IdologyNameFormPresenter$$Lambda$2 implements Action1 {
    private final IdologyNameFormPresenter arg$1;

    private IdologyNameFormPresenter$$Lambda$2(IdologyNameFormPresenter idologyNameFormPresenter) {
        this.arg$1 = idologyNameFormPresenter;
    }

    public static Action1 lambdaFactory$(IdologyNameFormPresenter idologyNameFormPresenter) {
        return new IdologyNameFormPresenter$$Lambda$2(idologyNameFormPresenter);
    }

    public void call(Object obj) {
        IdologyNameFormPresenter.lambda$onShow$1(this.arg$1, (Pair) obj);
    }
}
