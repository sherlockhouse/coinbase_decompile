package com.coinbase.android.wbl;

import rx.functions.Action0;

final /* synthetic */ class AvailableBalanceCalculator$$Lambda$3 implements Action0 {
    private final AvailableBalanceCalculator arg$1;

    private AvailableBalanceCalculator$$Lambda$3(AvailableBalanceCalculator availableBalanceCalculator) {
        this.arg$1 = availableBalanceCalculator;
    }

    public static Action0 lambdaFactory$(AvailableBalanceCalculator availableBalanceCalculator) {
        return new AvailableBalanceCalculator$$Lambda$3(availableBalanceCalculator);
    }

    public void call() {
        this.arg$1.mSyncAccountsTask.syncAccounts();
    }
}
