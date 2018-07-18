package com.coinbase.android.paymentmethods.card;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;
import org.apache.commons.validator.routines.CreditCardValidator;

public class CardFormPresenterModule {
    private final ActionBarController mController;
    private final CardFormScreen mScreen;

    public CardFormPresenterModule(CardFormScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    public CardFormScreen providesCardFormScreen() {
        return this.mScreen;
    }

    @ControllerScope
    ActionBarController providesCurrentController() {
        return this.mController;
    }

    @ControllerScope
    CreditCardValidator providesCardValidator() {
        return new CreditCardValidator(63);
    }
}
