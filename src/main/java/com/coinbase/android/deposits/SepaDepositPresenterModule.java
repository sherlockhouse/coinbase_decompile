package com.coinbase.android.deposits;

import com.coinbase.android.FragmentScope;

public class SepaDepositPresenterModule {
    private final SepaDepositScreen mScreen;

    public SepaDepositPresenterModule(SepaDepositScreen screen) {
        this.mScreen = screen;
    }

    @FragmentScope
    SepaDepositScreen providesSepaDepositScreen() {
        return this.mScreen;
    }
}
