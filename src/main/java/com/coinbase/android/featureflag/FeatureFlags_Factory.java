package com.coinbase.android.featureflag;

import android.content.SharedPreferences;
import com.coinbase.android.settings.UserUpdatedConnector;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class FeatureFlags_Factory implements Factory<FeatureFlags> {
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<SharedPreferences> sharedPrefsProvider;
    private final Provider<UserUpdatedConnector> userUpdatedConnectorProvider;

    public FeatureFlags_Factory(Provider<SharedPreferences> sharedPrefsProvider, Provider<UserUpdatedConnector> userUpdatedConnectorProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.sharedPrefsProvider = sharedPrefsProvider;
        this.userUpdatedConnectorProvider = userUpdatedConnectorProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public FeatureFlags get() {
        return provideInstance(this.sharedPrefsProvider, this.userUpdatedConnectorProvider, this.backgroundSchedulerProvider);
    }

    public static FeatureFlags provideInstance(Provider<SharedPreferences> sharedPrefsProvider, Provider<UserUpdatedConnector> userUpdatedConnectorProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new FeatureFlags((SharedPreferences) sharedPrefsProvider.get(), (UserUpdatedConnector) userUpdatedConnectorProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static FeatureFlags_Factory create(Provider<SharedPreferences> sharedPrefsProvider, Provider<UserUpdatedConnector> userUpdatedConnectorProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new FeatureFlags_Factory(sharedPrefsProvider, userUpdatedConnectorProvider, backgroundSchedulerProvider);
    }

    public static FeatureFlags newFeatureFlags(SharedPreferences sharedPrefs, UserUpdatedConnector userUpdatedConnector, Scheduler backgroundScheduler) {
        return new FeatureFlags(sharedPrefs, userUpdatedConnector, backgroundScheduler);
    }
}
