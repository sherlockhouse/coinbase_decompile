package com.coinbase.android.transfers;

import com.coinbase.android.utils.ApiErrorHandler;
import com.coinbase.android.wbl.WithdrawalBasedLimitsApiErrorHandler;

final /* synthetic */ class TransferSendPresenterModule$$Lambda$1 implements ApiErrorHandler {
    private final WithdrawalBasedLimitsApiErrorHandler arg$1;

    private TransferSendPresenterModule$$Lambda$1(WithdrawalBasedLimitsApiErrorHandler withdrawalBasedLimitsApiErrorHandler) {
        this.arg$1 = withdrawalBasedLimitsApiErrorHandler;
    }

    public static ApiErrorHandler lambdaFactory$(WithdrawalBasedLimitsApiErrorHandler withdrawalBasedLimitsApiErrorHandler) {
        return new TransferSendPresenterModule$$Lambda$1(withdrawalBasedLimitsApiErrorHandler);
    }

    public boolean handleApiError(String str, String str2) {
        return this.arg$1.handleApiError(str, str2);
    }
}
