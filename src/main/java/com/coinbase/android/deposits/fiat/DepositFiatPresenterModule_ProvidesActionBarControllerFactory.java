package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DepositFiatPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final DepositFiatPresenterModule module;

    public DepositFiatPresenterModule_ProvidesActionBarControllerFactory(DepositFiatPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(DepositFiatPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static DepositFiatPresenterModule_ProvidesActionBarControllerFactory create(DepositFiatPresenterModule module) {
        return new DepositFiatPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(DepositFiatPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
