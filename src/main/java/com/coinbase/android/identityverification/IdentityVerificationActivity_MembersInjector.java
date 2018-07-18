package com.coinbase.android.identityverification;

import com.coinbase.android.CoinbaseActivity_MembersInjector;
import com.coinbase.android.ScreenProtector;
import com.coinbase.android.pin.PINManager;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class IdentityVerificationActivity_MembersInjector implements MembersInjector<IdentityVerificationActivity> {
    private final Provider<BackNavigationConnector> mBackNavigationConnectorProvider;
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<PINManager> mPinManagerProvider;
    private final Provider<ScreenProtector> mScreenProtectorProvider;

    public IdentityVerificationActivity_MembersInjector(Provider<LoginManager> mLoginManagerProvider, Provider<PINManager> mPinManagerProvider, Provider<ScreenProtector> mScreenProtectorProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mPinManagerProvider = mPinManagerProvider;
        this.mScreenProtectorProvider = mScreenProtectorProvider;
        this.mBackNavigationConnectorProvider = mBackNavigationConnectorProvider;
    }

    public static MembersInjector<IdentityVerificationActivity> create(Provider<LoginManager> mLoginManagerProvider, Provider<PINManager> mPinManagerProvider, Provider<ScreenProtector> mScreenProtectorProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider) {
        return new IdentityVerificationActivity_MembersInjector(mLoginManagerProvider, mPinManagerProvider, mScreenProtectorProvider, mBackNavigationConnectorProvider);
    }

    public void injectMembers(IdentityVerificationActivity instance) {
        CoinbaseActivity_MembersInjector.injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        CoinbaseActivity_MembersInjector.injectMPinManager(instance, (PINManager) this.mPinManagerProvider.get());
        CoinbaseActivity_MembersInjector.injectMScreenProtector(instance, (ScreenProtector) this.mScreenProtectorProvider.get());
        injectMBackNavigationConnector(instance, (BackNavigationConnector) this.mBackNavigationConnectorProvider.get());
    }

    public static void injectMBackNavigationConnector(IdentityVerificationActivity instance, BackNavigationConnector mBackNavigationConnector) {
        instance.mBackNavigationConnector = mBackNavigationConnector;
    }
}
