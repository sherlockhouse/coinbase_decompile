package com.coinbase.android;

import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BalanceAppWidgetProvider_MembersInjector implements MembersInjector<BalanceAppWidgetProvider> {
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<MixpanelTracking> mMixpanelTrackingProvider;
    private final Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider;

    public BalanceAppWidgetProvider_MembersInjector(Provider<LoginManager> mLoginManagerProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mMoneyFormatterUtilProvider = mMoneyFormatterUtilProvider;
        this.mMixpanelTrackingProvider = mMixpanelTrackingProvider;
    }

    public static MembersInjector<BalanceAppWidgetProvider> create(Provider<LoginManager> mLoginManagerProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider) {
        return new BalanceAppWidgetProvider_MembersInjector(mLoginManagerProvider, mMoneyFormatterUtilProvider, mMixpanelTrackingProvider);
    }

    public void injectMembers(BalanceAppWidgetProvider instance) {
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        injectMMoneyFormatterUtil(instance, (MoneyFormatterUtil) this.mMoneyFormatterUtilProvider.get());
        injectMMixpanelTracking(instance, (MixpanelTracking) this.mMixpanelTrackingProvider.get());
    }

    public static void injectMLoginManager(BalanceAppWidgetProvider instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }

    public static void injectMMoneyFormatterUtil(BalanceAppWidgetProvider instance, MoneyFormatterUtil mMoneyFormatterUtil) {
        instance.mMoneyFormatterUtil = mMoneyFormatterUtil;
    }

    public static void injectMMixpanelTracking(BalanceAppWidgetProvider instance, MixpanelTracking mMixpanelTracking) {
        instance.mMixpanelTracking = mMixpanelTracking;
    }
}
