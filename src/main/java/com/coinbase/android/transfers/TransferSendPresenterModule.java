package com.coinbase.android.transfers;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.utils.ApiErrorHandler;
import com.coinbase.android.wbl.WithdrawalBasedLimitsApiErrorHandler;

public class TransferSendPresenterModule {
    private final TransferSendScreen mTransferSendScreen;

    public TransferSendPresenterModule(TransferSendScreen screen) {
        this.mTransferSendScreen = screen;
    }

    @ControllerScope
    public TransferSendScreen providesTransferSendScreen() {
        return this.mTransferSendScreen;
    }

    @ControllerScope
    ApiErrorHandler providesApiErrorHandler(WithdrawalBasedLimitsApiErrorHandler withdrawalBasedLimitsApiErrorHandler) {
        return TransferSendPresenterModule$$Lambda$1.lambdaFactory$(withdrawalBasedLimitsApiErrorHandler);
    }
}
