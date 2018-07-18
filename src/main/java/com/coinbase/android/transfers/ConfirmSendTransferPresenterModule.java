package com.coinbase.android.transfers;

import com.coinbase.android.ControllerScope;

public class ConfirmSendTransferPresenterModule {
    private final ConfirmSendTransferScreen mScreen;

    public ConfirmSendTransferPresenterModule(ConfirmSendTransferScreen confirmSendTransferScreen) {
        this.mScreen = confirmSendTransferScreen;
    }

    @ControllerScope
    public ConfirmSendTransferScreen providesScreen() {
        return this.mScreen;
    }
}
