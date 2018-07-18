package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class UpfrontKycIdentityFailedPresenterModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final UpfrontKycIdentityFailedPresenterModule module;

    public UpfrontKycIdentityFailedPresenterModule_ProvidesControllerFactory(UpfrontKycIdentityFailedPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(UpfrontKycIdentityFailedPresenterModule module) {
        return proxyProvidesController(module);
    }

    public static UpfrontKycIdentityFailedPresenterModule_ProvidesControllerFactory create(UpfrontKycIdentityFailedPresenterModule module) {
        return new UpfrontKycIdentityFailedPresenterModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(UpfrontKycIdentityFailedPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
