package com.coinbase.android.settings.idology;

import com.coinbase.android.idology.IdologyTrackingContextProvider;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class IdologyFailurePresenter_Factory implements Factory<IdologyFailurePresenter> {
    private final Provider<IdologyRouter> idologyRouterProvider;
    private final Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;

    public IdologyFailurePresenter_Factory(Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider, Provider<IdologyRouter> idologyRouterProvider) {
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.idologyTrackingContextProvider = idologyTrackingContextProvider;
        this.idologyRouterProvider = idologyRouterProvider;
    }

    public IdologyFailurePresenter get() {
        return provideInstance(this.mixpanelTrackingProvider, this.idologyTrackingContextProvider, this.idologyRouterProvider);
    }

    public static IdologyFailurePresenter provideInstance(Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider, Provider<IdologyRouter> idologyRouterProvider) {
        return new IdologyFailurePresenter((MixpanelTracking) mixpanelTrackingProvider.get(), (IdologyTrackingContextProvider) idologyTrackingContextProvider.get(), (IdologyRouter) idologyRouterProvider.get());
    }

    public static IdologyFailurePresenter_Factory create(Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider, Provider<IdologyRouter> idologyRouterProvider) {
        return new IdologyFailurePresenter_Factory(mixpanelTrackingProvider, idologyTrackingContextProvider, idologyRouterProvider);
    }

    public static IdologyFailurePresenter newIdologyFailurePresenter(MixpanelTracking mixpanelTracking, IdologyTrackingContextProvider idologyTrackingContext, IdologyRouter idologyRouter) {
        return new IdologyFailurePresenter(mixpanelTracking, idologyTrackingContext, idologyRouter);
    }
}
