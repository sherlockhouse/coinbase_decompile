package com.coinbase.android.identityverification;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class InAppIdentityDocumentScanRouter_Factory implements Factory<InAppIdentityDocumentScanRouter> {
    private final Provider<ActionBarController> controllerProvider;

    public InAppIdentityDocumentScanRouter_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public InAppIdentityDocumentScanRouter get() {
        return provideInstance(this.controllerProvider);
    }

    public static InAppIdentityDocumentScanRouter provideInstance(Provider<ActionBarController> controllerProvider) {
        return new InAppIdentityDocumentScanRouter((ActionBarController) controllerProvider.get());
    }

    public static InAppIdentityDocumentScanRouter_Factory create(Provider<ActionBarController> controllerProvider) {
        return new InAppIdentityDocumentScanRouter_Factory(controllerProvider);
    }

    public static InAppIdentityDocumentScanRouter newInAppIdentityDocumentScanRouter(ActionBarController controller) {
        return new InAppIdentityDocumentScanRouter(controller);
    }
}
