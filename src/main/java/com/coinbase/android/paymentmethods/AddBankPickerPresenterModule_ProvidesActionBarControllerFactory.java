package com.coinbase.android.paymentmethods;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AddBankPickerPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final AddBankPickerPresenterModule module;

    public AddBankPickerPresenterModule_ProvidesActionBarControllerFactory(AddBankPickerPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(AddBankPickerPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static AddBankPickerPresenterModule_ProvidesActionBarControllerFactory create(AddBankPickerPresenterModule module) {
        return new AddBankPickerPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(AddBankPickerPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
