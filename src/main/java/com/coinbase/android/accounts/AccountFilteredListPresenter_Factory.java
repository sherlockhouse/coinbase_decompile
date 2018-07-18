package com.coinbase.android.accounts;

import com.coinbase.android.settings.AccountsUpdatedConnector;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.task.SyncAccountsTask;
import com.coinbase.android.ui.RefreshRequestedConnector;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class AccountFilteredListPresenter_Factory implements Factory<AccountFilteredListPresenter> {
    private final Provider<AccountsUpdatedConnector> accountsUpdatedConnectorProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<AccountItemClickedConnector> itemClickedConnectorProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<RefreshRequestedConnector> refreshRequestedConnectorProvider;
    private final Provider<AccountListScreen> screenProvider;
    private final Provider<SplitTesting> splitTestingProvider;
    private final Provider<SyncAccountsTask> syncAccountsTaskProvider;

    public AccountFilteredListPresenter_Factory(Provider<LoginManager> loginManagerProvider, Provider<AccountListScreen> screenProvider, Provider<SyncAccountsTask> syncAccountsTaskProvider, Provider<AccountItemClickedConnector> itemClickedConnectorProvider, Provider<RefreshRequestedConnector> refreshRequestedConnectorProvider, Provider<AccountsUpdatedConnector> accountsUpdatedConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SplitTesting> splitTestingProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.syncAccountsTaskProvider = syncAccountsTaskProvider;
        this.itemClickedConnectorProvider = itemClickedConnectorProvider;
        this.refreshRequestedConnectorProvider = refreshRequestedConnectorProvider;
        this.accountsUpdatedConnectorProvider = accountsUpdatedConnectorProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.splitTestingProvider = splitTestingProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public AccountFilteredListPresenter get() {
        return provideInstance(this.loginManagerProvider, this.screenProvider, this.syncAccountsTaskProvider, this.itemClickedConnectorProvider, this.refreshRequestedConnectorProvider, this.accountsUpdatedConnectorProvider, this.mixpanelTrackingProvider, this.splitTestingProvider, this.mainSchedulerProvider, this.backgroundSchedulerProvider);
    }

    public static AccountFilteredListPresenter provideInstance(Provider<LoginManager> loginManagerProvider, Provider<AccountListScreen> screenProvider, Provider<SyncAccountsTask> syncAccountsTaskProvider, Provider<AccountItemClickedConnector> itemClickedConnectorProvider, Provider<RefreshRequestedConnector> refreshRequestedConnectorProvider, Provider<AccountsUpdatedConnector> accountsUpdatedConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SplitTesting> splitTestingProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new AccountFilteredListPresenter((LoginManager) loginManagerProvider.get(), (AccountListScreen) screenProvider.get(), (SyncAccountsTask) syncAccountsTaskProvider.get(), (AccountItemClickedConnector) itemClickedConnectorProvider.get(), (RefreshRequestedConnector) refreshRequestedConnectorProvider.get(), (AccountsUpdatedConnector) accountsUpdatedConnectorProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (SplitTesting) splitTestingProvider.get(), (Scheduler) mainSchedulerProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static AccountFilteredListPresenter_Factory create(Provider<LoginManager> loginManagerProvider, Provider<AccountListScreen> screenProvider, Provider<SyncAccountsTask> syncAccountsTaskProvider, Provider<AccountItemClickedConnector> itemClickedConnectorProvider, Provider<RefreshRequestedConnector> refreshRequestedConnectorProvider, Provider<AccountsUpdatedConnector> accountsUpdatedConnectorProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SplitTesting> splitTestingProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new AccountFilteredListPresenter_Factory(loginManagerProvider, screenProvider, syncAccountsTaskProvider, itemClickedConnectorProvider, refreshRequestedConnectorProvider, accountsUpdatedConnectorProvider, mixpanelTrackingProvider, splitTestingProvider, mainSchedulerProvider, backgroundSchedulerProvider);
    }

    public static AccountFilteredListPresenter newAccountFilteredListPresenter(LoginManager loginManager, AccountListScreen screen, SyncAccountsTask syncAccountsTask, AccountItemClickedConnector itemClickedConnector, RefreshRequestedConnector refreshRequestedConnector, AccountsUpdatedConnector accountsUpdatedConnector, MixpanelTracking mixpanelTracking, SplitTesting splitTesting, Scheduler mainScheduler, Scheduler backgroundScheduler) {
        return new AccountFilteredListPresenter(loginManager, screen, syncAccountsTask, itemClickedConnector, refreshRequestedConnector, accountsUpdatedConnector, mixpanelTracking, splitTesting, mainScheduler, backgroundScheduler);
    }
}
