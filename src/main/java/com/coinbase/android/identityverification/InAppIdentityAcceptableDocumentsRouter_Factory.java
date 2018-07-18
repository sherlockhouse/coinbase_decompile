package com.coinbase.android.identityverification;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class InAppIdentityAcceptableDocumentsRouter_Factory implements Factory<InAppIdentityAcceptableDocumentsRouter> {
    private final Provider<ActionBarController> controllerProvider;

    public InAppIdentityAcceptableDocumentsRouter_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public InAppIdentityAcceptableDocumentsRouter get() {
        return provideInstance(this.controllerProvider);
    }

    public static InAppIdentityAcceptableDocumentsRouter provideInstance(Provider<ActionBarController> controllerProvider) {
        return new InAppIdentityAcceptableDocumentsRouter((ActionBarController) controllerProvider.get());
    }

    public static InAppIdentityAcceptableDocumentsRouter_Factory create(Provider<ActionBarController> controllerProvider) {
        return new InAppIdentityAcceptableDocumentsRouter_Factory(controllerProvider);
    }

    public static InAppIdentityAcceptableDocumentsRouter newInAppIdentityAcceptableDocumentsRouter(ActionBarController controller) {
        return new InAppIdentityAcceptableDocumentsRouter(controller);
    }
}
