package com.coinbase.android;

import com.coinbase.android.pin.PINManager;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class CoinbaseActivity_MembersInjector implements MembersInjector<CoinbaseActivity> {
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<PINManager> mPinManagerProvider;
    private final Provider<ScreenProtector> mScreenProtectorProvider;

    public CoinbaseActivity_MembersInjector(Provider<LoginManager> mLoginManagerProvider, Provider<PINManager> mPinManagerProvider, Provider<ScreenProtector> mScreenProtectorProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mPinManagerProvider = mPinManagerProvider;
        this.mScreenProtectorProvider = mScreenProtectorProvider;
    }

    public static MembersInjector<CoinbaseActivity> create(Provider<LoginManager> mLoginManagerProvider, Provider<PINManager> mPinManagerProvider, Provider<ScreenProtector> mScreenProtectorProvider) {
        return new CoinbaseActivity_MembersInjector(mLoginManagerProvider, mPinManagerProvider, mScreenProtectorProvider);
    }

    public void injectMembers(CoinbaseActivity instance) {
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        injectMPinManager(instance, (PINManager) this.mPinManagerProvider.get());
        injectMScreenProtector(instance, (ScreenProtector) this.mScreenProtectorProvider.get());
    }

    public static void injectMLoginManager(CoinbaseActivity instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }

    public static void injectMPinManager(CoinbaseActivity instance, PINManager mPinManager) {
        instance.mPinManager = mPinManager;
    }

    public static void injectMScreenProtector(CoinbaseActivity instance, ScreenProtector mScreenProtector) {
        instance.mScreenProtector = mScreenProtector;
    }
}
