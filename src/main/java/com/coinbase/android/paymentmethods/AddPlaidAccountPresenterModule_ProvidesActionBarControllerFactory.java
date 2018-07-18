package com.coinbase.android.paymentmethods;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AddPlaidAccountPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final AddPlaidAccountPresenterModule module;

    public AddPlaidAccountPresenterModule_ProvidesActionBarControllerFactory(AddPlaidAccountPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(AddPlaidAccountPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static AddPlaidAccountPresenterModule_ProvidesActionBarControllerFactory create(AddPlaidAccountPresenterModule module) {
        return new AddPlaidAccountPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(AddPlaidAccountPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
