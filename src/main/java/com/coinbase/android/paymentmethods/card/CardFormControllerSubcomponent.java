package com.coinbase.android.paymentmethods.card;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface CardFormControllerSubcomponent {
    void inject(CardFormController cardFormController);
}
