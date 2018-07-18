package com.coinbase.android.deposits;

import com.coinbase.v2.models.transfers.Transfer;
import rx.functions.Action1;

final /* synthetic */ class FiatTransactionsController$$Lambda$7 implements Action1 {
    private final FiatTransactionsController arg$1;

    private FiatTransactionsController$$Lambda$7(FiatTransactionsController fiatTransactionsController) {
        this.arg$1 = fiatTransactionsController;
    }

    public static Action1 lambdaFactory$(FiatTransactionsController fiatTransactionsController) {
        return new FiatTransactionsController$$Lambda$7(fiatTransactionsController);
    }

    public void call(Object obj) {
        this.arg$1.onDepositWithdrawConfirmed((Transfer) obj);
    }
}
