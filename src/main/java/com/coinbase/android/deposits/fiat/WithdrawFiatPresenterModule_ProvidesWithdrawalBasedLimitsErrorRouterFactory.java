package com.coinbase.android.deposits.fiat;

import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorControllerRouter;
import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorRouter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class WithdrawFiatPresenterModule_ProvidesWithdrawalBasedLimitsErrorRouterFactory implements Factory<WithdrawalBasedLimitsErrorRouter> {
    private final WithdrawFiatPresenterModule module;
    private final Provider<WithdrawalBasedLimitsErrorControllerRouter> routerProvider;

    public WithdrawFiatPresenterModule_ProvidesWithdrawalBasedLimitsErrorRouterFactory(WithdrawFiatPresenterModule module, Provider<WithdrawalBasedLimitsErrorControllerRouter> routerProvider) {
        this.module = module;
        this.routerProvider = routerProvider;
    }

    public WithdrawalBasedLimitsErrorRouter get() {
        return provideInstance(this.module, this.routerProvider);
    }

    public static WithdrawalBasedLimitsErrorRouter provideInstance(WithdrawFiatPresenterModule module, Provider<WithdrawalBasedLimitsErrorControllerRouter> routerProvider) {
        return proxyProvidesWithdrawalBasedLimitsErrorRouter(module, (WithdrawalBasedLimitsErrorControllerRouter) routerProvider.get());
    }

    public static WithdrawFiatPresenterModule_ProvidesWithdrawalBasedLimitsErrorRouterFactory create(WithdrawFiatPresenterModule module, Provider<WithdrawalBasedLimitsErrorControllerRouter> routerProvider) {
        return new WithdrawFiatPresenterModule_ProvidesWithdrawalBasedLimitsErrorRouterFactory(module, routerProvider);
    }

    public static WithdrawalBasedLimitsErrorRouter proxyProvidesWithdrawalBasedLimitsErrorRouter(WithdrawFiatPresenterModule instance, WithdrawalBasedLimitsErrorControllerRouter router) {
        return (WithdrawalBasedLimitsErrorRouter) Preconditions.checkNotNull(instance.providesWithdrawalBasedLimitsErrorRouter(router), "Cannot return null from a non-@Nullable @Provides method");
    }
}
