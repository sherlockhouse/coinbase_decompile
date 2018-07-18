package com.coinbase.android.signin;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class TwoFactorPresenterModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final TwoFactorPresenterModule module;

    public TwoFactorPresenterModule_ProvidesControllerFactory(TwoFactorPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(TwoFactorPresenterModule module) {
        return proxyProvidesController(module);
    }

    public static TwoFactorPresenterModule_ProvidesControllerFactory create(TwoFactorPresenterModule module) {
        return new TwoFactorPresenterModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(TwoFactorPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
