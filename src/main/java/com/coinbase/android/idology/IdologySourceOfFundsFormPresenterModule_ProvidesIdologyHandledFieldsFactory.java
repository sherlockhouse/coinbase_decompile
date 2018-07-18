package com.coinbase.android.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;

public final class IdologySourceOfFundsFormPresenterModule_ProvidesIdologyHandledFieldsFactory implements Factory<List<String>> {
    private final IdologySourceOfFundsFormPresenterModule module;

    public IdologySourceOfFundsFormPresenterModule_ProvidesIdologyHandledFieldsFactory(IdologySourceOfFundsFormPresenterModule module) {
        this.module = module;
    }

    public List<String> get() {
        return provideInstance(this.module);
    }

    public static List<String> provideInstance(IdologySourceOfFundsFormPresenterModule module) {
        return proxyProvidesIdologyHandledFields(module);
    }

    public static IdologySourceOfFundsFormPresenterModule_ProvidesIdologyHandledFieldsFactory create(IdologySourceOfFundsFormPresenterModule module) {
        return new IdologySourceOfFundsFormPresenterModule_ProvidesIdologyHandledFieldsFactory(module);
    }

    public static List<String> proxyProvidesIdologyHandledFields(IdologySourceOfFundsFormPresenterModule instance) {
        return (List) Preconditions.checkNotNull(instance.providesIdologyHandledFields(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
