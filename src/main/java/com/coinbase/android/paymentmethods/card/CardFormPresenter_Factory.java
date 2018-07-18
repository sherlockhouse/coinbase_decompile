package com.coinbase.android.paymentmethods.card;

import android.app.Application;
import android.content.SharedPreferences;
import com.coinbase.android.billing.BillingAddressDeletedConnector;
import com.coinbase.android.ui.KeyboardListener;
import com.coinbase.android.ui.MystiqueListButtonConnector;
import com.coinbase.android.ui.MystiqueListSelectorConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.ui.SuccessRouter;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import org.apache.commons.validator.routines.CreditCardValidator;
import rx.Scheduler;

public final class CardFormPresenter_Factory implements Factory<CardFormPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<BillingAddressDeletedConnector> billingAddressDeletedConnectorProvider;
    private final Provider<Integer> checkFieldsErrorResProvider;
    private final Provider<CreditCardValidator> creditCardValidatorProvider;
    private final Provider<KeyboardListener> keyboardListenerProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MystiqueListButtonConnector> mystiqueListButtonConnectorProvider;
    private final Provider<MystiqueListSelectorConnector> mystiqueListSelectorConnectorProvider;
    private final Provider<Integer> paymentProcessingErrorResProvider;
    private final Provider<SharedPreferences> prefsProvider;
    private final Provider<CardFormRouter> routerProvider;
    private final Provider<CardFormScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<SuccessRouter> successRouterProvider;
    private final Provider<WorldPayPollingWrapper> worldPayPollingWrapperProvider;
    private final Provider<WorldPayValidator> worldPayValidatorProvider;

    public CardFormPresenter_Factory(Provider<LoginManager> loginManagerProvider, Provider<CardFormScreen> screenProvider, Provider<Application> appProvider, Provider<CardFormRouter> routerProvider, Provider<SharedPreferences> prefsProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Integer> checkFieldsErrorResProvider, Provider<Integer> paymentProcessingErrorResProvider, Provider<CreditCardValidator> creditCardValidatorProvider, Provider<MystiqueListSelectorConnector> mystiqueListSelectorConnectorProvider, Provider<MystiqueListButtonConnector> mystiqueListButtonConnectorProvider, Provider<BillingAddressDeletedConnector> billingAddressDeletedConnectorProvider, Provider<SuccessRouter> successRouterProvider, Provider<KeyboardListener> keyboardListenerProvider, Provider<WorldPayValidator> worldPayValidatorProvider, Provider<WorldPayPollingWrapper> worldPayPollingWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.appProvider = appProvider;
        this.routerProvider = routerProvider;
        this.prefsProvider = prefsProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.checkFieldsErrorResProvider = checkFieldsErrorResProvider;
        this.paymentProcessingErrorResProvider = paymentProcessingErrorResProvider;
        this.creditCardValidatorProvider = creditCardValidatorProvider;
        this.mystiqueListSelectorConnectorProvider = mystiqueListSelectorConnectorProvider;
        this.mystiqueListButtonConnectorProvider = mystiqueListButtonConnectorProvider;
        this.billingAddressDeletedConnectorProvider = billingAddressDeletedConnectorProvider;
        this.successRouterProvider = successRouterProvider;
        this.keyboardListenerProvider = keyboardListenerProvider;
        this.worldPayValidatorProvider = worldPayValidatorProvider;
        this.worldPayPollingWrapperProvider = worldPayPollingWrapperProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public CardFormPresenter get() {
        return provideInstance(this.loginManagerProvider, this.screenProvider, this.appProvider, this.routerProvider, this.prefsProvider, this.snackBarWrapperProvider, this.checkFieldsErrorResProvider, this.paymentProcessingErrorResProvider, this.creditCardValidatorProvider, this.mystiqueListSelectorConnectorProvider, this.mystiqueListButtonConnectorProvider, this.billingAddressDeletedConnectorProvider, this.successRouterProvider, this.keyboardListenerProvider, this.worldPayValidatorProvider, this.worldPayPollingWrapperProvider, this.mainSchedulerProvider);
    }

    public static CardFormPresenter provideInstance(Provider<LoginManager> loginManagerProvider, Provider<CardFormScreen> screenProvider, Provider<Application> appProvider, Provider<CardFormRouter> routerProvider, Provider<SharedPreferences> prefsProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Integer> checkFieldsErrorResProvider, Provider<Integer> paymentProcessingErrorResProvider, Provider<CreditCardValidator> creditCardValidatorProvider, Provider<MystiqueListSelectorConnector> mystiqueListSelectorConnectorProvider, Provider<MystiqueListButtonConnector> mystiqueListButtonConnectorProvider, Provider<BillingAddressDeletedConnector> billingAddressDeletedConnectorProvider, Provider<SuccessRouter> successRouterProvider, Provider<KeyboardListener> keyboardListenerProvider, Provider<WorldPayValidator> worldPayValidatorProvider, Provider<WorldPayPollingWrapper> worldPayPollingWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new CardFormPresenter((LoginManager) loginManagerProvider.get(), (CardFormScreen) screenProvider.get(), (Application) appProvider.get(), (CardFormRouter) routerProvider.get(), (SharedPreferences) prefsProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), ((Integer) checkFieldsErrorResProvider.get()).intValue(), ((Integer) paymentProcessingErrorResProvider.get()).intValue(), (CreditCardValidator) creditCardValidatorProvider.get(), (MystiqueListSelectorConnector) mystiqueListSelectorConnectorProvider.get(), (MystiqueListButtonConnector) mystiqueListButtonConnectorProvider.get(), (BillingAddressDeletedConnector) billingAddressDeletedConnectorProvider.get(), (SuccessRouter) successRouterProvider.get(), (KeyboardListener) keyboardListenerProvider.get(), (WorldPayValidator) worldPayValidatorProvider.get(), (WorldPayPollingWrapper) worldPayPollingWrapperProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static CardFormPresenter_Factory create(Provider<LoginManager> loginManagerProvider, Provider<CardFormScreen> screenProvider, Provider<Application> appProvider, Provider<CardFormRouter> routerProvider, Provider<SharedPreferences> prefsProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Integer> checkFieldsErrorResProvider, Provider<Integer> paymentProcessingErrorResProvider, Provider<CreditCardValidator> creditCardValidatorProvider, Provider<MystiqueListSelectorConnector> mystiqueListSelectorConnectorProvider, Provider<MystiqueListButtonConnector> mystiqueListButtonConnectorProvider, Provider<BillingAddressDeletedConnector> billingAddressDeletedConnectorProvider, Provider<SuccessRouter> successRouterProvider, Provider<KeyboardListener> keyboardListenerProvider, Provider<WorldPayValidator> worldPayValidatorProvider, Provider<WorldPayPollingWrapper> worldPayPollingWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new CardFormPresenter_Factory(loginManagerProvider, screenProvider, appProvider, routerProvider, prefsProvider, snackBarWrapperProvider, checkFieldsErrorResProvider, paymentProcessingErrorResProvider, creditCardValidatorProvider, mystiqueListSelectorConnectorProvider, mystiqueListButtonConnectorProvider, billingAddressDeletedConnectorProvider, successRouterProvider, keyboardListenerProvider, worldPayValidatorProvider, worldPayPollingWrapperProvider, mainSchedulerProvider);
    }

    public static CardFormPresenter newCardFormPresenter(LoginManager loginManager, Object screen, Application app, CardFormRouter router, SharedPreferences prefs, SnackBarWrapper snackBarWrapper, int checkFieldsErrorRes, int paymentProcessingErrorRes, CreditCardValidator creditCardValidator, MystiqueListSelectorConnector mystiqueListSelectorConnector, MystiqueListButtonConnector mystiqueListButtonConnector, BillingAddressDeletedConnector billingAddressDeletedConnector, SuccessRouter successRouter, KeyboardListener keyboardListener, WorldPayValidator worldPayValidator, WorldPayPollingWrapper worldPayPollingWrapper, Scheduler mainScheduler) {
        return new CardFormPresenter(loginManager, (CardFormScreen) screen, app, router, prefs, snackBarWrapper, checkFieldsErrorRes, paymentProcessingErrorRes, creditCardValidator, mystiqueListSelectorConnector, mystiqueListButtonConnector, billingAddressDeletedConnector, successRouter, keyboardListener, worldPayValidator, worldPayPollingWrapper, mainScheduler);
    }
}
