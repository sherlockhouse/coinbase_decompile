package com.coinbase.android.signin;

import android.app.Application;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AuthIntentManager_Factory implements Factory<AuthIntentManager> {
    private final Provider<Application> appProvider;

    public AuthIntentManager_Factory(Provider<Application> appProvider) {
        this.appProvider = appProvider;
    }

    public AuthIntentManager get() {
        return provideInstance(this.appProvider);
    }

    public static AuthIntentManager provideInstance(Provider<Application> appProvider) {
        return new AuthIntentManager((Application) appProvider.get());
    }

    public static AuthIntentManager_Factory create(Provider<Application> appProvider) {
        return new AuthIntentManager_Factory(appProvider);
    }

    public static AuthIntentManager newAuthIntentManager(Application app) {
        return new AuthIntentManager(app);
    }
}
