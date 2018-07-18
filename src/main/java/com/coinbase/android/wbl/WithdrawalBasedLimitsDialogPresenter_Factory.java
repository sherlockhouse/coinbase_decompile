package com.coinbase.android.wbl;

import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class WithdrawalBasedLimitsDialogPresenter_Factory implements Factory<WithdrawalBasedLimitsDialogPresenter> {
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<WithdrawalBasedLimitsDialogScreen> screenProvider;

    public WithdrawalBasedLimitsDialogPresenter_Factory(Provider<WithdrawalBasedLimitsDialogScreen> screenProvider, Provider<MixpanelTracking> mixpanelTrackingProvider) {
        this.screenProvider = screenProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
    }

    public WithdrawalBasedLimitsDialogPresenter get() {
        return provideInstance(this.screenProvider, this.mixpanelTrackingProvider);
    }

    public static WithdrawalBasedLimitsDialogPresenter provideInstance(Provider<WithdrawalBasedLimitsDialogScreen> screenProvider, Provider<MixpanelTracking> mixpanelTrackingProvider) {
        return new WithdrawalBasedLimitsDialogPresenter((WithdrawalBasedLimitsDialogScreen) screenProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get());
    }

    public static WithdrawalBasedLimitsDialogPresenter_Factory create(Provider<WithdrawalBasedLimitsDialogScreen> screenProvider, Provider<MixpanelTracking> mixpanelTrackingProvider) {
        return new WithdrawalBasedLimitsDialogPresenter_Factory(screenProvider, mixpanelTrackingProvider);
    }

    public static WithdrawalBasedLimitsDialogPresenter newWithdrawalBasedLimitsDialogPresenter(WithdrawalBasedLimitsDialogScreen screen, MixpanelTracking mixpanelTracking) {
        return new WithdrawalBasedLimitsDialogPresenter(screen, mixpanelTracking);
    }
}
