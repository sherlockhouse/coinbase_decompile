package com.coinbase.android.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologyNameFormPresenterModule_ProvidesIdologyTrackingContextFactory implements Factory<String> {
    private final IdologyNameFormPresenterModule module;

    public IdologyNameFormPresenterModule_ProvidesIdologyTrackingContextFactory(IdologyNameFormPresenterModule module) {
        this.module = module;
    }

    public String get() {
        return provideInstance(this.module);
    }

    public static String provideInstance(IdologyNameFormPresenterModule module) {
        return proxyProvidesIdologyTrackingContext(module);
    }

    public static IdologyNameFormPresenterModule_ProvidesIdologyTrackingContextFactory create(IdologyNameFormPresenterModule module) {
        return new IdologyNameFormPresenterModule_ProvidesIdologyTrackingContextFactory(module);
    }

    public static String proxyProvidesIdologyTrackingContext(IdologyNameFormPresenterModule instance) {
        return (String) Preconditions.checkNotNull(instance.providesIdologyTrackingContext(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
