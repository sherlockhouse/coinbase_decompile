package com.coinbase.android.utils;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class TransferUtils_Factory implements Factory<TransferUtils> {
    private final Provider<MoneyFormatterUtil> moneyFormatterUtilProvider;

    public TransferUtils_Factory(Provider<MoneyFormatterUtil> moneyFormatterUtilProvider) {
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
    }

    public TransferUtils get() {
        return provideInstance(this.moneyFormatterUtilProvider);
    }

    public static TransferUtils provideInstance(Provider<MoneyFormatterUtil> moneyFormatterUtilProvider) {
        return new TransferUtils((MoneyFormatterUtil) moneyFormatterUtilProvider.get());
    }

    public static TransferUtils_Factory create(Provider<MoneyFormatterUtil> moneyFormatterUtilProvider) {
        return new TransferUtils_Factory(moneyFormatterUtilProvider);
    }

    public static TransferUtils newTransferUtils(MoneyFormatterUtil moneyFormatterUtil) {
        return new TransferUtils(moneyFormatterUtil);
    }
}
