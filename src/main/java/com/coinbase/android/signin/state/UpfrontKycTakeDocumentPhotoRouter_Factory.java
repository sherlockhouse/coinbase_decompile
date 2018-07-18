package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UpfrontKycTakeDocumentPhotoRouter_Factory implements Factory<UpfrontKycTakeDocumentPhotoRouter> {
    private final Provider<ActionBarController> controllerProvider;

    public UpfrontKycTakeDocumentPhotoRouter_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public UpfrontKycTakeDocumentPhotoRouter get() {
        return provideInstance(this.controllerProvider);
    }

    public static UpfrontKycTakeDocumentPhotoRouter provideInstance(Provider<ActionBarController> controllerProvider) {
        return new UpfrontKycTakeDocumentPhotoRouter((ActionBarController) controllerProvider.get());
    }

    public static UpfrontKycTakeDocumentPhotoRouter_Factory create(Provider<ActionBarController> controllerProvider) {
        return new UpfrontKycTakeDocumentPhotoRouter_Factory(controllerProvider);
    }

    public static UpfrontKycTakeDocumentPhotoRouter newUpfrontKycTakeDocumentPhotoRouter(ActionBarController controller) {
        return new UpfrontKycTakeDocumentPhotoRouter(controller);
    }
}
