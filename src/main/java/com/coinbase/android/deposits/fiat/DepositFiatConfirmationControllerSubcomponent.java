package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface DepositFiatConfirmationControllerSubcomponent {
    void inject(DepositFiatConfirmationController depositFiatConfirmationController);
}
