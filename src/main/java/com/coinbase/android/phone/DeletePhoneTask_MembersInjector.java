package com.coinbase.android.phone;

import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class DeletePhoneTask_MembersInjector implements MembersInjector<DeletePhoneTask> {
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<PhoneNumbersUpdatedConnector> mPhoneNumbersUpdatedConnectorProvider;

    public DeletePhoneTask_MembersInjector(Provider<PhoneNumbersUpdatedConnector> mPhoneNumbersUpdatedConnectorProvider, Provider<LoginManager> mLoginManagerProvider) {
        this.mPhoneNumbersUpdatedConnectorProvider = mPhoneNumbersUpdatedConnectorProvider;
        this.mLoginManagerProvider = mLoginManagerProvider;
    }

    public static MembersInjector<DeletePhoneTask> create(Provider<PhoneNumbersUpdatedConnector> mPhoneNumbersUpdatedConnectorProvider, Provider<LoginManager> mLoginManagerProvider) {
        return new DeletePhoneTask_MembersInjector(mPhoneNumbersUpdatedConnectorProvider, mLoginManagerProvider);
    }

    public void injectMembers(DeletePhoneTask instance) {
        injectMPhoneNumbersUpdatedConnector(instance, (PhoneNumbersUpdatedConnector) this.mPhoneNumbersUpdatedConnectorProvider.get());
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
    }

    public static void injectMPhoneNumbersUpdatedConnector(DeletePhoneTask instance, PhoneNumbersUpdatedConnector mPhoneNumbersUpdatedConnector) {
        instance.mPhoneNumbersUpdatedConnector = mPhoneNumbersUpdatedConnector;
    }

    public static void injectMLoginManager(DeletePhoneTask instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }
}
