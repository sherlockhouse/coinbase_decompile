package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface WithdrawFiatConfirmationControllerSubcomponent {
    void inject(WithdrawFiatConfirmationController withdrawFiatConfirmationController);
}
