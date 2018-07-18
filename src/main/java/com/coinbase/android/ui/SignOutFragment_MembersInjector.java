package com.coinbase.android.ui;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class SignOutFragment_MembersInjector implements MembersInjector<SignOutFragment> {
    private final Provider<SignOutConnector> mSignOutConnectorProvider;

    public SignOutFragment_MembersInjector(Provider<SignOutConnector> mSignOutConnectorProvider) {
        this.mSignOutConnectorProvider = mSignOutConnectorProvider;
    }

    public static MembersInjector<SignOutFragment> create(Provider<SignOutConnector> mSignOutConnectorProvider) {
        return new SignOutFragment_MembersInjector(mSignOutConnectorProvider);
    }

    public void injectMembers(SignOutFragment instance) {
        injectMSignOutConnector(instance, (SignOutConnector) this.mSignOutConnectorProvider.get());
    }

    public static void injectMSignOutConnector(SignOutFragment instance, SignOutConnector mSignOutConnector) {
        instance.mSignOutConnector = mSignOutConnector;
    }
}
