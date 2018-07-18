package com.coinbase.android.transactions;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface TransactionListLayoutSubcomponent {
    void inject(TransactionListLayout transactionListLayout);
}
