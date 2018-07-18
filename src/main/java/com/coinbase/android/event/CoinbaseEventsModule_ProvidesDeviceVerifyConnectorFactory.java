package com.coinbase.android.event;

import com.coinbase.android.signin.DeviceVerifyConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesDeviceVerifyConnectorFactory implements Factory<DeviceVerifyConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesDeviceVerifyConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public DeviceVerifyConnector get() {
        return provideInstance(this.module);
    }

    public static DeviceVerifyConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesDeviceVerifyConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesDeviceVerifyConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesDeviceVerifyConnectorFactory(module);
    }

    public static DeviceVerifyConnector proxyProvidesDeviceVerifyConnector(CoinbaseEventsModule instance) {
        return (DeviceVerifyConnector) Preconditions.checkNotNull(instance.providesDeviceVerifyConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
