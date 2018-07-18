package com.coinbase.android.paymentmethods;

import com.coinbase.android.featureflag.FeatureFlags;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.ui.SuccessRouter;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class PaymentMethodsPresenter_Factory implements Factory<PaymentMethodsPresenter> {
    private final Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider;
    private final Provider<FeatureFlags> featureFlagsProvider;
    private final Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<Scheduler> mainSchedulerProvider;
    private final Provider<Integer> paymentMethodDeletedResIdProvider;
    private final Provider<PaymentMethodUtils> paymentMethodUtilsProvider;
    private final Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider;
    private final Provider<PaymentMethodsRouter> routerProvider;
    private final Provider<PaymentMethodsScreen> screenProvider;
    private final Provider<SnackBarWrapper> snackBarWrapperProvider;
    private final Provider<SplitTesting> splitTestingProvider;
    private final Provider<SuccessRouter> successRouterProvider;

    public PaymentMethodsPresenter_Factory(Provider<LoginManager> loginManagerProvider, Provider<PaymentMethodsScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Integer> paymentMethodDeletedResIdProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskProvider, Provider<Scheduler> mainSchedulerProvider, Provider<PaymentMethodsRouter> routerProvider, Provider<SuccessRouter> successRouterProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<SplitTesting> splitTestingProvider, Provider<FeatureFlags> featureFlagsProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.snackBarWrapperProvider = snackBarWrapperProvider;
        this.paymentMethodDeletedResIdProvider = paymentMethodDeletedResIdProvider;
        this.getPaymentMethodsTaskProvider = getPaymentMethodsTaskProvider;
        this.mainSchedulerProvider = mainSchedulerProvider;
        this.routerProvider = routerProvider;
        this.successRouterProvider = successRouterProvider;
        this.bankAccountsUpdatedConnectorProvider = bankAccountsUpdatedConnectorProvider;
        this.paymentMethodsUpdatedConnectorProvider = paymentMethodsUpdatedConnectorProvider;
        this.paymentMethodUtilsProvider = paymentMethodUtilsProvider;
        this.splitTestingProvider = splitTestingProvider;
        this.featureFlagsProvider = featureFlagsProvider;
    }

    public PaymentMethodsPresenter get() {
        return provideInstance(this.loginManagerProvider, this.screenProvider, this.snackBarWrapperProvider, this.paymentMethodDeletedResIdProvider, this.getPaymentMethodsTaskProvider, this.mainSchedulerProvider, this.routerProvider, this.successRouterProvider, this.bankAccountsUpdatedConnectorProvider, this.paymentMethodsUpdatedConnectorProvider, this.paymentMethodUtilsProvider, this.splitTestingProvider, this.featureFlagsProvider);
    }

    public static PaymentMethodsPresenter provideInstance(Provider<LoginManager> loginManagerProvider, Provider<PaymentMethodsScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Integer> paymentMethodDeletedResIdProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskProvider, Provider<Scheduler> mainSchedulerProvider, Provider<PaymentMethodsRouter> routerProvider, Provider<SuccessRouter> successRouterProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<SplitTesting> splitTestingProvider, Provider<FeatureFlags> featureFlagsProvider) {
        return new PaymentMethodsPresenter((LoginManager) loginManagerProvider.get(), (PaymentMethodsScreen) screenProvider.get(), (SnackBarWrapper) snackBarWrapperProvider.get(), ((Integer) paymentMethodDeletedResIdProvider.get()).intValue(), (GetPaymentMethodsTaskRx) getPaymentMethodsTaskProvider.get(), (Scheduler) mainSchedulerProvider.get(), (PaymentMethodsRouter) routerProvider.get(), (SuccessRouter) successRouterProvider.get(), (BankAccountsUpdatedConnector) bankAccountsUpdatedConnectorProvider.get(), (PaymentMethodsUpdatedConnector) paymentMethodsUpdatedConnectorProvider.get(), (PaymentMethodUtils) paymentMethodUtilsProvider.get(), (SplitTesting) splitTestingProvider.get(), (FeatureFlags) featureFlagsProvider.get());
    }

    public static PaymentMethodsPresenter_Factory create(Provider<LoginManager> loginManagerProvider, Provider<PaymentMethodsScreen> screenProvider, Provider<SnackBarWrapper> snackBarWrapperProvider, Provider<Integer> paymentMethodDeletedResIdProvider, Provider<GetPaymentMethodsTaskRx> getPaymentMethodsTaskProvider, Provider<Scheduler> mainSchedulerProvider, Provider<PaymentMethodsRouter> routerProvider, Provider<SuccessRouter> successRouterProvider, Provider<BankAccountsUpdatedConnector> bankAccountsUpdatedConnectorProvider, Provider<PaymentMethodsUpdatedConnector> paymentMethodsUpdatedConnectorProvider, Provider<PaymentMethodUtils> paymentMethodUtilsProvider, Provider<SplitTesting> splitTestingProvider, Provider<FeatureFlags> featureFlagsProvider) {
        return new PaymentMethodsPresenter_Factory(loginManagerProvider, screenProvider, snackBarWrapperProvider, paymentMethodDeletedResIdProvider, getPaymentMethodsTaskProvider, mainSchedulerProvider, routerProvider, successRouterProvider, bankAccountsUpdatedConnectorProvider, paymentMethodsUpdatedConnectorProvider, paymentMethodUtilsProvider, splitTestingProvider, featureFlagsProvider);
    }

    public static PaymentMethodsPresenter newPaymentMethodsPresenter(LoginManager loginManager, PaymentMethodsScreen screen, SnackBarWrapper snackBarWrapper, int paymentMethodDeletedResId, GetPaymentMethodsTaskRx getPaymentMethodsTask, Scheduler mainScheduler, PaymentMethodsRouter router, SuccessRouter successRouter, BankAccountsUpdatedConnector bankAccountsUpdatedConnector, PaymentMethodsUpdatedConnector paymentMethodsUpdatedConnector, PaymentMethodUtils paymentMethodUtils, SplitTesting splitTesting, FeatureFlags featureFlags) {
        return new PaymentMethodsPresenter(loginManager, screen, snackBarWrapper, paymentMethodDeletedResId, getPaymentMethodsTask, mainScheduler, router, successRouter, bankAccountsUpdatedConnector, paymentMethodsUpdatedConnector, paymentMethodUtils, splitTesting, featureFlags);
    }
}
