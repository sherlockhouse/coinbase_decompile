package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class UpfrontKycIdentityProcessingPresenterModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final UpfrontKycIdentityProcessingPresenterModule module;

    public UpfrontKycIdentityProcessingPresenterModule_ProvidesControllerFactory(UpfrontKycIdentityProcessingPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(UpfrontKycIdentityProcessingPresenterModule module) {
        return proxyProvidesController(module);
    }

    public static UpfrontKycIdentityProcessingPresenterModule_ProvidesControllerFactory create(UpfrontKycIdentityProcessingPresenterModule module) {
        return new UpfrontKycIdentityProcessingPresenterModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(UpfrontKycIdentityProcessingPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
