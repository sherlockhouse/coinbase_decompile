package com.coinbase.android.signin.state;

import com.coinbase.android.signin.SignInRouter;
import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UpfrontKycIdentityAcceptableDocumentsRouter_Factory implements Factory<UpfrontKycIdentityAcceptableDocumentsRouter> {
    private final Provider<ActionBarController> controllerProvider;
    private final Provider<SignInRouter> signInRouterProvider;

    public UpfrontKycIdentityAcceptableDocumentsRouter_Factory(Provider<ActionBarController> controllerProvider, Provider<SignInRouter> signInRouterProvider) {
        this.controllerProvider = controllerProvider;
        this.signInRouterProvider = signInRouterProvider;
    }

    public UpfrontKycIdentityAcceptableDocumentsRouter get() {
        return provideInstance(this.controllerProvider, this.signInRouterProvider);
    }

    public static UpfrontKycIdentityAcceptableDocumentsRouter provideInstance(Provider<ActionBarController> controllerProvider, Provider<SignInRouter> signInRouterProvider) {
        return new UpfrontKycIdentityAcceptableDocumentsRouter((ActionBarController) controllerProvider.get(), (SignInRouter) signInRouterProvider.get());
    }

    public static UpfrontKycIdentityAcceptableDocumentsRouter_Factory create(Provider<ActionBarController> controllerProvider, Provider<SignInRouter> signInRouterProvider) {
        return new UpfrontKycIdentityAcceptableDocumentsRouter_Factory(controllerProvider, signInRouterProvider);
    }

    public static UpfrontKycIdentityAcceptableDocumentsRouter newUpfrontKycIdentityAcceptableDocumentsRouter(ActionBarController controller, SignInRouter signInRouter) {
        return new UpfrontKycIdentityAcceptableDocumentsRouter(controller, signInRouter);
    }
}
