package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class WithdrawDepositSuccessPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final WithdrawDepositSuccessPresenterModule module;

    public WithdrawDepositSuccessPresenterModule_ProvidesActionBarControllerFactory(WithdrawDepositSuccessPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(WithdrawDepositSuccessPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static WithdrawDepositSuccessPresenterModule_ProvidesActionBarControllerFactory create(WithdrawDepositSuccessPresenterModule module) {
        return new WithdrawDepositSuccessPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(WithdrawDepositSuccessPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
