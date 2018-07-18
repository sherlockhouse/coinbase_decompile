package com.coinbase.android.transfers;

import com.coinbase.v2.models.transactions.Data;
import rx.functions.Func2;

final /* synthetic */ class TransferSendController$$Lambda$4 implements Func2 {
    private final TransferSendController arg$1;

    private TransferSendController$$Lambda$4(TransferSendController transferSendController) {
        this.arg$1 = transferSendController;
    }

    public static Func2 lambdaFactory$(TransferSendController transferSendController) {
        return new TransferSendController$$Lambda$4(transferSendController);
    }

    public Object call(Object obj, Object obj2) {
        return TransferSendController.lambda$onCreateView$3(this.arg$1, (Data) obj, (String) obj2);
    }
}
