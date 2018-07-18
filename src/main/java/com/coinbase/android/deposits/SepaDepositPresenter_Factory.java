package com.coinbase.android.deposits;

import android.app.Application;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SepaDepositPresenter_Factory implements Factory<SepaDepositPresenter> {
    private final Provider<Application> appProvider;
    private final Provider<Integer> copyResProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<SepaDepositScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;

    public SepaDepositPresenter_Factory(Provider<LoginManager> loginManagerProvider, Provider<SepaDepositScreen> screenProvider, Provider<Application> appProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Integer> copyResProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.appProvider = appProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.copyResProvider = copyResProvider;
    }

    public SepaDepositPresenter get() {
        return provideInstance(this.loginManagerProvider, this.screenProvider, this.appProvider, this.snackBarWrapperProvider, this.copyResProvider);
    }

    public static SepaDepositPresenter provideInstance(Provider<LoginManager> loginManagerProvider, Provider<SepaDepositScreen> screenProvider, Provider<Application> appProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Integer> copyResProvider) {
        return new SepaDepositPresenter((LoginManager) loginManagerProvider.get(), (SepaDepositScreen) screenProvider.get(), (Application) appProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), ((Integer) copyResProvider.get()).intValue());
    }

    public static SepaDepositPresenter_Factory create(Provider<LoginManager> loginManagerProvider, Provider<SepaDepositScreen> screenProvider, Provider<Application> appProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Integer> copyResProvider) {
        return new SepaDepositPresenter_Factory(loginManagerProvider, screenProvider, appProvider, snackBarWrapperProvider, copyResProvider);
    }

    public static SepaDepositPresenter newSepaDepositPresenter(LoginManager loginManager, SepaDepositScreen screen, Application app, SnackBarWrapper snackBarWrapper, int copyRes) {
        return new SepaDepositPresenter(loginManager, screen, app, snackBarWrapper, copyRes);
    }
}
