package com.coinbase.android.billing;

import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BillingAddressListAdapter_MembersInjector implements MembersInjector<BillingAddressListAdapter> {
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<BillingAddressDeletedConnector> mBillingAddressDeletedConnectorProvider;

    public BillingAddressListAdapter_MembersInjector(Provider<LoginManager> loginManagerProvider, Provider<BillingAddressDeletedConnector> mBillingAddressDeletedConnectorProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.mBillingAddressDeletedConnectorProvider = mBillingAddressDeletedConnectorProvider;
    }

    public static MembersInjector<BillingAddressListAdapter> create(Provider<LoginManager> loginManagerProvider, Provider<BillingAddressDeletedConnector> mBillingAddressDeletedConnectorProvider) {
        return new BillingAddressListAdapter_MembersInjector(loginManagerProvider, mBillingAddressDeletedConnectorProvider);
    }

    public void injectMembers(BillingAddressListAdapter instance) {
        injectLoginManager(instance, (LoginManager) this.loginManagerProvider.get());
        injectMBillingAddressDeletedConnector(instance, (BillingAddressDeletedConnector) this.mBillingAddressDeletedConnectorProvider.get());
    }

    public static void injectLoginManager(BillingAddressListAdapter instance, LoginManager loginManager) {
        instance.loginManager = loginManager;
    }

    public static void injectMBillingAddressDeletedConnector(BillingAddressListAdapter instance, BillingAddressDeletedConnector mBillingAddressDeletedConnector) {
        instance.mBillingAddressDeletedConnector = mBillingAddressDeletedConnector;
    }
}
