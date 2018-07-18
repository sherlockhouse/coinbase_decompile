package com.coinbase.android.ui;

import android.app.Application;
import android.view.LayoutInflater;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import java.util.List;
import javax.inject.Provider;
import rx.Scheduler;

public final class MystiqueBottomNavigationPresenter_Factory implements Factory<MystiqueBottomNavigationPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<BottomNavigationConnector> bottomNavigationConnectorProvider;
    private final Provider<List<NavigationItem>> bottomNavigationItemListProvider;
    private final Provider<LayoutInflater> layoutInflaterProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<MystiqueBottomNavigationScreen> screenProvider;

    public MystiqueBottomNavigationPresenter_Factory(Provider<MystiqueBottomNavigationScreen> screenProvider, Provider<List<NavigationItem>> bottomNavigationItemListProvider, Provider<Application> appProvider, Provider<LayoutInflater> layoutInflaterProvider, Provider<BottomNavigationConnector> bottomNavigationConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.bottomNavigationItemListProvider = bottomNavigationItemListProvider;
        this.appProvider = appProvider;
        this.layoutInflaterProvider = layoutInflaterProvider;
        this.bottomNavigationConnectorProvider = bottomNavigationConnectorProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public MystiqueBottomNavigationPresenter get() {
        return provideInstance(this.screenProvider, this.bottomNavigationItemListProvider, this.appProvider, this.layoutInflaterProvider, this.bottomNavigationConnectorProvider, this.mixpanelTrackingProvider, this.mainSchedulerProvider);
    }

    public static MystiqueBottomNavigationPresenter provideInstance(Provider<MystiqueBottomNavigationScreen> screenProvider, Provider<List<NavigationItem>> bottomNavigationItemListProvider, Provider<Application> appProvider, Provider<LayoutInflater> layoutInflaterProvider, Provider<BottomNavigationConnector> bottomNavigationConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new MystiqueBottomNavigationPresenter((MystiqueBottomNavigationScreen) screenProvider.get(), (List) bottomNavigationItemListProvider.get(), (Application) appProvider.get(), (LayoutInflater) layoutInflaterProvider.get(), (BottomNavigationConnector) bottomNavigationConnectorProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static MystiqueBottomNavigationPresenter_Factory create(Provider<MystiqueBottomNavigationScreen> screenProvider, Provider<List<NavigationItem>> bottomNavigationItemListProvider, Provider<Application> appProvider, Provider<LayoutInflater> layoutInflaterProvider, Provider<BottomNavigationConnector> bottomNavigationConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new MystiqueBottomNavigationPresenter_Factory(screenProvider, bottomNavigationItemListProvider, appProvider, layoutInflaterProvider, bottomNavigationConnectorProvider, mixpanelTrackingProvider, mainSchedulerProvider);
    }

    public static MystiqueBottomNavigationPresenter newMystiqueBottomNavigationPresenter(MystiqueBottomNavigationScreen screen, List<NavigationItem> bottomNavigationItemList, Application app, LayoutInflater layoutInflater, BottomNavigationConnector bottomNavigationConnector, MixpanelTracking mixpanelTracking, Scheduler mainScheduler) {
        return new MystiqueBottomNavigationPresenter(screen, bottomNavigationItemList, app, layoutInflater, bottomNavigationConnector, mixpanelTracking, mainScheduler);
    }
}
