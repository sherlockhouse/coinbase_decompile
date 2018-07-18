package com.coinbase.android.phone;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class PhoneNumberPresenterModule {
    private final ActionBarController mController;
    private final PhoneNumberScreen mScreen;

    public PhoneNumberPresenterModule(PhoneNumberController controller) {
        this.mScreen = controller;
        this.mController = controller;
    }

    @ControllerScope
    public PhoneNumberScreen providePhoneNumberPresenterModule() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
    }
}
