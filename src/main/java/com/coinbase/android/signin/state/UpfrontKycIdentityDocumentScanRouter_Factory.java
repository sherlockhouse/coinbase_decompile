package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UpfrontKycIdentityDocumentScanRouter_Factory implements Factory<UpfrontKycIdentityDocumentScanRouter> {
    private final Provider<ActionBarController> controllerProvider;

    public UpfrontKycIdentityDocumentScanRouter_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public UpfrontKycIdentityDocumentScanRouter get() {
        return provideInstance(this.controllerProvider);
    }

    public static UpfrontKycIdentityDocumentScanRouter provideInstance(Provider<ActionBarController> controllerProvider) {
        return new UpfrontKycIdentityDocumentScanRouter((ActionBarController) controllerProvider.get());
    }

    public static UpfrontKycIdentityDocumentScanRouter_Factory create(Provider<ActionBarController> controllerProvider) {
        return new UpfrontKycIdentityDocumentScanRouter_Factory(controllerProvider);
    }

    public static UpfrontKycIdentityDocumentScanRouter newUpfrontKycIdentityDocumentScanRouter(ActionBarController controller) {
        return new UpfrontKycIdentityDocumentScanRouter(controller);
    }
}
