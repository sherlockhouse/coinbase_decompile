package com.coinbase.android.paymentmethods;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AddBankErrorPresenterModule_ProvidesAddBankErrorScreenFactory implements Factory<AddBankErrorScreen> {
    private final AddBankErrorPresenterModule module;

    public AddBankErrorPresenterModule_ProvidesAddBankErrorScreenFactory(AddBankErrorPresenterModule module) {
        this.module = module;
    }

    public AddBankErrorScreen get() {
        return provideInstance(this.module);
    }

    public static AddBankErrorScreen provideInstance(AddBankErrorPresenterModule module) {
        return proxyProvidesAddBankErrorScreen(module);
    }

    public static AddBankErrorPresenterModule_ProvidesAddBankErrorScreenFactory create(AddBankErrorPresenterModule module) {
        return new AddBankErrorPresenterModule_ProvidesAddBankErrorScreenFactory(module);
    }

    public static AddBankErrorScreen proxyProvidesAddBankErrorScreen(AddBankErrorPresenterModule instance) {
        return (AddBankErrorScreen) Preconditions.checkNotNull(instance.providesAddBankErrorScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
