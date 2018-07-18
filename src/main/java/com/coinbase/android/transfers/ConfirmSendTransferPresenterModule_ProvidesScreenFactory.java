package com.coinbase.android.transfers;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ConfirmSendTransferPresenterModule_ProvidesScreenFactory implements Factory<ConfirmSendTransferScreen> {
    private final ConfirmSendTransferPresenterModule module;

    public ConfirmSendTransferPresenterModule_ProvidesScreenFactory(ConfirmSendTransferPresenterModule module) {
        this.module = module;
    }

    public ConfirmSendTransferScreen get() {
        return provideInstance(this.module);
    }

    public static ConfirmSendTransferScreen provideInstance(ConfirmSendTransferPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static ConfirmSendTransferPresenterModule_ProvidesScreenFactory create(ConfirmSendTransferPresenterModule module) {
        return new ConfirmSendTransferPresenterModule_ProvidesScreenFactory(module);
    }

    public static ConfirmSendTransferScreen proxyProvidesScreen(ConfirmSendTransferPresenterModule instance) {
        return (ConfirmSendTransferScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
