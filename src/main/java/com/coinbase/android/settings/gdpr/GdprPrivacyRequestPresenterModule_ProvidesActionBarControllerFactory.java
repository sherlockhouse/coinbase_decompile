package com.coinbase.android.settings.gdpr;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class GdprPrivacyRequestPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final GdprPrivacyRequestPresenterModule module;

    public GdprPrivacyRequestPresenterModule_ProvidesActionBarControllerFactory(GdprPrivacyRequestPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(GdprPrivacyRequestPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static GdprPrivacyRequestPresenterModule_ProvidesActionBarControllerFactory create(GdprPrivacyRequestPresenterModule module) {
        return new GdprPrivacyRequestPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(GdprPrivacyRequestPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
