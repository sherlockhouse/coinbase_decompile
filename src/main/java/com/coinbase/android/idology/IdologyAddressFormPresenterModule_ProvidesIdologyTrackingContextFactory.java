package com.coinbase.android.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologyAddressFormPresenterModule_ProvidesIdologyTrackingContextFactory implements Factory<String> {
    private final IdologyAddressFormPresenterModule module;

    public IdologyAddressFormPresenterModule_ProvidesIdologyTrackingContextFactory(IdologyAddressFormPresenterModule module) {
        this.module = module;
    }

    public String get() {
        return provideInstance(this.module);
    }

    public static String provideInstance(IdologyAddressFormPresenterModule module) {
        return proxyProvidesIdologyTrackingContext(module);
    }

    public static IdologyAddressFormPresenterModule_ProvidesIdologyTrackingContextFactory create(IdologyAddressFormPresenterModule module) {
        return new IdologyAddressFormPresenterModule_ProvidesIdologyTrackingContextFactory(module);
    }

    public static String proxyProvidesIdologyTrackingContext(IdologyAddressFormPresenterModule instance) {
        return (String) Preconditions.checkNotNull(instance.providesIdologyTrackingContext(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
