package com.coinbase.android.buysell;

import android.app.Application;
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

public final class SellConfirmationPresenter_Factory implements Factory<SellConfirmationPresenter> {
    private final Provider<AbstractBuySellConfirmationScreen> abstractBuySellConfirmationScreenProvider;
    private final Provider<Application> appProvider;
    private final Provider<BuySellMadeConnector> buySellMadeConnectorProvider;
    private final Provider<LinkedAccountConnector> linkedAccountConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<PaymentMethodUtils> paymentMethodUtilsProvider;
    private final Provider<SellConfirmationScreen> screenProvider;
    private final Provider<SellRouter> sellRouterProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<SplitTesting> splitTestingProvider;
    private final Provider<TransferUtils> transferUtilsProvider;

    public SellConfirmationPresenter_Factory(Provider<Application> appProvider, Provider<LoginManager> loginManagerProvider, Provider<SellConfirmationScreen> screenProvider, Provider<AbstractBuySellConfirmationScreen> abstractBuySellConfirmationScreenProvider, Provider<SellRouter> sellRouterProvider, Provider<BuySellMadeConnector> buySellMadeConnectorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<SplitTesting> splitTestingProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.appProvider = appProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.abstractBuySellConfirmationScreenProvider = abstractBuySellConfirmationScreenProvider;
        this.sellRouterProvider = sellRouterProvider;
        this.buySellMadeConnectorProvider = buySellMadeConnectorProvider;
        this.linkedAccountConnectorProvider = linkedAccountConnectorProvider;
        this.paymentMethodUtilsProvider = paymentMethodUtilsProvider;
        this.transferUtilsProvider = transferUtilsProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.splitTestingProvider = splitTestingProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public SellConfirmationPresenter get() {
        return provideInstance(this.appProvider, this.loginManagerProvider, this.screenProvider, this.abstractBuySellConfirmationScreenProvider, this.sellRouterProvider, this.buySellMadeConnectorProvider, this.linkedAccountConnectorProvider, this.paymentMethodUtilsProvider, this.transferUtilsProvider, this.mixpanelTrackingProvider, this.snackBarWrapperProvider, this.splitTestingProvider, this.mainSchedulerProvider);
    }

    public static SellConfirmationPresenter provideInstance(Provider<Application> appProvider, Provider<LoginManager> loginManagerProvider, Provider<SellConfirmationScreen> screenProvider, Provider<AbstractBuySellConfirmationScreen> abstractBuySellConfirmationScreenProvider, Provider<SellRouter> sellRouterProvider, Provider<BuySellMadeConnector> buySellMadeConnectorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<SplitTesting> splitTestingProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new SellConfirmationPresenter((Application) appProvider.get(), (LoginManager) loginManagerProvider.get(), (SellConfirmationScreen) screenProvider.get(), (AbstractBuySellConfirmationScreen) abstractBuySellConfirmationScreenProvider.get(), (SellRouter) sellRouterProvider.get(), (BuySellMadeConnector) buySellMadeConnectorProvider.get(), (LinkedAccountConnector) linkedAccountConnectorProvider.get(), (PaymentMethodUtils) paymentMethodUtilsProvider.get(), (TransferUtils) transferUtilsProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (SplitTesting) splitTestingProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static SellConfirmationPresenter_Factory create(Provider<Application> appProvider, Provider<LoginManager> loginManagerProvider, Provider<SellConfirmationScreen> screenProvider, Provider<AbstractBuySellConfirmationScreen> abstractBuySellConfirmationScreenProvider, Provider<SellRouter> sellRouterProvider, Provider<BuySellMadeConnector> buySellMadeConnectorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<SplitTesting> splitTestingProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new SellConfirmationPresenter_Factory(appProvider, loginManagerProvider, screenProvider, abstractBuySellConfirmationScreenProvider, sellRouterProvider, buySellMadeConnectorProvider, linkedAccountConnectorProvider, paymentMethodUtilsProvider, transferUtilsProvider, mixpanelTrackingProvider, snackBarWrapperProvider, splitTestingProvider, mainSchedulerProvider);
    }

    public static SellConfirmationPresenter newSellConfirmationPresenter(Application app, LoginManager loginManager, Object screen, AbstractBuySellConfirmationScreen abstractBuySellConfirmationScreen, SellRouter sellRouter, BuySellMadeConnector buySellMadeConnector, LinkedAccountConnector linkedAccountConnector, PaymentMethodUtils paymentMethodUtils, TransferUtils transferUtils, MixpanelTracking mixpanelTracking, SnackBarWrapper snackBarWrapper, SplitTesting splitTesting, Scheduler mainScheduler) {
        return new SellConfirmationPresenter(app, loginManager, (SellConfirmationScreen) screen, abstractBuySellConfirmationScreen, sellRouter, buySellMadeConnector, linkedAccountConnector, paymentMethodUtils, transferUtils, mixpanelTracking, snackBarWrapper, splitTesting, mainScheduler);
    }
}
