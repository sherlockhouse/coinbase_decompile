package com.coinbase.android.deposits;

import com.coinbase.android.wbl.LimitExceededTrackingContext;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class FiatTransactionsControllerModule_ProvidesSendExceededTrackingContextFactory implements Factory<LimitExceededTrackingContext> {
    private final FiatTransactionsControllerModule module;

    public FiatTransactionsControllerModule_ProvidesSendExceededTrackingContextFactory(FiatTransactionsControllerModule module) {
        this.module = module;
    }

    public LimitExceededTrackingContext get() {
        return provideInstance(this.module);
    }

    public static LimitExceededTrackingContext provideInstance(FiatTransactionsControllerModule module) {
        return proxyProvidesSendExceededTrackingContext(module);
    }

    public static FiatTransactionsControllerModule_ProvidesSendExceededTrackingContextFactory create(FiatTransactionsControllerModule module) {
        return new FiatTransactionsControllerModule_ProvidesSendExceededTrackingContextFactory(module);
    }

    public static LimitExceededTrackingContext proxyProvidesSendExceededTrackingContext(FiatTransactionsControllerModule instance) {
        return (LimitExceededTrackingContext) Preconditions.checkNotNull(instance.providesSendExceededTrackingContext(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
