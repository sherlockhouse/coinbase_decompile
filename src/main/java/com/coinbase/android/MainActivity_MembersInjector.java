package com.coinbase.android;

import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.pin.PINManager;
import com.coinbase.android.signin.StateDisclosureFinishedConnector;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.BottomNavigationConnector;
import com.coinbase.android.ui.RefreshRequestedConnector;
import com.coinbase.api.LoginManager;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;

public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
    private final Provider<BackNavigationConnector> mBackNavigationConnectorProvider;
    private final Provider<Scheduler> mBackgroundSchedulerProvider;
    private final Provider<BottomNavigationConnector> mBottomNavigationConnectorProvider;
    private final Provider<DatabaseManager> mDbManagerProvider;
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<PINManager> mPinManagerProvider;
    private final Provider<MainPresenter> mPresenterProvider;
    private final Provider<RefreshRequestedConnector> mRefreshRequestedConnectorProvider;
    private final Provider<ScreenProtector> mScreenProtectorProvider;
    private final Provider<SplitTesting> mSplitTestingProvider;
    private final Provider<StateDisclosureFinishedConnector> mStateDisclosureFinishedConnectorProvider;

    public MainActivity_MembersInjector(Provider<LoginManager> mLoginManagerProvider, Provider<PINManager> mPinManagerProvider, Provider<ScreenProtector> mScreenProtectorProvider, Provider<MainPresenter> mPresenterProvider, Provider<RefreshRequestedConnector> mRefreshRequestedConnectorProvider, Provider<StateDisclosureFinishedConnector> mStateDisclosureFinishedConnectorProvider, Provider<BottomNavigationConnector> mBottomNavigationConnectorProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<DatabaseManager> mDbManagerProvider, Provider<SplitTesting> mSplitTestingProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<Scheduler> mBackgroundSchedulerProvider) {
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mPinManagerProvider = mPinManagerProvider;
        this.mScreenProtectorProvider = mScreenProtectorProvider;
        this.mPresenterProvider = mPresenterProvider;
        this.mRefreshRequestedConnectorProvider = mRefreshRequestedConnectorProvider;
        this.mStateDisclosureFinishedConnectorProvider = mStateDisclosureFinishedConnectorProvider;
        this.mBottomNavigationConnectorProvider = mBottomNavigationConnectorProvider;
        this.mBackNavigationConnectorProvider = mBackNavigationConnectorProvider;
        this.mDbManagerProvider = mDbManagerProvider;
        this.mSplitTestingProvider = mSplitTestingProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mBackgroundSchedulerProvider = mBackgroundSchedulerProvider;
    }

    public static MembersInjector<MainActivity> create(Provider<LoginManager> mLoginManagerProvider, Provider<PINManager> mPinManagerProvider, Provider<ScreenProtector> mScreenProtectorProvider, Provider<MainPresenter> mPresenterProvider, Provider<RefreshRequestedConnector> mRefreshRequestedConnectorProvider, Provider<StateDisclosureFinishedConnector> mStateDisclosureFinishedConnectorProvider, Provider<BottomNavigationConnector> mBottomNavigationConnectorProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<DatabaseManager> mDbManagerProvider, Provider<SplitTesting> mSplitTestingProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<Scheduler> mBackgroundSchedulerProvider) {
        return new MainActivity_MembersInjector(mLoginManagerProvider, mPinManagerProvider, mScreenProtectorProvider, mPresenterProvider, mRefreshRequestedConnectorProvider, mStateDisclosureFinishedConnectorProvider, mBottomNavigationConnectorProvider, mBackNavigationConnectorProvider, mDbManagerProvider, mSplitTestingProvider, mMainSchedulerProvider, mBackgroundSchedulerProvider);
    }

    public void injectMembers(MainActivity instance) {
        CoinbaseActivity_MembersInjector.injectMLoginManager(instance, (LoginManager) this.mLoginManagerProvider.get());
        CoinbaseActivity_MembersInjector.injectMPinManager(instance, (PINManager) this.mPinManagerProvider.get());
        CoinbaseActivity_MembersInjector.injectMScreenProtector(instance, (ScreenProtector) this.mScreenProtectorProvider.get());
        injectMPresenter(instance, (MainPresenter) this.mPresenterProvider.get());
        injectMRefreshRequestedConnector(instance, (RefreshRequestedConnector) this.mRefreshRequestedConnectorProvider.get());
        injectMStateDisclosureFinishedConnector(instance, (StateDisclosureFinishedConnector) this.mStateDisclosureFinishedConnectorProvider.get());
        injectMBottomNavigationConnector(instance, (BottomNavigationConnector) this.mBottomNavigationConnectorProvider.get());
        injectMBackNavigationConnector(instance, (BackNavigationConnector) this.mBackNavigationConnectorProvider.get());
        injectMDbManager(instance, (DatabaseManager) this.mDbManagerProvider.get());
        injectMSplitTesting(instance, (SplitTesting) this.mSplitTestingProvider.get());
        injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        injectMBackgroundScheduler(instance, (Scheduler) this.mBackgroundSchedulerProvider.get());
    }

    public static void injectMPresenter(MainActivity instance, MainPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }

    public static void injectMRefreshRequestedConnector(MainActivity instance, RefreshRequestedConnector mRefreshRequestedConnector) {
        instance.mRefreshRequestedConnector = mRefreshRequestedConnector;
    }

    public static void injectMStateDisclosureFinishedConnector(MainActivity instance, StateDisclosureFinishedConnector mStateDisclosureFinishedConnector) {
        instance.mStateDisclosureFinishedConnector = mStateDisclosureFinishedConnector;
    }

    public static void injectMBottomNavigationConnector(MainActivity instance, BottomNavigationConnector mBottomNavigationConnector) {
        instance.mBottomNavigationConnector = mBottomNavigationConnector;
    }

    public static void injectMBackNavigationConnector(MainActivity instance, BackNavigationConnector mBackNavigationConnector) {
        instance.mBackNavigationConnector = mBackNavigationConnector;
    }

    public static void injectMDbManager(MainActivity instance, DatabaseManager mDbManager) {
        instance.mDbManager = mDbManager;
    }

    public static void injectMSplitTesting(MainActivity instance, SplitTesting mSplitTesting) {
        instance.mSplitTesting = mSplitTesting;
    }

    public static void injectMMainScheduler(MainActivity instance, Scheduler mMainScheduler) {
        instance.mMainScheduler = mMainScheduler;
    }

    public static void injectMBackgroundScheduler(MainActivity instance, Scheduler mBackgroundScheduler) {
        instance.mBackgroundScheduler = mBackgroundScheduler;
    }
}
