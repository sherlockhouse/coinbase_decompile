package com.coinbase.android.accounts;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AccountCryptoAddressPresenterModule_ProvidesAccountAddressLayoutScreenFactory implements Factory<AccountCryptoAddressScreen> {
    private final AccountCryptoAddressPresenterModule module;

    public AccountCryptoAddressPresenterModule_ProvidesAccountAddressLayoutScreenFactory(AccountCryptoAddressPresenterModule module) {
        this.module = module;
    }

    public AccountCryptoAddressScreen get() {
        return provideInstance(this.module);
    }

    public static AccountCryptoAddressScreen provideInstance(AccountCryptoAddressPresenterModule module) {
        return proxyProvidesAccountAddressLayoutScreen(module);
    }

    public static AccountCryptoAddressPresenterModule_ProvidesAccountAddressLayoutScreenFactory create(AccountCryptoAddressPresenterModule module) {
        return new AccountCryptoAddressPresenterModule_ProvidesAccountAddressLayoutScreenFactory(module);
    }

    public static AccountCryptoAddressScreen proxyProvidesAccountAddressLayoutScreen(AccountCryptoAddressPresenterModule instance) {
        return (AccountCryptoAddressScreen) Preconditions.checkNotNull(instance.providesAccountAddressLayoutScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
