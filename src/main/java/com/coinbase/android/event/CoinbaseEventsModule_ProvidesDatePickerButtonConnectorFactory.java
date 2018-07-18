package com.coinbase.android.event;

import com.coinbase.android.ui.DatePickerConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesDatePickerButtonConnectorFactory implements Factory<DatePickerConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesDatePickerButtonConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public DatePickerConnector get() {
        return provideInstance(this.module);
    }

    public static DatePickerConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesDatePickerButtonConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesDatePickerButtonConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesDatePickerButtonConnectorFactory(module);
    }

    public static DatePickerConnector proxyProvidesDatePickerButtonConnector(CoinbaseEventsModule instance) {
        return (DatePickerConnector) Preconditions.checkNotNull(instance.providesDatePickerButtonConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
