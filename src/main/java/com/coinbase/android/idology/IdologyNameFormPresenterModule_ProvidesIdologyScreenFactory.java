package com.coinbase.android.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologyNameFormPresenterModule_ProvidesIdologyScreenFactory implements Factory<IdologyNameFormScreen> {
    private final IdologyNameFormPresenterModule module;

    public IdologyNameFormPresenterModule_ProvidesIdologyScreenFactory(IdologyNameFormPresenterModule module) {
        this.module = module;
    }

    public IdologyNameFormScreen get() {
        return provideInstance(this.module);
    }

    public static IdologyNameFormScreen provideInstance(IdologyNameFormPresenterModule module) {
        return proxyProvidesIdologyScreen(module);
    }

    public static IdologyNameFormPresenterModule_ProvidesIdologyScreenFactory create(IdologyNameFormPresenterModule module) {
        return new IdologyNameFormPresenterModule_ProvidesIdologyScreenFactory(module);
    }

    public static IdologyNameFormScreen proxyProvidesIdologyScreen(IdologyNameFormPresenterModule instance) {
        return (IdologyNameFormScreen) Preconditions.checkNotNull(instance.providesIdologyScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
