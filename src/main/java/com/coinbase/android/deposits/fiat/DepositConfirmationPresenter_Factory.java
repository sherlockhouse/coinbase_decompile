package com.coinbase.android.deposits.fiat;

import android.app.Application;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.android.utils.TransferUtils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class DepositConfirmationPresenter_Factory implements Factory<DepositConfirmationPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<DepositFiatConfirmationRouter> depositFiatConfirmationRouterProvider;
    private final Provider<DepositFiatConfirmationScreen> depositFiatConfirmationScreenProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<PaymentMethodUtils> paymentMethodUtilsProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<TransferUtils> transferUtilsProvider;

    public DepositConfirmationPresenter_Factory(Provider<Application> applicationProvider, Provider<DepositFiatConfirmationRouter> depositFiatConfirmationRouterProvider, Provider<DepositFiatConfirmationScreen> depositFiatConfirmationScreenProvider, Provider<LoginManager> loginManagerProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.applicationProvider = applicationProvider;
        this.depositFiatConfirmationRouterProvider = depositFiatConfirmationRouterProvider;
        this.depositFiatConfirmationScreenProvider = depositFiatConfirmationScreenProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.paymentMethodUtilsProvider = paymentMethodUtilsProvider;
        this.transferUtilsProvider = transferUtilsProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public DepositConfirmationPresenter get() {
        return provideInstance(this.applicationProvider, this.depositFiatConfirmationRouterProvider, this.depositFiatConfirmationScreenProvider, this.loginManagerProvider, this.paymentMethodUtilsProvider, this.transferUtilsProvider, this.mixpanelTrackingProvider, this.snackBarWrapperProvider, this.mainSchedulerProvider);
    }

    public static DepositConfirmationPresenter provideInstance(Provider<Application> applicationProvider, Provider<DepositFiatConfirmationRouter> depositFiatConfirmationRouterProvider, Provider<DepositFiatConfirmationScreen> depositFiatConfirmationScreenProvider, Provider<LoginManager> loginManagerProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new DepositConfirmationPresenter((Application) applicationProvider.get(), (DepositFiatConfirmationRouter) depositFiatConfirmationRouterProvider.get(), (DepositFiatConfirmationScreen) depositFiatConfirmationScreenProvider.get(), (LoginManager) loginManagerProvider.get(), (PaymentMethodUtils) paymentMethodUtilsProvider.get(), (TransferUtils) transferUtilsProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static DepositConfirmationPresenter_Factory create(Provider<Application> applicationProvider, Provider<DepositFiatConfirmationRouter> depositFiatConfirmationRouterProvider, Provider<DepositFiatConfirmationScreen> depositFiatConfirmationScreenProvider, Provider<LoginManager> loginManagerProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new DepositConfirmationPresenter_Factory(applicationProvider, depositFiatConfirmationRouterProvider, depositFiatConfirmationScreenProvider, loginManagerProvider, paymentMethodUtilsProvider, transferUtilsProvider, mixpanelTrackingProvider, snackBarWrapperProvider, mainSchedulerProvider);
    }

    public static DepositConfirmationPresenter newDepositConfirmationPresenter(Application application, DepositFiatConfirmationRouter depositFiatConfirmationRouter, Object depositFiatConfirmationScreen, LoginManager loginManager, PaymentMethodUtils paymentMethodUtils, TransferUtils transferUtils, MixpanelTracking mixpanelTracking, SnackBarWrapper snackBarWrapper, Scheduler mainScheduler) {
        return new DepositConfirmationPresenter(application, depositFiatConfirmationRouter, (DepositFiatConfirmationScreen) depositFiatConfirmationScreen, loginManager, paymentMethodUtils, transferUtils, mixpanelTracking, snackBarWrapper, mainScheduler);
    }
}
