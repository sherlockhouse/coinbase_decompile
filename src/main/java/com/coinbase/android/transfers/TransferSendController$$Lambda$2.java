package com.coinbase.android.transfers;

import rx.functions.Func0;

final /* synthetic */ class TransferSendController$$Lambda$2 implements Func0 {
    private final TransferSendController arg$1;

    private TransferSendController$$Lambda$2(TransferSendController transferSendController) {
        this.arg$1 = transferSendController;
    }

    public static Func0 lambdaFactory$(TransferSendController transferSendController) {
        return new TransferSendController$$Lambda$2(transferSendController);
    }

    public Object call() {
        return TransferSendControllerPermissionsDispatcher.startQrScanWithCheck(this.arg$1);
    }
}
