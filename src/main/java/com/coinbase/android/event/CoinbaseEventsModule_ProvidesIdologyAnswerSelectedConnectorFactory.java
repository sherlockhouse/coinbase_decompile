package com.coinbase.android.event;

import com.coinbase.android.idology.IdologyAnswerSelectedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesIdologyAnswerSelectedConnectorFactory implements Factory<IdologyAnswerSelectedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesIdologyAnswerSelectedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public IdologyAnswerSelectedConnector get() {
        return provideInstance(this.module);
    }

    public static IdologyAnswerSelectedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesIdologyAnswerSelectedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesIdologyAnswerSelectedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesIdologyAnswerSelectedConnectorFactory(module);
    }

    public static IdologyAnswerSelectedConnector proxyProvidesIdologyAnswerSelectedConnector(CoinbaseEventsModule instance) {
        return (IdologyAnswerSelectedConnector) Preconditions.checkNotNull(instance.providesIdologyAnswerSelectedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
