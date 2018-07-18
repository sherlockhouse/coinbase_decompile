package com.coinbase.android.transfers;

import rx.functions.Action1;

final /* synthetic */ class TransferSendPresenter$$Lambda$5 implements Action1 {
    private final UpdateFeeCallback arg$1;

    private TransferSendPresenter$$Lambda$5(UpdateFeeCallback updateFeeCallback) {
        this.arg$1 = updateFeeCallback;
    }

    public static Action1 lambdaFactory$(UpdateFeeCallback updateFeeCallback) {
        return new TransferSendPresenter$$Lambda$5(updateFeeCallback);
    }

    public void call(Object obj) {
        TransferSendPresenter.lambda$updateFee$4(this.arg$1, (Throwable) obj);
    }
}
