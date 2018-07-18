package com.coinbase.android;

import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class PriceAppWidgetProvider_MembersInjector implements MembersInjector<PriceAppWidgetProvider> {
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<MixpanelTracking> mMixpanelTrackingProvider;

    public PriceAppWidgetProvider_MembersInjector(Provider<LoginManager> mLoginManagerProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mMixpanelTrackingProvider = mMixpanelTrackingProvider;
    }

    public static MembersInjector<PriceAppWidgetProvider> create(Provider<LoginManager> mLoginManagerProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider) {
        return new PriceAppWidgetProvider_MembersInjector(mLoginManagerProvider, mMixpanelTrackingProvider);
    }

    public void injectMembers(PriceAppWidgetProvider instance) {
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        injectMMixpanelTracking(instance, (MixpanelTracking) this.mMixpanelTrackingProvider.get());
    }

    public static void injectMLoginManager(PriceAppWidgetProvider instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }

    public static void injectMMixpanelTracking(PriceAppWidgetProvider instance, MixpanelTracking mMixpanelTracking) {
        instance.mMixpanelTracking = mMixpanelTracking;
    }
}
