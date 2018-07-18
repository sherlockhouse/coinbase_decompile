package com.coinbase.android.buysell;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SellPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final SellPresenterModule module;

    public SellPresenterModule_ProvidesActionBarControllerFactory(SellPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(SellPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static SellPresenterModule_ProvidesActionBarControllerFactory create(SellPresenterModule module) {
        return new SellPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(SellPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
