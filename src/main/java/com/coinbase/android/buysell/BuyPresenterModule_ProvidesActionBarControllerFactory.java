package com.coinbase.android.buysell;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class BuyPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final BuyPresenterModule module;

    public BuyPresenterModule_ProvidesActionBarControllerFactory(BuyPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(BuyPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static BuyPresenterModule_ProvidesActionBarControllerFactory create(BuyPresenterModule module) {
        return new BuyPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(BuyPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
