package com.coinbase.android.buysell;

import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorControllerRouter;
import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorRouter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SellPresenterModule_ProvidesWithdrawalBasedLimitsErrorRouterFactory implements Factory<WithdrawalBasedLimitsErrorRouter> {
    private final SellPresenterModule module;
    private final Provider<WithdrawalBasedLimitsErrorControllerRouter> routerProvider;

    public SellPresenterModule_ProvidesWithdrawalBasedLimitsErrorRouterFactory(SellPresenterModule module, Provider<WithdrawalBasedLimitsErrorControllerRouter> routerProvider) {
        this.module = module;
        this.routerProvider = routerProvider;
    }

    public WithdrawalBasedLimitsErrorRouter get() {
        return provideInstance(this.module, this.routerProvider);
    }

    public static WithdrawalBasedLimitsErrorRouter provideInstance(SellPresenterModule module, Provider<WithdrawalBasedLimitsErrorControllerRouter> routerProvider) {
        return proxyProvidesWithdrawalBasedLimitsErrorRouter(module, (WithdrawalBasedLimitsErrorControllerRouter) routerProvider.get());
    }

    public static SellPresenterModule_ProvidesWithdrawalBasedLimitsErrorRouterFactory create(SellPresenterModule module, Provider<WithdrawalBasedLimitsErrorControllerRouter> routerProvider) {
        return new SellPresenterModule_ProvidesWithdrawalBasedLimitsErrorRouterFactory(module, routerProvider);
    }

    public static WithdrawalBasedLimitsErrorRouter proxyProvidesWithdrawalBasedLimitsErrorRouter(SellPresenterModule instance, WithdrawalBasedLimitsErrorControllerRouter router) {
        return (WithdrawalBasedLimitsErrorRouter) Preconditions.checkNotNull(instance.providesWithdrawalBasedLimitsErrorRouter(router), "Cannot return null from a non-@Nullable @Provides method");
    }
}
