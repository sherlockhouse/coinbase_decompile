package com.coinbase.android.buysell;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class BuyConfirmationPresenterModule_ProvideActionBarControllerFactory implements Factory<ActionBarController> {
    private final BuyConfirmationPresenterModule module;

    public BuyConfirmationPresenterModule_ProvideActionBarControllerFactory(BuyConfirmationPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(BuyConfirmationPresenterModule module) {
        return proxyProvideActionBarController(module);
    }

    public static BuyConfirmationPresenterModule_ProvideActionBarControllerFactory create(BuyConfirmationPresenterModule module) {
        return new BuyConfirmationPresenterModule_ProvideActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvideActionBarController(BuyConfirmationPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.provideActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
