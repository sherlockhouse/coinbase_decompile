package com.coinbase.android.settings.gdpr;

import android.app.Application;
import com.coinbase.android.settings.SettingsPreferenceItem;
import com.coinbase.android.settings.SettingsPreferenceItemClickedConnector;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import java.util.List;
import javax.inject.Provider;
import rx.Scheduler;
import rx.functions.Func0;

public final class PrivacyRightsPresenter_Factory implements Factory<PrivacyRightsPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<SettingsPreferenceItemClickedConnector> clickedConnectorProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<List<Func0<SettingsPreferenceItem>>> preferenceListProvider;
    private final Provider<GdprSettingsRouter> routerProvider;
    private final Provider<PrivacyRightsScreen> screenProvider;

    public PrivacyRightsPresenter_Factory(Provider<Application> applicationProvider, Provider<PrivacyRightsScreen> screenProvider, Provider<GdprSettingsRouter> routerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SettingsPreferenceItemClickedConnector> clickedConnectorProvider, Provider<List<Func0<SettingsPreferenceItem>>> preferenceListProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.applicationProvider = applicationProvider;
        this.screenProvider = screenProvider;
        this.routerProvider = routerProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.clickedConnectorProvider = clickedConnectorProvider;
        this.preferenceListProvider = preferenceListProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public PrivacyRightsPresenter get() {
        return provideInstance(this.applicationProvider, this.screenProvider, this.routerProvider, this.mixpanelTrackingProvider, this.clickedConnectorProvider, this.preferenceListProvider, this.mainSchedulerProvider);
    }

    public static PrivacyRightsPresenter provideInstance(Provider<Application> applicationProvider, Provider<PrivacyRightsScreen> screenProvider, Provider<GdprSettingsRouter> routerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SettingsPreferenceItemClickedConnector> clickedConnectorProvider, Provider<List<Func0<SettingsPreferenceItem>>> preferenceListProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new PrivacyRightsPresenter((Application) applicationProvider.get(), (PrivacyRightsScreen) screenProvider.get(), (GdprSettingsRouter) routerProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (SettingsPreferenceItemClickedConnector) clickedConnectorProvider.get(), (List) preferenceListProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static PrivacyRightsPresenter_Factory create(Provider<Application> applicationProvider, Provider<PrivacyRightsScreen> screenProvider, Provider<GdprSettingsRouter> routerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SettingsPreferenceItemClickedConnector> clickedConnectorProvider, Provider<List<Func0<SettingsPreferenceItem>>> preferenceListProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new PrivacyRightsPresenter_Factory(applicationProvider, screenProvider, routerProvider, mixpanelTrackingProvider, clickedConnectorProvider, preferenceListProvider, mainSchedulerProvider);
    }

    public static PrivacyRightsPresenter newPrivacyRightsPresenter(Application application, Object screen, GdprSettingsRouter router, MixpanelTracking mixpanelTracking, SettingsPreferenceItemClickedConnector clickedConnector, List<Func0<SettingsPreferenceItem>> preferenceList, Scheduler mainScheduler) {
        return new PrivacyRightsPresenter(application, (PrivacyRightsScreen) screen, router, mixpanelTracking, clickedConnector, preferenceList, mainScheduler);
    }
}
