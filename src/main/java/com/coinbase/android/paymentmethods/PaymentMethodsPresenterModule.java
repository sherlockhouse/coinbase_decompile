package com.coinbase.android.paymentmethods;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class PaymentMethodsPresenterModule {
    private final ActionBarController mController;
    private final PaymentMethodsScreen mScreen;

    public PaymentMethodsPresenterModule(PaymentMethodsScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    PaymentMethodsScreen providesPaymentMethodsScreen() {
        return this.mScreen;
    }

    @ControllerScope
    ActionBarController providesActionBarController() {
        return this.mController;
    }
}
