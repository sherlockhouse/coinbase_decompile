package com.coinbase.android;

import com.coinbase.android.pin.PINManager;
import com.coinbase.android.utils.RefWatcherWrapper;
import dagger.MembersInjector;
import java.util.Set;
import javax.inject.Provider;

public final class CoinbaseApplication_MembersInjector implements MembersInjector<CoinbaseApplication> {
    private final Provider<Set<ApplicationOnCreateListener>> mApplicationOnCreateListenersProvider;
    private final Provider<PINManager> mPINManagerProvider;
    private final Provider<RefWatcherWrapper> mRefWatcherWrapperProvider;

    public CoinbaseApplication_MembersInjector(Provider<PINManager> mPINManagerProvider, Provider<RefWatcherWrapper> mRefWatcherWrapperProvider, Provider<Set<ApplicationOnCreateListener>> mApplicationOnCreateListenersProvider) {
        this.mPINManagerProvider = mPINManagerProvider;
        this.mRefWatcherWrapperProvider = mRefWatcherWrapperProvider;
        this.mApplicationOnCreateListenersProvider = mApplicationOnCreateListenersProvider;
    }

    public static MembersInjector<CoinbaseApplication> create(Provider<PINManager> mPINManagerProvider, Provider<RefWatcherWrapper> mRefWatcherWrapperProvider, Provider<Set<ApplicationOnCreateListener>> mApplicationOnCreateListenersProvider) {
        return new CoinbaseApplication_MembersInjector(mPINManagerProvider, mRefWatcherWrapperProvider, mApplicationOnCreateListenersProvider);
    }

    public void injectMembers(CoinbaseApplication instance) {
        injectMPINManager(instance, (PINManager) this.mPINManagerProvider.get());
        injectMRefWatcherWrapper(instance, (RefWatcherWrapper) this.mRefWatcherWrapperProvider.get());
        injectMApplicationOnCreateListeners(instance, (Set) this.mApplicationOnCreateListenersProvider.get());
    }

    public static void injectMPINManager(CoinbaseApplication instance, PINManager mPINManager) {
        instance.mPINManager = mPINManager;
    }

    public static void injectMRefWatcherWrapper(CoinbaseApplication instance, RefWatcherWrapper mRefWatcherWrapper) {
        instance.mRefWatcherWrapper = mRefWatcherWrapper;
    }

    public static void injectMApplicationOnCreateListeners(CoinbaseApplication instance, Set<ApplicationOnCreateListener> mApplicationOnCreateListeners) {
        instance.mApplicationOnCreateListeners = mApplicationOnCreateListeners;
    }
}
