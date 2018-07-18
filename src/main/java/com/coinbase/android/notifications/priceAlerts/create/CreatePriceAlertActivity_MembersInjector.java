package com.coinbase.android.notifications.priceAlerts.create;

import com.coinbase.android.notifications.priceAlerts.PriceAlertsConnector;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class CreatePriceAlertActivity_MembersInjector implements MembersInjector<CreatePriceAlertActivity> {
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider;
    private final Provider<PriceAlertsConnector> mPriceAlertsConnectorProvider;

    public CreatePriceAlertActivity_MembersInjector(Provider<PriceAlertsConnector> mPriceAlertsConnectorProvider, Provider<LoginManager> loginManagerProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider) {
        this.mPriceAlertsConnectorProvider = mPriceAlertsConnectorProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.mMoneyFormatterUtilProvider = mMoneyFormatterUtilProvider;
    }

    public static MembersInjector<CreatePriceAlertActivity> create(Provider<PriceAlertsConnector> mPriceAlertsConnectorProvider, Provider<LoginManager> loginManagerProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider) {
        return new CreatePriceAlertActivity_MembersInjector(mPriceAlertsConnectorProvider, loginManagerProvider, mMoneyFormatterUtilProvider);
    }

    public void injectMembers(CreatePriceAlertActivity instance) {
        injectMPriceAlertsConnector(instance, (PriceAlertsConnector) this.mPriceAlertsConnectorProvider.get());
        injectLoginManager(instance, (LoginManager) this.loginManagerProvider.get());
        injectMMoneyFormatterUtil(instance, (MoneyFormatterUtil) this.mMoneyFormatterUtilProvider.get());
    }

    public static void injectMPriceAlertsConnector(CreatePriceAlertActivity instance, PriceAlertsConnector mPriceAlertsConnector) {
        instance.mPriceAlertsConnector = mPriceAlertsConnector;
    }

    public static void injectLoginManager(CreatePriceAlertActivity instance, LoginManager loginManager) {
        instance.loginManager = loginManager;
    }

    public static void injectMMoneyFormatterUtil(CreatePriceAlertActivity instance, MoneyFormatterUtil mMoneyFormatterUtil) {
        instance.mMoneyFormatterUtil = mMoneyFormatterUtil;
    }
}
