package com.coinbase.android.event;

import com.coinbase.android.transfers.TransferMadeConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesTransferMadeConnectorFactory implements Factory<TransferMadeConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesTransferMadeConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public TransferMadeConnector get() {
        return provideInstance(this.module);
    }

    public static TransferMadeConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesTransferMadeConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesTransferMadeConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesTransferMadeConnectorFactory(module);
    }

    public static TransferMadeConnector proxyProvidesTransferMadeConnector(CoinbaseEventsModule instance) {
        return (TransferMadeConnector) Preconditions.checkNotNull(instance.providesTransferMadeConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
