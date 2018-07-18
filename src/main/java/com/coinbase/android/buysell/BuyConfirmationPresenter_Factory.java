package com.coinbase.android.buysell;

import android.app.Application;
import com.coinbase.android.Analytics;
import com.coinbase.android.featureflag.FeatureFlags;
import com.coinbase.android.paymentmethods.card.WorldPayPollingWrapper;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountConnector;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.android.utils.TransferUtils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class BuyConfirmationPresenter_Factory implements Factory<BuyConfirmationPresenter> {
    private final Provider<AbstractBuySellConfirmationScreen> abstractBuySellConfirmationScreenProvider;
    private final Provider<Analytics> analyticsProvider;
    private final Provider<Application> appProvider;
    private final Provider<Buy3dsVerificationConnector> buy3dsVerificationConnectorProvider;
    private final Provider<BuyRouter> buyRouterProvider;
    private final Provider<BuySellMadeConnector> buySellMadeConnectorProvider;
    private final Provider<FeatureFlags> featureFlagsProvider;
    private final Provider<Integer> genericErrorTryAgainResProvider;
    private final Provider<LinkedAccountConnector> linkedAccountConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<PaymentMethodUtils> paymentMethodUtilsProvider;
    private final Provider<BuyConfirmationScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<SplitTesting> splitTestingProvider;
    private final Provider<TransferUtils> transferUtilsProvider;
    private final Provider<WorldPayPollingWrapper> worldPayPollingWrapperProvider;

    public BuyConfirmationPresenter_Factory(Provider<Application> appProvider, Provider<LoginManager> loginManagerProvider, Provider<BuyConfirmationScreen> screenProvider, Provider<AbstractBuySellConfirmationScreen> abstractBuySellConfirmationScreenProvider, Provider<BuyRouter> buyRouterProvider, Provider<BuySellMadeConnector> buySellMadeConnectorProvider, Provider<Buy3dsVerificationConnector> buy3dsVerificationConnectorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<Analytics> analyticsProvider, Provider<FeatureFlags> featureFlagsProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<SplitTesting> splitTestingProvider, Provider<Integer> genericErrorTryAgainResProvider, Provider<WorldPayPollingWrapper> worldPayPollingWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.appProvider = appProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.abstractBuySellConfirmationScreenProvider = abstractBuySellConfirmationScreenProvider;
        this.buyRouterProvider = buyRouterProvider;
        this.buySellMadeConnectorProvider = buySellMadeConnectorProvider;
        this.buy3dsVerificationConnectorProvider = buy3dsVerificationConnectorProvider;
        this.linkedAccountConnectorProvider = linkedAccountConnectorProvider;
        this.paymentMethodUtilsProvider = paymentMethodUtilsProvider;
        this.transferUtilsProvider = transferUtilsProvider;
        this.analyticsProvider = analyticsProvider;
        this.featureFlagsProvider = featureFlagsProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.splitTestingProvider = splitTestingProvider;
        this.genericErrorTryAgainResProvider = genericErrorTryAgainResProvider;
        this.worldPayPollingWrapperProvider = worldPayPollingWrapperProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public BuyConfirmationPresenter get() {
        return provideInstance(this.appProvider, this.loginManagerProvider, this.screenProvider, this.abstractBuySellConfirmationScreenProvider, this.buyRouterProvider, this.buySellMadeConnectorProvider, this.buy3dsVerificationConnectorProvider, this.linkedAccountConnectorProvider, this.paymentMethodUtilsProvider, this.transferUtilsProvider, this.analyticsProvider, this.featureFlagsProvider, this.mixpanelTrackingProvider, this.snackBarWrapperProvider, this.splitTestingProvider, this.genericErrorTryAgainResProvider, this.worldPayPollingWrapperProvider, this.mainSchedulerProvider);
    }

    public static BuyConfirmationPresenter provideInstance(Provider<Application> appProvider, Provider<LoginManager> loginManagerProvider, Provider<BuyConfirmationScreen> screenProvider, Provider<AbstractBuySellConfirmationScreen> abstractBuySellConfirmationScreenProvider, Provider<BuyRouter> buyRouterProvider, Provider<BuySellMadeConnector> buySellMadeConnectorProvider, Provider<Buy3dsVerificationConnector> buy3dsVerificationConnectorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<Analytics> analyticsProvider, Provider<FeatureFlags> featureFlagsProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<SplitTesting> splitTestingProvider, Provider<Integer> genericErrorTryAgainResProvider, Provider<WorldPayPollingWrapper> worldPayPollingWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new BuyConfirmationPresenter((Application) appProvider.get(), (LoginManager) loginManagerProvider.get(), (BuyConfirmationScreen) screenProvider.get(), (AbstractBuySellConfirmationScreen) abstractBuySellConfirmationScreenProvider.get(), (BuyRouter) buyRouterProvider.get(), (BuySellMadeConnector) buySellMadeConnectorProvider.get(), (Buy3dsVerificationConnector) buy3dsVerificationConnectorProvider.get(), (LinkedAccountConnector) linkedAccountConnectorProvider.get(), (PaymentMethodUtils) paymentMethodUtilsProvider.get(), (TransferUtils) transferUtilsProvider.get(), (Analytics) analyticsProvider.get(), (FeatureFlags) featureFlagsProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (SplitTesting) splitTestingProvider.get(), ((Integer) genericErrorTryAgainResProvider.get()).intValue(), (WorldPayPollingWrapper) worldPayPollingWrapperProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static BuyConfirmationPresenter_Factory create(Provider<Application> appProvider, Provider<LoginManager> loginManagerProvider, Provider<BuyConfirmationScreen> screenProvider, Provider<AbstractBuySellConfirmationScreen> abstractBuySellConfirmationScreenProvider, Provider<BuyRouter> buyRouterProvider, Provider<BuySellMadeConnector> buySellMadeConnectorProvider, Provider<Buy3dsVerificationConnector> buy3dsVerificationConnectorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<Analytics> analyticsProvider, Provider<FeatureFlags> featureFlagsProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<SplitTesting> splitTestingProvider, Provider<Integer> genericErrorTryAgainResProvider, Provider<WorldPayPollingWrapper> worldPayPollingWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new BuyConfirmationPresenter_Factory(appProvider, loginManagerProvider, screenProvider, abstractBuySellConfirmationScreenProvider, buyRouterProvider, buySellMadeConnectorProvider, buy3dsVerificationConnectorProvider, linkedAccountConnectorProvider, paymentMethodUtilsProvider, transferUtilsProvider, analyticsProvider, featureFlagsProvider, mixpanelTrackingProvider, snackBarWrapperProvider, splitTestingProvider, genericErrorTryAgainResProvider, worldPayPollingWrapperProvider, mainSchedulerProvider);
    }

    public static BuyConfirmationPresenter newBuyConfirmationPresenter(Application app, LoginManager loginManager, Object screen, AbstractBuySellConfirmationScreen abstractBuySellConfirmationScreen, BuyRouter buyRouter, BuySellMadeConnector buySellMadeConnector, Buy3dsVerificationConnector buy3dsVerificationConnector, LinkedAccountConnector linkedAccountConnector, PaymentMethodUtils paymentMethodUtils, TransferUtils transferUtils, Analytics analytics, FeatureFlags featureFlags, MixpanelTracking mixpanelTracking, SnackBarWrapper snackBarWrapper, SplitTesting splitTesting, int genericErrorTryAgainRes, WorldPayPollingWrapper worldPayPollingWrapper, Scheduler mainScheduler) {
        return new BuyConfirmationPresenter(app, loginManager, (BuyConfirmationScreen) screen, abstractBuySellConfirmationScreen, buyRouter, buySellMadeConnector, buy3dsVerificationConnector, linkedAccountConnector, paymentMethodUtils, transferUtils, analytics, featureFlags, mixpanelTracking, snackBarWrapper, splitTesting, genericErrorTryAgainRes, worldPayPollingWrapper, mainScheduler);
    }
}
