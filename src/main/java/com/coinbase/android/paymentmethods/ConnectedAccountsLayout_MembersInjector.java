package com.coinbase.android.paymentmethods;

import com.coinbase.android.ui.BackgroundDimmer;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ConnectedAccountsLayout_MembersInjector implements MembersInjector<ConnectedAccountsLayout> {
    private final Provider<BackgroundDimmer> mBackgroundDimmerProvider;
    private final Provider<ConnectedAccountsPresenter> mPresenterProvider;

    public ConnectedAccountsLayout_MembersInjector(Provider<ConnectedAccountsPresenter> mPresenterProvider, Provider<BackgroundDimmer> mBackgroundDimmerProvider) {
        this.mPresenterProvider = mPresenterProvider;
        this.mBackgroundDimmerProvider = mBackgroundDimmerProvider;
    }

    public static MembersInjector<ConnectedAccountsLayout> create(Provider<ConnectedAccountsPresenter> mPresenterProvider, Provider<BackgroundDimmer> mBackgroundDimmerProvider) {
        return new ConnectedAccountsLayout_MembersInjector(mPresenterProvider, mBackgroundDimmerProvider);
    }

    public void injectMembers(ConnectedAccountsLayout instance) {
        injectMPresenter(instance, (ConnectedAccountsPresenter) this.mPresenterProvider.get());
        injectMBackgroundDimmer(instance, (BackgroundDimmer) this.mBackgroundDimmerProvider.get());
    }

    public static void injectMPresenter(ConnectedAccountsLayout instance, ConnectedAccountsPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }

    public static void injectMBackgroundDimmer(ConnectedAccountsLayout instance, BackgroundDimmer mBackgroundDimmer) {
        instance.mBackgroundDimmer = mBackgroundDimmer;
    }
}
