package com.coinbase.android.wbl;

import com.coinbase.android.ControllerScope;

public class WithdrawalBasedLimitsDialogPresenterModule {
    private final WithdrawalBasedLimitsDialogScreen mScreen;

    public WithdrawalBasedLimitsDialogPresenterModule(WithdrawalBasedLimitsDialogScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public WithdrawalBasedLimitsDialogScreen providesScreen() {
        return this.mScreen;
    }
}
