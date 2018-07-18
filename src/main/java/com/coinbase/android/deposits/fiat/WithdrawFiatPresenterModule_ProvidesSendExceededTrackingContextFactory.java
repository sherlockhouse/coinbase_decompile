package com.coinbase.android.deposits.fiat;

import com.coinbase.android.wbl.LimitExceededTrackingContext;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class WithdrawFiatPresenterModule_ProvidesSendExceededTrackingContextFactory implements Factory<LimitExceededTrackingContext> {
    private final WithdrawFiatPresenterModule module;

    public WithdrawFiatPresenterModule_ProvidesSendExceededTrackingContextFactory(WithdrawFiatPresenterModule module) {
        this.module = module;
    }

    public LimitExceededTrackingContext get() {
        return provideInstance(this.module);
    }

    public static LimitExceededTrackingContext provideInstance(WithdrawFiatPresenterModule module) {
        return proxyProvidesSendExceededTrackingContext(module);
    }

    public static WithdrawFiatPresenterModule_ProvidesSendExceededTrackingContextFactory create(WithdrawFiatPresenterModule module) {
        return new WithdrawFiatPresenterModule_ProvidesSendExceededTrackingContextFactory(module);
    }

    public static LimitExceededTrackingContext proxyProvidesSendExceededTrackingContext(WithdrawFiatPresenterModule instance) {
        return (LimitExceededTrackingContext) Preconditions.checkNotNull(instance.providesSendExceededTrackingContext(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
