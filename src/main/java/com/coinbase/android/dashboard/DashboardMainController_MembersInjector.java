package com.coinbase.android.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.coinbase.android.ui.ActionBarController_MembersInjector;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.BottomNavigationConnector;
import com.coinbase.android.ui.ControllerLifeCycleFactory;
import com.coinbase.android.ui.ControllerTransitionContainer;
import com.coinbase.android.ui.StatusBarUpdater;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;
import rx.functions.Func0;

public final class DashboardMainController_MembersInjector implements MembersInjector<DashboardMainController> {
    private final Provider<AppCompatActivity> mAppCompatActivityProvider;
    private final Provider<BackNavigationConnector> mBackNavigationConnectorProvider;
    private final Provider<BottomNavigationConnector> mBottomNavigationConnectorProvider;
    private final Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider;
    private final Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<MixpanelTracking> mMixpanelTrackingProvider;
    private final Provider<Func0<ViewGroup>> mModalViewProvider;
    private final Provider<DashboardMainPresenter> mPresenterProvider;
    private final Provider<StatusBarUpdater> mStatusBarUpdaterProvider;

    public DashboardMainController_MembersInjector(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<DashboardMainPresenter> mPresenterProvider, Provider<BottomNavigationConnector> mBottomNavigationConnectorProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider) {
        this.mAppCompatActivityProvider = mAppCompatActivityProvider;
        this.mBackNavigationConnectorProvider = mBackNavigationConnectorProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mStatusBarUpdaterProvider = mStatusBarUpdaterProvider;
        this.mChildTransitionBehaviorProvider = mChildTransitionBehaviorProvider;
        this.mLifeCycleFactoryProvider = mLifeCycleFactoryProvider;
        this.mModalViewProvider = mModalViewProvider;
        this.mPresenterProvider = mPresenterProvider;
        this.mBottomNavigationConnectorProvider = mBottomNavigationConnectorProvider;
        this.mMixpanelTrackingProvider = mMixpanelTrackingProvider;
    }

    public static MembersInjector<DashboardMainController> create(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<DashboardMainPresenter> mPresenterProvider, Provider<BottomNavigationConnector> mBottomNavigationConnectorProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider) {
        return new DashboardMainController_MembersInjector(mAppCompatActivityProvider, mBackNavigationConnectorProvider, mMainSchedulerProvider, mStatusBarUpdaterProvider, mChildTransitionBehaviorProvider, mLifeCycleFactoryProvider, mModalViewProvider, mPresenterProvider, mBottomNavigationConnectorProvider, mMixpanelTrackingProvider);
    }

    public void injectMembers(DashboardMainController instance) {
        ActionBarController_MembersInjector.injectMAppCompatActivity(instance, (AppCompatActivity) this.mAppCompatActivityProvider.get());
        ActionBarController_MembersInjector.injectMBackNavigationConnector(instance, (BackNavigationConnector) this.mBackNavigationConnectorProvider.get());
        ActionBarController_MembersInjector.injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        ActionBarController_MembersInjector.injectMStatusBarUpdater(instance, (StatusBarUpdater) this.mStatusBarUpdaterProvider.get());
        ActionBarController_MembersInjector.injectMChildTransitionBehavior(instance, (ControllerTransitionContainer) this.mChildTransitionBehaviorProvider.get());
        ActionBarController_MembersInjector.injectMLifeCycleFactory(instance, (ControllerLifeCycleFactory) this.mLifeCycleFactoryProvider.get());
        ActionBarController_MembersInjector.injectMModalView(instance, (Func0) this.mModalViewProvider.get());
        injectMPresenter(instance, (DashboardMainPresenter) this.mPresenterProvider.get());
        injectMBottomNavigationConnector(instance, (BottomNavigationConnector) this.mBottomNavigationConnectorProvider.get());
        injectMMixpanelTracking(instance, (MixpanelTracking) this.mMixpanelTrackingProvider.get());
    }

    public static void injectMPresenter(DashboardMainController instance, DashboardMainPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }

    public static void injectMBottomNavigationConnector(DashboardMainController instance, BottomNavigationConnector mBottomNavigationConnector) {
        instance.mBottomNavigationConnector = mBottomNavigationConnector;
    }

    public static void injectMMixpanelTracking(DashboardMainController instance, MixpanelTracking mMixpanelTracking) {
        instance.mMixpanelTracking = mMixpanelTracking;
    }
}
