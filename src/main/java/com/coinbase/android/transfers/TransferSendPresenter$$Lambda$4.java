package com.coinbase.android.transfers;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class TransferSendPresenter$$Lambda$4 implements Action1 {
    private final TransferSendPresenter arg$1;
    private final UpdateFeeCallback arg$2;

    private TransferSendPresenter$$Lambda$4(TransferSendPresenter transferSendPresenter, UpdateFeeCallback updateFeeCallback) {
        this.arg$1 = transferSendPresenter;
        this.arg$2 = updateFeeCallback;
    }

    public static Action1 lambdaFactory$(TransferSendPresenter transferSendPresenter, UpdateFeeCallback updateFeeCallback) {
        return new TransferSendPresenter$$Lambda$4(transferSendPresenter, updateFeeCallback);
    }

    public void call(Object obj) {
        TransferSendPresenter.lambda$updateFee$3(this.arg$1, this.arg$2, (Pair) obj);
    }
}
