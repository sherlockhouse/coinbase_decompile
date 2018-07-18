package com.coinbase.android.deposits;

import rx.functions.Action1;

final /* synthetic */ class FiatTransactionsController$$Lambda$9 implements Action1 {
    private final FiatTransactionsController arg$1;

    private FiatTransactionsController$$Lambda$9(FiatTransactionsController fiatTransactionsController) {
        this.arg$1 = fiatTransactionsController;
    }

    public static Action1 lambdaFactory$(FiatTransactionsController fiatTransactionsController) {
        return new FiatTransactionsController$$Lambda$9(fiatTransactionsController);
    }

    public void call(Object obj) {
        this.arg$1.updateSetupViews();
    }
}
