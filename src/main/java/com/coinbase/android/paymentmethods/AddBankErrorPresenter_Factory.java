package com.coinbase.android.paymentmethods;

import com.coinbase.android.ui.SuccessRouter;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class AddBankErrorPresenter_Factory implements Factory<AddBankErrorPresenter> {
    private final Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<AddBankErrorScreen> screenProvider;
    private final Provider<SuccessRouter> successRouterProvider;

    public AddBankErrorPresenter_Factory(Provider<AddBankErrorScreen> screenProvider, Provider<SuccessRouter> successRouterProvider, Provider<Scheduler> mainSchedulerProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider) {
        this.screenProvider = screenProvider;
        this.successRouterProvider = successRouterProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.bankAccountsUpdatedConnectorProvider = bankAccountsUpdatedConnectorProvider;
    }

    public AddBankErrorPresenter get() {
        return provideInstance(this.screenProvider, this.successRouterProvider, this.mainSchedulerProvider, this.bankAccountsUpdatedConnectorProvider);
    }

    public static AddBankErrorPresenter provideInstance(Provider<AddBankErrorScreen> screenProvider, Provider<SuccessRouter> successRouterProvider, Provider<Scheduler> mainSchedulerProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider) {
        return new AddBankErrorPresenter((AddBankErrorScreen) screenProvider.get(), (SuccessRouter) successRouterProvider.get(), (Scheduler) mainSchedulerProvider.get(), (BankAccountsUpdatedConnector) bankAccountsUpdatedConnectorProvider.get());
    }

    public static AddBankErrorPresenter_Factory create(Provider<AddBankErrorScreen> screenProvider, Provider<SuccessRouter> successRouterProvider, Provider<Scheduler> mainSchedulerProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider) {
        return new AddBankErrorPresenter_Factory(screenProvider, successRouterProvider, mainSchedulerProvider, bankAccountsUpdatedConnectorProvider);
    }

    public static AddBankErrorPresenter newAddBankErrorPresenter(AddBankErrorScreen screen, SuccessRouter successRouter, Scheduler mainScheduler, BankAccountsUpdatedConnector bankAccountsUpdatedConnector) {
        return new AddBankErrorPresenter(screen, successRouter, mainScheduler, bankAccountsUpdatedConnector);
    }
}
