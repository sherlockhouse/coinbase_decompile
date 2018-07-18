package com.coinbase.android.identityverification;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class InAppIdentityDocumentScanPresenterModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final InAppIdentityDocumentScanPresenterModule module;

    public InAppIdentityDocumentScanPresenterModule_ProvidesControllerFactory(InAppIdentityDocumentScanPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(InAppIdentityDocumentScanPresenterModule module) {
        return proxyProvidesController(module);
    }

    public static InAppIdentityDocumentScanPresenterModule_ProvidesControllerFactory create(InAppIdentityDocumentScanPresenterModule module) {
        return new InAppIdentityDocumentScanPresenterModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(InAppIdentityDocumentScanPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
