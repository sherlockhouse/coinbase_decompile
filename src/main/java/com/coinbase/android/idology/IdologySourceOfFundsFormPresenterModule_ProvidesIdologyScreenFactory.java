package com.coinbase.android.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IdologySourceOfFundsFormPresenterModule_ProvidesIdologyScreenFactory implements Factory<IdologySourceOfFundsFormScreen> {
    private final IdologySourceOfFundsFormPresenterModule module;

    public IdologySourceOfFundsFormPresenterModule_ProvidesIdologyScreenFactory(IdologySourceOfFundsFormPresenterModule module) {
        this.module = module;
    }

    public IdologySourceOfFundsFormScreen get() {
        return provideInstance(this.module);
    }

    public static IdologySourceOfFundsFormScreen provideInstance(IdologySourceOfFundsFormPresenterModule module) {
        return proxyProvidesIdologyScreen(module);
    }

    public static IdologySourceOfFundsFormPresenterModule_ProvidesIdologyScreenFactory create(IdologySourceOfFundsFormPresenterModule module) {
        return new IdologySourceOfFundsFormPresenterModule_ProvidesIdologyScreenFactory(module);
    }

    public static IdologySourceOfFundsFormScreen proxyProvidesIdologyScreen(IdologySourceOfFundsFormPresenterModule instance) {
        return (IdologySourceOfFundsFormScreen) Preconditions.checkNotNull(instance.providesIdologyScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
