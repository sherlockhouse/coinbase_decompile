package com.coinbase.android.utils;

import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PaymentMethodUtils_Factory implements Factory<PaymentMethodUtils> {
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<MoneyFormatterUtil> moneyFormatterUtilProvider;

    public PaymentMethodUtils_Factory(Provider<LoginManager> loginManagerProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
    }

    public PaymentMethodUtils get() {
        return provideInstance(this.loginManagerProvider, this.moneyFormatterUtilProvider);
    }

    public static PaymentMethodUtils provideInstance(Provider<LoginManager> loginManagerProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider) {
        return new PaymentMethodUtils((LoginManager) loginManagerProvider.get(), (MoneyFormatterUtil) moneyFormatterUtilProvider.get());
    }

    public static PaymentMethodUtils_Factory create(Provider<LoginManager> loginManagerProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider) {
        return new PaymentMethodUtils_Factory(loginManagerProvider, moneyFormatterUtilProvider);
    }

    public static PaymentMethodUtils newPaymentMethodUtils(LoginManager loginManager, MoneyFormatterUtil moneyFormatterUtil) {
        return new PaymentMethodUtils(loginManager, moneyFormatterUtil);
    }
}
