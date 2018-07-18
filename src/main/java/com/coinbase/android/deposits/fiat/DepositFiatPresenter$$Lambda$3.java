package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ui.NumericKeypadConnector.KeypadType;
import rx.functions.Action1;

final /* synthetic */ class DepositFiatPresenter$$Lambda$3 implements Action1 {
    private final DepositFiatPresenter arg$1;

    private DepositFiatPresenter$$Lambda$3(DepositFiatPresenter depositFiatPresenter) {
        this.arg$1 = depositFiatPresenter;
    }

    public static Action1 lambdaFactory$(DepositFiatPresenter depositFiatPresenter) {
        return new DepositFiatPresenter$$Lambda$3(depositFiatPresenter);
    }

    public void call(Object obj) {
        this.arg$1.onKeystrokeEntered(KeypadType.DIGIT, ((Character) obj).charValue());
    }
}
