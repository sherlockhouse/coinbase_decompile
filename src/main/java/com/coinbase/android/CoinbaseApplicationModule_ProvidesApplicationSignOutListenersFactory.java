package com.coinbase.android;

import com.coinbase.android.accounts.AccountListConnector;
import com.coinbase.android.alerts.AlertsSignOutListener;
import com.coinbase.android.dashboard.DashboardAlertsConnector;
import com.coinbase.android.dashboard.DashboardBalanceUpdatedConnector;
import com.coinbase.android.dashboard.DashboardVerificationConnector;
import com.coinbase.android.dashboard.SpotPriceUpdatedConnector;
import com.coinbase.android.featureflag.FeatureFlags;
import com.coinbase.android.gdpr.OnboardingUpdatedConnector;
import com.coinbase.android.idology.IdologySignOutListener;
import com.coinbase.android.paymentmethods.BankAccountsUpdatedConnector;
import com.coinbase.android.paymentmethods.PaymentMethodsFetchedConnector;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.BottomNavigationConnector;
import com.coinbase.api.internal.CacheFeatureFlagInitializer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;

public final class CoinbaseApplicationModule_ProvidesApplicationSignOutListenersFactory implements Factory<Set<ApplicationSignOutListener>> {
    private final Provider<AccountListConnector> accountListConnectorProvider;
    private final Provider<AlertsSignOutListener> alertsSignOutListenerProvider;
    private final Provider<AppRateOnCreateListener> appRateOnCreateListenerProvider;
    private final Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider;
    private final Provider<BottomNavigationConnector> bottomNavigationConnectorProvider;
    private final Provider<CacheFeatureFlagInitializer> cacheFeatureFlagInitializerProvider;
    private final Provider<DashboardAlertsConnector> dashboardAlertsConnectorProvider;
    private final Provider<DashboardBalanceUpdatedConnector> dashboardBalanceUpdatedConnectorProvider;
    private final Provider<DashboardVerificationConnector> dashboardVerificationConnectorProvider;
    private final Provider<FeatureFlags> featureFlagsProvider;
    private final Provider<IdologySignOutListener> idologySignOutListenerProvider;
    private final CoinbaseApplicationModule module;
    private final Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider;
    private final Provider<PaymentMethodsFetchedConnector> paymentMethodsFetchedConnectorProvider;
    private final Provider<SplitTesting> splitTestingProvider;
    private final Provider<SpotPriceUpdatedConnector> spotPriceUpdatedConnectorProvider;

    public CoinbaseApplicationModule_ProvidesApplicationSignOutListenersFactory(CoinbaseApplicationModule module, Provider<DashboardBalanceUpdatedConnector> dashboardBalanceUpdatedConnectorProvider, Provider<DashboardVerificationConnector> dashboardVerificationConnectorProvider, Provider<BottomNavigationConnector> bottomNavigationConnectorProvider, Provider<AccountListConnector> accountListConnectorProvider, Provider<SpotPriceUpdatedConnector> spotPriceUpdatedConnectorProvider, Provider<AlertsSignOutListener> alertsSignOutListenerProvider, Provider<DashboardAlertsConnector> dashboardAlertsConnectorProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<SplitTesting> splitTestingProvider, Provider<AppRateOnCreateListener> appRateOnCreateListenerProvider, Provider<IdologySignOutListener> idologySignOutListenerProvider, Provider<PaymentMethodsFetchedConnector> paymentMethodsFetchedConnectorProvider, Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider, Provider<FeatureFlags> featureFlagsProvider, Provider<CacheFeatureFlagInitializer> cacheFeatureFlagInitializerProvider) {
        this.module = module;
        this.dashboardBalanceUpdatedConnectorProvider = dashboardBalanceUpdatedConnectorProvider;
        this.dashboardVerificationConnectorProvider = dashboardVerificationConnectorProvider;
        this.bottomNavigationConnectorProvider = bottomNavigationConnectorProvider;
        this.accountListConnectorProvider = accountListConnectorProvider;
        this.spotPriceUpdatedConnectorProvider = spotPriceUpdatedConnectorProvider;
        this.alertsSignOutListenerProvider = alertsSignOutListenerProvider;
        this.dashboardAlertsConnectorProvider = dashboardAlertsConnectorProvider;
        this.bankAccountsUpdatedConnectorProvider = bankAccountsUpdatedConnectorProvider;
        this.splitTestingProvider = splitTestingProvider;
        this.appRateOnCreateListenerProvider = appRateOnCreateListenerProvider;
        this.idologySignOutListenerProvider = idologySignOutListenerProvider;
        this.paymentMethodsFetchedConnectorProvider = paymentMethodsFetchedConnectorProvider;
        this.onboardingUpdatedConnectorProvider = onboardingUpdatedConnectorProvider;
        this.featureFlagsProvider = featureFlagsProvider;
        this.cacheFeatureFlagInitializerProvider = cacheFeatureFlagInitializerProvider;
    }

    public Set<ApplicationSignOutListener> get() {
        return provideInstance(this.module, this.dashboardBalanceUpdatedConnectorProvider, this.dashboardVerificationConnectorProvider, this.bottomNavigationConnectorProvider, this.accountListConnectorProvider, this.spotPriceUpdatedConnectorProvider, this.alertsSignOutListenerProvider, this.dashboardAlertsConnectorProvider, this.bankAccountsUpdatedConnectorProvider, this.splitTestingProvider, this.appRateOnCreateListenerProvider, this.idologySignOutListenerProvider, this.paymentMethodsFetchedConnectorProvider, this.onboardingUpdatedConnectorProvider, this.featureFlagsProvider, this.cacheFeatureFlagInitializerProvider);
    }

    public static Set<ApplicationSignOutListener> provideInstance(CoinbaseApplicationModule module, Provider<DashboardBalanceUpdatedConnector> dashboardBalanceUpdatedConnectorProvider, Provider<DashboardVerificationConnector> dashboardVerificationConnectorProvider, Provider<BottomNavigationConnector> bottomNavigationConnectorProvider, Provider<AccountListConnector> accountListConnectorProvider, Provider<SpotPriceUpdatedConnector> spotPriceUpdatedConnectorProvider, Provider<AlertsSignOutListener> alertsSignOutListenerProvider, Provider<DashboardAlertsConnector> dashboardAlertsConnectorProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<SplitTesting> splitTestingProvider, Provider<AppRateOnCreateListener> appRateOnCreateListenerProvider, Provider<IdologySignOutListener> idologySignOutListenerProvider, Provider<PaymentMethodsFetchedConnector> paymentMethodsFetchedConnectorProvider, Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider, Provider<FeatureFlags> featureFlagsProvider, Provider<CacheFeatureFlagInitializer> cacheFeatureFlagInitializerProvider) {
        return proxyProvidesApplicationSignOutListeners(module, (DashboardBalanceUpdatedConnector) dashboardBalanceUpdatedConnectorProvider.get(), (DashboardVerificationConnector) dashboardVerificationConnectorProvider.get(), (BottomNavigationConnector) bottomNavigationConnectorProvider.get(), (AccountListConnector) accountListConnectorProvider.get(), (SpotPriceUpdatedConnector) spotPriceUpdatedConnectorProvider.get(), (AlertsSignOutListener) alertsSignOutListenerProvider.get(), (DashboardAlertsConnector) dashboardAlertsConnectorProvider.get(), (BankAccountsUpdatedConnector) bankAccountsUpdatedConnectorProvider.get(), (SplitTesting) splitTestingProvider.get(), (AppRateOnCreateListener) appRateOnCreateListenerProvider.get(), (IdologySignOutListener) idologySignOutListenerProvider.get(), (PaymentMethodsFetchedConnector) paymentMethodsFetchedConnectorProvider.get(), (OnboardingUpdatedConnector) onboardingUpdatedConnectorProvider.get(), (FeatureFlags) featureFlagsProvider.get(), (CacheFeatureFlagInitializer) cacheFeatureFlagInitializerProvider.get());
    }

    public static CoinbaseApplicationModule_ProvidesApplicationSignOutListenersFactory create(CoinbaseApplicationModule module, Provider<DashboardBalanceUpdatedConnector> dashboardBalanceUpdatedConnectorProvider, Provider<DashboardVerificationConnector> dashboardVerificationConnectorProvider, Provider<BottomNavigationConnector> bottomNavigationConnectorProvider, Provider<AccountListConnector> accountListConnectorProvider, Provider<SpotPriceUpdatedConnector> spotPriceUpdatedConnectorProvider, Provider<AlertsSignOutListener> alertsSignOutListenerProvider, Provider<DashboardAlertsConnector> dashboardAlertsConnectorProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<SplitTesting> splitTestingProvider, Provider<AppRateOnCreateListener> appRateOnCreateListenerProvider, Provider<IdologySignOutListener> idologySignOutListenerProvider, Provider<PaymentMethodsFetchedConnector> paymentMethodsFetchedConnectorProvider, Provider<OnboardingUpdatedConnector> onboardingUpdatedConnectorProvider, Provider<FeatureFlags> featureFlagsProvider, Provider<CacheFeatureFlagInitializer> cacheFeatureFlagInitializerProvider) {
        return new CoinbaseApplicationModule_ProvidesApplicationSignOutListenersFactory(module, dashboardBalanceUpdatedConnectorProvider, dashboardVerificationConnectorProvider, bottomNavigationConnectorProvider, accountListConnectorProvider, spotPriceUpdatedConnectorProvider, alertsSignOutListenerProvider, dashboardAlertsConnectorProvider, bankAccountsUpdatedConnectorProvider, splitTestingProvider, appRateOnCreateListenerProvider, idologySignOutListenerProvider, paymentMethodsFetchedConnectorProvider, onboardingUpdatedConnectorProvider, featureFlagsProvider, cacheFeatureFlagInitializerProvider);
    }

    public static Set<ApplicationSignOutListener> proxyProvidesApplicationSignOutListeners(CoinbaseApplicationModule instance, DashboardBalanceUpdatedConnector dashboardBalanceUpdatedConnector, DashboardVerificationConnector dashboardVerificationConnector, BottomNavigationConnector bottomNavigationConnector, AccountListConnector accountListConnector, SpotPriceUpdatedConnector spotPriceUpdatedConnector, AlertsSignOutListener alertsSignOutListener, DashboardAlertsConnector dashboardAlertsConnector, BankAccountsUpdatedConnector bankAccountsUpdatedConnector, SplitTesting splitTesting, AppRateOnCreateListener appRateOnCreateListener, IdologySignOutListener idologySignOutListener, PaymentMethodsFetchedConnector paymentMethodsFetchedConnector, OnboardingUpdatedConnector onboardingUpdatedConnector, FeatureFlags featureFlags, CacheFeatureFlagInitializer cacheFeatureFlagInitializer) {
        return (Set) Preconditions.checkNotNull(instance.providesApplicationSignOutListeners(dashboardBalanceUpdatedConnector, dashboardVerificationConnector, bottomNavigationConnector, accountListConnector, spotPriceUpdatedConnector, alertsSignOutListener, dashboardAlertsConnector, bankAccountsUpdatedConnector, splitTesting, appRateOnCreateListener, idologySignOutListener, paymentMethodsFetchedConnector, onboardingUpdatedConnector, featureFlags, cacheFeatureFlagInitializer), "Cannot return null from a non-@Nullable @Provides method");
    }
}
