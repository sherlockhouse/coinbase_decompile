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
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class DepositFiatPresenter_Factory implements Factory<DepositFiatPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider;
    private final Provider<DepositFiatRouter> depositFiatRouterProvider;
    private final Provider<DepositFiatScreen> depositFiatScreenProvider;
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

    public DepositFiatPresenter_Factory(Provider<Application> applicationProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<DepositFiatRouter> depositFiatRouterProvider, Provider<DepositFiatScreen> depositFiatScreenProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskProvider, Provider<GoToSettingsConnector> goToSettingsConnectorProvider, Provider<KeypadAmountFormatter> keypadAmountFormatterProvider, Provider<KeypadAmountValidator> keypadAmountValidatorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<NumericKeypadConnector> numericKeypadConnectorProvider, Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<QuickstartManager> quickstartManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.applicationProvider = applicationProvider;
        this.bankAccountsUpdatedConnectorProvider = bankAccountsUpdatedConnectorProvider;
        this.depositFiatRouterProvider = depositFiatRouterProvider;
        this.depositFiatScreenProvider = depositFiatScreenProvider;
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
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public DepositFiatPresenter get() {
        return provideInstance(this.applicationProvider, this.bankAccountsUpdatedConnectorProvider, this.depositFiatRouterProvider, this.depositFiatScreenProvider, this.getPaymentMethodsTaskProvider, this.goToSettingsConnectorProvider, this.keypadAmountFormatterProvider, this.keypadAmountValidatorProvider, this.linkedAccountConnectorProvider, this.loginManagerProvider, this.mixpanelTrackingProvider, this.numericKeypadConnectorProvider, this.paymentMethodsUpdatedConnectorProvider, this.paymentMethodUtilsProvider, this.phoneNumbersUpdatedConnectorProvider, this.quickstartManagerProvider, this.snackBarWrapperProvider, this.mainSchedulerProvider);
    }

    public static DepositFiatPresenter provideInstance(Provider<Application> applicationProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<DepositFiatRouter> depositFiatRouterProvider, Provider<DepositFiatScreen> depositFiatScreenProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskProvider, Provider<GoToSettingsConnector> goToSettingsConnectorProvider, Provider<KeypadAmountFormatter> keypadAmountFormatterProvider, Provider<KeypadAmountValidator> keypadAmountValidatorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<NumericKeypadConnector> numericKeypadConnectorProvider, Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<QuickstartManager> quickstartManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new DepositFiatPresenter((Application) applicationProvider.get(), (BankAccountsUpdatedConnector) bankAccountsUpdatedConnectorProvider.get(), (DepositFiatRouter) depositFiatRouterProvider.get(), (DepositFiatScreen) depositFiatScreenProvider.get(), (GetPaymentMethodsTaskRx) getPaymentMethodsTaskProvider.get(), (GoToSettingsConnector) goToSettingsConnectorProvider.get(), (KeypadAmountFormatter) keypadAmountFormatterProvider.get(), (KeypadAmountValidator) keypadAmountValidatorProvider.get(), (LinkedAccountConnector) linkedAccountConnectorProvider.get(), (LoginManager) loginManagerProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (NumericKeypadConnector) numericKeypadConnectorProvider.get(), (PaymentMethodsUpdatedConnector) paymentMethodsUpdatedConnectorProvider.get(), (PaymentMethodUtils) paymentMethodUtilsProvider.get(), (PhoneNumbersUpdatedConnector) phoneNumbersUpdatedConnectorProvider.get(), (QuickstartManager) quickstartManagerProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static DepositFiatPresenter_Factory create(Provider<Application> applicationProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<DepositFiatRouter> depositFiatRouterProvider, Provider<DepositFiatScreen> depositFiatScreenProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskProvider, Provider<GoToSettingsConnector> goToSettingsConnectorProvider, Provider<KeypadAmountFormatter> keypadAmountFormatterProvider, Provider<KeypadAmountValidator> keypadAmountValidatorProvider, Provider<LinkedAccountConnector> linkedAccountConnectorProvider, Provider<LoginManager> loginManagerProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<NumericKeypadConnector> numericKeypadConnectorProvider, Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<QuickstartManager> quickstartManagerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new DepositFiatPresenter_Factory(applicationProvider, bankAccountsUpdatedConnectorProvider, depositFiatRouterProvider, depositFiatScreenProvider, getPaymentMethodsTaskProvider, goToSettingsConnectorProvider, keypadAmountFormatterProvider, keypadAmountValidatorProvider, linkedAccountConnectorProvider, loginManagerProvider, mixpanelTrackingProvider, numericKeypadConnectorProvider, paymentMethodsUpdatedConnectorProvider, paymentMethodUtilsProvider, phoneNumbersUpdatedConnectorProvider, quickstartManagerProvider, snackBarWrapperProvider, mainSchedulerProvider);
    }

    public static DepositFiatPresenter newDepositFiatPresenter(Application application, BankAccountsUpdatedConnector bankAccountsUpdatedConnector, DepositFiatRouter depositFiatRouter, DepositFiatScreen depositFiatScreen, GetPaymentMethodsTaskRx getPaymentMethodsTask, GoToSettingsConnector goToSettingsConnector, KeypadAmountFormatter keypadAmountFormatter, KeypadAmountValidator keypadAmountValidator, LinkedAccountConnector linkedAccountConnector, LoginManager loginManager, MixpanelTracking mixpanelTracking, NumericKeypadConnector numericKeypadConnector, PaymentMethodsUpdatedConnector paymentMethodsUpdatedConnector, PaymentMethodUtils paymentMethodUtils, PhoneNumbersUpdatedConnector phoneNumbersUpdatedConnector, QuickstartManager quickstartManager, SnackBarWrapper snackBarWrapper, Scheduler mainScheduler) {
        return new DepositFiatPresenter(application, bankAccountsUpdatedConnector, depositFiatRouter, depositFiatScreen, getPaymentMethodsTask, goToSettingsConnector, keypadAmountFormatter, keypadAmountValidator, linkedAccountConnector, loginManager, mixpanelTracking, numericKeypadConnector, paymentMethodsUpdatedConnector, paymentMethodUtils, phoneNumbersUpdatedConnector, quickstartManager, snackBarWrapper, mainScheduler);
    }
}
