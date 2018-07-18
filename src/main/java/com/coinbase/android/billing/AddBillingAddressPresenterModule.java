package com.coinbase.android.billing;

import com.coinbase.android.FragmentScope;

public class AddBillingAddressPresenterModule {
    private final AddBillingAddressScreen mScreen;

    public AddBillingAddressPresenterModule(AddBillingAddressScreen screen) {
        this.mScreen = screen;
    }

    @FragmentScope
    AddBillingAddressScreen providesAddBillingAddressScreen() {
        return this.mScreen;
    }
}
