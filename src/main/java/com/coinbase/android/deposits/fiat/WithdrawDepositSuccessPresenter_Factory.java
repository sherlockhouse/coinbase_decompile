package com.coinbase.android.deposits.fiat;

import android.app.Application;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.TransferUtils;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class WithdrawDepositSuccessPresenter_Factory implements Factory<WithdrawDepositSuccessPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<WithdrawDepositSuccessScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<TransferUtils> transferUtilsProvider;
    private final Provider<WithdrawDepositSuccessRouter> withdrawDepositSuccessRouterProvider;

    public WithdrawDepositSuccessPresenter_Factory(Provider<Application> applicationProvider, Provider<WithdrawDepositSuccessScreen> screenProvider, Provider<WithdrawDepositSuccessRouter> withdrawDepositSuccessRouterProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<TransferUtils> transferUtilsProvider) {
        this.applicationProvider = applicationProvider;
        this.screenProvider = screenProvider;
        this.withdrawDepositSuccessRouterProvider = withdrawDepositSuccessRouterProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.transferUtilsProvider = transferUtilsProvider;
    }

    public WithdrawDepositSuccessPresenter get() {
        return provideInstance(this.applicationProvider, this.screenProvider, this.withdrawDepositSuccessRouterProvider, this.snackBarWrapperProvider, this.transferUtilsProvider);
    }

    public static WithdrawDepositSuccessPresenter provideInstance(Provider<Application> applicationProvider, Provider<WithdrawDepositSuccessScreen> screenProvider, Provider<WithdrawDepositSuccessRouter> withdrawDepositSuccessRouterProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<TransferUtils> transferUtilsProvider) {
        return new WithdrawDepositSuccessPresenter((Application) applicationProvider.get(), (WithdrawDepositSuccessScreen) screenProvider.get(), (WithdrawDepositSuccessRouter) withdrawDepositSuccessRouterProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (TransferUtils) transferUtilsProvider.get());
    }

    public static WithdrawDepositSuccessPresenter_Factory create(Provider<Application> applicationProvider, Provider<WithdrawDepositSuccessScreen> screenProvider, Provider<WithdrawDepositSuccessRouter> withdrawDepositSuccessRouterProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<TransferUtils> transferUtilsProvider) {
        return new WithdrawDepositSuccessPresenter_Factory(applicationProvider, screenProvider, withdrawDepositSuccessRouterProvider, snackBarWrapperProvider, transferUtilsProvider);
    }

    public static WithdrawDepositSuccessPresenter newWithdrawDepositSuccessPresenter(Application application, Object screen, WithdrawDepositSuccessRouter withdrawDepositSuccessRouter, SnackBarWrapper snackBarWrapper, TransferUtils transferUtils) {
        return new WithdrawDepositSuccessPresenter(application, (WithdrawDepositSuccessScreen) screen, withdrawDepositSuccessRouter, snackBarWrapper, transferUtils);
    }
}
