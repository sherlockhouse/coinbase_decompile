package com.coinbase.android.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;

public final class IdologyNameFormPresenterModule_ProvidesIdologyHandledFieldsFactory implements Factory<List<String>> {
    private final IdologyNameFormPresenterModule module;

    public IdologyNameFormPresenterModule_ProvidesIdologyHandledFieldsFactory(IdologyNameFormPresenterModule module) {
        this.module = module;
    }

    public List<String> get() {
        return provideInstance(this.module);
    }

    public static List<String> provideInstance(IdologyNameFormPresenterModule module) {
        return proxyProvidesIdologyHandledFields(module);
    }

    public static IdologyNameFormPresenterModule_ProvidesIdologyHandledFieldsFactory create(IdologyNameFormPresenterModule module) {
        return new IdologyNameFormPresenterModule_ProvidesIdologyHandledFieldsFactory(module);
    }

    public static List<String> proxyProvidesIdologyHandledFields(IdologyNameFormPresenterModule instance) {
        return (List) Preconditions.checkNotNull(instance.providesIdologyHandledFields(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
