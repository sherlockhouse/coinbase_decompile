package com.coinbase.android.paymentmethods;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AddBankPickerPresenterModule_ProvidesAddBankPickerScreenFactory implements Factory<AddBankPickerScreen> {
    private final AddBankPickerPresenterModule module;

    public AddBankPickerPresenterModule_ProvidesAddBankPickerScreenFactory(AddBankPickerPresenterModule module) {
        this.module = module;
    }

    public AddBankPickerScreen get() {
        return provideInstance(this.module);
    }

    public static AddBankPickerScreen provideInstance(AddBankPickerPresenterModule module) {
        return proxyProvidesAddBankPickerScreen(module);
    }

    public static AddBankPickerPresenterModule_ProvidesAddBankPickerScreenFactory create(AddBankPickerPresenterModule module) {
        return new AddBankPickerPresenterModule_ProvidesAddBankPickerScreenFactory(module);
    }

    public static AddBankPickerScreen proxyProvidesAddBankPickerScreen(AddBankPickerPresenterModule instance) {
        return (AddBankPickerScreen) Preconditions.checkNotNull(instance.providesAddBankPickerScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
