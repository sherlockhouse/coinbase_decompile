package com.coinbase.android.paymentmethods.linkedaccounts;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class LinkedAccountsPickerPresenterModule_ProvidesLinkedAccountsPickerScreenFactory implements Factory<LinkedAccountsPickerScreen> {
    private final LinkedAccountsPickerPresenterModule module;

    public LinkedAccountsPickerPresenterModule_ProvidesLinkedAccountsPickerScreenFactory(LinkedAccountsPickerPresenterModule module) {
        this.module = module;
    }

    public LinkedAccountsPickerScreen get() {
        return provideInstance(this.module);
    }

    public static LinkedAccountsPickerScreen provideInstance(LinkedAccountsPickerPresenterModule module) {
        return proxyProvidesLinkedAccountsPickerScreen(module);
    }

    public static LinkedAccountsPickerPresenterModule_ProvidesLinkedAccountsPickerScreenFactory create(LinkedAccountsPickerPresenterModule module) {
        return new LinkedAccountsPickerPresenterModule_ProvidesLinkedAccountsPickerScreenFactory(module);
    }

    public static LinkedAccountsPickerScreen proxyProvidesLinkedAccountsPickerScreen(LinkedAccountsPickerPresenterModule instance) {
        return (LinkedAccountsPickerScreen) Preconditions.checkNotNull(instance.providesLinkedAccountsPickerScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
