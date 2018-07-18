package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class UpfrontKycIdentityAcceptableDocumentsPresenterModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final UpfrontKycIdentityAcceptableDocumentsPresenterModule module;

    public UpfrontKycIdentityAcceptableDocumentsPresenterModule_ProvidesControllerFactory(UpfrontKycIdentityAcceptableDocumentsPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(UpfrontKycIdentityAcceptableDocumentsPresenterModule module) {
        return proxyProvidesController(module);
    }

    public static UpfrontKycIdentityAcceptableDocumentsPresenterModule_ProvidesControllerFactory create(UpfrontKycIdentityAcceptableDocumentsPresenterModule module) {
        return new UpfrontKycIdentityAcceptableDocumentsPresenterModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(UpfrontKycIdentityAcceptableDocumentsPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
