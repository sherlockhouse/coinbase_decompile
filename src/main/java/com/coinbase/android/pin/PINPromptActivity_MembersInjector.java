package com.coinbase.android.pin;

import com.coinbase.android.CoinbaseActivity_MembersInjector;
import com.coinbase.android.ScreenProtector;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class PINPromptActivity_MembersInjector implements MembersInjector<PINPromptActivity> {
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<PINManager> mPINManagerAndMPinManagerProvider;
    private final Provider<ScreenProtector> mScreenProtectorProvider;

    public PINPromptActivity_MembersInjector(Provider<LoginManager> mLoginManagerProvider, Provider<PINManager> mPINManagerAndMPinManagerProvider, Provider<ScreenProtector> mScreenProtectorProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mPINManagerAndMPinManagerProvider = mPINManagerAndMPinManagerProvider;
        this.mScreenProtectorProvider = mScreenProtectorProvider;
    }

    public static MembersInjector<PINPromptActivity> create(Provider<LoginManager> mLoginManagerProvider, Provider<PINManager> mPINManagerAndMPinManagerProvider, Provider<ScreenProtector> mScreenProtectorProvider) {
        return new PINPromptActivity_MembersInjector(mLoginManagerProvider, mPINManagerAndMPinManagerProvider, mScreenProtectorProvider);
    }

    public void injectMembers(PINPromptActivity instance) {
        CoinbaseActivity_MembersInjector.injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        CoinbaseActivity_MembersInjector.injectMPinManager(instance, (PINManager) this.mPINManagerAndMPinManagerProvider.get());
        CoinbaseActivity_MembersInjector.injectMScreenProtector(instance, (ScreenProtector) this.mScreenProtectorProvider.get());
        injectMPINManager(instance, (PINManager) this.mPINManagerAndMPinManagerProvider.get());
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
    }

    public static void injectMPINManager(PINPromptActivity instance, PINManager mPINManager) {
        instance.mPINManager = mPINManager;
    }

    public static void injectMLoginManager(PINPromptActivity instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }
}
