package com.coinbase.android.paymentmethods.linkedaccounts;

import com.coinbase.android.utils.PaymentMethodUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class LinkedAccountItemLayout_MembersInjector implements MembersInjector<LinkedAccountItemLayout> {
    private final Provider<LinkedAccountConnector> mConnectorProvider;
    private final Provider<PaymentMethodUtils> mPaymentMethodUtilsProvider;

    public LinkedAccountItemLayout_MembersInjector(Provider<LinkedAccountConnector> mConnectorProvider, Provider<PaymentMethodUtils> mPaymentMethodUtilsProvider) {
        this.mConnectorProvider = mConnectorProvider;
        this.mPaymentMethodUtilsProvider = mPaymentMethodUtilsProvider;
    }

    public static MembersInjector<LinkedAccountItemLayout> create(Provider<LinkedAccountConnector> mConnectorProvider, Provider<PaymentMethodUtils> mPaymentMethodUtilsProvider) {
        return new LinkedAccountItemLayout_MembersInjector(mConnectorProvider, mPaymentMethodUtilsProvider);
    }

    public void injectMembers(LinkedAccountItemLayout instance) {
        injectMConnector(instance, (LinkedAccountConnector) this.mConnectorProvider.get());
        injectMPaymentMethodUtils(instance, (PaymentMethodUtils) this.mPaymentMethodUtilsProvider.get());
    }

    public static void injectMConnector(LinkedAccountItemLayout instance, LinkedAccountConnector mConnector) {
        instance.mConnector = mConnector;
    }

    public static void injectMPaymentMethodUtils(LinkedAccountItemLayout instance, PaymentMethodUtils mPaymentMethodUtils) {
        instance.mPaymentMethodUtils = mPaymentMethodUtils;
    }
}
