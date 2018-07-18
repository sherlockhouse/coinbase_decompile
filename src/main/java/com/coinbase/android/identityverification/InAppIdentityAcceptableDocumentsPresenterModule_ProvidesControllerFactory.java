package com.coinbase.android.identityverification;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class InAppIdentityAcceptableDocumentsPresenterModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final InAppIdentityAcceptableDocumentsPresenterModule module;

    public InAppIdentityAcceptableDocumentsPresenterModule_ProvidesControllerFactory(InAppIdentityAcceptableDocumentsPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(InAppIdentityAcceptableDocumentsPresenterModule module) {
        return proxyProvidesController(module);
    }

    public static InAppIdentityAcceptableDocumentsPresenterModule_ProvidesControllerFactory create(InAppIdentityAcceptableDocumentsPresenterModule module) {
        return new InAppIdentityAcceptableDocumentsPresenterModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(InAppIdentityAcceptableDocumentsPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
