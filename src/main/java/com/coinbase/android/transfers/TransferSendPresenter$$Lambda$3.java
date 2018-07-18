package com.coinbase.android.transfers;

import rx.functions.Action0;

final /* synthetic */ class TransferSendPresenter$$Lambda$3 implements Action0 {
    private final TransferSendPresenter arg$1;

    private TransferSendPresenter$$Lambda$3(TransferSendPresenter transferSendPresenter) {
        this.arg$1 = transferSendPresenter;
    }

    public static Action0 lambdaFactory$(TransferSendPresenter transferSendPresenter) {
        return new TransferSendPresenter$$Lambda$3(transferSendPresenter);
    }

    public void call() {
        this.arg$1.onFinally();
    }
}
