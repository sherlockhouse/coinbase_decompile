package com.coinbase.android.signin;

import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.BackNavigationConnector;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class RegistrationControllerActivity_MembersInjector implements MembersInjector<RegistrationControllerActivity> {
    private final Provider<AuthManager> mAuthManagerProvider;
    private final Provider<BackNavigationConnector> mBackNavigationConnectorProvider;
    private final Provider<SplitTesting> mSplitTestingProvider;

    public RegistrationControllerActivity_MembersInjector(Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<SplitTesting> mSplitTestingProvider, Provider<AuthManager> mAuthManagerProvider) {
        this.mBackNavigationConnectorProvider = mBackNavigationConnectorProvider;
        this.mSplitTestingProvider = mSplitTestingProvider;
        this.mAuthManagerProvider = mAuthManagerProvider;
    }

    public static MembersInjector<RegistrationControllerActivity> create(Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<SplitTesting> mSplitTestingProvider, Provider<AuthManager> mAuthManagerProvider) {
        return new RegistrationControllerActivity_MembersInjector(mBackNavigationConnectorProvider, mSplitTestingProvider, mAuthManagerProvider);
    }

    public void injectMembers(RegistrationControllerActivity instance) {
        injectMBackNavigationConnector(instance, (BackNavigationConnector) this.mBackNavigationConnectorProvider.get());
        injectMSplitTesting(instance, (SplitTesting) this.mSplitTestingProvider.get());
        injectMAuthManager(instance, (AuthManager) this.mAuthManagerProvider.get());
    }

    public static void injectMBackNavigationConnector(RegistrationControllerActivity instance, BackNavigationConnector mBackNavigationConnector) {
        instance.mBackNavigationConnector = mBackNavigationConnector;
    }

    public static void injectMSplitTesting(RegistrationControllerActivity instance, SplitTesting mSplitTesting) {
        instance.mSplitTesting = mSplitTesting;
    }

    public static void injectMAuthManager(RegistrationControllerActivity instance, AuthManager mAuthManager) {
        instance.mAuthManager = mAuthManager;
    }
}
