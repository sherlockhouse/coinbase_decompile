package com.coinbase.android.paymentmethods.linkedaccounts;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class LinkedAccountsPickerPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final LinkedAccountsPickerPresenterModule module;

    public LinkedAccountsPickerPresenterModule_ProvidesActionBarControllerFactory(LinkedAccountsPickerPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(LinkedAccountsPickerPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static LinkedAccountsPickerPresenterModule_ProvidesActionBarControllerFactory create(LinkedAccountsPickerPresenterModule module) {
        return new LinkedAccountsPickerPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(LinkedAccountsPickerPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
