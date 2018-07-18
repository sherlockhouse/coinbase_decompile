package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class UpfrontKycTakeDocumentPhotoPresenterModule_ProvidesControllerFactory implements Factory<ActionBarController> {
    private final UpfrontKycTakeDocumentPhotoPresenterModule module;

    public UpfrontKycTakeDocumentPhotoPresenterModule_ProvidesControllerFactory(UpfrontKycTakeDocumentPhotoPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(UpfrontKycTakeDocumentPhotoPresenterModule module) {
        return proxyProvidesController(module);
    }

    public static UpfrontKycTakeDocumentPhotoPresenterModule_ProvidesControllerFactory create(UpfrontKycTakeDocumentPhotoPresenterModule module) {
        return new UpfrontKycTakeDocumentPhotoPresenterModule_ProvidesControllerFactory(module);
    }

    public static ActionBarController proxyProvidesController(UpfrontKycTakeDocumentPhotoPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
