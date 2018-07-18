package com.coinbase.android.gdpr;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class GdprModalRouter_Factory implements Factory<GdprModalRouter> {
    private final Provider<OnboardingRouter> onboardingRouterProvider;
    private final Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider;

    public GdprModalRouter_Factory(Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider, Provider<OnboardingRouter> onboardingRouterProvider) {
        this.onboardingUpdatedConnectorProvider = onboardingUpdatedConnectorProvider;
        this.onboardingRouterProvider = onboardingRouterProvider;
    }

    public GdprModalRouter get() {
        return provideInstance(this.onboardingUpdatedConnectorProvider, this.onboardingRouterProvider);
    }

    public static GdprModalRouter provideInstance(Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider, Provider<OnboardingRouter> onboardingRouterProvider) {
        return new GdprModalRouter((OnboardingUpdatedConnector) onboardingUpdatedConnectorProvider.get(), (OnboardingRouter) onboardingRouterProvider.get());
    }

    public static GdprModalRouter_Factory create(Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider, Provider<OnboardingRouter> onboardingRouterProvider) {
        return new GdprModalRouter_Factory(onboardingUpdatedConnectorProvider, onboardingRouterProvider);
    }

    public static GdprModalRouter newGdprModalRouter(OnboardingUpdatedConnector onboardingUpdatedConnector, OnboardingRouter onboardingRouter) {
        return new GdprModalRouter(onboardingUpdatedConnector, onboardingRouter);
    }
}
