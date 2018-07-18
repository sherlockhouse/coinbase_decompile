package com.coinbase.android.transfers;

import com.coinbase.android.wbl.LimitExceededTrackingContext;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class TransferSendControllerModule_ProvidesSendExceededTrackingContextFactory implements Factory<LimitExceededTrackingContext> {
    private final TransferSendControllerModule module;

    public TransferSendControllerModule_ProvidesSendExceededTrackingContextFactory(TransferSendControllerModule module) {
        this.module = module;
    }

    public LimitExceededTrackingContext get() {
        return provideInstance(this.module);
    }

    public static LimitExceededTrackingContext provideInstance(TransferSendControllerModule module) {
        return proxyProvidesSendExceededTrackingContext(module);
    }

    public static TransferSendControllerModule_ProvidesSendExceededTrackingContextFactory create(TransferSendControllerModule module) {
        return new TransferSendControllerModule_ProvidesSendExceededTrackingContextFactory(module);
    }

    public static LimitExceededTrackingContext proxyProvidesSendExceededTrackingContext(TransferSendControllerModule instance) {
        return (LimitExceededTrackingContext) Preconditions.checkNotNull(instance.providesSendExceededTrackingContext(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
