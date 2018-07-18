package com.coinbase.android.paymentmethods.linkedaccounts;

import android.app.Application;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.utils.PaymentMethodUtils;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class LinkedAccountsPickerPresenter_Factory implements Factory<LinkedAccountsPickerPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<BackNavigationConnector> backNavigationConnectorProvider;
    private final Provider<LinkedAccountConnector> linkedAccountConnectorProvider;
    private final Provider<PaymentMethodUtils> paymentMethodUtilsProvider;
    private final Provider<LinkedAccountsPickerScreen> screenProvider;

    public LinkedAccountsPickerPresenter_Factory(Provider<Application> applicationProvider, Provider<LinkedAccountsPickerScreen> screenProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider) {
        this.applicationProvider = applicationProvider;
        this.screenProvider = screenProvider;
        this.paymentMethodUtilsProvider = paymentMethodUtilsProvider;
        this.linkedAccountConnectorProvider = linkedAccountConnectorProvider;
        this.backNavigationConnectorProvider = backNavigationConnectorProvider;
    }

    public LinkedAccountsPickerPresenter get() {
        return provideInstance(this.applicationProvider, this.screenProvider, this.paymentMethodUtilsProvider, this.linkedAccountConnectorProvider, this.backNavigationConnectorProvider);
    }

    public static LinkedAccountsPickerPresenter provideInstance(Provider<Application> applicationProvider, Provider<LinkedAccountsPickerScreen> screenProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider) {
        return new LinkedAccountsPickerPresenter((Application) applicationProvider.get(), (LinkedAccountsPickerScreen) screenProvider.get(), (PaymentMethodUtils) paymentMethodUtilsProvider.get(), (LinkedAccountConnector) linkedAccountConnectorProvider.get(), (BackNavigationConnector) backNavigationConnectorProvider.get());
    }

    public static LinkedAccountsPickerPresenter_Factory create(Provider<Application> applicationProvider, Provider<LinkedAccountsPickerScreen> screenProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<BackNavigationConnector> backNavigationConnectorProvider) {
        return new LinkedAccountsPickerPresenter_Factory(applicationProvider, screenProvider, paymentMethodUtilsProvider, linkedAccountConnectorProvider, backNavigationConnectorProvider);
    }

    public static LinkedAccountsPickerPresenter newLinkedAccountsPickerPresenter(Application application, Object screen, PaymentMethodUtils paymentMethodUtils, LinkedAccountConnector linkedAccountConnector, BackNavigationConnector backNavigationConnector) {
        return new LinkedAccountsPickerPresenter(application, (LinkedAccountsPickerScreen) screen, paymentMethodUtils, linkedAccountConnector, backNavigationConnector);
    }
}
