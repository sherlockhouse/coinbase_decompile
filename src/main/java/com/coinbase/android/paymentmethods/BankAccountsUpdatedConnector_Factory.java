package com.coinbase.android.paymentmethods;

import dagger.internal.Factory;

public final class BankAccountsUpdatedConnector_Factory implements Factory<BankAccountsUpdatedConnector> {
    private static final BankAccountsUpdatedConnector_Factory INSTANCE = new BankAccountsUpdatedConnector_Factory();

    public BankAccountsUpdatedConnector get() {
        return provideInstance();
    }

    public static BankAccountsUpdatedConnector provideInstance() {
        return new BankAccountsUpdatedConnector();
    }

    public static BankAccountsUpdatedConnector_Factory create() {
        return INSTANCE;
    }

    public static BankAccountsUpdatedConnector newBankAccountsUpdatedConnector() {
        return new BankAccountsUpdatedConnector();
    }
}
