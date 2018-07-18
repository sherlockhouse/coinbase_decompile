package com.coinbase.android.phone;

import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.ui.SuccessRouter;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class PhoneNumberPresenter_Factory implements Factory<PhoneNumberPresenter> {
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<PhoneNumberScreen> phoneNumberScreenProvider;
    private final Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<SuccessRouter> successRouterProvider;

    public PhoneNumberPresenter_Factory(Provider<LoginManager> loginManagerProvider, Provider<PhoneNumberScreen> phoneNumberScreenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<SuccessRouter> successRouterProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.phoneNumberScreenProvider = phoneNumberScreenProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.phoneNumbersUpdatedConnectorProvider = phoneNumbersUpdatedConnectorProvider;
        this.successRouterProvider = successRouterProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public PhoneNumberPresenter get() {
        return provideInstance(this.loginManagerProvider, this.phoneNumberScreenProvider, this.snackBarWrapperProvider, this.phoneNumbersUpdatedConnectorProvider, this.successRouterProvider, this.mainSchedulerProvider);
    }

    public static PhoneNumberPresenter provideInstance(Provider<LoginManager> loginManagerProvider, Provider<PhoneNumberScreen> phoneNumberScreenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<SuccessRouter> successRouterProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new PhoneNumberPresenter((LoginManager) loginManagerProvider.get(), (PhoneNumberScreen) phoneNumberScreenProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (PhoneNumbersUpdatedConnector) phoneNumbersUpdatedConnectorProvider.get(), (SuccessRouter) successRouterProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static PhoneNumberPresenter_Factory create(Provider<LoginManager> loginManagerProvider, Provider<PhoneNumberScreen> phoneNumberScreenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<PhoneNumbersUpdatedConnector> phoneNumbersUpdatedConnectorProvider, Provider<SuccessRouter> successRouterProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new PhoneNumberPresenter_Factory(loginManagerProvider, phoneNumberScreenProvider, snackBarWrapperProvider, phoneNumbersUpdatedConnectorProvider, successRouterProvider, mainSchedulerProvider);
    }

    public static PhoneNumberPresenter newPhoneNumberPresenter(LoginManager loginManager, PhoneNumberScreen phoneNumberScreen, SnackBarWrapper snackBarWrapper, PhoneNumbersUpdatedConnector phoneNumbersUpdatedConnector, SuccessRouter successRouter, Scheduler mainScheduler) {
        return new PhoneNumberPresenter(loginManager, phoneNumberScreen, snackBarWrapper, phoneNumbersUpdatedConnector, successRouter, mainScheduler);
    }
}
