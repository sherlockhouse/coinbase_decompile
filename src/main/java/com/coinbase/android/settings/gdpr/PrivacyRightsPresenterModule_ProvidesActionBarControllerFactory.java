package com.coinbase.android.settings.gdpr;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class PrivacyRightsPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final PrivacyRightsPresenterModule module;

    public PrivacyRightsPresenterModule_ProvidesActionBarControllerFactory(PrivacyRightsPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(PrivacyRightsPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static PrivacyRightsPresenterModule_ProvidesActionBarControllerFactory create(PrivacyRightsPresenterModule module) {
        return new PrivacyRightsPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(PrivacyRightsPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
