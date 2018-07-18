package com.coinbase.android.idology;

import java.util.List;
import rx.functions.Action1;

final /* synthetic */ class IdologyNameFormPresenter$$Lambda$8 implements Action1 {
    private final IdologyNameFormPresenter arg$1;

    private IdologyNameFormPresenter$$Lambda$8(IdologyNameFormPresenter idologyNameFormPresenter) {
        this.arg$1 = idologyNameFormPresenter;
    }

    public static Action1 lambdaFactory$(IdologyNameFormPresenter idologyNameFormPresenter) {
        return new IdologyNameFormPresenter$$Lambda$8(idologyNameFormPresenter);
    }

    public void call(Object obj) {
        this.arg$1.handleErrors((List) obj);
    }
}
