package com.coinbase.android.transfers;

import android.app.Application;
import com.coinbase.android.db.DatabaseManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DelayedTransactionDialogPresenter_Factory implements Factory<DelayedTransactionDialogPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<DatabaseManager> databaseManagerProvider;
    private final Provider<TransferMadeConnector> transferMadeConnectorProvider;

    public DelayedTransactionDialogPresenter_Factory(Provider<DatabaseManager> databaseManagerProvider, Provider<TransferMadeConnector> transferMadeConnectorProvider, Provider<Application> appProvider) {
        this.databaseManagerProvider = databaseManagerProvider;
        this.transferMadeConnectorProvider = transferMadeConnectorProvider;
        this.appProvider = appProvider;
    }

    public DelayedTransactionDialogPresenter get() {
        return provideInstance(this.databaseManagerProvider, this.transferMadeConnectorProvider, this.appProvider);
    }

    public static DelayedTransactionDialogPresenter provideInstance(Provider<DatabaseManager> databaseManagerProvider, Provider<TransferMadeConnector> transferMadeConnectorProvider, Provider<Application> appProvider) {
        return new DelayedTransactionDialogPresenter((DatabaseManager) databaseManagerProvider.get(), (TransferMadeConnector) transferMadeConnectorProvider.get(), (Application) appProvider.get());
    }

    public static DelayedTransactionDialogPresenter_Factory create(Provider<DatabaseManager> databaseManagerProvider, Provider<TransferMadeConnector> transferMadeConnectorProvider, Provider<Application> appProvider) {
        return new DelayedTransactionDialogPresenter_Factory(databaseManagerProvider, transferMadeConnectorProvider, appProvider);
    }

    public static DelayedTransactionDialogPresenter newDelayedTransactionDialogPresenter(DatabaseManager databaseManager, TransferMadeConnector transferMadeConnector, Application app) {
        return new DelayedTransactionDialogPresenter(databaseManager, transferMadeConnector, app);
    }
}
