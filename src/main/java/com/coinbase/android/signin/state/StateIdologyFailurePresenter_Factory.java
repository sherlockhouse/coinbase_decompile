package com.coinbase.android.signin.state;

import com.coinbase.android.idology.IdologyTrackingContextProvider;
import com.coinbase.android.settings.idology.IdologyRouter;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class StateIdologyFailurePresenter_Factory implements Factory<StateIdologyFailurePresenter> {
    private final Provider<IdologyRouter> idologyRouterProvider;
    private final Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;

    public StateIdologyFailurePresenter_Factory(Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider, Provider<IdologyRouter> idologyRouterProvider) {
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.idologyTrackingContextProvider = idologyTrackingContextProvider;
        this.idologyRouterProvider = idologyRouterProvider;
    }

    public StateIdologyFailurePresenter get() {
        return provideInstance(this.mixpanelTrackingProvider, this.idologyTrackingContextProvider, this.idologyRouterProvider);
    }

    public static StateIdologyFailurePresenter provideInstance(Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider, Provider<IdologyRouter> idologyRouterProvider) {
        return new StateIdologyFailurePresenter((MixpanelTracking) mixpanelTrackingProvider.get(), (IdologyTrackingContextProvider) idologyTrackingContextProvider.get(), (IdologyRouter) idologyRouterProvider.get());
    }

    public static StateIdologyFailurePresenter_Factory create(Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider, Provider<IdologyRouter> idologyRouterProvider) {
        return new StateIdologyFailurePresenter_Factory(mixpanelTrackingProvider, idologyTrackingContextProvider, idologyRouterProvider);
    }

    public static StateIdologyFailurePresenter newStateIdologyFailurePresenter(MixpanelTracking mixpanelTracking, IdologyTrackingContextProvider idologyTrackingContext, IdologyRouter idologyRouter) {
        return new StateIdologyFailurePresenter(mixpanelTracking, idologyTrackingContext, idologyRouter);
    }
}
