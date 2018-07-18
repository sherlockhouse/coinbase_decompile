package com.coinbase.android.transfers;

import java.util.List;
import rx.functions.Action1;

final /* synthetic */ class TransferSendPresenter$$Lambda$1 implements Action1 {
    private final TransferSendPresenter arg$1;

    private TransferSendPresenter$$Lambda$1(TransferSendPresenter transferSendPresenter) {
        this.arg$1 = transferSendPresenter;
    }

    public static Action1 lambdaFactory$(TransferSendPresenter transferSendPresenter) {
        return new TransferSendPresenter$$Lambda$1(transferSendPresenter);
    }

    public void call(Object obj) {
        this.arg$1.onSuccess((List) obj);
    }
}
