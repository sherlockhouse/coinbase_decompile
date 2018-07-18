package com.coinbase.android.signin.state;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UpfrontKycIdentityProcessingRouter_Factory implements Factory<UpfrontKycIdentityProcessingRouter> {
    private final Provider<ActionBarController> controllerProvider;

    public UpfrontKycIdentityProcessingRouter_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public UpfrontKycIdentityProcessingRouter get() {
        return provideInstance(this.controllerProvider);
    }

    public static UpfrontKycIdentityProcessingRouter provideInstance(Provider<ActionBarController> controllerProvider) {
        return new UpfrontKycIdentityProcessingRouter((ActionBarController) controllerProvider.get());
    }

    public static UpfrontKycIdentityProcessingRouter_Factory create(Provider<ActionBarController> controllerProvider) {
        return new UpfrontKycIdentityProcessingRouter_Factory(controllerProvider);
    }

    public static UpfrontKycIdentityProcessingRouter newUpfrontKycIdentityProcessingRouter(ActionBarController controller) {
        return new UpfrontKycIdentityProcessingRouter(controller);
    }
}
