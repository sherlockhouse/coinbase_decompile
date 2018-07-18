package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class UpfrontKycIdentityDocumentScanPresenterModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final UpfrontKycIdentityDocumentScanPresenterModule module;

    public UpfrontKycIdentityDocumentScanPresenterModule_ProvidesControllerFactory(UpfrontKycIdentityDocumentScanPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(UpfrontKycIdentityDocumentScanPresenterModule module) {
        return proxyProvidesController(module);
    }

    public static UpfrontKycIdentityDocumentScanPresenterModule_ProvidesControllerFactory create(UpfrontKycIdentityDocumentScanPresenterModule module) {
        return new UpfrontKycIdentityDocumentScanPresenterModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(UpfrontKycIdentityDocumentScanPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
