package com.coinbase.android.transfers;

import android.app.Application;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.android.ui.KeypadAmountFormatter;
import com.coinbase.android.ui.KeypadAmountValidator;
import com.coinbase.android.ui.NumericKeypadConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.android.wbl.AvailableBalanceAppBarPresenter;
import com.coinbase.android.wbl.AvailableBalanceAppBarScreen;
import com.coinbase.android.wbl.AvailableBalanceCalculator;
import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorControllerRouter;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class SendPresenter_Factory implements Factory<SendPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<AvailableBalanceAppBarPresenter> availableBalanceAppBarPresenterProvider;
    private final Provider<AvailableBalanceAppBarScreen> availableBalanceAppBarScreenProvider;
    private final Provider<AvailableBalanceCalculator> availableBalanceCalculatorProvider;
    private final Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider;
    private final Provider<KeypadAmountFormatter> keypadAmountFormatterProvider;
    private final Provider<KeypadAmountValidator> keypadAmountValidatorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<NumericKeypadConnector> numericKeypadConnectorProvider;
    private final Provider<SendScreen> screenProvider;
    private final Provider<SendRouter> sendRouterProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<WithdrawalBasedLimitsErrorControllerRouter> withdrawalBasedLimitsErrorControllerRouterProvider;

    public SendPresenter_Factory(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<SendScreen> screenProvider, Provider<AvailableBalanceAppBarScreen> availableBalanceAppBarScreenProvider, Provider<AvailableBalanceAppBarPresenter> availableBalanceAppBarPresenterProvider, Provider<SendRouter> sendRouterProvider, Provider<WithdrawalBasedLimitsErrorControllerRouter> withdrawalBasedLimitsErrorControllerRouterProvider, Provider<NumericKeypadConnector> numericKeypadConnectorProvider, Provider<KeypadAmountFormatter> keypadAmountFormatterProvider, Provider<KeypadAmountValidator> keypadAmountValidatorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<AvailableBalanceCalculator> availableBalanceCalculatorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.applicationProvider = applicationProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.availableBalanceAppBarScreenProvider = availableBalanceAppBarScreenProvider;
        this.availableBalanceAppBarPresenterProvider = availableBalanceAppBarPresenterProvider;
        this.sendRouterProvider = sendRouterProvider;
        this.withdrawalBasedLimitsErrorControllerRouterProvider = withdrawalBasedLimitsErrorControllerRouterProvider;
        this.numericKeypadConnectorProvider = numericKeypadConnectorProvider;
        this.keypadAmountFormatterProvider = keypadAmountFormatterProvider;
        this.keypadAmountValidatorProvider = keypadAmountValidatorProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.currenciesUpdatedConnectorProvider = currenciesUpdatedConnectorProvider;
        this.availableBalanceCalculatorProvider = availableBalanceCalculatorProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public SendPresenter get() {
        return provideInstance(this.applicationProvider, this.loginManagerProvider, this.screenProvider, this.availableBalanceAppBarScreenProvider, this.availableBalanceAppBarPresenterProvider, this.sendRouterProvider, this.withdrawalBasedLimitsErrorControllerRouterProvider, this.numericKeypadConnectorProvider, this.keypadAmountFormatterProvider, this.keypadAmountValidatorProvider, this.snackBarWrapperProvider, this.currenciesUpdatedConnectorProvider, this.availableBalanceCalculatorProvider, this.mixpanelTrackingProvider, this.mainSchedulerProvider);
    }

    public static SendPresenter provideInstance(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<SendScreen> screenProvider, Provider<AvailableBalanceAppBarScreen> availableBalanceAppBarScreenProvider, Provider<AvailableBalanceAppBarPresenter> availableBalanceAppBarPresenterProvider, Provider<SendRouter> sendRouterProvider, Provider<WithdrawalBasedLimitsErrorControllerRouter> withdrawalBasedLimitsErrorControllerRouterProvider, Provider<NumericKeypadConnector> numericKeypadConnectorProvider, Provider<KeypadAmountFormatter> keypadAmountFormatterProvider, Provider<KeypadAmountValidator> keypadAmountValidatorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<AvailableBalanceCalculator> availableBalanceCalculatorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new SendPresenter((Application) applicationProvider.get(), (LoginManager) loginManagerProvider.get(), (SendScreen) screenProvider.get(), (AvailableBalanceAppBarScreen) availableBalanceAppBarScreenProvider.get(), (AvailableBalanceAppBarPresenter) availableBalanceAppBarPresenterProvider.get(), (SendRouter) sendRouterProvider.get(), (WithdrawalBasedLimitsErrorControllerRouter) withdrawalBasedLimitsErrorControllerRouterProvider.get(), (NumericKeypadConnector) numericKeypadConnectorProvider.get(), (KeypadAmountFormatter) keypadAmountFormatterProvider.get(), (KeypadAmountValidator) keypadAmountValidatorProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (CurrenciesUpdatedConnector) currenciesUpdatedConnectorProvider.get(), (AvailableBalanceCalculator) availableBalanceCalculatorProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static SendPresenter_Factory create(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<SendScreen> screenProvider, Provider<AvailableBalanceAppBarScreen> availableBalanceAppBarScreenProvider, Provider<AvailableBalanceAppBarPresenter> availableBalanceAppBarPresenterProvider, Provider<SendRouter> sendRouterProvider, Provider<WithdrawalBasedLimitsErrorControllerRouter> withdrawalBasedLimitsErrorControllerRouterProvider, Provider<NumericKeypadConnector> numericKeypadConnectorProvider, Provider<KeypadAmountFormatter> keypadAmountFormatterProvider, Provider<KeypadAmountValidator> keypadAmountValidatorProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<CurrenciesUpdatedConnector> currenciesUpdatedConnectorProvider, Provider<AvailableBalanceCalculator> availableBalanceCalculatorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new SendPresenter_Factory(applicationProvider, loginManagerProvider, screenProvider, availableBalanceAppBarScreenProvider, availableBalanceAppBarPresenterProvider, sendRouterProvider, withdrawalBasedLimitsErrorControllerRouterProvider, numericKeypadConnectorProvider, keypadAmountFormatterProvider, keypadAmountValidatorProvider, snackBarWrapperProvider, currenciesUpdatedConnectorProvider, availableBalanceCalculatorProvider, mixpanelTrackingProvider, mainSchedulerProvider);
    }

    public static SendPresenter newSendPresenter(Application application, LoginManager loginManager, Object screen, AvailableBalanceAppBarScreen availableBalanceAppBarScreen, AvailableBalanceAppBarPresenter availableBalanceAppBarPresenter, SendRouter sendRouter, WithdrawalBasedLimitsErrorControllerRouter withdrawalBasedLimitsErrorControllerRouter, NumericKeypadConnector numericKeypadConnector, KeypadAmountFormatter keypadAmountFormatter, KeypadAmountValidator keypadAmountValidator, SnackBarWrapper snackBarWrapper, CurrenciesUpdatedConnector currenciesUpdatedConnector, AvailableBalanceCalculator availableBalanceCalculator, MixpanelTracking mixpanelTracking, Scheduler mainScheduler) {
        return new SendPresenter(application, loginManager, (SendScreen) screen, availableBalanceAppBarScreen, availableBalanceAppBarPresenter, sendRouter, withdrawalBasedLimitsErrorControllerRouter, numericKeypadConnector, keypadAmountFormatter, keypadAmountValidator, snackBarWrapper, currenciesUpdatedConnector, availableBalanceCalculator, mixpanelTracking, mainScheduler);
    }
}
