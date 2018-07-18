package com.coinbase.android.event;

import com.coinbase.android.idology.IdologyAnswerListValidConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesIdologyAnswerListValidConnectorFactory implements Factory<IdologyAnswerListValidConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesIdologyAnswerListValidConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public IdologyAnswerListValidConnector get() {
        return provideInstance(this.module);
    }

    public static IdologyAnswerListValidConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesIdologyAnswerListValidConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesIdologyAnswerListValidConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesIdologyAnswerListValidConnectorFactory(module);
    }

    public static IdologyAnswerListValidConnector proxyProvidesIdologyAnswerListValidConnector(CoinbaseEventsModule instance) {
        return (IdologyAnswerListValidConnector) Preconditions.checkNotNull(instance.providesIdologyAnswerListValidConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
