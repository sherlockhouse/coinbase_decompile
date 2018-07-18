package com.coinbase.android.widgets;

import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class UpdateWidgetPriceService_MembersInjector implements MembersInjector<UpdateWidgetPriceService> {
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<MixpanelTracking> mMixpanelTrackingProvider;

    public UpdateWidgetPriceService_MembersInjector(Provider<LoginManager> mLoginManagerProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mMixpanelTrackingProvider = mMixpanelTrackingProvider;
    }

    public static MembersInjector<UpdateWidgetPriceService> create(Provider<LoginManager> mLoginManagerProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider) {
        return new UpdateWidgetPriceService_MembersInjector(mLoginManagerProvider, mMixpanelTrackingProvider);
    }

    public void injectMembers(UpdateWidgetPriceService instance) {
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        injectMMixpanelTracking(instance, (MixpanelTracking) this.mMixpanelTrackingProvider.get());
    }

    public static void injectMLoginManager(UpdateWidgetPriceService instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }

    public static void injectMMixpanelTracking(UpdateWidgetPriceService instance, MixpanelTracking mMixpanelTracking) {
        instance.mMixpanelTracking = mMixpanelTracking;
    }
}
