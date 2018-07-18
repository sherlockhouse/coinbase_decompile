package com.coinbase.android.deposits;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface FiatTransactionsControllerSubcomponent {
    void inject(FiatTransactionsController fiatTransactionsController);
}
