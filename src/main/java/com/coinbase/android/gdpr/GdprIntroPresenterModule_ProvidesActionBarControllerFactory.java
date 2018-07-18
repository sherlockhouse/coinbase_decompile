package com.coinbase.android.gdpr;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class GdprIntroPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final GdprIntroPresenterModule module;

    public GdprIntroPresenterModule_ProvidesActionBarControllerFactory(GdprIntroPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(GdprIntroPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static GdprIntroPresenterModule_ProvidesActionBarControllerFactory create(GdprIntroPresenterModule module) {
        return new GdprIntroPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(GdprIntroPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
