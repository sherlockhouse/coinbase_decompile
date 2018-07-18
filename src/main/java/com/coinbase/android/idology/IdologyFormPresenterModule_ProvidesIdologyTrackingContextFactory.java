package com.coinbase.android.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologyFormPresenterModule_ProvidesIdologyTrackingContextFactory implements Factory<String> {
    private final IdologyFormPresenterModule module;

    public IdologyFormPresenterModule_ProvidesIdologyTrackingContextFactory(IdologyFormPresenterModule module) {
        this.module = module;
    }

    public String get() {
        return provideInstance(this.module);
    }

    public static String provideInstance(IdologyFormPresenterModule module) {
        return proxyProvidesIdologyTrackingContext(module);
    }

    public static IdologyFormPresenterModule_ProvidesIdologyTrackingContextFactory create(IdologyFormPresenterModule module) {
        return new IdologyFormPresenterModule_ProvidesIdologyTrackingContextFactory(module);
    }

    public static String proxyProvidesIdologyTrackingContext(IdologyFormPresenterModule instance) {
        return (String) Preconditions.checkNotNull(instance.providesIdologyTrackingContext(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
