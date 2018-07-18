package com.coinbase.android.transfers;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class TransferSendPresenter$$Lambda$6 implements Action1 {
    private final TransferSendPresenter arg$1;

    private TransferSendPresenter$$Lambda$6(TransferSendPresenter transferSendPresenter) {
        this.arg$1 = transferSendPresenter;
    }

    public static Action1 lambdaFactory$(TransferSendPresenter transferSendPresenter) {
        return new TransferSendPresenter$$Lambda$6(transferSendPresenter);
    }

    public void call(Object obj) {
        TransferSendPresenter.lambda$getCBSuggestions$5(this.arg$1, (Pair) obj);
    }
}
