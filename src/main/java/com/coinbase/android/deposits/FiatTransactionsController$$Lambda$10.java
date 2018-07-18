package com.coinbase.android.deposits;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class FiatTransactionsController$$Lambda$10 implements Action1 {
    private final FiatTransactionsController arg$1;

    private FiatTransactionsController$$Lambda$10(FiatTransactionsController fiatTransactionsController) {
        this.arg$1 = fiatTransactionsController;
    }

    public static Action1 lambdaFactory$(FiatTransactionsController fiatTransactionsController) {
        return new FiatTransactionsController$$Lambda$10(fiatTransactionsController);
    }

    public void call(Object obj) {
        FiatTransactionsController.lambda$refreshData$9(this.arg$1, (Pair) obj);
    }
}
