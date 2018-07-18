package com.coinbase.android.signin;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AcceptTermsPresenterModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final AcceptTermsPresenterModule module;

    public AcceptTermsPresenterModule_ProvidesControllerFactory(AcceptTermsPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(AcceptTermsPresenterModule module) {
        return proxyProvidesController(module);
    }

    public static AcceptTermsPresenterModule_ProvidesControllerFactory create(AcceptTermsPresenterModule module) {
        return new AcceptTermsPresenterModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(AcceptTermsPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
