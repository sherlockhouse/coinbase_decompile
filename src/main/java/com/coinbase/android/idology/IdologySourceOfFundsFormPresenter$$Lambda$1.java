package com.coinbase.android.idology;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class IdologySourceOfFundsFormPresenter$$Lambda$1 implements Action1 {
    private final IdologySourceOfFundsFormPresenter arg$1;

    private IdologySourceOfFundsFormPresenter$$Lambda$1(IdologySourceOfFundsFormPresenter idologySourceOfFundsFormPresenter) {
        this.arg$1 = idologySourceOfFundsFormPresenter;
    }

    public static Action1 lambdaFactory$(IdologySourceOfFundsFormPresenter idologySourceOfFundsFormPresenter) {
        return new IdologySourceOfFundsFormPresenter$$Lambda$1(idologySourceOfFundsFormPresenter);
    }

    public void call(Object obj) {
        this.arg$1.idologyOptionChosen((Pair) obj);
    }
}
