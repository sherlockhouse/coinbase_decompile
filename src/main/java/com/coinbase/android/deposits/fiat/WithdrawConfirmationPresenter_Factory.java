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

public final class WithdrawConfirmationPresenter_Factory implements Factory<WithdrawConfirmationPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<PaymentMethodUtils> paymentMethodUtilsProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<TransferUtils> transferUtilsProvider;
    private final Provider<WithdrawFiatConfirmationRouter> withdrawFiatConfirmationRouterProvider;
    private final Provider<WithdrawFiatConfirmationScreen> withdrawFiatConfirmationScreenProvider;

    public WithdrawConfirmationPresenter_Factory(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<WithdrawFiatConfirmationRouter> withdrawFiatConfirmationRouterProvider, Provider<WithdrawFiatConfirmationScreen> withdrawFiatConfirmationScreenProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.applicationProvider = applicationProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.paymentMethodUtilsProvider = paymentMethodUtilsProvider;
        this.transferUtilsProvider = transferUtilsProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.withdrawFiatConfirmationRouterProvider = withdrawFiatConfirmationRouterProvider;
        this.withdrawFiatConfirmationScreenProvider = withdrawFiatConfirmationScreenProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public WithdrawConfirmationPresenter get() {
        return provideInstance(this.applicationProvider, this.loginManagerProvider, this.paymentMethodUtilsProvider, this.transferUtilsProvider, this.mixpanelTrackingProvider, this.snackBarWrapperProvider, this.withdrawFiatConfirmationRouterProvider, this.withdrawFiatConfirmationScreenProvider, this.mainSchedulerProvider);
    }

    public static WithdrawConfirmationPresenter provideInstance(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<WithdrawFiatConfirmationRouter> withdrawFiatConfirmationRouterProvider, Provider<WithdrawFiatConfirmationScreen> withdrawFiatConfirmationScreenProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new WithdrawConfirmationPresenter((Application) applicationProvider.get(), (LoginManager) loginManagerProvider.get(), (PaymentMethodUtils) paymentMethodUtilsProvider.get(), (TransferUtils) transferUtilsProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), (WithdrawFiatConfirmationRouter) withdrawFiatConfirmationRouterProvider.get(), (WithdrawFiatConfirmationScreen) withdrawFiatConfirmationScreenProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static WithdrawConfirmationPresenter_Factory create(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<TransferUtils> transferUtilsProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<WithdrawFiatConfirmationRouter> withdrawFiatConfirmationRouterProvider, Provider<WithdrawFiatConfirmationScreen> withdrawFiatConfirmationScreenProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new WithdrawConfirmationPresenter_Factory(applicationProvider, loginManagerProvider, paymentMethodUtilsProvider, transferUtilsProvider, mixpanelTrackingProvider, snackBarWrapperProvider, withdrawFiatConfirmationRouterProvider, withdrawFiatConfirmationScreenProvider, mainSchedulerProvider);
    }

    public static WithdrawConfirmationPresenter newWithdrawConfirmationPresenter(Application application, LoginManager loginManager, PaymentMethodUtils paymentMethodUtils, TransferUtils transferUtils, MixpanelTracking mixpanelTracking, SnackBarWrapper snackBarWrapper, WithdrawFiatConfirmationRouter withdrawFiatConfirmationRouter, Object withdrawFiatConfirmationScreen, Scheduler mainScheduler) {
        return new WithdrawConfirmationPresenter(application, loginManager, paymentMethodUtils, transferUtils, mixpanelTracking, snackBarWrapper, withdrawFiatConfirmationRouter, (WithdrawFiatConfirmationScreen) withdrawFiatConfirmationScreen, mainScheduler);
    }
}
