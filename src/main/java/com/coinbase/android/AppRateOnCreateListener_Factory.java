package com.coinbase.android;

import android.app.Application;
import android.content.SharedPreferences;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AppRateOnCreateListener_Factory implements Factory<AppRateOnCreateListener> {
    private final Provider<Application> contextProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<SharedPreferences> sharedPrefsProvider;

    public AppRateOnCreateListener_Factory(Provider<Application> contextProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SharedPreferences> sharedPrefsProvider) {
        this.contextProvider = contextProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.sharedPrefsProvider = sharedPrefsProvider;
    }

    public AppRateOnCreateListener get() {
        return provideInstance(this.contextProvider, this.mixpanelTrackingProvider, this.sharedPrefsProvider);
    }

    public static AppRateOnCreateListener provideInstance(Provider<Application> contextProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SharedPreferences> sharedPrefsProvider) {
        return new AppRateOnCreateListener((Application) contextProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (SharedPreferences) sharedPrefsProvider.get());
    }

    public static AppRateOnCreateListener_Factory create(Provider<Application> contextProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SharedPreferences> sharedPrefsProvider) {
        return new AppRateOnCreateListener_Factory(contextProvider, mixpanelTrackingProvider, sharedPrefsProvider);
    }

    public static AppRateOnCreateListener newAppRateOnCreateListener(Application context, MixpanelTracking mixpanelTracking, SharedPreferences sharedPrefs) {
        return new AppRateOnCreateListener(context, mixpanelTracking, sharedPrefs);
    }
}
