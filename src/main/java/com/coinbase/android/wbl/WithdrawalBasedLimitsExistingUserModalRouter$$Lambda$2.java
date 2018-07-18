package com.coinbase.android.wbl;

import android.util.Pair;
import rx.functions.Action0;

final /* synthetic */ class WithdrawalBasedLimitsExistingUserModalRouter$$Lambda$2 implements Action0 {
    private final WithdrawalBasedLimitsExistingUserModalRouter arg$1;
    private final Pair arg$2;

    private WithdrawalBasedLimitsExistingUserModalRouter$$Lambda$2(WithdrawalBasedLimitsExistingUserModalRouter withdrawalBasedLimitsExistingUserModalRouter, Pair pair) {
        this.arg$1 = withdrawalBasedLimitsExistingUserModalRouter;
        this.arg$2 = pair;
    }

    public static Action0 lambdaFactory$(WithdrawalBasedLimitsExistingUserModalRouter withdrawalBasedLimitsExistingUserModalRouter, Pair pair) {
        return new WithdrawalBasedLimitsExistingUserModalRouter$$Lambda$2(withdrawalBasedLimitsExistingUserModalRouter, pair);
    }

    public void call() {
        WithdrawalBasedLimitsExistingUserModalRouter.lambda$null$0(this.arg$1, this.arg$2);
    }
}
