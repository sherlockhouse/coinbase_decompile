package com.coinbase.android.paymentmethods;

import android.app.Application;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class AddBankPickerPresenter_Factory implements Factory<AddBankPickerPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<PlaidOnExitConnector> plaidOnExitConnectorProvider;
    private final Provider<AddBankRouter> routerProvider;
    private final Provider<AddBankPickerScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public AddBankPickerPresenter_Factory(Provider<AddBankPickerScreen> screenProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<AddBankRouter> routerProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Application> applicationProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<PlaidOnExitConnector> plaidOnExitConnectorProvider) {
        this.screenProvider = screenProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.routerProvider = routerProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.applicationProvider = applicationProvider;
        this.bankAccountsUpdatedConnectorProvider = bankAccountsUpdatedConnectorProvider;
        this.plaidOnExitConnectorProvider = plaidOnExitConnectorProvider;
    }

    public AddBankPickerPresenter get() {
        return provideInstance(this.screenProvider, this.mixpanelTrackingProvider, this.routerProvider, this.mainSchedulerProvider, this.snackBarWrapperProvider, this.applicationProvider, this.bankAccountsUpdatedConnectorProvider, this.plaidOnExitConnectorProvider);
    }

    public static AddBankPickerPresenter provideInstance(Provider<AddBankPickerScreen> screenProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<AddBankRouter> routerProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Application> applicationProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<PlaidOnExitConnector> plaidOnExitConnectorProvider) {
        return new AddBankPickerPresenter((AddBankPickerScreen) screenProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (AddBankRouter) routerProvider.get(), (Scheduler) mainSchedulerProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (Application) applicationProvider.get(), (BankAccountsUpdatedConnector) bankAccountsUpdatedConnectorProvider.get(), (PlaidOnExitConnector) plaidOnExitConnectorProvider.get());
    }

    public static AddBankPickerPresenter_Factory create(Provider<AddBankPickerScreen> screenProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<AddBankRouter> routerProvider, Provider<Scheduler> mainSchedulerProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Application> applicationProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<PlaidOnExitConnector> plaidOnExitConnectorProvider) {
        return new AddBankPickerPresenter_Factory(screenProvider, mixpanelTrackingProvider, routerProvider, mainSchedulerProvider, snackBarWrapperProvider, applicationProvider, bankAccountsUpdatedConnectorProvider, plaidOnExitConnectorProvider);
    }

    public static AddBankPickerPresenter newAddBankPickerPresenter(AddBankPickerScreen screen, MixpanelTracking mixpanelTracking, AddBankRouter router, Scheduler mainScheduler, SnackBarWrapper snackBarWrapper, Application application, BankAccountsUpdatedConnector bankAccountsUpdatedConnector, PlaidOnExitConnector plaidOnExitConnector) {
        return new AddBankPickerPresenter(screen, mixpanelTracking, router, mainScheduler, snackBarWrapper, application, bankAccountsUpdatedConnector, plaidOnExitConnector);
    }
}
