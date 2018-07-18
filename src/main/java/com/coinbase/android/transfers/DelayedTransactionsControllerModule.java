package com.coinbase.android.transfers;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class DelayedTransactionsControllerModule {
    private final ActionBarController mController;

    public DelayedTransactionsControllerModule(ActionBarController controller) {
        this.mController = controller;
    }

    @ControllerScope
    public ActionBarController providesController() {
        return this.mController;
    }
}
