package com.coinbase.android.accounts;

import com.coinbase.android.ControllerScope;

public class AccountCryptoAddressPresenterModule {
    private final AccountCryptoAddressScreen mScreen;

    public AccountCryptoAddressPresenterModule(AccountCryptoAddressScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public AccountCryptoAddressScreen providesAccountAddressLayoutScreen() {
        return this.mScreen;
    }
}
