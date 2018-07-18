package com.coinbase.android.wbl;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class WithdrawalBasedLimitsApiErrorHandler_Factory implements Factory<WithdrawalBasedLimitsApiErrorHandler> {
    private final Provider<WithdrawalBasedLimitsErrorRouter> withdrawalBasedLimitsErrorRouterProvider;

    public WithdrawalBasedLimitsApiErrorHandler_Factory(Provider<WithdrawalBasedLimitsErrorRouter> withdrawalBasedLimitsErrorRouterProvider) {
        this.withdrawalBasedLimitsErrorRouterProvider = withdrawalBasedLimitsErrorRouterProvider;
    }

    public WithdrawalBasedLimitsApiErrorHandler get() {
        return provideInstance(this.withdrawalBasedLimitsErrorRouterProvider);
    }

    public static WithdrawalBasedLimitsApiErrorHandler provideInstance(Provider<WithdrawalBasedLimitsErrorRouter> withdrawalBasedLimitsErrorRouterProvider) {
        return new WithdrawalBasedLimitsApiErrorHandler((WithdrawalBasedLimitsErrorRouter) withdrawalBasedLimitsErrorRouterProvider.get());
    }

    public static WithdrawalBasedLimitsApiErrorHandler_Factory create(Provider<WithdrawalBasedLimitsErrorRouter> withdrawalBasedLimitsErrorRouterProvider) {
        return new WithdrawalBasedLimitsApiErrorHandler_Factory(withdrawalBasedLimitsErrorRouterProvider);
    }

    public static WithdrawalBasedLimitsApiErrorHandler newWithdrawalBasedLimitsApiErrorHandler(WithdrawalBasedLimitsErrorRouter withdrawalBasedLimitsErrorRouter) {
        return new WithdrawalBasedLimitsApiErrorHandler(withdrawalBasedLimitsErrorRouter);
    }
}
