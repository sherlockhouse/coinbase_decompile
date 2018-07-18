package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface WithdrawFiatControllerSubcomponent {
    void inject(WithdrawFiatController withdrawFiatController);
}
