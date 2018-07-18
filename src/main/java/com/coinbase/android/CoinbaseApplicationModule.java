package com.coinbase.android;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import com.coinbase.android.accounts.AccountListConnector;
import com.coinbase.android.alerts.AlertsSignOutListener;
import com.coinbase.android.currencies.FetchCurrenciesOnCreateListener;
import com.coinbase.android.dashboard.DashboardAlertsConnector;
import com.coinbase.android.dashboard.DashboardBalanceUpdatedConnector;
import com.coinbase.android.dashboard.DashboardVerificationConnector;
import com.coinbase.android.dashboard.SpotPriceUpdatedConnector;
import com.coinbase.android.db.AccountORM;
import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.featureflag.FeatureFlags;
import com.coinbase.android.gdpr.GdprInitializer;
import com.coinbase.android.gdpr.OnboardingUpdatedConnector;
import com.coinbase.android.idology.IdologySignOutListener;
import com.coinbase.android.idology.IdologyUtils;
import com.coinbase.android.paymentmethods.BankAccountsUpdatedConnector;
import com.coinbase.android.paymentmethods.GetPaymentMethodsTaskRx;
import com.coinbase.android.paymentmethods.PaymentMethodsFetchedConnector;
import com.coinbase.android.pin.PINManager;
import com.coinbase.android.settings.LocalUserDataUpdatedConnector;
import com.coinbase.android.signin.AuthManager;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.BottomNavigationConnector;
import com.coinbase.android.ui.CurrenciesUpdatedConnector;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SignOutConnector;
import com.coinbase.android.utils.ConfigUtils;
import com.coinbase.android.utils.RefWatcherWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.CacheFeatureFlagInitializer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CoinbaseApplicationModule {
    private final Application mApplication;

    public CoinbaseApplicationModule(Application application) {
        this.mApplication = application;
    }

    @ApplicationScope
    Application providesApplication() {
        return this.mApplication;
    }

    @ApplicationScope
    DatabaseManager providesDatabaseManager(Application application) {
        return new DatabaseManager(application);
    }

    @ApplicationScope
    Handler providesMainHandler() {
        return new Handler(Looper.getMainLooper());
    }

    @ApplicationScope
    SharedPreferences providesDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this.mApplication);
    }

    @MainScheduler
    @ApplicationScope
    Scheduler providesMainScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @ApplicationScope
    ConfigUtils providesConfigUtils() {
        return new ConfigUtils(this.mApplication);
    }

    @ApplicationScope
    PINManager providesPINManager(LocalUserDataUpdatedConnector connector) {
        return new PINManager(connector);
    }

    @ApplicationScope
    AuthManager providesAuthManager(LoginManager loginManager, SharedPreferences sharedPreferences, @MainScheduler Scheduler mainScheduler, SplitTesting splitTesting) {
        return new AuthManager(loginManager, sharedPreferences, mainScheduler, splitTesting);
    }

    @ApplicationScope
    GetPaymentMethodsTaskRx providesGetPaymentMethodsRx(LoginManager loginManager) {
        return new GetPaymentMethodsTaskRx(loginManager);
    }

    @ApplicationScope
    RefWatcherWrapper providesRefWatcherWrapper(ConfigUtils configUtils) {
        return new RefWatcherWrapper(this.mApplication, configUtils);
    }

    @ApplicationScope
    @BackgroundScheduler
    Scheduler providesBackgroundScheduler() {
        return Schedulers.io();
    }

    @ApplicationScope
    AccountORM providesAccountORM() {
        return new AccountORM();
    }

    @ApplicationScope
    Set<ApplicationSignOutListener> providesApplicationSignOutListeners(DashboardBalanceUpdatedConnector dashboardBalanceUpdatedConnector, DashboardVerificationConnector dashboardVerificationConnector, BottomNavigationConnector bottomNavigationConnector, AccountListConnector accountListConnector, SpotPriceUpdatedConnector spotPriceUpdatedConnector, AlertsSignOutListener alertsSignOutListener, DashboardAlertsConnector dashboardAlertsConnector, BankAccountsUpdatedConnector bankAccountsUpdatedConnector, SplitTesting splitTesting, AppRateOnCreateListener appRateOnCreateListener, IdologySignOutListener idologySignOutListener, PaymentMethodsFetchedConnector paymentMethodsFetchedConnector, OnboardingUpdatedConnector onboardingUpdatedConnector, FeatureFlags featureFlags, CacheFeatureFlagInitializer cacheFeatureFlagInitializer) {
        return new LinkedHashSet(Arrays.asList(new ApplicationSignOutListener[]{bottomNavigationConnector, dashboardBalanceUpdatedConnector, dashboardVerificationConnector, accountListConnector, spotPriceUpdatedConnector, alertsSignOutListener, dashboardAlertsConnector, bankAccountsUpdatedConnector, splitTesting, appRateOnCreateListener, idologySignOutListener, paymentMethodsFetchedConnector, onboardingUpdatedConnector, featureFlags, cacheFeatureFlagInitializer}));
    }

    @ApplicationScope
    Set<ApplicationOnCreateListener> providesApplicationOnCreateListeners(LoginManager loginManager, CurrenciesUpdatedConnector currenciesUpdatedConnector, @BackgroundScheduler Scheduler backgroundScheduler, SignOutConnector signOutConnector, Set<ApplicationSignOutListener> applicationSignOutListeners, FetchAdvertisingIdOnCreateListener fetchAdvertisingIdOnCreateListener, SplitTesting splitTesting, FeatureFlags featureFlags, AppRateOnCreateListener appRateOnCreateListener, CacheFeatureFlagInitializer cacheFeatureFlagInitializer, IdologyUtils idologyUtils, GdprInitializer gdprInitializer) {
        Set<ApplicationOnCreateListener> listenerSet = new HashSet();
        listenerSet.add(new FetchCurrenciesOnCreateListener(this.mApplication, loginManager, currenciesUpdatedConnector, backgroundScheduler));
        listenerSet.add(new SignOutOnCreateListener(signOutConnector, applicationSignOutListeners, backgroundScheduler));
        listenerSet.add(appRateOnCreateListener);
        listenerSet.add(fetchAdvertisingIdOnCreateListener);
        listenerSet.add(splitTesting);
        listenerSet.add(featureFlags);
        listenerSet.add(cacheFeatureFlagInitializer);
        listenerSet.add(idologyUtils);
        listenerSet.add(gdprInitializer);
        return listenerSet;
    }

    @ApplicationScope
    MixpanelTracking providesMixpanelTracking() {
        return MixpanelTracking.getInstance();
    }
}
