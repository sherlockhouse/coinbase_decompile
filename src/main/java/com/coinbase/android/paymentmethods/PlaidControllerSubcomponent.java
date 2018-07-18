package com.coinbase.android.paymentmethods;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface PlaidControllerSubcomponent {
    void inject(PlaidController plaidController);
}
