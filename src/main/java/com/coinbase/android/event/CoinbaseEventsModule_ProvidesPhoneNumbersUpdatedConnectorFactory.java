package com.coinbase.android.event;

import com.coinbase.android.phone.PhoneNumbersUpdatedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesPhoneNumbersUpdatedConnectorFactory implements Factory<PhoneNumbersUpdatedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesPhoneNumbersUpdatedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public PhoneNumbersUpdatedConnector get() {
        return provideInstance(this.module);
    }

    public static PhoneNumbersUpdatedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesPhoneNumbersUpdatedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesPhoneNumbersUpdatedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesPhoneNumbersUpdatedConnectorFactory(module);
    }

    public static PhoneNumbersUpdatedConnector proxyProvidesPhoneNumbersUpdatedConnector(CoinbaseEventsModule instance) {
        return (PhoneNumbersUpdatedConnector) Preconditions.checkNotNull(instance.providesPhoneNumbersUpdatedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
