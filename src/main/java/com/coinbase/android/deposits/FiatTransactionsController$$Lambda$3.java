package com.coinbase.android.deposits;

import rx.functions.Action1;

final /* synthetic */ class FiatTransactionsController$$Lambda$3 implements Action1 {
    private final FiatTransactionsController arg$1;

    private FiatTransactionsController$$Lambda$3(FiatTransactionsController fiatTransactionsController) {
        this.arg$1 = fiatTransactionsController;
    }

    public static Action1 lambdaFactory$(FiatTransactionsController fiatTransactionsController) {
        return new FiatTransactionsController$$Lambda$3(fiatTransactionsController);
    }

    public void call(Object obj) {
        this.arg$1.onBanksUpdated();
    }
}
