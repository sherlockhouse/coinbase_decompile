package com.coinbase.android.settings.gdpr;

import android.app.Application;
import com.coinbase.android.settings.SettingsPreferenceItemClickedConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class EmailSettingsPresenter_Factory implements Factory<EmailSettingsPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<SettingsPreferenceItemClickedConnector> clickedConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<GdprSettingsRouter> routerProvider;
    private final Provider<EmailSettingsScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public EmailSettingsPresenter_Factory(Provider<Application> applicationProvider, Provider<EmailSettingsScreen> screenProvider, Provider<GdprSettingsRouter> routerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SettingsPreferenceItemClickedConnector> clickedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<LoginManager> loginManagerProvider) {
        this.applicationProvider = applicationProvider;
        this.screenProvider = screenProvider;
        this.routerProvider = routerProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.clickedConnectorProvider = clickedConnectorProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.loginManagerProvider = loginManagerProvider;
    }

    public EmailSettingsPresenter get() {
        return provideInstance(this.applicationProvider, this.screenProvider, this.routerProvider, this.mixpanelTrackingProvider, this.clickedConnectorProvider, this.mainSchedulerProvider, this.backgroundSchedulerProvider, this.snackBarWrapperProvider, this.loginManagerProvider);
    }

    public static EmailSettingsPresenter provideInstance(Provider<Application> applicationProvider, Provider<EmailSettingsScreen> screenProvider, Provider<GdprSettingsRouter> routerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SettingsPreferenceItemClickedConnector> clickedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<LoginManager> loginManagerProvider) {
        return new EmailSettingsPresenter((Application) applicationProvider.get(), (EmailSettingsScreen) screenProvider.get(), (GdprSettingsRouter) routerProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (SettingsPreferenceItemClickedConnector) clickedConnectorProvider.get(), (Scheduler) mainSchedulerProvider.get(), (Scheduler) backgroundSchedulerProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (LoginManager) loginManagerProvider.get());
    }

    public static EmailSettingsPresenter_Factory create(Provider<Application> applicationProvider, Provider<EmailSettingsScreen> screenProvider, Provider<GdprSettingsRouter> routerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SettingsPreferenceItemClickedConnector> clickedConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<LoginManager> loginManagerProvider) {
        return new EmailSettingsPresenter_Factory(applicationProvider, screenProvider, routerProvider, mixpanelTrackingProvider, clickedConnectorProvider, mainSchedulerProvider, backgroundSchedulerProvider, snackBarWrapperProvider, loginManagerProvider);
    }

    public static EmailSettingsPresenter newEmailSettingsPresenter(Application application, Object screen, GdprSettingsRouter router, MixpanelTracking mixpanelTracking, SettingsPreferenceItemClickedConnector clickedConnector, Scheduler mainScheduler, Scheduler backgroundScheduler, SnackBarWrapper snackBarWrapper, LoginManager loginManager) {
        return new EmailSettingsPresenter(application, (EmailSettingsScreen) screen, router, mixpanelTracking, clickedConnector, mainScheduler, backgroundScheduler, snackBarWrapper, loginManager);
    }
}
