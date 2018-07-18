package com.coinbase.android.buysell;

import android.app.Application;
import com.coinbase.android.paymentmethods.GetPaymentMethodsTaskRx;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountConnector;
import com.coinbase.android.phone.PhoneNumbersUpdatedConnector;
import com.coinbase.android.quickstart.QuickstartManager;
import com.coinbase.android.settings.GoToSettingsConnector;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.android.ui.KeypadAmountFormatter;
import com.coinbase.android.ui.KeypadAmountValidator;
import com.coinbase.android.ui.NumericKeypadConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.android.utils.TransferUtils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class BuyPresenter_Factory implements Factory<BuyPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<BuyRouter> buyRouterProvider;
    private final Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider;
    private final Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskRxProvider;
    private final Provider<GoToSettingsConnector> goToSettingsConnectorProvider;
    private final Provider<KeypadAmountFormatter> keypadAmountFormatterProvider;
    private final Provider<KeypadAmountValidator> keypadAmountValidatorProvider;
    private final Provider<LinkedAccountConnector> linkedAccountConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<MoneyFormatterUtil> moneyFormatterUtilProvider;
    private final Provider<NumericKeypadConnector> numericKeypadConnectorProvider;
    private final Provider<PaymentMethodUtils> paymentMethodUtilsProvider;
    private final Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider;
    private final Provider<QuickBuyConnector> quickBuyConnectorProvider;
    private final Provider<QuickstartManager> quickstartManagerProvider;
    private final Provider<BuyScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<SplitTesting> splitTestingProvider;
    private final Provider<TransferUtils> transferUtilsProvider;

    public BuyPresenter_Factory(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<BuyScreen> screenProvider, Provider<NumericKeypadConnector> numericKeypadConnectorProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<GoToSettingsConnector> goToSettingsConnectorProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<QuickBuyConnector> quickBuyConnectorProvider, Provider<QuickstartManager> quickstartManagerProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskRxProvider, Provider<SplitTesting> splitTestingProvider, Provider<BuyRouter> buyRouterProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<KeypadAmountFormatter> keypadAmountFormatterProvider, Provider<KeypadAmountValidator> keypadAmountValidatorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.applicationProvider = applicationProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.numericKeypadConnectorProvider = numericKeypadConnectorProvider;
        this.currenciesUpdatedConnectorProvider = currenciesUpdatedConnectorProvider;
        this.linkedAccountConnectorProvider = linkedAccountConnectorProvider;
        this.goToSettingsConnectorProvider = goToSettingsConnectorProvider;
        this.phoneNumbersUpdatedConnectorProvider = phoneNumbersUpdatedConnectorProvider;
        this.quickBuyConnectorProvider = quickBuyConnectorProvider;
        this.quickstartManagerProvider = quickstartManagerProvider;
        this.getPaymentMethodsTaskRxProvider = getPaymentMethodsTaskRxProvider;
        this.splitTestingProvider = splitTestingProvider;
        this.buyRouterProvider = buyRouterProvider;
        this.paymentMethodUtilsProvider = paymentMethodUtilsProvider;
        this.transferUtilsProvider = transferUtilsProvider;
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
        this.keypadAmountFormatterProvider = keypadAmountFormatterProvider;
        this.keypadAmountValidatorProvider = keypadAmountValidatorProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public BuyPresenter get() {
        return provideInstance(this.applicationProvider, this.loginManagerProvider, this.screenProvider, this.numericKeypadConnectorProvider, this.currenciesUpdatedConnectorProvider, this.linkedAccountConnectorProvider, this.goToSettingsConnectorProvider, this.phoneNumbersUpdatedConnectorProvider, this.quickBuyConnectorProvider, this.quickstartManagerProvider, this.getPaymentMethodsTaskRxProvider, this.splitTestingProvider, this.buyRouterProvider, this.paymentMethodUtilsProvider, this.transferUtilsProvider, this.moneyFormatterUtilProvider, this.keypadAmountFormatterProvider, this.keypadAmountValidatorProvider, this.mixpanelTrackingProvider, this.snackBarWrapperProvider, this.mainSchedulerProvider);
    }

    public static BuyPresenter provideInstance(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<BuyScreen> screenProvider, Provider<NumericKeypadConnector> numericKeypadConnectorProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<GoToSettingsConnector> goToSettingsConnectorProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<QuickBuyConnector> quickBuyConnectorProvider, Provider<QuickstartManager> quickstartManagerProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskRxProvider, Provider<SplitTesting> splitTestingProvider, Provider<BuyRouter> buyRouterProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<KeypadAmountFormatter> keypadAmountFormatterProvider, Provider<KeypadAmountValidator> keypadAmountValidatorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new BuyPresenter((Application) applicationProvider.get(), (LoginManager) loginManagerProvider.get(), (BuyScreen) screenProvider.get(), (NumericKeypadConnector) numericKeypadConnectorProvider.get(), (CurrenciesUpdatedConnector) currenciesUpdatedConnectorProvider.get(), (LinkedAccountConnector) linkedAccountConnectorProvider.get(), (GoToSettingsConnector) goToSettingsConnectorProvider.get(), (PhoneNumbersUpdatedConnector) phoneNumbersUpdatedConnectorProvider.get(), (QuickBuyConnector) quickBuyConnectorProvider.get(), (QuickstartManager) quickstartManagerProvider.get(), (GetPaymentMethodsTaskRx) getPaymentMethodsTaskRxProvider.get(), (SplitTesting) splitTestingProvider.get(), (BuyRouter) buyRouterProvider.get(), (PaymentMethodUtils) paymentMethodUtilsProvider.get(), (TransferUtils) transferUtilsProvider.get(), (MoneyFormatterUtil) moneyFormatterUtilProvider.get(), (KeypadAmountFormatter) keypadAmountFormatterProvider.get(), (KeypadAmountValidator) keypadAmountValidatorProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static BuyPresenter_Factory create(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<BuyScreen> screenProvider, Provider<NumericKeypadConnector> numericKeypadConnectorProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<GoToSettingsConnector> goToSettingsConnectorProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<QuickBuyConnector> quickBuyConnectorProvider, Provider<QuickstartManager> quickstartManagerProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskRxProvider, Provider<SplitTesting> splitTestingProvider, Provider<BuyRouter> buyRouterProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<KeypadAmountFormatter> keypadAmountFormatterProvider, Provider<KeypadAmountValidator> keypadAmountValidatorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new BuyPresenter_Factory(applicationProvider, loginManagerProvider, screenProvider, numericKeypadConnectorProvider, currenciesUpdatedConnectorProvider, linkedAccountConnectorProvider, goToSettingsConnectorProvider, phoneNumbersUpdatedConnectorProvider, quickBuyConnectorProvider, quickstartManagerProvider, getPaymentMethodsTaskRxProvider, splitTestingProvider, buyRouterProvider, paymentMethodUtilsProvider, transferUtilsProvider, moneyFormatterUtilProvider, keypadAmountFormatterProvider, keypadAmountValidatorProvider, mixpanelTrackingProvider, snackBarWrapperProvider, mainSchedulerProvider);
    }

    public static BuyPresenter newBuyPresenter(Application application, LoginManager loginManager, Object screen, NumericKeypadConnector numericKeypadConnector, CurrenciesUpdatedConnector currenciesUpdatedConnector, LinkedAccountConnector linkedAccountConnector, GoToSettingsConnector goToSettingsConnector, PhoneNumbersUpdatedConnector phoneNumbersUpdatedConnector, QuickBuyConnector quickBuyConnector, QuickstartManager quickstartManager, GetPaymentMethodsTaskRx getPaymentMethodsTaskRx, SplitTesting splitTesting, BuyRouter buyRouter, PaymentMethodUtils paymentMethodUtils, TransferUtils transferUtils, MoneyFormatterUtil moneyFormatterUtil, KeypadAmountFormatter keypadAmountFormatter, KeypadAmountValidator keypadAmountValidator, MixpanelTracking mixpanelTracking, SnackBarWrapper snackBarWrapper, Scheduler mainScheduler) {
        return new BuyPresenter(application, loginManager, (BuyScreen) screen, numericKeypadConnector, currenciesUpdatedConnector, linkedAccountConnector, goToSettingsConnector, phoneNumbersUpdatedConnector, quickBuyConnector, quickstartManager, getPaymentMethodsTaskRx, splitTesting, buyRouter, paymentMethodUtils, transferUtils, moneyFormatterUtil, keypadAmountFormatter, keypadAmountValidator, mixpanelTracking, snackBarWrapper, mainScheduler);
    }
}
