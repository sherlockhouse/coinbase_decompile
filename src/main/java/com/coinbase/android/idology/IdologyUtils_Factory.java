package com.coinbase.android.idology;

import android.content.SharedPreferences;
import com.coinbase.android.featureflag.FeatureFlags;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class IdologyUtils_Factory implements Factory<IdologyUtils> {
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<FeatureFlags> featureFlagsProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<SharedPreferences> sharedPrefsProvider;

    public IdologyUtils_Factory(Provider<LoginManager> loginManagerProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<FeatureFlags> featureFlagsProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.sharedPrefsProvider = sharedPrefsProvider;
        this.featureFlagsProvider = featureFlagsProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public IdologyUtils get() {
        return provideInstance(this.loginManagerProvider, this.sharedPrefsProvider, this.featureFlagsProvider, this.backgroundSchedulerProvider);
    }

    public static IdologyUtils provideInstance(Provider<LoginManager> loginManagerProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<FeatureFlags> featureFlagsProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new IdologyUtils((LoginManager) loginManagerProvider.get(), (SharedPreferences) sharedPrefsProvider.get(), (FeatureFlags) featureFlagsProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static IdologyUtils_Factory create(Provider<LoginManager> loginManagerProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<FeatureFlags> featureFlagsProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new IdologyUtils_Factory(loginManagerProvider, sharedPrefsProvider, featureFlagsProvider, backgroundSchedulerProvider);
    }

    public static IdologyUtils newIdologyUtils(LoginManager loginManager, SharedPreferences sharedPrefs, FeatureFlags featureFlags, Scheduler backgroundScheduler) {
        return new IdologyUtils(loginManager, sharedPrefs, featureFlags, backgroundScheduler);
    }
}
