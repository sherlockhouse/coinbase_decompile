package com.coinbase.android.idology;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;

public final class IdologyAddressFormPresenterModule_ProvidesIdologyHandledFieldsFactory implements Factory<List<String>> {
    private final IdologyAddressFormPresenterModule module;

    public IdologyAddressFormPresenterModule_ProvidesIdologyHandledFieldsFactory(IdologyAddressFormPresenterModule module) {
        this.module = module;
    }

    public List<String> get() {
        return provideInstance(this.module);
    }

    public static List<String> provideInstance(IdologyAddressFormPresenterModule module) {
        return proxyProvidesIdologyHandledFields(module);
    }

    public static IdologyAddressFormPresenterModule_ProvidesIdologyHandledFieldsFactory create(IdologyAddressFormPresenterModule module) {
        return new IdologyAddressFormPresenterModule_ProvidesIdologyHandledFieldsFactory(module);
    }

    public static List<String> proxyProvidesIdologyHandledFields(IdologyAddressFormPresenterModule instance) {
        return (List) Preconditions.checkNotNull(instance.providesIdologyHandledFields(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
