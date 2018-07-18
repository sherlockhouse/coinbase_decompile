package com.coinbase.android.settings.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologyFailurePresenterModule_ProvidesIdologyFailureScreenFactory implements Factory<IdologyFailureScreen> {
    private final IdologyFailurePresenterModule module;

    public IdologyFailurePresenterModule_ProvidesIdologyFailureScreenFactory(IdologyFailurePresenterModule module) {
        this.module = module;
    }

    public IdologyFailureScreen get() {
        return provideInstance(this.module);
    }

    public static IdologyFailureScreen provideInstance(IdologyFailurePresenterModule module) {
        return proxyProvidesIdologyFailureScreen(module);
    }

    public static IdologyFailurePresenterModule_ProvidesIdologyFailureScreenFactory create(IdologyFailurePresenterModule module) {
        return new IdologyFailurePresenterModule_ProvidesIdologyFailureScreenFactory(module);
    }

    public static IdologyFailureScreen proxyProvidesIdologyFailureScreen(IdologyFailurePresenterModule instance) {
        return (IdologyFailureScreen) Preconditions.checkNotNull(instance.providesIdologyFailureScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
