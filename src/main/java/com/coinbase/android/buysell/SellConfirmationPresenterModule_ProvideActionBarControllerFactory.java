package com.coinbase.android.buysell;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SellConfirmationPresenterModule_ProvideActionBarControllerFactory implements Factory<ActionBarController> {
    private final SellConfirmationPresenterModule module;

    public SellConfirmationPresenterModule_ProvideActionBarControllerFactory(SellConfirmationPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(SellConfirmationPresenterModule module) {
        return proxyProvideActionBarController(module);
    }

    public static SellConfirmationPresenterModule_ProvideActionBarControllerFactory create(SellConfirmationPresenterModule module) {
        return new SellConfirmationPresenterModule_ProvideActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvideActionBarController(SellConfirmationPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.provideActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
