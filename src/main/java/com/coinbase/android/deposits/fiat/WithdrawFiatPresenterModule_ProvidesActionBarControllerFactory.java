package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class WithdrawFiatPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final WithdrawFiatPresenterModule module;

    public WithdrawFiatPresenterModule_ProvidesActionBarControllerFactory(WithdrawFiatPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(WithdrawFiatPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static WithdrawFiatPresenterModule_ProvidesActionBarControllerFactory create(WithdrawFiatPresenterModule module) {
        return new WithdrawFiatPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(WithdrawFiatPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
