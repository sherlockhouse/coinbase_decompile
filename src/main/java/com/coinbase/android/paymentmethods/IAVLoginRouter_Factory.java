package com.coinbase.android.paymentmethods;

import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class IAVLoginRouter_Factory implements Factory<IAVLoginRouter> {
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<IAVLoginScreen> screenProvider;

    public IAVLoginRouter_Factory(Provider<IAVLoginScreen> screenProvider, Provider<LoginManager> loginManagerProvider) {
        this.screenProvider = screenProvider;
        this.loginManagerProvider = loginManagerProvider;
    }

    public IAVLoginRouter get() {
        return provideInstance(this.screenProvider, this.loginManagerProvider);
    }

    public static IAVLoginRouter provideInstance(Provider<IAVLoginScreen> screenProvider, Provider<LoginManager> loginManagerProvider) {
        return new IAVLoginRouter((IAVLoginScreen) screenProvider.get(), (LoginManager) loginManagerProvider.get());
    }

    public static IAVLoginRouter_Factory create(Provider<IAVLoginScreen> screenProvider, Provider<LoginManager> loginManagerProvider) {
        return new IAVLoginRouter_Factory(screenProvider, loginManagerProvider);
    }

    public static IAVLoginRouter newIAVLoginRouter(IAVLoginScreen screen, LoginManager loginManager) {
        return new IAVLoginRouter(screen, loginManager);
    }
}
