package com.coinbase.android.settings.gdpr;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class GdprRequestSentPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final GdprRequestSentPresenterModule module;

    public GdprRequestSentPresenterModule_ProvidesActionBarControllerFactory(GdprRequestSentPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(GdprRequestSentPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static GdprRequestSentPresenterModule_ProvidesActionBarControllerFactory create(GdprRequestSentPresenterModule module) {
        return new GdprRequestSentPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(GdprRequestSentPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
