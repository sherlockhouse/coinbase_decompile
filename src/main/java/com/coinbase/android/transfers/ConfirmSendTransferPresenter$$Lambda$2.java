package com.coinbase.android.transfers;

import rx.functions.Action1;

final /* synthetic */ class ConfirmSendTransferPresenter$$Lambda$2 implements Action1 {
    private final ConfirmSendTransferPresenter arg$1;

    private ConfirmSendTransferPresenter$$Lambda$2(ConfirmSendTransferPresenter confirmSendTransferPresenter) {
        this.arg$1 = confirmSendTransferPresenter;
    }

    public static Action1 lambdaFactory$(ConfirmSendTransferPresenter confirmSendTransferPresenter) {
        return new ConfirmSendTransferPresenter$$Lambda$2(confirmSendTransferPresenter);
    }

    public void call(Object obj) {
        ConfirmSendTransferPresenter.lambda$onUserConfirm$1(this.arg$1, (Throwable) obj);
    }
}
