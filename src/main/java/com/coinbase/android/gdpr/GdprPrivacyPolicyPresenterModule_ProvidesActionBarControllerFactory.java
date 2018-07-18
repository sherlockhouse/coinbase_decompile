package com.coinbase.android.gdpr;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class GdprPrivacyPolicyPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final GdprPrivacyPolicyPresenterModule module;

    public GdprPrivacyPolicyPresenterModule_ProvidesActionBarControllerFactory(GdprPrivacyPolicyPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(GdprPrivacyPolicyPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static GdprPrivacyPolicyPresenterModule_ProvidesActionBarControllerFactory create(GdprPrivacyPolicyPresenterModule module) {
        return new GdprPrivacyPolicyPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(GdprPrivacyPolicyPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
