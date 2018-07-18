package com.coinbase.android.wbl;

import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AvailableBalanceRouter_Factory implements Factory<AvailableBalanceRouter> {
    private final Provider<ActionBarController> actionBarControllerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;

    public AvailableBalanceRouter_Factory(Provider<ActionBarController> actionBarControllerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider) {
        this.actionBarControllerProvider = actionBarControllerProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
    }

    public AvailableBalanceRouter get() {
        return provideInstance(this.actionBarControllerProvider, this.mixpanelTrackingProvider);
    }

    public static AvailableBalanceRouter provideInstance(Provider<ActionBarController> actionBarControllerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider) {
        return new AvailableBalanceRouter((ActionBarController) actionBarControllerProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get());
    }

    public static AvailableBalanceRouter_Factory create(Provider<ActionBarController> actionBarControllerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider) {
        return new AvailableBalanceRouter_Factory(actionBarControllerProvider, mixpanelTrackingProvider);
    }

    public static AvailableBalanceRouter newAvailableBalanceRouter(ActionBarController actionBarController, MixpanelTracking mixpanelTracking) {
        return new AvailableBalanceRouter(actionBarController, mixpanelTracking);
    }
}
