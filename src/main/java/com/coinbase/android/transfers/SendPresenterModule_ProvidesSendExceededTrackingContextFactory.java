package com.coinbase.android.transfers;

import com.coinbase.android.wbl.LimitExceededTrackingContext;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SendPresenterModule_ProvidesSendExceededTrackingContextFactory implements Factory<LimitExceededTrackingContext> {
    private final SendPresenterModule module;

    public SendPresenterModule_ProvidesSendExceededTrackingContextFactory(SendPresenterModule module) {
        this.module = module;
    }

    public LimitExceededTrackingContext get() {
        return provideInstance(this.module);
    }

    public static LimitExceededTrackingContext provideInstance(SendPresenterModule module) {
        return proxyProvidesSendExceededTrackingContext(module);
    }

    public static SendPresenterModule_ProvidesSendExceededTrackingContextFactory create(SendPresenterModule module) {
        return new SendPresenterModule_ProvidesSendExceededTrackingContextFactory(module);
    }

    public static LimitExceededTrackingContext proxyProvidesSendExceededTrackingContext(SendPresenterModule instance) {
        return (LimitExceededTrackingContext) Preconditions.checkNotNull(instance.providesSendExceededTrackingContext(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
