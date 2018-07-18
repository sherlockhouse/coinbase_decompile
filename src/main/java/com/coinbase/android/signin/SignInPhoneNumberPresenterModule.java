package com.coinbase.android.signin;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class SignInPhoneNumberPresenterModule {
    private final ActionBarController mController;
    private final SignInPhoneNumberScreen mScreen;

    public SignInPhoneNumberPresenterModule(SignInPhoneNumberController controller) {
        this.mScreen = controller;
        this.mController = controller;
    }

    @ControllerScope
    public SignInPhoneNumberScreen providePhoneNumberPresenterModule() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
    }
}
