package com.coinbase.android.transfers;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ConfirmSendTransferController$$Lambda$1 implements OnClickListener {
    private final ConfirmSendTransferController arg$1;

    private ConfirmSendTransferController$$Lambda$1(ConfirmSendTransferController confirmSendTransferController) {
        this.arg$1 = confirmSendTransferController;
    }

    public static OnClickListener lambdaFactory$(ConfirmSendTransferController confirmSendTransferController) {
        return new ConfirmSendTransferController$$Lambda$1(confirmSendTransferController);
    }

    public void onClick(View view) {
        ConfirmSendTransferController.lambda$inflateContent$0(this.arg$1, view);
    }
}
