package com.coinbase.android.dashboard;

import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SpotPriceUpdatedConnector_Factory implements Factory<SpotPriceUpdatedConnector> {
    private final Provider<LoginManager> loginManagerProvider;

    public SpotPriceUpdatedConnector_Factory(Provider<LoginManager> loginManagerProvider) {
        this.loginManagerProvider = loginManagerProvider;
    }

    public SpotPriceUpdatedConnector get() {
        return provideInstance(this.loginManagerProvider);
    }

    public static SpotPriceUpdatedConnector provideInstance(Provider<LoginManager> loginManagerProvider) {
        return new SpotPriceUpdatedConnector((LoginManager) loginManagerProvider.get());
    }

    public static SpotPriceUpdatedConnector_Factory create(Provider<LoginManager> loginManagerProvider) {
        return new SpotPriceUpdatedConnector_Factory(loginManagerProvider);
    }

    public static SpotPriceUpdatedConnector newSpotPriceUpdatedConnector(LoginManager loginManager) {
        return new SpotPriceUpdatedConnector(loginManager);
    }
}
