package com.coinbase.android.paymentmethods;

import com.coinbase.android.ui.BackgroundDimmer;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class IAVLoginFragment_MembersInjector implements MembersInjector<IAVLoginFragment> {
    private final Provider<BackgroundDimmer> mDimmerProvider;
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<IAVLoginPresenter> mPresenterProvider;

    public IAVLoginFragment_MembersInjector(Provider<LoginManager> mLoginManagerProvider, Provider<BackgroundDimmer> mDimmerProvider, Provider<IAVLoginPresenter> mPresenterProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mDimmerProvider = mDimmerProvider;
        this.mPresenterProvider = mPresenterProvider;
    }

    public static MembersInjector<IAVLoginFragment> create(Provider<LoginManager> mLoginManagerProvider, Provider<BackgroundDimmer> mDimmerProvider, Provider<IAVLoginPresenter> mPresenterProvider) {
        return new IAVLoginFragment_MembersInjector(mLoginManagerProvider, mDimmerProvider, mPresenterProvider);
    }

    public void injectMembers(IAVLoginFragment instance) {
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        injectMDimmer(instance, (BackgroundDimmer) this.mDimmerProvider.get());
        injectMPresenter(instance, (IAVLoginPresenter) this.mPresenterProvider.get());
    }

    public static void injectMLoginManager(IAVLoginFragment instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }

    public static void injectMDimmer(IAVLoginFragment instance, BackgroundDimmer mDimmer) {
        instance.mDimmer = mDimmer;
    }

    public static void injectMPresenter(IAVLoginFragment instance, IAVLoginPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }
}
