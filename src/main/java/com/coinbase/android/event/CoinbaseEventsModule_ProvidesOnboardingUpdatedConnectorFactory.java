package com.coinbase.android.event;

import com.coinbase.android.gdpr.OnboardingUpdatedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesOnboardingUpdatedConnectorFactory implements Factory<OnboardingUpdatedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesOnboardingUpdatedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public OnboardingUpdatedConnector get() {
        return provideInstance(this.module);
    }

    public static OnboardingUpdatedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesOnboardingUpdatedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesOnboardingUpdatedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesOnboardingUpdatedConnectorFactory(module);
    }

    public static OnboardingUpdatedConnector proxyProvidesOnboardingUpdatedConnector(CoinbaseEventsModule instance) {
        return (OnboardingUpdatedConnector) Preconditions.checkNotNull(instance.providesOnboardingUpdatedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
