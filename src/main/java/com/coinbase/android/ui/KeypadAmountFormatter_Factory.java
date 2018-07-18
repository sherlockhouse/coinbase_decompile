package com.coinbase.android.ui;

import android.app.Application;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class KeypadAmountFormatter_Factory implements Factory<KeypadAmountFormatter> {
    private final Provider<Application> applicationProvider;
    private final Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider;

    public KeypadAmountFormatter_Factory(Provider<Application> applicationProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider) {
        this.applicationProvider = applicationProvider;
        this.currenciesUpdatedConnectorProvider = currenciesUpdatedConnectorProvider;
    }

    public KeypadAmountFormatter get() {
        return provideInstance(this.applicationProvider, this.currenciesUpdatedConnectorProvider);
    }

    public static KeypadAmountFormatter provideInstance(Provider<Application> applicationProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider) {
        return new KeypadAmountFormatter((Application) applicationProvider.get(), (CurrenciesUpdatedConnector) currenciesUpdatedConnectorProvider.get());
    }

    public static KeypadAmountFormatter_Factory create(Provider<Application> applicationProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider) {
        return new KeypadAmountFormatter_Factory(applicationProvider, currenciesUpdatedConnectorProvider);
    }

    public static KeypadAmountFormatter newKeypadAmountFormatter(Application application, CurrenciesUpdatedConnector currenciesUpdatedConnector) {
        return new KeypadAmountFormatter(application, currenciesUpdatedConnector);
    }
}
