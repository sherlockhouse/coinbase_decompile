package com.coinbase.android.transfers;

import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorControllerRouter;
import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorRouter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class TransferSendControllerModule_ProvidesWithdrawalBasedLimitsErrorControllerRouterFactory implements Factory<WithdrawalBasedLimitsErrorRouter> {
    private final TransferSendControllerModule module;
    private final Provider<WithdrawalBasedLimitsErrorControllerRouter> routerProvider;

    public TransferSendControllerModule_ProvidesWithdrawalBasedLimitsErrorControllerRouterFactory(TransferSendControllerModule module, Provider<WithdrawalBasedLimitsErrorControllerRouter> routerProvider) {
        this.module = module;
        this.routerProvider = routerProvider;
    }

    public WithdrawalBasedLimitsErrorRouter get() {
        return provideInstance(this.module, this.routerProvider);
    }

    public static WithdrawalBasedLimitsErrorRouter provideInstance(TransferSendControllerModule module, Provider<WithdrawalBasedLimitsErrorControllerRouter> routerProvider) {
        return proxyProvidesWithdrawalBasedLimitsErrorControllerRouter(module, (WithdrawalBasedLimitsErrorControllerRouter) routerProvider.get());
    }

    public static TransferSendControllerModule_ProvidesWithdrawalBasedLimitsErrorControllerRouterFactory create(TransferSendControllerModule module, Provider<WithdrawalBasedLimitsErrorControllerRouter> routerProvider) {
        return new TransferSendControllerModule_ProvidesWithdrawalBasedLimitsErrorControllerRouterFactory(module, routerProvider);
    }

    public static WithdrawalBasedLimitsErrorRouter proxyProvidesWithdrawalBasedLimitsErrorControllerRouter(TransferSendControllerModule instance, WithdrawalBasedLimitsErrorControllerRouter router) {
        return (WithdrawalBasedLimitsErrorRouter) Preconditions.checkNotNull(instance.providesWithdrawalBasedLimitsErrorControllerRouter(router), "Cannot return null from a non-@Nullable @Provides method");
    }
}
