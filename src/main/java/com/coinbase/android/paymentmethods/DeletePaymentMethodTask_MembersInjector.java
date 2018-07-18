package com.coinbase.android.paymentmethods;

import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class DeletePaymentMethodTask_MembersInjector implements MembersInjector<DeletePaymentMethodTask> {
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<PaymentMethodsUpdatedConnector> mConnectorProvider;

    public DeletePaymentMethodTask_MembersInjector(Provider<LoginManager> loginManagerProvider, Provider<PaymentMethodsUpdatedConnector> mConnectorProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.mConnectorProvider = mConnectorProvider;
    }

    public static MembersInjector<DeletePaymentMethodTask> create(Provider<LoginManager> loginManagerProvider, Provider<PaymentMethodsUpdatedConnector> mConnectorProvider) {
        return new DeletePaymentMethodTask_MembersInjector(loginManagerProvider, mConnectorProvider);
    }

    public void injectMembers(DeletePaymentMethodTask instance) {
        injectLoginManager(instance, (LoginManager) this.loginManagerProvider.get());
        injectMConnector(instance, (PaymentMethodsUpdatedConnector) this.mConnectorProvider.get());
    }

    public static void injectLoginManager(DeletePaymentMethodTask instance, LoginManager loginManager) {
        instance.loginManager = loginManager;
    }

    public static void injectMConnector(DeletePaymentMethodTask instance, PaymentMethodsUpdatedConnector mConnector) {
        instance.mConnector = mConnector;
    }
}
