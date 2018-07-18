package com.coinbase.android.paymentmethods;

import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;

public final class PlaidAccountLoginFragment_MembersInjector implements MembersInjector<PlaidAccountLoginFragment> {
    private final Provider<BankAccountsUpdatedConnector> mBankAccountsUpdatedConnectorProvider;
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<PaymentMethodsUpdatedConnector> mPaymentMethodsUpdatedConnectorProvider;

    public PlaidAccountLoginFragment_MembersInjector(Provider<LoginManager> mLoginManagerProvider, Provider<PaymentMethodsUpdatedConnector> mPaymentMethodsUpdatedConnectorProvider, Provider<BankAccountsUpdatedConnector> mBankAccountsUpdatedConnectorProvider, Provider<Scheduler> mMainSchedulerProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mPaymentMethodsUpdatedConnectorProvider = mPaymentMethodsUpdatedConnectorProvider;
        this.mBankAccountsUpdatedConnectorProvider = mBankAccountsUpdatedConnectorProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
    }

    public static MembersInjector<PlaidAccountLoginFragment> create(Provider<LoginManager> mLoginManagerProvider, Provider<PaymentMethodsUpdatedConnector> mPaymentMethodsUpdatedConnectorProvider, Provider<BankAccountsUpdatedConnector> mBankAccountsUpdatedConnectorProvider, Provider<Scheduler> mMainSchedulerProvider) {
        return new PlaidAccountLoginFragment_MembersInjector(mLoginManagerProvider, mPaymentMethodsUpdatedConnectorProvider, mBankAccountsUpdatedConnectorProvider, mMainSchedulerProvider);
    }

    public void injectMembers(PlaidAccountLoginFragment instance) {
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        injectMPaymentMethodsUpdatedConnector(instance, (PaymentMethodsUpdatedConnector) this.mPaymentMethodsUpdatedConnectorProvider.get());
        injectMBankAccountsUpdatedConnector(instance, (BankAccountsUpdatedConnector) this.mBankAccountsUpdatedConnectorProvider.get());
        injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
    }

    public static void injectMLoginManager(PlaidAccountLoginFragment instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }

    public static void injectMPaymentMethodsUpdatedConnector(PlaidAccountLoginFragment instance, PaymentMethodsUpdatedConnector mPaymentMethodsUpdatedConnector) {
        instance.mPaymentMethodsUpdatedConnector = mPaymentMethodsUpdatedConnector;
    }

    public static void injectMBankAccountsUpdatedConnector(PlaidAccountLoginFragment instance, BankAccountsUpdatedConnector mBankAccountsUpdatedConnector) {
        instance.mBankAccountsUpdatedConnector = mBankAccountsUpdatedConnector;
    }

    public static void injectMMainScheduler(PlaidAccountLoginFragment instance, Scheduler mMainScheduler) {
        instance.mMainScheduler = mMainScheduler;
    }
}
