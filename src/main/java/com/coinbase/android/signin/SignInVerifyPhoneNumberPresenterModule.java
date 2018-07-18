package com.coinbase.android.signin;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class SignInVerifyPhoneNumberPresenterModule {
    private final ActionBarController mController;
    private final SignInVerifyPhoneNumberScreen mScreen;

    public SignInVerifyPhoneNumberPresenterModule(SignInVerifyPhoneNumberController controller) {
        this.mScreen = controller;
        this.mController = controller;
    }

    @ControllerScope
    public SignInVerifyPhoneNumberScreen providePhoneNumberPresenterModule() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
    }
}
