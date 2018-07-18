package com.coinbase.android.transfers;

import com.coinbase.android.utils.ApiErrorHandler;
import com.coinbase.android.wbl.WithdrawalBasedLimitsApiErrorHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class TransferSendPresenterModule_ProvidesApiErrorHandlerFactory implements Factory<ApiErrorHandler> {
    private final TransferSendPresenterModule module;
    private final Provider<WithdrawalBasedLimitsApiErrorHandler> withdrawalBasedLimitsApiErrorHandlerProvider;

    public TransferSendPresenterModule_ProvidesApiErrorHandlerFactory(TransferSendPresenterModule module, Provider<WithdrawalBasedLimitsApiErrorHandler> withdrawalBasedLimitsApiErrorHandlerProvider) {
        this.module = module;
        this.withdrawalBasedLimitsApiErrorHandlerProvider = withdrawalBasedLimitsApiErrorHandlerProvider;
    }

    public ApiErrorHandler get() {
        return provideInstance(this.module, this.withdrawalBasedLimitsApiErrorHandlerProvider);
    }

    public static ApiErrorHandler provideInstance(TransferSendPresenterModule module, Provider<WithdrawalBasedLimitsApiErrorHandler> withdrawalBasedLimitsApiErrorHandlerProvider) {
        return proxyProvidesApiErrorHandler(module, (WithdrawalBasedLimitsApiErrorHandler) withdrawalBasedLimitsApiErrorHandlerProvider.get());
    }

    public static TransferSendPresenterModule_ProvidesApiErrorHandlerFactory create(TransferSendPresenterModule module, Provider<WithdrawalBasedLimitsApiErrorHandler> withdrawalBasedLimitsApiErrorHandlerProvider) {
        return new TransferSendPresenterModule_ProvidesApiErrorHandlerFactory(module, withdrawalBasedLimitsApiErrorHandlerProvider);
    }

    public static ApiErrorHandler proxyProvidesApiErrorHandler(TransferSendPresenterModule instance, WithdrawalBasedLimitsApiErrorHandler withdrawalBasedLimitsApiErrorHandler) {
        return (ApiErrorHandler) Preconditions.checkNotNull(instance.providesApiErrorHandler(withdrawalBasedLimitsApiErrorHandler), "Cannot return null from a non-@Nullable @Provides method");
    }
}
