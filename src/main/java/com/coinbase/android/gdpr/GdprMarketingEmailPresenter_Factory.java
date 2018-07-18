package com.coinbase.android.gdpr;

import android.app.Application;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class GdprMarketingEmailPresenter_Factory implements Factory<GdprMarketingEmailPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<GdprMarketingEmailScreen> gdprMarketingEmailScreenProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<OnboardingRouter> onboardingRouterProvider;
    private final Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public GdprMarketingEmailPresenter_Factory(Provider<Application> applicationProvider, Provider<GdprMarketingEmailScreen> gdprMarketingEmailScreenProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<OnboardingRouter> onboardingRouterProvider, Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider) {
        this.applicationProvider = applicationProvider;
        this.gdprMarketingEmailScreenProvider = gdprMarketingEmailScreenProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.onboardingRouterProvider = onboardingRouterProvider;
        this.onboardingUpdatedConnectorProvider = onboardingUpdatedConnectorProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
    }

    public GdprMarketingEmailPresenter get() {
        return provideInstance(this.applicationProvider, this.gdprMarketingEmailScreenProvider, this.loginManagerProvider, this.mixpanelTrackingProvider, this.onboardingRouterProvider, this.onboardingUpdatedConnectorProvider, this.mainSchedulerProvider, this.snackBarWrapperProvider);
    }

    public static GdprMarketingEmailPresenter provideInstance(Provider<Application> applicationProvider, Provider<GdprMarketingEmailScreen> gdprMarketingEmailScreenProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<OnboardingRouter> onboardingRouterProvider, Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider) {
        return new GdprMarketingEmailPresenter((Application) applicationProvider.get(), (GdprMarketingEmailScreen) gdprMarketingEmailScreenProvider.get(), (LoginManager) loginManagerProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (OnboardingRouter) onboardingRouterProvider.get(), (OnboardingUpdatedConnector) onboardingUpdatedConnectorProvider.get(), (Scheduler) mainSchedulerProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get());
    }

    public static GdprMarketingEmailPresenter_Factory create(Provider<Application> applicationProvider, Provider<GdprMarketingEmailScreen> gdprMarketingEmailScreenProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<OnboardingRouter> onboardingRouterProvider, Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider) {
        return new GdprMarketingEmailPresenter_Factory(applicationProvider, gdprMarketingEmailScreenProvider, loginManagerProvider, mixpanelTrackingProvider, onboardingRouterProvider, onboardingUpdatedConnectorProvider, mainSchedulerProvider, snackBarWrapperProvider);
    }

    public static GdprMarketingEmailPresenter newGdprMarketingEmailPresenter(Application application, GdprMarketingEmailScreen gdprMarketingEmailScreen, LoginManager loginManager, MixpanelTracking mixpanelTracking, OnboardingRouter onboardingRouter, OnboardingUpdatedConnector onboardingUpdatedConnector, Scheduler mainScheduler, SnackBarWrapper snackBarWrapper) {
        return new GdprMarketingEmailPresenter(application, gdprMarketingEmailScreen, loginManager, mixpanelTracking, onboardingRouter, onboardingUpdatedConnector, mainScheduler, snackBarWrapper);
    }
}
