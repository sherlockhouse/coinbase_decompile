package com.coinbase.android.wbl;

import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ExistingUserDialogPresenter_Factory implements Factory<ExistingUserDialogPresenter> {
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<ExistingUserDialogRouter> routerProvider;
    private final Provider<ExistingUserDialogScreen> screenProvider;

    public ExistingUserDialogPresenter_Factory(Provider<ExistingUserDialogScreen> screenProvider, Provider<ExistingUserDialogRouter> routerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider) {
        this.screenProvider = screenProvider;
        this.routerProvider = routerProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
    }

    public ExistingUserDialogPresenter get() {
        return provideInstance(this.screenProvider, this.routerProvider, this.mixpanelTrackingProvider);
    }

    public static ExistingUserDialogPresenter provideInstance(Provider<ExistingUserDialogScreen> screenProvider, Provider<ExistingUserDialogRouter> routerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider) {
        return new ExistingUserDialogPresenter((ExistingUserDialogScreen) screenProvider.get(), (ExistingUserDialogRouter) routerProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get());
    }

    public static ExistingUserDialogPresenter_Factory create(Provider<ExistingUserDialogScreen> screenProvider, Provider<ExistingUserDialogRouter> routerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider) {
        return new ExistingUserDialogPresenter_Factory(screenProvider, routerProvider, mixpanelTrackingProvider);
    }

    public static ExistingUserDialogPresenter newExistingUserDialogPresenter(ExistingUserDialogScreen screen, ExistingUserDialogRouter router, MixpanelTracking mixpanelTracking) {
        return new ExistingUserDialogPresenter(screen, router, mixpanelTracking);
    }
}
