package com.coinbase.android.transfers;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SendPresenterModule_ProvidesSendScreenFactory implements Factory<SendScreen> {
    private final SendPresenterModule module;

    public SendPresenterModule_ProvidesSendScreenFactory(SendPresenterModule module) {
        this.module = module;
    }

    public SendScreen get() {
        return provideInstance(this.module);
    }

    public static SendScreen provideInstance(SendPresenterModule module) {
        return proxyProvidesSendScreen(module);
    }

    public static SendPresenterModule_ProvidesSendScreenFactory create(SendPresenterModule module) {
        return new SendPresenterModule_ProvidesSendScreenFactory(module);
    }

    public static SendScreen proxyProvidesSendScreen(SendPresenterModule instance) {
        return (SendScreen) Preconditions.checkNotNull(instance.providesSendScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
