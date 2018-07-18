package com.coinbase.android.transfers;

import org.joda.money.Money;
import rx.functions.Func8;

final /* synthetic */ class TransferSendController$$Lambda$3 implements Func8 {
    private final TransferSendController arg$1;

    private TransferSendController$$Lambda$3(TransferSendController transferSendController) {
        this.arg$1 = transferSendController;
    }

    public static Func8 lambdaFactory$(TransferSendController transferSendController) {
        return new TransferSendController$$Lambda$3(transferSendController);
    }

    public Object call(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return TransferSendController.lambda$onCreateView$2(this.arg$1, (String) obj, (String) obj2, (Money) obj3, (String) obj4, (Boolean) obj5, (String) obj6, (String) obj7, (Integer) obj8);
    }
}
