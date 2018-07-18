package com.coinbase.android.paymentmethods;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AddBankErrorPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final AddBankErrorPresenterModule module;

    public AddBankErrorPresenterModule_ProvidesActionBarControllerFactory(AddBankErrorPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(AddBankErrorPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static AddBankErrorPresenterModule_ProvidesActionBarControllerFactory create(AddBankErrorPresenterModule module) {
        return new AddBankErrorPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(AddBankErrorPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
