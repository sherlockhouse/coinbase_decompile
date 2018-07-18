package com.coinbase.android.transfers;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface DelayedTransactionDialogControllerSubcomponent {
    void inject(DelayedTransactionDialogController delayedTransactionDialogController);
}
