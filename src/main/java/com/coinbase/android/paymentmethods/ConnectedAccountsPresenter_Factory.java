package com.coinbase.android.paymentmethods;

import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class ConnectedAccountsPresenter_Factory implements Factory<ConnectedAccountsPresenter> {
    private final Provider<AddPaymentMethodConnector> addPaymentMethodConnectorProvider;
    private final Provider<AppCompatActivity> appCompatActivityProvider;
    private final Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskRxProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<MixpanelTracking> mixpanelTrackingProvider;
    private final Provider<Integer> paymentMethodDeletedResIdProvider;
    private final Provider<PaymentMethodUtils> paymentMethodUtilsProvider;
    private final Provider<PaymentMethodsFetchedConnector> paymentMethodsFetchedConnectorProvider;
    private final Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider;
    private final Provider<ConnectedAccountsScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<VerifyPaymentMethodConnector> verifyPaymentMethodConnectorProvider;

    public ConnectedAccountsPresenter_Factory(Provider<AppCompatActivity> appCompatActivityProvider, Provider<ConnectedAccountsScreen> screenProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskRxProvider, Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider, Provider<AddPaymentMethodConnector> addPaymentMethodConnectorProvider, Provider<VerifyPaymentMethodConnector> verifyPaymentMethodConnectorProvider, Provider<PaymentMethodsFetchedConnector> paymentMethodsFetchedConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Integer> paymentMethodDeletedResIdProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        this.appCompatActivityProvider = appCompatActivityProvider;
        this.screenProvider = screenProvider;
        this.getPaymentMethodsTaskRxProvider = getPaymentMethodsTaskRxProvider;
        this.paymentMethodsUpdatedConnectorProvider = paymentMethodsUpdatedConnectorProvider;
        this.addPaymentMethodConnectorProvider = addPaymentMethodConnectorProvider;
        this.verifyPaymentMethodConnectorProvider = verifyPaymentMethodConnectorProvider;
        this.paymentMethodsFetchedConnectorProvider = paymentMethodsFetchedConnectorProvider;
        this.paymentMethodUtilsProvider = paymentMethodUtilsProvider;
        this.mixpanelTrackingProvider = mixpanelTrackingProvider;
        this.paymentMethodDeletedResIdProvider = paymentMethodDeletedResIdProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
    }

    public ConnectedAccountsPresenter get() {
        return provideInstance(this.appCompatActivityProvider, this.screenProvider, this.getPaymentMethodsTaskRxProvider, this.paymentMethodsUpdatedConnectorProvider, this.addPaymentMethodConnectorProvider, this.verifyPaymentMethodConnectorProvider, this.paymentMethodsFetchedConnectorProvider, this.paymentMethodUtilsProvider, this.mixpanelTrackingProvider, this.paymentMethodDeletedResIdProvider, this.snackBarWrapperProvider, this.mainSchedulerProvider);
    }

    public static ConnectedAccountsPresenter provideInstance(Provider<AppCompatActivity> appCompatActivityProvider, Provider<ConnectedAccountsScreen> screenProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskRxProvider, Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider, Provider<AddPaymentMethodConnector> addPaymentMethodConnectorProvider, Provider<VerifyPaymentMethodConnector> verifyPaymentMethodConnectorProvider, Provider<PaymentMethodsFetchedConnector> paymentMethodsFetchedConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Integer> paymentMethodDeletedResIdProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new ConnectedAccountsPresenter((AppCompatActivity) appCompatActivityProvider.get(), (ConnectedAccountsScreen) screenProvider.get(), (GetPaymentMethodsTaskRx) getPaymentMethodsTaskRxProvider.get(), (PaymentMethodsUpdatedConnector) paymentMethodsUpdatedConnectorProvider.get(), (AddPaymentMethodConnector) addPaymentMethodConnectorProvider.get(), (VerifyPaymentMethodConnector) verifyPaymentMethodConnectorProvider.get(), (PaymentMethodsFetchedConnector) paymentMethodsFetchedConnectorProvider.get(), (PaymentMethodUtils) paymentMethodUtilsProvider.get(), (MixpanelTracking) mixpanelTrackingProvider.get(), ((Integer) paymentMethodDeletedResIdProvider.get()).intValue(), (SnackBarWrapper) snackBarWrapperProvider.get(), (Scheduler) mainSchedulerProvider.get());
    }

    public static ConnectedAccountsPresenter_Factory create(Provider<AppCompatActivity> appCompatActivityProvider, Provider<ConnectedAccountsScreen> screenProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskRxProvider, Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider, Provider<AddPaymentMethodConnector> addPaymentMethodConnectorProvider, Provider<VerifyPaymentMethodConnector> verifyPaymentMethodConnectorProvider, Provider<PaymentMethodsFetchedConnector> paymentMethodsFetchedConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<MixpanelTracking> mixpanelTrackingProvider, Provider<Integer> paymentMethodDeletedResIdProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Scheduler> mainSchedulerProvider) {
        return new ConnectedAccountsPresenter_Factory(appCompatActivityProvider, screenProvider, getPaymentMethodsTaskRxProvider, paymentMethodsUpdatedConnectorProvider, addPaymentMethodConnectorProvider, verifyPaymentMethodConnectorProvider, paymentMethodsFetchedConnectorProvider, paymentMethodUtilsProvider, mixpanelTrackingProvider, paymentMethodDeletedResIdProvider, snackBarWrapperProvider, mainSchedulerProvider);
    }

    public static ConnectedAccountsPresenter newConnectedAccountsPresenter(AppCompatActivity appCompatActivity, Object screen, GetPaymentMethodsTaskRx getPaymentMethodsTaskRx, PaymentMethodsUpdatedConnector paymentMethodsUpdatedConnector, AddPaymentMethodConnector addPaymentMethodConnector, VerifyPaymentMethodConnector verifyPaymentMethodConnector, PaymentMethodsFetchedConnector paymentMethodsFetchedConnector, PaymentMethodUtils paymentMethodUtils, MixpanelTracking mixpanelTracking, int paymentMethodDeletedResId, SnackBarWrapper snackBarWrapper, Scheduler mainScheduler) {
        return new ConnectedAccountsPresenter(appCompatActivity, (ConnectedAccountsScreen) screen, getPaymentMethodsTaskRx, paymentMethodsUpdatedConnector, addPaymentMethodConnector, verifyPaymentMethodConnector, paymentMethodsFetchedConnector, paymentMethodUtils, mixpanelTracking, paymentMethodDeletedResId, snackBarWrapper, mainScheduler);
    }
}
