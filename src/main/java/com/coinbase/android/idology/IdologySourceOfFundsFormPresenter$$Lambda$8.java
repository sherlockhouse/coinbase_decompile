package com.coinbase.android.idology;

import java.util.Map;
import rx.functions.Action1;

final /* synthetic */ class IdologySourceOfFundsFormPresenter$$Lambda$8 implements Action1 {
    private final IdologySourceOfFundsFormPresenter arg$1;

    private IdologySourceOfFundsFormPresenter$$Lambda$8(IdologySourceOfFundsFormPresenter idologySourceOfFundsFormPresenter) {
        this.arg$1 = idologySourceOfFundsFormPresenter;
    }

    public static Action1 lambdaFactory$(IdologySourceOfFundsFormPresenter idologySourceOfFundsFormPresenter) {
        return new IdologySourceOfFundsFormPresenter$$Lambda$8(idologySourceOfFundsFormPresenter);
    }

    public void call(Object obj) {
        IdologySourceOfFundsFormPresenter.lambda$fetchIdologyOptions$7(this.arg$1, (Map) obj);
    }
}
