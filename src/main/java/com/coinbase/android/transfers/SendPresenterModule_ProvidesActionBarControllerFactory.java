package com.coinbase.android.transfers;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SendPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final SendPresenterModule module;

    public SendPresenterModule_ProvidesActionBarControllerFactory(SendPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(SendPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static SendPresenterModule_ProvidesActionBarControllerFactory create(SendPresenterModule module) {
        return new SendPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(SendPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
