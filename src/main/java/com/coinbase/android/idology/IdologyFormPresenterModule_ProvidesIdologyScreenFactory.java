package com.coinbase.android.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologyFormPresenterModule_ProvidesIdologyScreenFactory implements Factory<IdologyFormScreen> {
    private final IdologyFormPresenterModule module;

    public IdologyFormPresenterModule_ProvidesIdologyScreenFactory(IdologyFormPresenterModule module) {
        this.module = module;
    }

    public IdologyFormScreen get() {
        return provideInstance(this.module);
    }

    public static IdologyFormScreen provideInstance(IdologyFormPresenterModule module) {
        return proxyProvidesIdologyScreen(module);
    }

    public static IdologyFormPresenterModule_ProvidesIdologyScreenFactory create(IdologyFormPresenterModule module) {
        return new IdologyFormPresenterModule_ProvidesIdologyScreenFactory(module);
    }

    public static IdologyFormScreen proxyProvidesIdologyScreen(IdologyFormPresenterModule instance) {
        return (IdologyFormScreen) Preconditions.checkNotNull(instance.providesIdologyScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
