package com.coinbase.android.gdpr;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class GdprMarketingEmailPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final GdprMarketingEmailPresenterModule module;

    public GdprMarketingEmailPresenterModule_ProvidesActionBarControllerFactory(GdprMarketingEmailPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(GdprMarketingEmailPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static GdprMarketingEmailPresenterModule_ProvidesActionBarControllerFactory create(GdprMarketingEmailPresenterModule module) {
        return new GdprMarketingEmailPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(GdprMarketingEmailPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
