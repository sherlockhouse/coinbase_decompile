package com.coinbase.android.pin;

import com.coinbase.android.settings.LocalUserDataUpdatedConnector;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class PINSettingDialogFragment_MembersInjector implements MembersInjector<PINSettingDialogFragment> {
    private final Provider<LocalUserDataUpdatedConnector> mLocalUserDataUpdatedConnectorProvider;
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<PINManager> mPINManagerProvider;

    public PINSettingDialogFragment_MembersInjector(Provider<LoginManager> mLoginManagerProvider, Provider<LocalUserDataUpdatedConnector> mLocalUserDataUpdatedConnectorProvider, Provider<PINManager> mPINManagerProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mLocalUserDataUpdatedConnectorProvider = mLocalUserDataUpdatedConnectorProvider;
        this.mPINManagerProvider = mPINManagerProvider;
    }

    public static MembersInjector<PINSettingDialogFragment> create(Provider<LoginManager> mLoginManagerProvider, Provider<LocalUserDataUpdatedConnector> mLocalUserDataUpdatedConnectorProvider, Provider<PINManager> mPINManagerProvider) {
        return new PINSettingDialogFragment_MembersInjector(mLoginManagerProvider, mLocalUserDataUpdatedConnectorProvider, mPINManagerProvider);
    }

    public void injectMembers(PINSettingDialogFragment instance) {
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        injectMLocalUserDataUpdatedConnector(instance, (LocalUserDataUpdatedConnector) this.mLocalUserDataUpdatedConnectorProvider.get());
        injectMPINManager(instance, (PINManager) this.mPINManagerProvider.get());
    }

    public static void injectMLoginManager(PINSettingDialogFragment instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }

    public static void injectMLocalUserDataUpdatedConnector(PINSettingDialogFragment instance, LocalUserDataUpdatedConnector mLocalUserDataUpdatedConnector) {
        instance.mLocalUserDataUpdatedConnector = mLocalUserDataUpdatedConnector;
    }

    public static void injectMPINManager(PINSettingDialogFragment instance, PINManager mPINManager) {
        instance.mPINManager = mPINManager;
    }
}
