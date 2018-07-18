package com.coinbase.android.signin.state;

import com.coinbase.android.signin.SignInRouter;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UpfrontKycIdentityFailedPresenter_Factory implements Factory<UpfrontKycIdentityFailedPresenter> {
    private final Provider<UpfrontKycIdentityFailedRouter> routerProvider;
    private final Provider<UpfrontKycIdentityFailedScreen> screenProvider;
    private final Provider<SignInRouter> signInRouterProvider;

    public UpfrontKycIdentityFailedPresenter_Factory(Provider<UpfrontKycIdentityFailedScreen> screenProvider, Provider<SignInRouter> signInRouterProvider, Provider<UpfrontKycIdentityFailedRouter> routerProvider) {
        this.screenProvider = screenProvider;
        this.signInRouterProvider = signInRouterProvider;
        this.routerProvider = routerProvider;
    }

    public UpfrontKycIdentityFailedPresenter get() {
        return provideInstance(this.screenProvider, this.signInRouterProvider, this.routerProvider);
    }

    public static UpfrontKycIdentityFailedPresenter provideInstance(Provider<UpfrontKycIdentityFailedScreen> screenProvider, Provider<SignInRouter> signInRouterProvider, Provider<UpfrontKycIdentityFailedRouter> routerProvider) {
        return new UpfrontKycIdentityFailedPresenter((UpfrontKycIdentityFailedScreen) screenProvider.get(), (SignInRouter) signInRouterProvider.get(), (UpfrontKycIdentityFailedRouter) routerProvider.get());
    }

    public static UpfrontKycIdentityFailedPresenter_Factory create(Provider<UpfrontKycIdentityFailedScreen> screenProvider, Provider<SignInRouter> signInRouterProvider, Provider<UpfrontKycIdentityFailedRouter> routerProvider) {
        return new UpfrontKycIdentityFailedPresenter_Factory(screenProvider, signInRouterProvider, routerProvider);
    }

    public static UpfrontKycIdentityFailedPresenter newUpfrontKycIdentityFailedPresenter(UpfrontKycIdentityFailedScreen screen, SignInRouter signInRouter, UpfrontKycIdentityFailedRouter router) {
        return new UpfrontKycIdentityFailedPresenter(screen, signInRouter, router);
    }
}
