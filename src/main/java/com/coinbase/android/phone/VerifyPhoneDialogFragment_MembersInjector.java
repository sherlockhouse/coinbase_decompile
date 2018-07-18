package com.coinbase.android.phone;

import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class VerifyPhoneDialogFragment_MembersInjector implements MembersInjector<VerifyPhoneDialogFragment> {
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<PhoneNumbersUpdatedConnector> mPhoneNumbersUpdatedConnectorProvider;

    public VerifyPhoneDialogFragment_MembersInjector(Provider<PhoneNumbersUpdatedConnector> mPhoneNumbersUpdatedConnectorProvider, Provider<LoginManager> mLoginManagerProvider) {
        this.mPhoneNumbersUpdatedConnectorProvider = mPhoneNumbersUpdatedConnectorProvider;
        this.mLoginManagerProvider = mLoginManagerProvider;
    }

    public static MembersInjector<VerifyPhoneDialogFragment> create(Provider<PhoneNumbersUpdatedConnector> mPhoneNumbersUpdatedConnectorProvider, Provider<LoginManager> mLoginManagerProvider) {
        return new VerifyPhoneDialogFragment_MembersInjector(mPhoneNumbersUpdatedConnectorProvider, mLoginManagerProvider);
    }

    public void injectMembers(VerifyPhoneDialogFragment instance) {
        injectMPhoneNumbersUpdatedConnector(instance, (PhoneNumbersUpdatedConnector) this.mPhoneNumbersUpdatedConnectorProvider.get());
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
    }

    public static void injectMPhoneNumbersUpdatedConnector(VerifyPhoneDialogFragment instance, PhoneNumbersUpdatedConnector mPhoneNumbersUpdatedConnector) {
        instance.mPhoneNumbersUpdatedConnector = mPhoneNumbersUpdatedConnector;
    }

    public static void injectMLoginManager(VerifyPhoneDialogFragment instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }
}
