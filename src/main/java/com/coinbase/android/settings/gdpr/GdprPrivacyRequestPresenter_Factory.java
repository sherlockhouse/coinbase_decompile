package com.coinbase.android.settings.gdpr;

import android.app.Application;
import com.coinbase.android.settings.SettingsPreferenceItemClickedConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import java.util.Map;
import javax.inject.Provider;
import rx.Scheduler;
import rx.functions.Func0;

public final class GdprPrivacyRequestPresenter_Factory implements Factory<GdprPrivacyRequestPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<SettingsPreferenceItemClickedConnector> clickedConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<Map<Class<?>, Func0<PrivacyRequestViewModel>>> requestViewModelMapProvider;
    private final Provider<GdprSettingsRouter> routerProvider;
    private final Provider<GdprPrivacyRequestScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public GdprPrivacyRequestPresenter_Factory(Provider<Application> applicationProvider, Provider<GdprPrivacyRequestScreen> screenProvider, Provider<LoginManager> loginManagerProvider, Provider<SettingsPreferenceItemClickedConnector> clickedConnectorProvider, Provider<Map<Class<?>, Func0<PrivacyRequestViewModel>>> requestViewModelMapProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<GdprSettingsRouter> routerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.applicationProvider = applicationProvider;
        this.screenProvider = screenProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.clickedConnectorProvider = clickedConnectorProvider;
        this.requestViewModelMapProvider = requestViewModelMapProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.routerProvider = routerProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public GdprPrivacyRequestPresenter get() {
        return provideInstance(this.applicationProvider, this.screenProvider, this.loginManagerProvider, this.clickedConnectorProvider, this.requestViewModelMapProvider, this.mixpanelTrackingProvider, this.routerProvider, this.snackBarWrapperProvider, this.mainSchedulerProvider);
    }

    public static GdprPrivacyRequestPresenter provideInstance(Provider<Application> applicationProvider, Provider<GdprPrivacyRequestScreen> screenProvider, Provider<LoginManager> loginManagerProvider, Provider<SettingsPreferenceItemClickedConnector> clickedConnectorProvider, Provider<Map<Class<?>, Func0<PrivacyRequestViewModel>>> requestViewModelMapProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<GdprSettingsRouter> routerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new GdprPrivacyRequestPresenter((Application) applicationProvider.get(), (GdprPrivacyRequestScreen) screenProvider.get(), (LoginManager) loginManagerProvider.get(), (SettingsPreferenceItemClickedConnector) clickedConnectorProvider.get(), (Map) requestViewModelMapProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (GdprSettingsRouter) routerProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static GdprPrivacyRequestPresenter_Factory create(Provider<Application> applicationProvider, Provider<GdprPrivacyRequestScreen> screenProvider, Provider<LoginManager> loginManagerProvider, Provider<SettingsPreferenceItemClickedConnector> clickedConnectorProvider, Provider<Map<Class<?>, Func0<PrivacyRequestViewModel>>> requestViewModelMapProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<GdprSettingsRouter> routerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new GdprPrivacyRequestPresenter_Factory(applicationProvider, screenProvider, loginManagerProvider, clickedConnectorProvider, requestViewModelMapProvider, mixpanelTrackingProvider, routerProvider, snackBarWrapperProvider, mainSchedulerProvider);
    }

    public static GdprPrivacyRequestPresenter newGdprPrivacyRequestPresenter(Application application, GdprPrivacyRequestScreen screen, LoginManager loginManager, SettingsPreferenceItemClickedConnector clickedConnector, Map<Class<?>, Func0<PrivacyRequestViewModel>> requestViewModelMap, MixpanelTracking mixpanelTracking, GdprSettingsRouter router, SnackBarWrapper snackBarWrapper, Scheduler mainScheduler) {
        return new GdprPrivacyRequestPresenter(application, screen, loginManager, clickedConnector, requestViewModelMap, mixpanelTracking, router, snackBarWrapper, mainScheduler);
    }
}
