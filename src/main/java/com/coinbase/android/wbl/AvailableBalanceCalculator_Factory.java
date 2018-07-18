package com.coinbase.android.wbl;

import com.coinbase.android.settings.AccountsUpdatedConnector;
import com.coinbase.android.task.SyncAccountsTask;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.wbl.PendingHold;
import dagger.internal.Factory;
import java.util.List;
import javax.inject.Provider;
import rx.Scheduler;
import rx.functions.Func0;

public final class AvailableBalanceCalculator_Factory implements Factory<AvailableBalanceCalculator> {
    private final Provider<AccountsUpdatedConnector> accountsUpdatedConnectorProvider;
    private final Provider<Func0<List<PendingHold>>> additionalPendingHoldsProvider;
    private final Provider<Scheduler> backgroundSchedulerProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<MoneyFormatterUtil> moneyFormatterUtilProvider;
    private final Provider<SyncAccountsTask> syncAccountsTaskProvider;

    public AvailableBalanceCalculator_Factory(Provider<SyncAccountsTask> syncAccountsTaskProvider, Provider<LoginManager> loginManagerProvider, Provider<AccountsUpdatedConnector> accountsUpdatedConnectorProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<Func0<List<PendingHold>>> additionalPendingHoldsProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        this.syncAccountsTaskProvider = syncAccountsTaskProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.accountsUpdatedConnectorProvider = accountsUpdatedConnectorProvider;
        this.moneyFormatterUtilProvider = moneyFormatterUtilProvider;
        this.additionalPendingHoldsProvider = additionalPendingHoldsProvider;
        this.backgroundSchedulerProvider = backgroundSchedulerProvider;
    }

    public AvailableBalanceCalculator get() {
        return provideInstance(this.syncAccountsTaskProvider, this.loginManagerProvider, this.accountsUpdatedConnectorProvider, this.moneyFormatterUtilProvider, this.additionalPendingHoldsProvider, this.backgroundSchedulerProvider);
    }

    public static AvailableBalanceCalculator provideInstance(Provider<SyncAccountsTask> syncAccountsTaskProvider, Provider<LoginManager> loginManagerProvider, Provider<AccountsUpdatedConnector> accountsUpdatedConnectorProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<Func0<List<PendingHold>>> additionalPendingHoldsProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new AvailableBalanceCalculator((SyncAccountsTask) syncAccountsTaskProvider.get(), (LoginManager) loginManagerProvider.get(), (AccountsUpdatedConnector) accountsUpdatedConnectorProvider.get(), (MoneyFormatterUtil) moneyFormatterUtilProvider.get(), (Func0) additionalPendingHoldsProvider.get(), (Scheduler) backgroundSchedulerProvider.get());
    }

    public static AvailableBalanceCalculator_Factory create(Provider<SyncAccountsTask> syncAccountsTaskProvider, Provider<LoginManager> loginManagerProvider, Provider<AccountsUpdatedConnector> accountsUpdatedConnectorProvider, Provider<MoneyFormatterUtil> moneyFormatterUtilProvider, Provider<Func0<List<PendingHold>>> additionalPendingHoldsProvider, Provider<Scheduler> backgroundSchedulerProvider) {
        return new AvailableBalanceCalculator_Factory(syncAccountsTaskProvider, loginManagerProvider, accountsUpdatedConnectorProvider, moneyFormatterUtilProvider, additionalPendingHoldsProvider, backgroundSchedulerProvider);
    }

    public static AvailableBalanceCalculator newAvailableBalanceCalculator(SyncAccountsTask syncAccountsTask, LoginManager loginManager, AccountsUpdatedConnector accountsUpdatedConnector, MoneyFormatterUtil moneyFormatterUtil, Func0<List<PendingHold>> additionalPendingHolds, Scheduler backgroundScheduler) {
        return new AvailableBalanceCalculator(syncAccountsTask, loginManager, accountsUpdatedConnector, moneyFormatterUtil, additionalPendingHolds, backgroundScheduler);
    }
}
