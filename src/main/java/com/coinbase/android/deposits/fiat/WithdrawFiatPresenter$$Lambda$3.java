package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ui.NumericKeypadConnector.KeypadType;
import rx.functions.Action1;

final /* synthetic */ class WithdrawFiatPresenter$$Lambda$3 implements Action1 {
    private final WithdrawFiatPresenter arg$1;

    private WithdrawFiatPresenter$$Lambda$3(WithdrawFiatPresenter withdrawFiatPresenter) {
        this.arg$1 = withdrawFiatPresenter;
    }

    public static Action1 lambdaFactory$(WithdrawFiatPresenter withdrawFiatPresenter) {
        return new WithdrawFiatPresenter$$Lambda$3(withdrawFiatPresenter);
    }

    public void call(Object obj) {
        this.arg$1.onKeystrokeEntered(KeypadType.DIGIT, ((Character) obj).charValue());
    }
}
