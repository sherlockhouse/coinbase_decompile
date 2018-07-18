package com.coinbase.android.deposits.fiat;

import android.app.Application;
import com.coinbase.android.paymentmethods.BankAccountsUpdatedConnector;
import com.coinbase.android.paymentmethods.GetPaymentMethodsTaskRx;
import com.coinbase.android.paymentmethods.PaymentMethodsUpdatedConnector;
import com.coinbase.android.paymentmethods.linkedaccounts.LinkedAccountConnector;
import com.coinbase.android.phone.PhoneNumbersUpdatedConnector;
import com.coinbase.android.quickstart.QuickstartManager;
import com.coinbase.android.settings.GoToSettingsConnector;
import com.coinbase.android.ui.KeypadAmountFormatter;
import com.coinbase.android.ui.KeypadAmountValidator;
import com.coinbase.android.ui.NumericKeypadConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.android.wbl.AvailableBalanceAppBarPresenter;
import com.coinbase.android.wbl.WithdrawalBasedLimitsApiErrorHandler;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class WithdrawFiatPresenter_Factory implements Factory<WithdrawFiatPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<AvailableBalanceAppBarPresenter> availableBalanceAppBarPresenterProvider;
    private final Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider;
    private final Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskProvider;
    private final Provider<GoToSettingsConnector> goToSettingsConnectorProvider;
    private final Provider<KeypadAmountFormatter> keypadAmountFormatterProvider;
    private final Provider<KeypadAmountValidator> keypadAmountValidatorProvider;
    private final Provider<LinkedAccountConnector> linkedAccountConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<NumericKeypadConnector> numericKeypadConnectorProvider;
    private final Provider<PaymentMethodUtils> paymentMethodUtilsProvider;
    private final Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider;
    private final Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider;
    private final Provider<QuickstartManager> quickstartManagerProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<WithdrawFiatRouter> withdrawFiatRouterProvider;
    private final Provider<WithdrawFiatScreen> withdrawFiatScreenProvider;
    private final Provider<WithdrawalBasedLimitsApiErrorHandler> withdrawalBasedLimitsApiErrorHandlerProvider;

    public WithdrawFiatPresenter_Factory(Provider<Application> applicationProvider, Provider<AvailableBalanceAppBarPresenter> availableBalanceAppBarPresenterProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskProvider, Provider<GoToSettingsConnector> goToSettingsConnectorProvider, Provider<KeypadAmountFormatter> keypadAmountFormatterProvider, Provider<KeypadAmountValidator> keypadAmountValidatorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<NumericKeypadConnector> numericKeypadConnectorProvider, Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<QuickstartManager> quickstartManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<WithdrawFiatRouter> withdrawFiatRouterProvider, Provider<WithdrawFiatScreen> withdrawFiatScreenProvider, Provider<WithdrawalBasedLimitsApiErrorHandler> withdrawalBasedLimitsApiErrorHandlerProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.applicationProvider = applicationProvider;
        this.availableBalanceAppBarPresenterProvider = availableBalanceAppBarPresenterProvider;
        this.bankAccountsUpdatedConnectorProvider = bankAccountsUpdatedConnectorProvider;
        this.getPaymentMethodsTaskProvider = getPaymentMethodsTaskProvider;
        this.goToSettingsConnectorProvider = goToSettingsConnectorProvider;
        this.keypadAmountFormatterProvider = keypadAmountFormatterProvider;
        this.keypadAmountValidatorProvider = keypadAmountValidatorProvider;
        this.linkedAccountConnectorProvider = linkedAccountConnectorProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.numericKeypadConnectorProvider = numericKeypadConnectorProvider;
        this.paymentMethodsUpdatedConnectorProvider = paymentMethodsUpdatedConnectorProvider;
        this.paymentMethodUtilsProvider = paymentMethodUtilsProvider;
        this.phoneNumbersUpdatedConnectorProvider = phoneNumbersUpdatedConnectorProvider;
        this.quickstartManagerProvider = quickstartManagerProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.withdrawFiatRouterProvider = withdrawFiatRouterProvider;
        this.withdrawFiatScreenProvider = withdrawFiatScreenProvider;
        this.withdrawalBasedLimitsApiErrorHandlerProvider = withdrawalBasedLimitsApiErrorHandlerProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public WithdrawFiatPresenter get() {
        return provideInstance(this.applicationProvider, this.availableBalanceAppBarPresenterProvider, this.bankAccountsUpdatedConnectorProvider, this.getPaymentMethodsTaskProvider, this.goToSettingsConnectorProvider, this.keypadAmountFormatterProvider, this.keypadAmountValidatorProvider, this.linkedAccountConnectorProvider, this.loginManagerProvider, this.mixpanelTrackingProvider, this.numericKeypadConnectorProvider, this.paymentMethodsUpdatedConnectorProvider, this.paymentMethodUtilsProvider, this.phoneNumbersUpdatedConnectorProvider, this.quickstartManagerProvider, this.snackBarWrapperProvider, this.withdrawFiatRouterProvider, this.withdrawFiatScreenProvider, this.withdrawalBasedLimitsApiErrorHandlerProvider, this.mainSchedulerProvider);
    }

    public static WithdrawFiatPresenter provideInstance(Provider<Application> applicationProvider, Provider<AvailableBalanceAppBarPresenter> availableBalanceAppBarPresenterProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskProvider, Provider<GoToSettingsConnector> goToSettingsConnectorProvider, Provider<KeypadAmountFormatter> keypadAmountFormatterProvider, Provider<KeypadAmountValidator> keypadAmountValidatorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<NumericKeypadConnector> numericKeypadConnectorProvider, Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<QuickstartManager> quickstartManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<WithdrawFiatRouter> withdrawFiatRouterProvider, Provider<WithdrawFiatScreen> withdrawFiatScreenProvider, Provider<WithdrawalBasedLimitsApiErrorHandler> withdrawalBasedLimitsApiErrorHandlerProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new WithdrawFiatPresenter((Application) applicationProvider.get(), (AvailableBalanceAppBarPresenter) availableBalanceAppBarPresenterProvider.get(), (BankAccountsUpdatedConnector) bankAccountsUpdatedConnectorProvider.get(), (GetPaymentMethodsTaskRx) getPaymentMethodsTaskProvider.get(), (GoToSettingsConnector) goToSettingsConnectorProvider.get(), (KeypadAmountFormatter) keypadAmountFormatterProvider.get(), (KeypadAmountValidator) keypadAmountValidatorProvider.get(), (LinkedAccountConnector) linkedAccountConnectorProvider.get(), (LoginManager) loginManagerProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (NumericKeypadConnector) numericKeypadConnectorProvider.get(), (PaymentMethodsUpdatedConnector) paymentMethodsUpdatedConnectorProvider.get(), (PaymentMethodUtils) paymentMethodUtilsProvider.get(), (PhoneNumbersUpdatedConnector) phoneNumbersUpdatedConnectorProvider.get(), (QuickstartManager) quickstartManagerProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (WithdrawFiatRouter) withdrawFiatRouterProvider.get(), (WithdrawFiatScreen) withdrawFiatScreenProvider.get(), (WithdrawalBasedLimitsApiErrorHandler) withdrawalBasedLimitsApiErrorHandlerProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static WithdrawFiatPresenter_Factory create(Provider<Application> applicationProvider, Provider<AvailableBalanceAppBarPresenter> availableBalanceAppBarPresenterProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskProvider, Provider<GoToSettingsConnector> goToSettingsConnectorProvider, Provider<KeypadAmountFormatter> keypadAmountFormatterProvider, Provider<KeypadAmountValidator> keypadAmountValidatorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<NumericKeypadConnector> numericKeypadConnectorProvider, Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<QuickstartManager> quickstartManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<WithdrawFiatRouter> withdrawFiatRouterProvider, Provider<WithdrawFiatScreen> withdrawFiatScreenProvider, Provider<WithdrawalBasedLimitsApiErrorHandler> withdrawalBasedLimitsApiErrorHandlerProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new WithdrawFiatPresenter_Factory(applicationProvider, availableBalanceAppBarPresenterProvider, bankAccountsUpdatedConnectorProvider, getPaymentMethodsTaskProvider, goToSettingsConnectorProvider, keypadAmountFormatterProvider, keypadAmountValidatorProvider, linkedAccountConnectorProvider, loginManagerProvider, mixpanelTrackingProvider, numericKeypadConnectorProvider, paymentMethodsUpdatedConnectorProvider, paymentMethodUtilsProvider, phoneNumbersUpdatedConnectorProvider, quickstartManagerProvider, snackBarWrapperProvider, withdrawFiatRouterProvider, withdrawFiatScreenProvider, withdrawalBasedLimitsApiErrorHandlerProvider, mainSchedulerProvider);
    }

    public static WithdrawFiatPresenter newWithdrawFiatPresenter(Application application, AvailableBalanceAppBarPresenter availableBalanceAppBarPresenter, BankAccountsUpdatedConnector bankAccountsUpdatedConnector, GetPaymentMethodsTaskRx getPaymentMethodsTask, GoToSettingsConnector goToSettingsConnector, KeypadAmountFormatter keypadAmountFormatter, KeypadAmountValidator keypadAmountValidator, LinkedAccountConnector linkedAccountConnector, LoginManager loginManager, MixpanelTracking mixpanelTracking, NumericKeypadConnector numericKeypadConnector, PaymentMethodsUpdatedConnector paymentMethodsUpdatedConnector, PaymentMethodUtils paymentMethodUtils, PhoneNumbersUpdatedConnector phoneNumbersUpdatedConnector, QuickstartManager quickstartManager, SnackBarWrapper snackBarWrapper, WithdrawFiatRouter withdrawFiatRouter, WithdrawFiatScreen withdrawFiatScreen, WithdrawalBasedLimitsApiErrorHandler withdrawalBasedLimitsApiErrorHandler, Scheduler mainScheduler) {
        return new WithdrawFiatPresenter(application, availableBalanceAppBarPresenter, bankAccountsUpdatedConnector, getPaymentMethodsTask, goToSettingsConnector, keypadAmountFormatter, keypadAmountValidator, linkedAccountConnector, loginManager, mixpanelTracking, numericKeypadConnector, paymentMethodsUpdatedConnector, paymentMethodUtils, phoneNumbersUpdatedConnector, quickstartManager, snackBarWrapper, withdrawFiatRouter, withdrawFiatScreen, withdrawalBasedLimitsApiErrorHandler, mainScheduler);
    }
}
