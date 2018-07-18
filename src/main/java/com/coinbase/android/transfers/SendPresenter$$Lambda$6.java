package com.coinbase.android.transfers;

import com.coinbase.android.ui.NumericKeypadConnector;
import com.coinbase.android.ui.NumericKeypadConnector.KeypadType;
import rx.functions.Action1;

final /* synthetic */ class SendPresenter$$Lambda$6 implements Action1 {
    private final SendPresenter arg$1;

    private SendPresenter$$Lambda$6(SendPresenter sendPresenter) {
        this.arg$1 = sendPresenter;
    }

    public static Action1 lambdaFactory$(SendPresenter sendPresenter) {
        return new SendPresenter$$Lambda$6(sendPresenter);
    }

    public void call(Object obj) {
        this.arg$1.onKeystrokeEntered(KeypadType.DOT, NumericKeypadConnector.DOT);
    }
}
