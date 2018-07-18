package com.coinbase.android.billing;

import com.coinbase.android.signin.StateListSelectorConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class AddBillingAddressPresenter_Factory implements Factory<AddBillingAddressPresenter> {
    private final Provider<Integer> checkFieldsErrorResProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<AddBillingAddressScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<StateListSelectorConnector> stateListSelectorConnectorProvider;

    public AddBillingAddressPresenter_Factory(Provider<LoginManager> loginManagerProvider, Provider<AddBillingAddressScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<StateListSelectorConnector> stateListSelectorConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Integer> checkFieldsErrorResProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.stateListSelectorConnectorProvider = stateListSelectorConnectorProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.checkFieldsErrorResProvider = checkFieldsErrorResProvider;
    }

    public AddBillingAddressPresenter get() {
        return provideInstance(this.loginManagerProvider, this.screenProvider, this.snackBarWrapperProvider, this.stateListSelectorConnectorProvider, this.mainSchedulerProvider, this.checkFieldsErrorResProvider);
    }

    public static AddBillingAddressPresenter provideInstance(Provider<LoginManager> loginManagerProvider, Provider<AddBillingAddressScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<StateListSelectorConnector> stateListSelectorConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Integer> checkFieldsErrorResProvider) {
        return new AddBillingAddressPresenter((LoginManager) loginManagerProvider.get(), (AddBillingAddressScreen) screenProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (StateListSelectorConnector) stateListSelectorConnectorProvider.get(), (Scheduler) mainSchedulerProvider.get(), ((Integer) checkFieldsErrorResProvider.get()).intValue());
    }

    public static AddBillingAddressPresenter_Factory create(Provider<LoginManager> loginManagerProvider, Provider<AddBillingAddressScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<StateListSelectorConnector> stateListSelectorConnectorProvider, Provider<Scheduler> mainSchedulerProvider, Provider<Integer> checkFieldsErrorResProvider) {
        return new AddBillingAddressPresenter_Factory(loginManagerProvider, screenProvider, snackBarWrapperProvider, stateListSelectorConnectorProvider, mainSchedulerProvider, checkFieldsErrorResProvider);
    }

    public static AddBillingAddressPresenter newAddBillingAddressPresenter(LoginManager loginManager, AddBillingAddressScreen screen, SnackBarWrapper snackBarWrapper, StateListSelectorConnector stateListSelectorConnector, Scheduler mainScheduler, int checkFieldsErrorRes) {
        return new AddBillingAddressPresenter(loginManager, screen, snackBarWrapper, stateListSelectorConnector, mainScheduler, checkFieldsErrorRes);
    }
}
