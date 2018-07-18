package com.coinbase.android.settings.idology;

import android.app.Application;
import com.coinbase.android.idology.IdologyTrackingContextProvider;
import com.coinbase.android.ui.SuccessRouter;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class IdologySuccessPresenter_Factory implements Factory<IdologySuccessPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<IdologySuccessScreen> screenProvider;
    private final Provider<SuccessRouter> successRouterProvider;

    public IdologySuccessPresenter_Factory(Provider<Application> appProvider, Provider<IdologySuccessScreen> screenProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider, Provider<SuccessRouter> successRouterProvider) {
        this.appProvider = appProvider;
        this.screenProvider = screenProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.idologyTrackingContextProvider = idologyTrackingContextProvider;
        this.successRouterProvider = successRouterProvider;
    }

    public IdologySuccessPresenter get() {
        return provideInstance(this.appProvider, this.screenProvider, this.mixpanelTrackingProvider, this.idologyTrackingContextProvider, this.successRouterProvider);
    }

    public static IdologySuccessPresenter provideInstance(Provider<Application> appProvider, Provider<IdologySuccessScreen> screenProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider, Provider<SuccessRouter> successRouterProvider) {
        return new IdologySuccessPresenter((Application) appProvider.get(), (IdologySuccessScreen) screenProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (IdologyTrackingContextProvider) idologyTrackingContextProvider.get(), (SuccessRouter) successRouterProvider.get());
    }

    public static IdologySuccessPresenter_Factory create(Provider<Application> appProvider, Provider<IdologySuccessScreen> screenProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<IdologyTrackingContextProvider> idologyTrackingContextProvider, Provider<SuccessRouter> successRouterProvider) {
        return new IdologySuccessPresenter_Factory(appProvider, screenProvider, mixpanelTrackingProvider, idologyTrackingContextProvider, successRouterProvider);
    }

    public static IdologySuccessPresenter newIdologySuccessPresenter(Application app, IdologySuccessScreen screen, MixpanelTracking mixpanelTracking, IdologyTrackingContextProvider idologyTrackingContext, SuccessRouter successRouter) {
        return new IdologySuccessPresenter(app, screen, mixpanelTracking, idologyTrackingContext, successRouter);
    }
}
