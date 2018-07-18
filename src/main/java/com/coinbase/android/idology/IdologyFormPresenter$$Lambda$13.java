package com.coinbase.android.idology;

import java.util.Map;
import rx.functions.Action1;

final /* synthetic */ class IdologyFormPresenter$$Lambda$13 implements Action1 {
    private final IdologyFormPresenter arg$1;

    private IdologyFormPresenter$$Lambda$13(IdologyFormPresenter idologyFormPresenter) {
        this.arg$1 = idologyFormPresenter;
    }

    public static Action1 lambdaFactory$(IdologyFormPresenter idologyFormPresenter) {
        return new IdologyFormPresenter$$Lambda$13(idologyFormPresenter);
    }

    public void call(Object obj) {
        IdologyFormPresenter.lambda$fetchIdologyOptions$12(this.arg$1, (Map) obj);
    }
}
