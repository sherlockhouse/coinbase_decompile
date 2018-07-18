package com.coinbase.android.accounts;

import android.app.Application;
import com.coinbase.android.db.AccountORM;
import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class AccountCryptoAddressPresenter_Factory implements Factory<AccountCryptoAddressPresenter> {
    private final Provider<AccountCryptoAddressButtonConnector> accountCryptoAddressButtonConnectorProvider;
    private final Provider<AccountCryptoAddressUpdatedConnector> accountCryptoAddressUpdatedConnectorProvider;
    private final Provider<AccountORM> accountORMProvider;
    private final Provider<Application> appProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<DatabaseManager> dbManagerProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<AccountCryptoAddressScreen> screenProvider;

    public AccountCryptoAddressPresenter_Factory(Provider<LoginManager> loginManagerProvider, Provider<DatabaseManager> dbManagerProvider, Provider<AccountORM> accountORMProvider, Provider<Application> appProvider, Provider<AccountCryptoAddressScreen> screenProvider, Provider<AccountCryptoAddressUpdatedConnector> accountCryptoAddressUpdatedConnectorProvider, Provider<AccountCryptoAddressButtonConnector> accountCryptoAddressButtonConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.dbManagerProvider = dbManagerProvider;
        this.accountORMProvider = accountORMProvider;
        this.appProvider = appProvider;
        this.screenProvider = screenProvider;
        this.accountCryptoAddressUpdatedConnectorProvider = accountCryptoAddressUpdatedConnectorProvider;
        this.accountCryptoAddressButtonConnectorProvider = accountCryptoAddressButtonConnectorProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public AccountCryptoAddressPresenter get() {
        return provideInstance(this.loginManagerProvider, this.dbManagerProvider, this.accountORMProvider, this.appProvider, this.screenProvider, this.accountCryptoAddressUpdatedConnectorProvider, this.accountCryptoAddressButtonConnectorProvider, this.mixpanelTrackingProvider, this.mainSchedulerProvider, this.backgroundSchedulerProvider);
    }

    public static AccountCryptoAddressPresenter provideInstance(Provider<LoginManager> loginManagerProvider, Provider<DatabaseManager> dbManagerProvider, Provider<AccountORM> accountORMProvider, Provider<Application> appProvider, Provider<AccountCryptoAddressScreen> screenProvider, Provider<AccountCryptoAddressUpdatedConnector> accountCryptoAddressUpdatedConnectorProvider, Provider<AccountCryptoAddressButtonConnector> accountCryptoAddressButtonConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new AccountCryptoAddressPresenter((LoginManager) loginManagerProvider.get(), (DatabaseManager) dbManagerProvider.get(), (AccountORM) accountORMProvider.get(), (Application) appProvider.get(), (AccountCryptoAddressScreen) screenProvider.get(), (AccountCryptoAddressUpdatedConnector) accountCryptoAddressUpdatedConnectorProvider.get(), (AccountCryptoAddressButtonConnector) accountCryptoAddressButtonConnectorProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (Scheduler) mainSchedulerProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static AccountCryptoAddressPresenter_Factory create(Provider<LoginManager> loginManagerProvider, Provider<DatabaseManager> dbManagerProvider, Provider<AccountORM> accountORMProvider, Provider<Application> appProvider, Provider<AccountCryptoAddressScreen> screenProvider, Provider<AccountCryptoAddressUpdatedConnector> accountCryptoAddressUpdatedConnectorProvider, Provider<AccountCryptoAddressButtonConnector> accountCryptoAddressButtonConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new AccountCryptoAddressPresenter_Factory(loginManagerProvider, dbManagerProvider, accountORMProvider, appProvider, screenProvider, accountCryptoAddressUpdatedConnectorProvider, accountCryptoAddressButtonConnectorProvider, mixpanelTrackingProvider, mainSchedulerProvider, backgroundSchedulerProvider);
    }

    public static AccountCryptoAddressPresenter newAccountCryptoAddressPresenter(LoginManager loginManager, DatabaseManager dbManager, AccountORM accountORM, Application app, AccountCryptoAddressScreen screen, AccountCryptoAddressUpdatedConnector accountCryptoAddressUpdatedConnector, AccountCryptoAddressButtonConnector accountCryptoAddressButtonConnector, MixpanelTracking mixpanelTracking, Scheduler mainScheduler, Scheduler backgroundScheduler) {
        return new AccountCryptoAddressPresenter(loginManager, dbManager, accountORM, app, screen, accountCryptoAddressUpdatedConnector, accountCryptoAddressButtonConnector, mixpanelTracking, mainScheduler, backgroundScheduler);
    }
}
