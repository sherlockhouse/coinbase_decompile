package com.coinbase.android.transfers;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class TransferSendPresenterModule_ProvidesTransferSendScreenFactory implements Factory<TransferSendScreen> {
    private final TransferSendPresenterModule module;

    public TransferSendPresenterModule_ProvidesTransferSendScreenFactory(TransferSendPresenterModule module) {
        this.module = module;
    }

    public TransferSendScreen get() {
        return provideInstance(this.module);
    }

    public static TransferSendScreen provideInstance(TransferSendPresenterModule module) {
        return proxyProvidesTransferSendScreen(module);
    }

    public static TransferSendPresenterModule_ProvidesTransferSendScreenFactory create(TransferSendPresenterModule module) {
        return new TransferSendPresenterModule_ProvidesTransferSendScreenFactory(module);
    }

    public static TransferSendScreen proxyProvidesTransferSendScreen(TransferSendPresenterModule instance) {
        return (TransferSendScreen) Preconditions.checkNotNull(instance.providesTransferSendScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
