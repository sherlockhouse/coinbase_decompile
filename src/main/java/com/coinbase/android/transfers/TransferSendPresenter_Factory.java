package com.coinbase.android.transfers;

import android.app.Application;
import com.coinbase.android.pin.PINManager;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.android.utils.ApiErrorHandler;
import com.coinbase.android.utils.CryptoUriParser;
import com.coinbase.android.utils.CurrencyUtils;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class TransferSendPresenter_Factory implements Factory<TransferSendPresenter> {
    private final Provider<ApiErrorHandler> apiErrorHandlerProvider;
    private final Provider<Application> appProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<CryptoUriParser> cryptoUriParserProvider;
    private final Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider;
    private final Provider<CurrencyUtils> currencyUtilsProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<MoneyFormatterUtil> moneyFormatterUtilProvider;
    private final Provider<PINManager> pinManagerProvider;
    private final Provider<TransferSendScreen> screenProvider;
    private final Provider<TransferMadeConnector> transferMadeConnectorProvider;

    public TransferSendPresenter_Factory(Provider<TransferSendScreen> screenProvider, Provider<Application> appProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<LoginManager> loginManagerProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<CurrencyUtils> currencyUtilsProvider, Provider<PINManager> pinManagerProvider, Provider<CryptoUriParser> cryptoUriParserProvider, Provider<TransferMadeConnector> transferMadeConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<ApiErrorHandler> apiErrorHandlerProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.screenProvider = screenProvider;
        this.appProvider = appProvider;
        this.currenciesUpdatedConnectorProvider = currenciesUpdatedConnectorProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
        this.currencyUtilsProvider = currencyUtilsProvider;
        this.pinManagerProvider = pinManagerProvider;
        this.cryptoUriParserProvider = cryptoUriParserProvider;
        this.transferMadeConnectorProvider = transferMadeConnectorProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.apiErrorHandlerProvider = apiErrorHandlerProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public TransferSendPresenter get() {
        return provideInstance(this.screenProvider, this.appProvider, this.currenciesUpdatedConnectorProvider, this.loginManagerProvider, this.moneyFormatterUtilProvider, this.currencyUtilsProvider, this.pinManagerProvider, this.cryptoUriParserProvider, this.transferMadeConnectorProvider, this.mixpanelTrackingProvider, this.apiErrorHandlerProvider, this.mainSchedulerProvider, this.backgroundSchedulerProvider);
    }

    public static TransferSendPresenter provideInstance(Provider<TransferSendScreen> screenProvider, Provider<Application> appProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<LoginManager> loginManagerProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<CurrencyUtils> currencyUtilsProvider, Provider<PINManager> pinManagerProvider, Provider<CryptoUriParser> cryptoUriParserProvider, Provider<TransferMadeConnector> transferMadeConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<ApiErrorHandler> apiErrorHandlerProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new TransferSendPresenter((TransferSendScreen) screenProvider.get(), (Application) appProvider.get(), (CurrenciesUpdatedConnector) currenciesUpdatedConnectorProvider.get(), (LoginManager) loginManagerProvider.get(), (MoneyFormatterUtil) moneyFormatterUtilProvider.get(), (CurrencyUtils) currencyUtilsProvider.get(), (PINManager) pinManagerProvider.get(), (CryptoUriParser) cryptoUriParserProvider.get(), (TransferMadeConnector) transferMadeConnectorProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (ApiErrorHandler) apiErrorHandlerProvider.get(), (Scheduler) mainSchedulerProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static TransferSendPresenter_Factory create(Provider<TransferSendScreen> screenProvider, Provider<Application> appProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<LoginManager> loginManagerProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<CurrencyUtils> currencyUtilsProvider, Provider<PINManager> pinManagerProvider, Provider<CryptoUriParser> cryptoUriParserProvider, Provider<TransferMadeConnector> transferMadeConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<ApiErrorHandler> apiErrorHandlerProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new TransferSendPresenter_Factory(screenProvider, appProvider, currenciesUpdatedConnectorProvider, loginManagerProvider, moneyFormatterUtilProvider, currencyUtilsProvider, pinManagerProvider, cryptoUriParserProvider, transferMadeConnectorProvider, mixpanelTrackingProvider, apiErrorHandlerProvider, mainSchedulerProvider, backgroundSchedulerProvider);
    }

    public static TransferSendPresenter newTransferSendPresenter(TransferSendScreen screen, Application app, CurrenciesUpdatedConnector currenciesUpdatedConnector, LoginManager loginManager, MoneyFormatterUtil moneyFormatterUtil, CurrencyUtils currencyUtils, PINManager pinManager, CryptoUriParser cryptoUriParser, TransferMadeConnector transferMadeConnector, MixpanelTracking mixpanelTracking, ApiErrorHandler apiErrorHandler, Scheduler mainScheduler, Scheduler backgroundScheduler) {
        return new TransferSendPresenter(screen, app, currenciesUpdatedConnector, loginManager, moneyFormatterUtil, currencyUtils, pinManager, cryptoUriParser, transferMadeConnector, mixpanelTracking, apiErrorHandler, mainScheduler, backgroundScheduler);
    }
}
