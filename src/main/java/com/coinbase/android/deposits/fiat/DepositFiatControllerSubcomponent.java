package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface DepositFiatControllerSubcomponent {
    void inject(DepositFiatController depositFiatController);
}
