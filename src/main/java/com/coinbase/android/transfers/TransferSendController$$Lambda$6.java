package com.coinbase.android.transfers;

import android.content.Intent;
import rx.functions.Func2;

final /* synthetic */ class TransferSendController$$Lambda$6 implements Func2 {
    private final TransferSendController arg$1;

    private TransferSendController$$Lambda$6(TransferSendController transferSendController) {
        this.arg$1 = transferSendController;
    }

    public static Func2 lambdaFactory$(TransferSendController transferSendController) {
        return new TransferSendController$$Lambda$6(transferSendController);
    }

    public Object call(Object obj, Object obj2) {
        return this.arg$1.startActivityForResult((Intent) obj, ((Integer) obj2).intValue());
    }
}
