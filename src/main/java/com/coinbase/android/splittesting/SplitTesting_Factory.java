package com.coinbase.android.splittesting;

import android.content.SharedPreferences;
import com.coinbase.android.settings.UserUpdatedConnector;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class SplitTesting_Factory implements Factory<SplitTesting> {
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<SharedPreferences> sharedPrefsProvider;
    private final Provider<UserUpdatedConnector> userUpdatedConnectorProvider;

    public SplitTesting_Factory(Provider<SharedPreferences> sharedPrefsProvider, Provider<UserUpdatedConnector> userUpdatedConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.sharedPrefsProvider = sharedPrefsProvider;
        this.userUpdatedConnectorProvider = userUpdatedConnectorProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public SplitTesting get() {
        return provideInstance(this.sharedPrefsProvider, this.userUpdatedConnectorProvider, this.mixpanelTrackingProvider, this.backgroundSchedulerProvider);
    }

    public static SplitTesting provideInstance(Provider<SharedPreferences> sharedPrefsProvider, Provider<UserUpdatedConnector> userUpdatedConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new SplitTesting((SharedPreferences) sharedPrefsProvider.get(), (UserUpdatedConnector) userUpdatedConnectorProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static SplitTesting_Factory create(Provider<SharedPreferences> sharedPrefsProvider, Provider<UserUpdatedConnector> userUpdatedConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new SplitTesting_Factory(sharedPrefsProvider, userUpdatedConnectorProvider, mixpanelTrackingProvider, backgroundSchedulerProvider);
    }

    public static SplitTesting newSplitTesting(SharedPreferences sharedPrefs, UserUpdatedConnector userUpdatedConnector, MixpanelTracking mixpanelTracking, Scheduler backgroundScheduler) {
        return new SplitTesting(sharedPrefs, userUpdatedConnector, mixpanelTracking, backgroundScheduler);
    }
}
