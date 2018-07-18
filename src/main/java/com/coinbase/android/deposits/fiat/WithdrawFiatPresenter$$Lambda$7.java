package com.coinbase.android.deposits.fiat;

import android.util.Pair;
import com.coinbase.android.ui.NumericKeypadConnector.KeypadType;
import rx.functions.Action1;

final /* synthetic */ class WithdrawFiatPresenter$$Lambda$7 implements Action1 {
    private final WithdrawFiatPresenter arg$1;

    private WithdrawFiatPresenter$$Lambda$7(WithdrawFiatPresenter withdrawFiatPresenter) {
        this.arg$1 = withdrawFiatPresenter;
    }

    public static Action1 lambdaFactory$(WithdrawFiatPresenter withdrawFiatPresenter) {
        return new WithdrawFiatPresenter$$Lambda$7(withdrawFiatPresenter);
    }

    public void call(Object obj) {
        this.arg$1.onKeystrokeEntered((KeypadType) ((Pair) obj).first, ((Character) ((Pair) obj).second).charValue());
    }
}
