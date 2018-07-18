package com.coinbase.android.event;

import com.coinbase.android.identityverification.PhotoTakenConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesPhotoTakenConnectorFactory implements Factory<PhotoTakenConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesPhotoTakenConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public PhotoTakenConnector get() {
        return provideInstance(this.module);
    }

    public static PhotoTakenConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesPhotoTakenConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesPhotoTakenConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesPhotoTakenConnectorFactory(module);
    }

    public static PhotoTakenConnector proxyProvidesPhotoTakenConnector(CoinbaseEventsModule instance) {
        return (PhotoTakenConnector) Preconditions.checkNotNull(instance.providesPhotoTakenConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
