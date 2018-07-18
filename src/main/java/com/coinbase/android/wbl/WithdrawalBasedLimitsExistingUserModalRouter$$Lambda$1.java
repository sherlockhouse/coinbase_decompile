package com.coinbase.android.wbl;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class WithdrawalBasedLimitsExistingUserModalRouter$$Lambda$1 implements Func1 {
    private final WithdrawalBasedLimitsExistingUserModalRouter arg$1;

    private WithdrawalBasedLimitsExistingUserModalRouter$$Lambda$1(WithdrawalBasedLimitsExistingUserModalRouter withdrawalBasedLimitsExistingUserModalRouter) {
        this.arg$1 = withdrawalBasedLimitsExistingUserModalRouter;
    }

    public static Func1 lambdaFactory$(WithdrawalBasedLimitsExistingUserModalRouter withdrawalBasedLimitsExistingUserModalRouter) {
        return new WithdrawalBasedLimitsExistingUserModalRouter$$Lambda$1(withdrawalBasedLimitsExistingUserModalRouter);
    }

    public Object call(Object obj) {
        return WithdrawalBasedLimitsExistingUserModalRouter.lambda$route$1(this.arg$1, (Pair) obj);
    }
}
