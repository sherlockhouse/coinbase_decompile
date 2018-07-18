package com.coinbase.android.event;

import com.coinbase.android.settings.SettingsPreferenceItemClickedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesPrivacyRightsSettingsClickedConnectorFactory implements Factory<SettingsPreferenceItemClickedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesPrivacyRightsSettingsClickedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public SettingsPreferenceItemClickedConnector get() {
        return provideInstance(this.module);
    }

    public static SettingsPreferenceItemClickedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesPrivacyRightsSettingsClickedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesPrivacyRightsSettingsClickedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesPrivacyRightsSettingsClickedConnectorFactory(module);
    }

    public static SettingsPreferenceItemClickedConnector proxyProvidesPrivacyRightsSettingsClickedConnector(CoinbaseEventsModule instance) {
        return (SettingsPreferenceItemClickedConnector) Preconditions.checkNotNull(instance.providesPrivacyRightsSettingsClickedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
