package com.coinbase.android.paymentmethods;

import com.coinbase.android.FragmentScope;

public class IAVLoginPresenterModule {
    private final IAVLoginScreen mScreen;

    public IAVLoginPresenterModule(IAVLoginScreen screen) {
        this.mScreen = screen;
    }

    @FragmentScope
    IAVLoginScreen providesIAVLoginScreen() {
        return this.mScreen;
    }
}
