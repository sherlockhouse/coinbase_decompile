package com.coinbase.android.paymentmethods;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class PlaidPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final PlaidPresenterModule module;

    public PlaidPresenterModule_ProvidesActionBarControllerFactory(PlaidPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(PlaidPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static PlaidPresenterModule_ProvidesActionBarControllerFactory create(PlaidPresenterModule module) {
        return new PlaidPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(PlaidPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
