package com.coinbase.android.settings;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.idology.IdologyUtils;
import com.coinbase.android.idology.IdologyVerificationConnector;
import com.coinbase.android.paymentmethods.AddPaymentMethodConnector;
import com.coinbase.android.paymentmethods.PaymentMethodsFetchedConnector;
import com.coinbase.android.paymentmethods.PaymentMethodsUpdatedConnector;
import com.coinbase.android.paymentmethods.VerifyPaymentMethodConnector;
import com.coinbase.android.pin.PINManager;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.CurrencySelectorConnector;
import com.coinbase.android.ui.SignOutConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import java.util.List;
import javax.inject.Provider;
import rx.Scheduler;
import rx.functions.Func1;

public final class AccountSettingsPresenter_Factory implements Factory<AccountSettingsPresenter> {
    private final Provider<AddPaymentMethodConnector> addPaymentMethodConnectorProvider;
    private final Provider<List<Func1<AccountSettingsPresenter, AccountSettingsPreferenceItem>>> additionalDebugItemsProvider;
    private final Provider<AppCompatActivity> appCompatActivityProvider;
    private final Provider<Application> applicationProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<CurrencySelectorConnector> currencySelectorConnectorProvider;
    private final Provider<IdologyUtils> idologyUtilsProvider;
    private final Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider;
    private final Provider<LocalUserDataUpdatedConnector> localUserDataUpdatedConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<PaymentMethodsFetchedConnector> paymentMethodsFetchedConnectorProvider;
    private final Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider;
    private final Provider<PINManager> pinManagerProvider;
    private final Provider<AccountSettingsScreen> screenProvider;
    private final Provider<SharedPreferences> sharedPrefsProvider;
    private final Provider<SignOutConnector> signOutConnectorProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<SplitTesting> splitTestingProvider;
    private final Provider<UserUpdatedConnector> userUpdatedConnectorProvider;
    private final Provider<VerifyPaymentMethodConnector> verifyPaymentMethodConnectorProvider;

    public AccountSettingsPresenter_Factory(Provider<LoginManager> loginManagerProvider, Provider<PINManager> pinManagerProvider, Provider<Application> applicationProvider, Provider<AppCompatActivity> appCompatActivityProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<AccountSettingsScreen> screenProvider, Provider<UserUpdatedConnector> userUpdatedConnectorProvider, Provider<LocalUserDataUpdatedConnector> localUserDataUpdatedConnectorProvider, Provider<CurrencySelectorConnector> currencySelectorConnectorProvider, Provider<SignOutConnector> signOutConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyUtils> idologyUtilsProvider, Provider<AddPaymentMethodConnector> addPaymentMethodConnectorProvider, Provider<VerifyPaymentMethodConnector> verifyPaymentMethodConnectorProvider, Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider, Provider<PaymentMethodsFetchedConnector> paymentMethodsFetchedConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SplitTesting> splitTestingProvider, Provider<List<Func1<AccountSettingsPresenter, AccountSettingsPreferenceItem>>> additionalDebugItemsProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.pinManagerProvider = pinManagerProvider;
        this.applicationProvider = applicationProvider;
        this.appCompatActivityProvider = appCompatActivityProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.sharedPrefsProvider = sharedPrefsProvider;
        this.screenProvider = screenProvider;
        this.userUpdatedConnectorProvider = userUpdatedConnectorProvider;
        this.localUserDataUpdatedConnectorProvider = localUserDataUpdatedConnectorProvider;
        this.currencySelectorConnectorProvider = currencySelectorConnectorProvider;
        this.signOutConnectorProvider = signOutConnectorProvider;
        this.idologyVerificationConnectorProvider = idologyVerificationConnectorProvider;
        this.idologyUtilsProvider = idologyUtilsProvider;
        this.addPaymentMethodConnectorProvider = addPaymentMethodConnectorProvider;
        this.verifyPaymentMethodConnectorProvider = verifyPaymentMethodConnectorProvider;
        this.paymentMethodsUpdatedConnectorProvider = paymentMethodsUpdatedConnectorProvider;
        this.paymentMethodsFetchedConnectorProvider = paymentMethodsFetchedConnectorProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.splitTestingProvider = splitTestingProvider;
        this.additionalDebugItemsProvider = additionalDebugItemsProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public AccountSettingsPresenter get() {
        return provideInstance(this.loginManagerProvider, this.pinManagerProvider, this.applicationProvider, this.appCompatActivityProvider, this.snackBarWrapperProvider, this.sharedPrefsProvider, this.screenProvider, this.userUpdatedConnectorProvider, this.localUserDataUpdatedConnectorProvider, this.currencySelectorConnectorProvider, this.signOutConnectorProvider, this.idologyVerificationConnectorProvider, this.idologyUtilsProvider, this.addPaymentMethodConnectorProvider, this.verifyPaymentMethodConnectorProvider, this.paymentMethodsUpdatedConnectorProvider, this.paymentMethodsFetchedConnectorProvider, this.mixpanelTrackingProvider, this.splitTestingProvider, this.additionalDebugItemsProvider, this.mainSchedulerProvider, this.backgroundSchedulerProvider);
    }

    public static AccountSettingsPresenter provideInstance(Provider<LoginManager> loginManagerProvider, Provider<PINManager> pinManagerProvider, Provider<Application> applicationProvider, Provider<AppCompatActivity> appCompatActivityProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<AccountSettingsScreen> screenProvider, Provider<UserUpdatedConnector> userUpdatedConnectorProvider, Provider<LocalUserDataUpdatedConnector> localUserDataUpdatedConnectorProvider, Provider<CurrencySelectorConnector> currencySelectorConnectorProvider, Provider<SignOutConnector> signOutConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyUtils> idologyUtilsProvider, Provider<AddPaymentMethodConnector> addPaymentMethodConnectorProvider, Provider<VerifyPaymentMethodConnector> verifyPaymentMethodConnectorProvider, Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider, Provider<PaymentMethodsFetchedConnector> paymentMethodsFetchedConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SplitTesting> splitTestingProvider, Provider<List<Func1<AccountSettingsPresenter, AccountSettingsPreferenceItem>>> additionalDebugItemsProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new AccountSettingsPresenter((LoginManager) loginManagerProvider.get(), (PINManager) pinManagerProvider.get(), (Application) applicationProvider.get(), (AppCompatActivity) appCompatActivityProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (SharedPreferences) sharedPrefsProvider.get(), (AccountSettingsScreen) screenProvider.get(), (UserUpdatedConnector) userUpdatedConnectorProvider.get(), (LocalUserDataUpdatedConnector) localUserDataUpdatedConnectorProvider.get(), (CurrencySelectorConnector) currencySelectorConnectorProvider.get(), (SignOutConnector) signOutConnectorProvider.get(), (IdologyVerificationConnector) idologyVerificationConnectorProvider.get(), (IdologyUtils) idologyUtilsProvider.get(), (AddPaymentMethodConnector) addPaymentMethodConnectorProvider.get(), (VerifyPaymentMethodConnector) verifyPaymentMethodConnectorProvider.get(), (PaymentMethodsUpdatedConnector) paymentMethodsUpdatedConnectorProvider.get(), (PaymentMethodsFetchedConnector) paymentMethodsFetchedConnectorProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (SplitTesting) splitTestingProvider.get(), (List) additionalDebugItemsProvider.get(), (Scheduler) mainSchedulerProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static AccountSettingsPresenter_Factory create(Provider<LoginManager> loginManagerProvider, Provider<PINManager> pinManagerProvider, Provider<Application> applicationProvider, Provider<AppCompatActivity> appCompatActivityProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<AccountSettingsScreen> screenProvider, Provider<UserUpdatedConnector> userUpdatedConnectorProvider, Provider<LocalUserDataUpdatedConnector> localUserDataUpdatedConnectorProvider, Provider<CurrencySelectorConnector> currencySelectorConnectorProvider, Provider<SignOutConnector> signOutConnectorProvider, Provider<IdologyVerificationConnector> idologyVerificationConnectorProvider, Provider<IdologyUtils> idologyUtilsProvider, Provider<AddPaymentMethodConnector> addPaymentMethodConnectorProvider, Provider<VerifyPaymentMethodConnector> verifyPaymentMethodConnectorProvider, Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider, Provider<PaymentMethodsFetchedConnector> paymentMethodsFetchedConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SplitTesting> splitTestingProvider, Provider<List<Func1<AccountSettingsPresenter, AccountSettingsPreferenceItem>>> additionalDebugItemsProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new AccountSettingsPresenter_Factory(loginManagerProvider, pinManagerProvider, applicationProvider, appCompatActivityProvider, snackBarWrapperProvider, sharedPrefsProvider, screenProvider, userUpdatedConnectorProvider, localUserDataUpdatedConnectorProvider, currencySelectorConnectorProvider, signOutConnectorProvider, idologyVerificationConnectorProvider, idologyUtilsProvider, addPaymentMethodConnectorProvider, verifyPaymentMethodConnectorProvider, paymentMethodsUpdatedConnectorProvider, paymentMethodsFetchedConnectorProvider, mixpanelTrackingProvider, splitTestingProvider, additionalDebugItemsProvider, mainSchedulerProvider, backgroundSchedulerProvider);
    }

    public static AccountSettingsPresenter newAccountSettingsPresenter(LoginManager loginManager, PINManager pinManager, Application application, AppCompatActivity appCompatActivity, SnackBarWrapper snackBarWrapper, SharedPreferences sharedPrefs, AccountSettingsScreen screen, UserUpdatedConnector userUpdatedConnector, LocalUserDataUpdatedConnector localUserDataUpdatedConnector, CurrencySelectorConnector currencySelectorConnector, SignOutConnector signOutConnector, IdologyVerificationConnector idologyVerificationConnector, IdologyUtils idologyUtils, AddPaymentMethodConnector addPaymentMethodConnector, VerifyPaymentMethodConnector verifyPaymentMethodConnector, PaymentMethodsUpdatedConnector paymentMethodsUpdatedConnector, PaymentMethodsFetchedConnector paymentMethodsFetchedConnector, MixpanelTracking mixpanelTracking, SplitTesting splitTesting, List<Func1<AccountSettingsPresenter, AccountSettingsPreferenceItem>> additionalDebugItems, Scheduler mainScheduler, Scheduler backgroundScheduler) {
        return new AccountSettingsPresenter(loginManager, pinManager, application, appCompatActivity, snackBarWrapper, sharedPrefs, screen, userUpdatedConnector, localUserDataUpdatedConnector, currencySelectorConnector, signOutConnector, idologyVerificationConnector, idologyUtils, addPaymentMethodConnector, verifyPaymentMethodConnector, paymentMethodsUpdatedConnector, paymentMethodsFetchedConnector, mixpanelTracking, splitTesting, (List) additionalDebugItems, mainScheduler, backgroundScheduler);
    }
}
