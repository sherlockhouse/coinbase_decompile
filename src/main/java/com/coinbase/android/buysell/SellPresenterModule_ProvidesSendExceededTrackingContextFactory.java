package com.coinbase.android.buysell;

import com.coinbase.android.wbl.LimitExceededTrackingContext;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SellPresenterModule_ProvidesSendExceededTrackingContextFactory implements Factory<LimitExceededTrackingContext> {
    private final SellPresenterModule module;

    public SellPresenterModule_ProvidesSendExceededTrackingContextFactory(SellPresenterModule module) {
        this.module = module;
    }

    public LimitExceededTrackingContext get() {
        return provideInstance(this.module);
    }

    public static LimitExceededTrackingContext provideInstance(SellPresenterModule module) {
        return proxyProvidesSendExceededTrackingContext(module);
    }

    public static SellPresenterModule_ProvidesSendExceededTrackingContextFactory create(SellPresenterModule module) {
        return new SellPresenterModule_ProvidesSendExceededTrackingContextFactory(module);
    }

    public static LimitExceededTrackingContext proxyProvidesSendExceededTrackingContext(SellPresenterModule instance) {
        return (LimitExceededTrackingContext) Preconditions.checkNotNull(instance.providesSendExceededTrackingContext(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
