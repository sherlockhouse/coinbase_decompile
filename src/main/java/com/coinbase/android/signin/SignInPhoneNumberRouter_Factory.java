package com.coinbase.android.signin;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SignInPhoneNumberRouter_Factory implements Factory<SignInPhoneNumberRouter> {
    private final Provider<ActionBarController> controllerProvider;

    public SignInPhoneNumberRouter_Factory(Provider<ActionBarController> controllerProvider) {
        this.controllerProvider = controllerProvider;
    }

    public SignInPhoneNumberRouter get() {
        return provideInstance(this.controllerProvider);
    }

    public static SignInPhoneNumberRouter provideInstance(Provider<ActionBarController> controllerProvider) {
        return new SignInPhoneNumberRouter((ActionBarController) controllerProvider.get());
    }

    public static SignInPhoneNumberRouter_Factory create(Provider<ActionBarController> controllerProvider) {
        return new SignInPhoneNumberRouter_Factory(controllerProvider);
    }

    public static SignInPhoneNumberRouter newSignInPhoneNumberRouter(ActionBarController controller) {
        return new SignInPhoneNumberRouter(controller);
    }
}
