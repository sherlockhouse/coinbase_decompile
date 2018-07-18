package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DepositFiatConfirmationPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final DepositFiatConfirmationPresenterModule module;

    public DepositFiatConfirmationPresenterModule_ProvidesActionBarControllerFactory(DepositFiatConfirmationPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(DepositFiatConfirmationPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static DepositFiatConfirmationPresenterModule_ProvidesActionBarControllerFactory create(DepositFiatConfirmationPresenterModule module) {
        return new DepositFiatConfirmationPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(DepositFiatConfirmationPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
