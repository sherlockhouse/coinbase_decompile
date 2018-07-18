package com.coinbase.android.deposits;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.coinbase.android.buysell.BuySellMadeConnector;
import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.paymentmethods.BankAccountsUpdatedConnector;
import com.coinbase.android.paymentmethods.GetPaymentMethodsTaskRx;
import com.coinbase.android.paymentmethods.PaymentMethodsUpdatedConnector;
import com.coinbase.android.phone.PhoneNumbersUpdatedConnector;
import com.coinbase.android.quickstart.QuickstartManager;
import com.coinbase.android.ui.ActionBarController_MembersInjector;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.ControllerLifeCycleFactory;
import com.coinbase.android.ui.ControllerTransitionContainer;
import com.coinbase.android.ui.StatusBarUpdater;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.android.wbl.WithdrawalBasedLimitsApiErrorHandler;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;
import rx.functions.Func0;

public final class FiatTransactionsController_MembersInjector implements MembersInjector<FiatTransactionsController> {
    private final Provider<DatabaseManager> dbManagerProvider;
    private final Provider<AppCompatActivity> mAppCompatActivityProvider;
    private final Provider<BackNavigationConnector> mBackNavigationConnectorProvider;
    private final Provider<BankAccountsUpdatedConnector> mBankAccountsUpdatedConnectorProvider;
    private final Provider<BuySellMadeConnector> mBuySellMadeConnectorProvider;
    private final Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider;
    private final Provider<FiatTransactionsConnector> mFiatTransactionsConnectorProvider;
    private final Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider;
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<MixpanelTracking> mMixpanelTrackingProvider;
    private final Provider<Func0<ViewGroup>> mModalViewProvider;
    private final Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider;
    private final Provider<GetPaymentMethodsTaskRx> mPaymentMethodsTaskProvider;
    private final Provider<PaymentMethodsUpdatedConnector> mPaymentMethodsUpdateConnectorProvider;
    private final Provider<PhoneNumbersUpdatedConnector> mPhoneNumbersUpdatedConnectorProvider;
    private final Provider<QuickstartManager> mQuickstartManagerProvider;
    private final Provider<StatusBarUpdater> mStatusBarUpdaterProvider;
    private final Provider<WithdrawalBasedLimitsApiErrorHandler> mWithdrawalBasedLimitsApiErrorHandlerProvider;

    public FiatTransactionsController_MembersInjector(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<DatabaseManager> dbManagerProvider, Provider<LoginManager> mLoginManagerProvider, Provider<QuickstartManager> mQuickstartManagerProvider, Provider<GetPaymentMethodsTaskRx> mPaymentMethodsTaskProvider, Provider<BuySellMadeConnector> mBuySellMadeConnectorProvider, Provider<BankAccountsUpdatedConnector> mBankAccountsUpdatedConnectorProvider, Provider<PaymentMethodsUpdatedConnector> mPaymentMethodsUpdateConnectorProvider, Provider<PhoneNumbersUpdatedConnector> mPhoneNumbersUpdatedConnectorProvider, Provider<FiatTransactionsConnector> mFiatTransactionsConnectorProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider, Provider<WithdrawalBasedLimitsApiErrorHandler> mWithdrawalBasedLimitsApiErrorHandlerProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider) {
        this.mAppCompatActivityProvider = mAppCompatActivityProvider;
        this.mBackNavigationConnectorProvider = mBackNavigationConnectorProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mStatusBarUpdaterProvider = mStatusBarUpdaterProvider;
        this.mChildTransitionBehaviorProvider = mChildTransitionBehaviorProvider;
        this.mLifeCycleFactoryProvider = mLifeCycleFactoryProvider;
        this.mModalViewProvider = mModalViewProvider;
        this.dbManagerProvider = dbManagerProvider;
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mQuickstartManagerProvider = mQuickstartManagerProvider;
        this.mPaymentMethodsTaskProvider = mPaymentMethodsTaskProvider;
        this.mBuySellMadeConnectorProvider = mBuySellMadeConnectorProvider;
        this.mBankAccountsUpdatedConnectorProvider = mBankAccountsUpdatedConnectorProvider;
        this.mPaymentMethodsUpdateConnectorProvider = mPaymentMethodsUpdateConnectorProvider;
        this.mPhoneNumbersUpdatedConnectorProvider = mPhoneNumbersUpdatedConnectorProvider;
        this.mFiatTransactionsConnectorProvider = mFiatTransactionsConnectorProvider;
        this.mMoneyFormatterUtilProvider = mMoneyFormatterUtilProvider;
        this.mWithdrawalBasedLimitsApiErrorHandlerProvider = mWithdrawalBasedLimitsApiErrorHandlerProvider;
        this.mMixpanelTrackingProvider = mMixpanelTrackingProvider;
    }

    public static MembersInjector<FiatTransactionsController> create(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<DatabaseManager> dbManagerProvider, Provider<LoginManager> mLoginManagerProvider, Provider<QuickstartManager> mQuickstartManagerProvider, Provider<GetPaymentMethodsTaskRx> mPaymentMethodsTaskProvider, Provider<BuySellMadeConnector> mBuySellMadeConnectorProvider, Provider<BankAccountsUpdatedConnector> mBankAccountsUpdatedConnectorProvider, Provider<PaymentMethodsUpdatedConnector> mPaymentMethodsUpdateConnectorProvider, Provider<PhoneNumbersUpdatedConnector> mPhoneNumbersUpdatedConnectorProvider, Provider<FiatTransactionsConnector> mFiatTransactionsConnectorProvider, Provider<MoneyFormatterUtil> mMoneyFormatterUtilProvider, Provider<WithdrawalBasedLimitsApiErrorHandler> mWithdrawalBasedLimitsApiErrorHandlerProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider) {
        return new FiatTransactionsController_MembersInjector(mAppCompatActivityProvider, mBackNavigationConnectorProvider, mMainSchedulerProvider, mStatusBarUpdaterProvider, mChildTransitionBehaviorProvider, mLifeCycleFactoryProvider, mModalViewProvider, dbManagerProvider, mLoginManagerProvider, mQuickstartManagerProvider, mPaymentMethodsTaskProvider, mBuySellMadeConnectorProvider, mBankAccountsUpdatedConnectorProvider, mPaymentMethodsUpdateConnectorProvider, mPhoneNumbersUpdatedConnectorProvider, mFiatTransactionsConnectorProvider, mMoneyFormatterUtilProvider, mWithdrawalBasedLimitsApiErrorHandlerProvider, mMixpanelTrackingProvider);
    }

    public void injectMembers(FiatTransactionsController instance) {
        ActionBarController_MembersInjector.injectMAppCompatActivity(instance, (AppCompatActivity) this.mAppCompatActivityProvider.get());
        ActionBarController_MembersInjector.injectMBackNavigationConnector(instance, (BackNavigationConnector) this.mBackNavigationConnectorProvider.get());
        ActionBarController_MembersInjector.injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        ActionBarController_MembersInjector.injectMStatusBarUpdater(instance, (StatusBarUpdater) this.mStatusBarUpdaterProvider.get());
        ActionBarController_MembersInjector.injectMChildTransitionBehavior(instance, (ControllerTransitionContainer) this.mChildTransitionBehaviorProvider.get());
        ActionBarController_MembersInjector.injectMLifeCycleFactory(instance, (ControllerLifeCycleFactory) this.mLifeCycleFactoryProvider.get());
        ActionBarController_MembersInjector.injectMModalView(instance, (Func0) this.mModalViewProvider.get());
        injectDbManager(instance, (DatabaseManager) this.dbManagerProvider.get());
        injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        injectMQuickstartManager(instance, (QuickstartManager) this.mQuickstartManagerProvider.get());
        injectMPaymentMethodsTask(instance, (GetPaymentMethodsTaskRx) this.mPaymentMethodsTaskProvider.get());
        injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        injectMBuySellMadeConnector(instance, (BuySellMadeConnector) this.mBuySellMadeConnectorProvider.get());
        injectMBankAccountsUpdatedConnector(instance, (BankAccountsUpdatedConnector) this.mBankAccountsUpdatedConnectorProvider.get());
        injectMPaymentMethodsUpdateConnector(instance, (PaymentMethodsUpdatedConnector) this.mPaymentMethodsUpdateConnectorProvider.get());
        injectMPhoneNumbersUpdatedConnector(instance, (PhoneNumbersUpdatedConnector) this.mPhoneNumbersUpdatedConnectorProvider.get());
        injectMFiatTransactionsConnector(instance, (FiatTransactionsConnector) this.mFiatTransactionsConnectorProvider.get());
        injectMAppCompatActivity(instance, (AppCompatActivity) this.mAppCompatActivityProvider.get());
        injectMMoneyFormatterUtil(instance, (MoneyFormatterUtil) this.mMoneyFormatterUtilProvider.get());
        injectMWithdrawalBasedLimitsApiErrorHandler(instance, (WithdrawalBasedLimitsApiErrorHandler) this.mWithdrawalBasedLimitsApiErrorHandlerProvider.get());
        injectMMixpanelTracking(instance, (MixpanelTracking) this.mMixpanelTrackingProvider.get());
    }

    public static void injectDbManager(FiatTransactionsController instance, DatabaseManager dbManager) {
        instance.dbManager = dbManager;
    }

    public static void injectMLoginManager(FiatTransactionsController instance, LoginManager mLoginManager) {
        instance.mLoginManager = mLoginManager;
    }

    public static void injectMQuickstartManager(FiatTransactionsController instance, QuickstartManager mQuickstartManager) {
        instance.mQuickstartManager = mQuickstartManager;
    }

    public static void injectMPaymentMethodsTask(FiatTransactionsController instance, GetPaymentMethodsTaskRx mPaymentMethodsTask) {
        instance.mPaymentMethodsTask = mPaymentMethodsTask;
    }

    public static void injectMMainScheduler(FiatTransactionsController instance, Scheduler mMainScheduler) {
        instance.mMainScheduler = mMainScheduler;
    }

    public static void injectMBuySellMadeConnector(FiatTransactionsController instance, BuySellMadeConnector mBuySellMadeConnector) {
        instance.mBuySellMadeConnector = mBuySellMadeConnector;
    }

    public static void injectMBankAccountsUpdatedConnector(FiatTransactionsController instance, BankAccountsUpdatedConnector mBankAccountsUpdatedConnector) {
        instance.mBankAccountsUpdatedConnector = mBankAccountsUpdatedConnector;
    }

    public static void injectMPaymentMethodsUpdateConnector(FiatTransactionsController instance, PaymentMethodsUpdatedConnector mPaymentMethodsUpdateConnector) {
        instance.mPaymentMethodsUpdateConnector = mPaymentMethodsUpdateConnector;
    }

    public static void injectMPhoneNumbersUpdatedConnector(FiatTransactionsController instance, PhoneNumbersUpdatedConnector mPhoneNumbersUpdatedConnector) {
        instance.mPhoneNumbersUpdatedConnector = mPhoneNumbersUpdatedConnector;
    }

    public static void injectMFiatTransactionsConnector(FiatTransactionsController instance, FiatTransactionsConnector mFiatTransactionsConnector) {
        instance.mFiatTransactionsConnector = mFiatTransactionsConnector;
    }

    public static void injectMAppCompatActivity(FiatTransactionsController instance, AppCompatActivity mAppCompatActivity) {
        instance.mAppCompatActivity = mAppCompatActivity;
    }

    public static void injectMMoneyFormatterUtil(FiatTransactionsController instance, MoneyFormatterUtil mMoneyFormatterUtil) {
        instance.mMoneyFormatterUtil = mMoneyFormatterUtil;
    }

    public static void injectMWithdrawalBasedLimitsApiErrorHandler(FiatTransactionsController instance, WithdrawalBasedLimitsApiErrorHandler mWithdrawalBasedLimitsApiErrorHandler) {
        instance.mWithdrawalBasedLimitsApiErrorHandler = mWithdrawalBasedLimitsApiErrorHandler;
    }

    public static void injectMMixpanelTracking(FiatTransactionsController instance, MixpanelTracking mMixpanelTracking) {
        instance.mMixpanelTracking = mMixpanelTracking;
    }
}
