package com.coinbase.android.buysell;

import android.app.Application;
import com.coinbase.android.paymentmethods.GetPaymentMethodsTaskRx;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountConnector;
import com.coinbase.android.phone.PhoneNumbersUpdatedConnector;
import com.coinbase.android.quickstart.QuickstartManager;
import com.coinbase.android.settings.GoToSettingsConnector;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.android.ui.KeypadAmountFormatter;
import com.coinbase.android.ui.KeypadAmountValidator;
import com.coinbase.android.ui.NumericKeypadConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.android.utils.TransferUtils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.android.wbl.AvailableBalanceAppBarPresenter;
import com.coinbase.android.wbl.AvailableBalanceAppBarScreen;
import com.coinbase.android.wbl.WithdrawalBasedLimitsApiErrorHandler;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class SellPresenter_Factory implements Factory<SellPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<AvailableBalanceAppBarPresenter> availableBalanceAppBarPresenterProvider;
    private final Provider<AvailableBalanceAppBarScreen> availableBalanceAppBarScreenProvider;
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
    private final Provider<QuickstartManager> quickstartManagerProvider;
    private final Provider<SellScreen> screenProvider;
    private final Provider<SellRouter> sellRouterProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<TransferUtils> transferUtilsProvider;
    private final Provider<WithdrawalBasedLimitsApiErrorHandler> withdrawalBasedLimitsApiErrorHandlerProvider;

    public SellPresenter_Factory(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<SellScreen> screenProvider, Provider<AvailableBalanceAppBarScreen> availableBalanceAppBarScreenProvider, Provider<AvailableBalanceAppBarPresenter> availableBalanceAppBarPresenterProvider, Provider<NumericKeypadConnector> numericKeypadConnectorProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<GoToSettingsConnector> goToSettingsConnectorProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<QuickstartManager> quickstartManagerProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskRxProvider, Provider<SellRouter> sellRouterProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<KeypadAmountFormatter> keypadAmountFormatterProvider, Provider<KeypadAmountValidator> keypadAmountValidatorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<WithdrawalBasedLimitsApiErrorHandler> withdrawalBasedLimitsApiErrorHandlerProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.applicationProvider = applicationProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.availableBalanceAppBarScreenProvider = availableBalanceAppBarScreenProvider;
        this.availableBalanceAppBarPresenterProvider = availableBalanceAppBarPresenterProvider;
        this.numericKeypadConnectorProvider = numericKeypadConnectorProvider;
        this.currenciesUpdatedConnectorProvider = currenciesUpdatedConnectorProvider;
        this.linkedAccountConnectorProvider = linkedAccountConnectorProvider;
        this.goToSettingsConnectorProvider = goToSettingsConnectorProvider;
        this.phoneNumbersUpdatedConnectorProvider = phoneNumbersUpdatedConnectorProvider;
        this.quickstartManagerProvider = quickstartManagerProvider;
        this.getPaymentMethodsTaskRxProvider = getPaymentMethodsTaskRxProvider;
        this.sellRouterProvider = sellRouterProvider;
        this.paymentMethodUtilsProvider = paymentMethodUtilsProvider;
        this.transferUtilsProvider = transferUtilsProvider;
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
        this.keypadAmountFormatterProvider = keypadAmountFormatterProvider;
        this.keypadAmountValidatorProvider = keypadAmountValidatorProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.withdrawalBasedLimitsApiErrorHandlerProvider = withdrawalBasedLimitsApiErrorHandlerProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public SellPresenter get() {
        return provideInstance(this.applicationProvider, this.loginManagerProvider, this.screenProvider, this.availableBalanceAppBarScreenProvider, this.availableBalanceAppBarPresenterProvider, this.numericKeypadConnectorProvider, this.currenciesUpdatedConnectorProvider, this.linkedAccountConnectorProvider, this.goToSettingsConnectorProvider, this.phoneNumbersUpdatedConnectorProvider, this.quickstartManagerProvider, this.getPaymentMethodsTaskRxProvider, this.sellRouterProvider, this.paymentMethodUtilsProvider, this.transferUtilsProvider, this.moneyFormatterUtilProvider, this.keypadAmountFormatterProvider, this.keypadAmountValidatorProvider, this.mixpanelTrackingProvider, this.snackBarWrapperProvider, this.withdrawalBasedLimitsApiErrorHandlerProvider, this.mainSchedulerProvider);
    }

    public static SellPresenter provideInstance(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<SellScreen> screenProvider, Provider<AvailableBalanceAppBarScreen> availableBalanceAppBarScreenProvider, Provider<AvailableBalanceAppBarPresenter> availableBalanceAppBarPresenterProvider, Provider<NumericKeypadConnector> numericKeypadConnectorProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<GoToSettingsConnector> goToSettingsConnectorProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<QuickstartManager> quickstartManagerProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskRxProvider, Provider<SellRouter> sellRouterProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<KeypadAmountFormatter> keypadAmountFormatterProvider, Provider<KeypadAmountValidator> keypadAmountValidatorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<WithdrawalBasedLimitsApiErrorHandler> withdrawalBasedLimitsApiErrorHandlerProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new SellPresenter((Application) applicationProvider.get(), (LoginManager) loginManagerProvider.get(), (SellScreen) screenProvider.get(), (AvailableBalanceAppBarScreen) availableBalanceAppBarScreenProvider.get(), (AvailableBalanceAppBarPresenter) availableBalanceAppBarPresenterProvider.get(), (NumericKeypadConnector) numericKeypadConnectorProvider.get(), (CurrenciesUpdatedConnector) currenciesUpdatedConnectorProvider.get(), (LinkedAccountConnector) linkedAccountConnectorProvider.get(), (GoToSettingsConnector) goToSettingsConnectorProvider.get(), (PhoneNumbersUpdatedConnector) phoneNumbersUpdatedConnectorProvider.get(), (QuickstartManager) quickstartManagerProvider.get(), (GetPaymentMethodsTaskRx) getPaymentMethodsTaskRxProvider.get(), (SellRouter) sellRouterProvider.get(), (PaymentMethodUtils) paymentMethodUtilsProvider.get(), (TransferUtils) transferUtilsProvider.get(), (MoneyFormatterUtil) moneyFormatterUtilProvider.get(), (KeypadAmountFormatter) keypadAmountFormatterProvider.get(), (KeypadAmountValidator) keypadAmountValidatorProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (WithdrawalBasedLimitsApiErrorHandler) withdrawalBasedLimitsApiErrorHandlerProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static SellPresenter_Factory create(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<SellScreen> screenProvider, Provider<AvailableBalanceAppBarScreen> availableBalanceAppBarScreenProvider, Provider<AvailableBalanceAppBarPresenter> availableBalanceAppBarPresenterProvider, Provider<NumericKeypadConnector> numericKeypadConnectorProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<GoToSettingsConnector> goToSettingsConnectorProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<QuickstartManager> quickstartManagerProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskRxProvider, Provider<SellRouter> sellRouterProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<KeypadAmountFormatter> keypadAmountFormatterProvider, Provider<KeypadAmountValidator> keypadAmountValidatorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<WithdrawalBasedLimitsApiErrorHandler> withdrawalBasedLimitsApiErrorHandlerProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new SellPresenter_Factory(applicationProvider, loginManagerProvider, screenProvider, availableBalanceAppBarScreenProvider, availableBalanceAppBarPresenterProvider, numericKeypadConnectorProvider, currenciesUpdatedConnectorProvider, linkedAccountConnectorProvider, goToSettingsConnectorProvider, phoneNumbersUpdatedConnectorProvider, quickstartManagerProvider, getPaymentMethodsTaskRxProvider, sellRouterProvider, paymentMethodUtilsProvider, transferUtilsProvider, moneyFormatterUtilProvider, keypadAmountFormatterProvider, keypadAmountValidatorProvider, mixpanelTrackingProvider, snackBarWrapperProvider, withdrawalBasedLimitsApiErrorHandlerProvider, mainSchedulerProvider);
    }

    public static SellPresenter newSellPresenter(Application application, LoginManager loginManager, Object screen, AvailableBalanceAppBarScreen availableBalanceAppBarScreen, AvailableBalanceAppBarPresenter availableBalanceAppBarPresenter, NumericKeypadConnector numericKeypadConnector, CurrenciesUpdatedConnector currenciesUpdatedConnector, LinkedAccountConnector linkedAccountConnector, GoToSettingsConnector goToSettingsConnector, PhoneNumbersUpdatedConnector phoneNumbersUpdatedConnector, QuickstartManager quickstartManager, GetPaymentMethodsTaskRx getPaymentMethodsTaskRx, SellRouter sellRouter, PaymentMethodUtils paymentMethodUtils, TransferUtils transferUtils, MoneyFormatterUtil moneyFormatterUtil, KeypadAmountFormatter keypadAmountFormatter, KeypadAmountValidator keypadAmountValidator, MixpanelTracking mixpanelTracking, SnackBarWrapper snackBarWrapper, WithdrawalBasedLimitsApiErrorHandler withdrawalBasedLimitsApiErrorHandler, Scheduler mainScheduler) {
        return new SellPresenter(application, loginManager, (SellScreen) screen, availableBalanceAppBarScreen, availableBalanceAppBarPresenter, numericKeypadConnector, currenciesUpdatedConnector, linkedAccountConnector, goToSettingsConnector, phoneNumbersUpdatedConnector, quickstartManager, getPaymentMethodsTaskRx, sellRouter, paymentMethodUtils, transferUtils, moneyFormatterUtil, keypadAmountFormatter, keypadAmountValidator, mixpanelTracking, snackBarWrapper, withdrawalBasedLimitsApiErrorHandler, mainScheduler);
    }
}
