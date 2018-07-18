package com.coinbase.android.idology;

import rx.functions.Action1;

final /* synthetic */ class IdologySourceOfFundsFormPresenter$$Lambda$9 implements Action1 {
    private final IdologySourceOfFundsFormPresenter arg$1;

    private IdologySourceOfFundsFormPresenter$$Lambda$9(IdologySourceOfFundsFormPresenter idologySourceOfFundsFormPresenter) {
        this.arg$1 = idologySourceOfFundsFormPresenter;
    }

    public static Action1 lambdaFactory$(IdologySourceOfFundsFormPresenter idologySourceOfFundsFormPresenter) {
        return new IdologySourceOfFundsFormPresenter$$Lambda$9(idologySourceOfFundsFormPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mSnackBarWrapper.showFailure((Throwable) obj);
    }
}
