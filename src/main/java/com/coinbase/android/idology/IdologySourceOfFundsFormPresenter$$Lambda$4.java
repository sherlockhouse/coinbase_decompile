package com.coinbase.android.idology;

import java.util.List;
import rx.functions.Action1;

final /* synthetic */ class IdologySourceOfFundsFormPresenter$$Lambda$4 implements Action1 {
    private final IdologySourceOfFundsFormPresenter arg$1;

    private IdologySourceOfFundsFormPresenter$$Lambda$4(IdologySourceOfFundsFormPresenter idologySourceOfFundsFormPresenter) {
        this.arg$1 = idologySourceOfFundsFormPresenter;
    }

    public static Action1 lambdaFactory$(IdologySourceOfFundsFormPresenter idologySourceOfFundsFormPresenter) {
        return new IdologySourceOfFundsFormPresenter$$Lambda$4(idologySourceOfFundsFormPresenter);
    }

    public void call(Object obj) {
        this.arg$1.handleErrors((List) obj);
    }
}
