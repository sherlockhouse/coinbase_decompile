package com.coinbase.android.deposits.fiat;

import rx.functions.Action1;

final /* synthetic */ class WithdrawFiatPresenter$$Lambda$15 implements Action1 {
    private final WithdrawFiatPresenter arg$1;

    private WithdrawFiatPresenter$$Lambda$15(WithdrawFiatPresenter withdrawFiatPresenter) {
        this.arg$1 = withdrawFiatPresenter;
    }

    public static Action1 lambdaFactory$(WithdrawFiatPresenter withdrawFiatPresenter) {
        return new WithdrawFiatPresenter$$Lambda$15(withdrawFiatPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Error subscribing to LinkedAccountConnector for getMissingAccountClickedSubject()");
    }
}
