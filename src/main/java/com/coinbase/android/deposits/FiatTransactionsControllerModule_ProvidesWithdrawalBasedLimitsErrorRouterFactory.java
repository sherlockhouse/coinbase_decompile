package com.coinbase.android.deposits;

import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorControllerRouter;
import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorRouter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class FiatTransactionsControllerModule_ProvidesWithdrawalBasedLimitsErrorRouterFactory implements Factory<WithdrawalBasedLimitsErrorRouter> {
    private final FiatTransactionsControllerModule module;
    private final Provider<WithdrawalBasedLimitsErrorControllerRouter> routerProvider;

    public FiatTransactionsControllerModule_ProvidesWithdrawalBasedLimitsErrorRouterFactory(FiatTransactionsControllerModule module, Provider<WithdrawalBasedLimitsErrorControllerRouter> routerProvider) {
        this.module = module;
        this.routerProvider = routerProvider;
    }

    public WithdrawalBasedLimitsErrorRouter get() {
        return provideInstance(this.module, this.routerProvider);
    }

    public static WithdrawalBasedLimitsErrorRouter provideInstance(FiatTransactionsControllerModule module, Provider<WithdrawalBasedLimitsErrorControllerRouter> routerProvider) {
        return proxyProvidesWithdrawalBasedLimitsErrorRouter(module, (WithdrawalBasedLimitsErrorControllerRouter) routerProvider.get());
    }

    public static FiatTransactionsControllerModule_ProvidesWithdrawalBasedLimitsErrorRouterFactory create(FiatTransactionsControllerModule module, Provider<WithdrawalBasedLimitsErrorControllerRouter> routerProvider) {
        return new FiatTransactionsControllerModule_ProvidesWithdrawalBasedLimitsErrorRouterFactory(module, routerProvider);
    }

    public static WithdrawalBasedLimitsErrorRouter proxyProvidesWithdrawalBasedLimitsErrorRouter(FiatTransactionsControllerModule instance, WithdrawalBasedLimitsErrorControllerRouter router) {
        return (WithdrawalBasedLimitsErrorRouter) Preconditions.checkNotNull(instance.providesWithdrawalBasedLimitsErrorRouter(router), "Cannot return null from a non-@Nullable @Provides method");
    }
}
