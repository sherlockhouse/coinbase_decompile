package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UpfrontKycIdentityFailedRouter_Factory implements Factory<UpfrontKycIdentityFailedRouter> {
    private final Provider<ActionBarController> controllerProvider;

    public UpfrontKycIdentityFailedRouter_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public UpfrontKycIdentityFailedRouter get() {
        return provideInstance(this.controllerProvider);
    }

    public static UpfrontKycIdentityFailedRouter provideInstance(Provider<ActionBarController> controllerProvider) {
        return new UpfrontKycIdentityFailedRouter((ActionBarController) controllerProvider.get());
    }

    public static UpfrontKycIdentityFailedRouter_Factory create(Provider<ActionBarController> controllerProvider) {
        return new UpfrontKycIdentityFailedRouter_Factory(controllerProvider);
    }

    public static UpfrontKycIdentityFailedRouter newUpfrontKycIdentityFailedRouter(ActionBarController controller) {
        return new UpfrontKycIdentityFailedRouter(controller);
    }
}
