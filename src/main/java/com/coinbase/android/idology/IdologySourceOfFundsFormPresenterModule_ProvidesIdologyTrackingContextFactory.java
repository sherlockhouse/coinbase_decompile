package com.coinbase.android.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologySourceOfFundsFormPresenterModule_ProvidesIdologyTrackingContextFactory implements Factory<String> {
    private final IdologySourceOfFundsFormPresenterModule module;

    public IdologySourceOfFundsFormPresenterModule_ProvidesIdologyTrackingContextFactory(IdologySourceOfFundsFormPresenterModule module) {
        this.module = module;
    }

    public String get() {
        return provideInstance(this.module);
    }

    public static String provideInstance(IdologySourceOfFundsFormPresenterModule module) {
        return proxyProvidesIdologyTrackingContext(module);
    }

    public static IdologySourceOfFundsFormPresenterModule_ProvidesIdologyTrackingContextFactory create(IdologySourceOfFundsFormPresenterModule module) {
        return new IdologySourceOfFundsFormPresenterModule_ProvidesIdologyTrackingContextFactory(module);
    }

    public static String proxyProvidesIdologyTrackingContext(IdologySourceOfFundsFormPresenterModule instance) {
        return (String) Preconditions.checkNotNull(instance.providesIdologyTrackingContext(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
