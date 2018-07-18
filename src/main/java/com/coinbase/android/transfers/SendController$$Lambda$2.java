package com.coinbase.android.transfers;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SendController$$Lambda$2 implements OnClickListener {
    private final SendController arg$1;

    private SendController$$Lambda$2(SendController sendController) {
        this.arg$1 = sendController;
    }

    public static OnClickListener lambdaFactory$(SendController sendController) {
        return new SendController$$Lambda$2(sendController);
    }

    public void onClick(View view) {
        SendController.lambda$onCreateView$1(this.arg$1, view);
    }
}
