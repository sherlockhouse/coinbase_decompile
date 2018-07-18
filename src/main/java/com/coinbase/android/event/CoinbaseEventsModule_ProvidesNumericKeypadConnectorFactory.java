package com.coinbase.android.event;

import com.coinbase.android.ui.NumericKeypadConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesNumericKeypadConnectorFactory implements Factory<NumericKeypadConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesNumericKeypadConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public NumericKeypadConnector get() {
        return provideInstance(this.module);
    }

    public static NumericKeypadConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesNumericKeypadConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesNumericKeypadConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesNumericKeypadConnectorFactory(module);
    }

    public static NumericKeypadConnector proxyProvidesNumericKeypadConnector(CoinbaseEventsModule instance) {
        return (NumericKeypadConnector) Preconditions.checkNotNull(instance.providesNumericKeypadConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
