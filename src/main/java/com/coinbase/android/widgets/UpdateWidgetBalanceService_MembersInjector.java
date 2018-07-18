package com.coinbase.android.widgets;

import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class UpdateWidgetBalanceService_MembersInjector implements MembersInjector<UpdateWidgetBalanceService> {
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<MixpanelTracking> mMixpanelTrackingProvider;
    private final Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider;

    public UpdateWidgetBalanceService_MembersInjector(Provider<LoginManager> mLoginManagerProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mMoneyFormatterUtilProvider = mMoneyFormatterUtilProvider;
        this.mMixpanelTrackingProvider = mMixpanelTrackingProvider;
    }

    public static MembersInjector<UpdateWidgetBalanceService> create(Provider<LoginManager> mLoginManagerProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider) {
        return new UpdateWidgetBalanceService_MembersInjector(mLoginManagerProvider, mMoneyFormatterUtilProvider, mMixpanelTrackingProvider);
    }

    public void injectMembers(UpdateWidgetBalanceService instance) {
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        injectMMoneyFormatterUtil(instance, (MoneyFormatterUtil) this.mMoneyFormatterUtilProvider.get());
        injectMMixpanelTracking(instance, (MixpanelTracking) this.mMixpanelTrackingProvider.get());
    }

    public static void injectMLoginManager(UpdateWidgetBalanceService instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }

    public static void injectMMoneyFormatterUtil(UpdateWidgetBalanceService instance, MoneyFormatterUtil mMoneyFormatterUtil) {
        instance.mMoneyFormatterUtil = mMoneyFormatterUtil;
    }

    public static void injectMMixpanelTracking(UpdateWidgetBalanceService instance, MixpanelTracking mMixpanelTracking) {
        instance.mMixpanelTracking = mMixpanelTracking;
    }
}
