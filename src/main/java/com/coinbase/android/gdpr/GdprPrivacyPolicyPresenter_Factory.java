package com.coinbase.android.gdpr;

import android.app.Application;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class GdprPrivacyPolicyPresenter_Factory implements Factory<GdprPrivacyPolicyPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<GdprPrivacyPolicyScreen> gdprPrivacyPolicyScreenProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<OnboardingRouter> onboardingRouterProvider;
    private final Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public GdprPrivacyPolicyPresenter_Factory(Provider<Application> applicationProvider, Provider<GdprPrivacyPolicyScreen> gdprPrivacyPolicyScreenProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<OnboardingRouter> onboardingRouterProvider, Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider) {
        this.applicationProvider = applicationProvider;
        this.gdprPrivacyPolicyScreenProvider = gdprPrivacyPolicyScreenProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.onboardingRouterProvider = onboardingRouterProvider;
        this.onboardingUpdatedConnectorProvider = onboardingUpdatedConnectorProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
    }

    public GdprPrivacyPolicyPresenter get() {
        return provideInstance(this.applicationProvider, this.gdprPrivacyPolicyScreenProvider, this.loginManagerProvider, this.mixpanelTrackingProvider, this.onboardingRouterProvider, this.onboardingUpdatedConnectorProvider, this.mainSchedulerProvider, this.snackBarWrapperProvider);
    }

    public static GdprPrivacyPolicyPresenter provideInstance(Provider<Application> applicationProvider, Provider<GdprPrivacyPolicyScreen> gdprPrivacyPolicyScreenProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<OnboardingRouter> onboardingRouterProvider, Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider) {
        return new GdprPrivacyPolicyPresenter((Application) applicationProvider.get(), (GdprPrivacyPolicyScreen) gdprPrivacyPolicyScreenProvider.get(), (LoginManager) loginManagerProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (OnboardingRouter) onboardingRouterProvider.get(), (OnboardingUpdatedConnector) onboardingUpdatedConnectorProvider.get(), (Scheduler) mainSchedulerProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get());
    }

    public static GdprPrivacyPolicyPresenter_Factory create(Provider<Application> applicationProvider, Provider<GdprPrivacyPolicyScreen> gdprPrivacyPolicyScreenProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<OnboardingRouter> onboardingRouterProvider, Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider) {
        return new GdprPrivacyPolicyPresenter_Factory(applicationProvider, gdprPrivacyPolicyScreenProvider, loginManagerProvider, mixpanelTrackingProvider, onboardingRouterProvider, onboardingUpdatedConnectorProvider, mainSchedulerProvider, snackBarWrapperProvider);
    }

    public static GdprPrivacyPolicyPresenter newGdprPrivacyPolicyPresenter(Application application, GdprPrivacyPolicyScreen gdprPrivacyPolicyScreen, LoginManager loginManager, MixpanelTracking mixpanelTracking, OnboardingRouter onboardingRouter, OnboardingUpdatedConnector onboardingUpdatedConnector, Scheduler mainScheduler, SnackBarWrapper snackBarWrapper) {
        return new GdprPrivacyPolicyPresenter(application, gdprPrivacyPolicyScreen, loginManager, mixpanelTracking, onboardingRouter, onboardingUpdatedConnector, mainScheduler, snackBarWrapper);
    }
}
