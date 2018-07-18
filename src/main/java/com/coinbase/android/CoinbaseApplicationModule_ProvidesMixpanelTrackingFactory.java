package com.coinbase.android;

import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseApplicationModule_ProvidesMixpanelTrackingFactory implements Factory<MixpanelTracking> {
    private final CoinbaseApplicationModule module;

    public CoinbaseApplicationModule_ProvidesMixpanelTrackingFactory(CoinbaseApplicationModule module) {
        this.module = module;
    }

    public MixpanelTracking get() {
        return provideInstance(this.module);
    }

    public static MixpanelTracking provideInstance(CoinbaseApplicationModule module) {
        return proxyProvidesMixpanelTracking(module);
    }

    public static CoinbaseApplicationModule_ProvidesMixpanelTrackingFactory create(CoinbaseApplicationModule module) {
        return new CoinbaseApplicationModule_ProvidesMixpanelTrackingFactory(module);
    }

    public static MixpanelTracking proxyProvidesMixpanelTracking(CoinbaseApplicationModule instance) {
        return (MixpanelTracking) Preconditions.checkNotNull(instance.providesMixpanelTracking(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
