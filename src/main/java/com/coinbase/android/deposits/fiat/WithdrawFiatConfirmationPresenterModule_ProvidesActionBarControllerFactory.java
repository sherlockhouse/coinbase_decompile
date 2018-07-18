package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class WithdrawFiatConfirmationPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final WithdrawFiatConfirmationPresenterModule module;

    public WithdrawFiatConfirmationPresenterModule_ProvidesActionBarControllerFactory(WithdrawFiatConfirmationPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(WithdrawFiatConfirmationPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static WithdrawFiatConfirmationPresenterModule_ProvidesActionBarControllerFactory create(WithdrawFiatConfirmationPresenterModule module) {
        return new WithdrawFiatConfirmationPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(WithdrawFiatConfirmationPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
