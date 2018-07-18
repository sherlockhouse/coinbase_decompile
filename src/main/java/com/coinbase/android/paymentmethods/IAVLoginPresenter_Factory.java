package com.coinbase.android.paymentmethods;

import android.app.Application;
import android.content.SharedPreferences;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class IAVLoginPresenter_Factory implements Factory<IAVLoginPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<IAVLoginRouter> routerProvider;
    private final Provider<Scheduler> schedulerProvider;
    private final Provider<IAVLoginScreen> screenProvider;
    private final Provider<SharedPreferences> sharedPrefsProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public IAVLoginPresenter_Factory(Provider<IAVLoginScreen> screenProvider, Provider<Application> appProvider, Provider<IAVLoginRouter> routerProvider, Provider<LoginManager> loginManagerProvider, Provider<Scheduler> schedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider) {
        this.screenProvider = screenProvider;
        this.appProvider = appProvider;
        this.routerProvider = routerProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.schedulerProvider = schedulerProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.sharedPrefsProvider = sharedPrefsProvider;
        this.bankAccountsUpdatedConnectorProvider = bankAccountsUpdatedConnectorProvider;
    }

    public IAVLoginPresenter get() {
        return provideInstance(this.screenProvider, this.appProvider, this.routerProvider, this.loginManagerProvider, this.schedulerProvider, this.snackBarWrapperProvider, this.mixpanelTrackingProvider, this.sharedPrefsProvider, this.bankAccountsUpdatedConnectorProvider);
    }

    public static IAVLoginPresenter provideInstance(Provider<IAVLoginScreen> screenProvider, Provider<Application> appProvider, Provider<IAVLoginRouter> routerProvider, Provider<LoginManager> loginManagerProvider, Provider<Scheduler> schedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider) {
        return new IAVLoginPresenter((IAVLoginScreen) screenProvider.get(), (Application) appProvider.get(), (IAVLoginRouter) routerProvider.get(), (LoginManager) loginManagerProvider.get(), (Scheduler) schedulerProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (SharedPreferences) sharedPrefsProvider.get(), (BankAccountsUpdatedConnector) bankAccountsUpdatedConnectorProvider.get());
    }

    public static IAVLoginPresenter_Factory create(Provider<IAVLoginScreen> screenProvider, Provider<Application> appProvider, Provider<IAVLoginRouter> routerProvider, Provider<LoginManager> loginManagerProvider, Provider<Scheduler> schedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SharedPreferences> sharedPrefsProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider) {
        return new IAVLoginPresenter_Factory(screenProvider, appProvider, routerProvider, loginManagerProvider, schedulerProvider, snackBarWrapperProvider, mixpanelTrackingProvider, sharedPrefsProvider, bankAccountsUpdatedConnectorProvider);
    }

    public static IAVLoginPresenter newIAVLoginPresenter(IAVLoginScreen screen, Application app, IAVLoginRouter router, LoginManager loginManager, Scheduler scheduler, SnackBarWrapper snackBarWrapper, MixpanelTracking mixpanelTracking, SharedPreferences sharedPrefs, BankAccountsUpdatedConnector bankAccountsUpdatedConnector) {
        return new IAVLoginPresenter(screen, app, router, loginManager, scheduler, snackBarWrapper, mixpanelTracking, sharedPrefs, bankAccountsUpdatedConnector);
    }
}
