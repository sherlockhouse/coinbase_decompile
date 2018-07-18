package com.coinbase.android.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologyAddressFormPresenterModule_ProvidesIdologyScreenFactory implements Factory<IdologyAddressFormScreen> {
    private final IdologyAddressFormPresenterModule module;

    public IdologyAddressFormPresenterModule_ProvidesIdologyScreenFactory(IdologyAddressFormPresenterModule module) {
        this.module = module;
    }

    public IdologyAddressFormScreen get() {
        return provideInstance(this.module);
    }

    public static IdologyAddressFormScreen provideInstance(IdologyAddressFormPresenterModule module) {
        return proxyProvidesIdologyScreen(module);
    }

    public static IdologyAddressFormPresenterModule_ProvidesIdologyScreenFactory create(IdologyAddressFormPresenterModule module) {
        return new IdologyAddressFormPresenterModule_ProvidesIdologyScreenFactory(module);
    }

    public static IdologyAddressFormScreen proxyProvidesIdologyScreen(IdologyAddressFormPresenterModule instance) {
        return (IdologyAddressFormScreen) Preconditions.checkNotNull(instance.providesIdologyScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
