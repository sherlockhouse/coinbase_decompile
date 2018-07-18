package com.coinbase.android.settings.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologySuccessPresenterModule_ProvidesIdologySuccessScreenFactory implements Factory<IdologySuccessScreen> {
    private final IdologySuccessPresenterModule module;

    public IdologySuccessPresenterModule_ProvidesIdologySuccessScreenFactory(IdologySuccessPresenterModule module) {
        this.module = module;
    }

    public IdologySuccessScreen get() {
        return provideInstance(this.module);
    }

    public static IdologySuccessScreen provideInstance(IdologySuccessPresenterModule module) {
        return proxyProvidesIdologySuccessScreen(module);
    }

    public static IdologySuccessPresenterModule_ProvidesIdologySuccessScreenFactory create(IdologySuccessPresenterModule module) {
        return new IdologySuccessPresenterModule_ProvidesIdologySuccessScreenFactory(module);
    }

    public static IdologySuccessScreen proxyProvidesIdologySuccessScreen(IdologySuccessPresenterModule instance) {
        return (IdologySuccessScreen) Preconditions.checkNotNull(instance.providesIdologySuccessScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
