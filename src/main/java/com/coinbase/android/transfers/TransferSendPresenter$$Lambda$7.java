package com.coinbase.android.transfers;

import rx.functions.Action1;

final /* synthetic */ class TransferSendPresenter$$Lambda$7 implements Action1 {
    private final TransferSendPresenter arg$1;

    private TransferSendPresenter$$Lambda$7(TransferSendPresenter transferSendPresenter) {
        this.arg$1 = transferSendPresenter;
    }

    public static Action1 lambdaFactory$(TransferSendPresenter transferSendPresenter) {
        return new TransferSendPresenter$$Lambda$7(transferSendPresenter);
    }

    public void call(Object obj) {
        TransferSendPresenter.lambda$getCBSuggestions$6(this.arg$1, (Throwable) obj);
    }
}
