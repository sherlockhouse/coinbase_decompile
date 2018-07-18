package com.coinbase.android.transfers;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class ConfirmSendTransferPresenter$$Lambda$1 implements Action1 {
    private final ConfirmSendTransferPresenter arg$1;
    private final String arg$2;

    private ConfirmSendTransferPresenter$$Lambda$1(ConfirmSendTransferPresenter confirmSendTransferPresenter, String str) {
        this.arg$1 = confirmSendTransferPresenter;
        this.arg$2 = str;
    }

    public static Action1 lambdaFactory$(ConfirmSendTransferPresenter confirmSendTransferPresenter, String str) {
        return new ConfirmSendTransferPresenter$$Lambda$1(confirmSendTransferPresenter, str);
    }

    public void call(Object obj) {
        ConfirmSendTransferPresenter.lambda$onUserConfirm$0(this.arg$1, this.arg$2, (Pair) obj);
    }
}
