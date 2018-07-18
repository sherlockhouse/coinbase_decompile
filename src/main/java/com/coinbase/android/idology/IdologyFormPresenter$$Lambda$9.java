package com.coinbase.android.idology;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class IdologyFormPresenter$$Lambda$9 implements Action1 {
    private final IdologyFormPresenter arg$1;

    private IdologyFormPresenter$$Lambda$9(IdologyFormPresenter idologyFormPresenter) {
        this.arg$1 = idologyFormPresenter;
    }

    public static Action1 lambdaFactory$(IdologyFormPresenter idologyFormPresenter) {
        return new IdologyFormPresenter$$Lambda$9(idologyFormPresenter);
    }

    public void call(Object obj) {
        IdologyFormPresenter.lambda$submitForm$8(this.arg$1, (Pair) obj);
    }
}
