package com.coinbase.android.deposits.fiat;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.coinbase.android.ui.ActionBarController_MembersInjector;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.ControllerLifeCycleFactory;
import com.coinbase.android.ui.ControllerTransitionContainer;
import com.coinbase.android.ui.StatusBarUpdater;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;
import rx.functions.Func0;

public final class DepositFiatController_MembersInjector implements MembersInjector<DepositFiatController> {
    private final Provider<AppCompatActivity> mAppCompatActivityAndMCallingActivityProvider;
    private final Provider<BackNavigationConnector> mBackNavigationConnectorProvider;
    private final Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider;
    private final Provider<DepositFiatPresenter> mDepositFiatPresenterProvider;
    private final Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<Func0<ViewGroup>> mModalViewProvider;
    private final Provider<StatusBarUpdater> mStatusBarUpdaterProvider;

    public DepositFiatController_MembersInjector(Provider<AppCompatActivity> mAppCompatActivityAndMCallingActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<DepositFiatPresenter> mDepositFiatPresenterProvider) {
        this.mAppCompatActivityAndMCallingActivityProvider = mAppCompatActivityAndMCallingActivityProvider;
        this.mBackNavigationConnectorProvider = mBackNavigationConnectorProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mStatusBarUpdaterProvider = mStatusBarUpdaterProvider;
        this.mChildTransitionBehaviorProvider = mChildTransitionBehaviorProvider;
        this.mLifeCycleFactoryProvider = mLifeCycleFactoryProvider;
        this.mModalViewProvider = mModalViewProvider;
        this.mDepositFiatPresenterProvider = mDepositFiatPresenterProvider;
    }

    public static MembersInjector<DepositFiatController> create(Provider<AppCompatActivity> mAppCompatActivityAndMCallingActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<DepositFiatPresenter> mDepositFiatPresenterProvider) {
        return new DepositFiatController_MembersInjector(mAppCompatActivityAndMCallingActivityProvider, mBackNavigationConnectorProvider, mMainSchedulerProvider, mStatusBarUpdaterProvider, mChildTransitionBehaviorProvider, mLifeCycleFactoryProvider, mModalViewProvider, mDepositFiatPresenterProvider);
    }

    public void injectMembers(DepositFiatController instance) {
        ActionBarController_MembersInjector.injectMAppCompatActivity(instance, (AppCompatActivity) this.mAppCompatActivityAndMCallingActivityProvider.get());
        ActionBarController_MembersInjector.injectMBackNavigationConnector(instance, (BackNavigationConnector) this.mBackNavigationConnectorProvider.get());
        ActionBarController_MembersInjector.injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        ActionBarController_MembersInjector.injectMStatusBarUpdater(instance, (StatusBarUpdater) this.mStatusBarUpdaterProvider.get());
        ActionBarController_MembersInjector.injectMChildTransitionBehavior(instance, (ControllerTransitionContainer) this.mChildTransitionBehaviorProvider.get());
        ActionBarController_MembersInjector.injectMLifeCycleFactory(instance, (ControllerLifeCycleFactory) this.mLifeCycleFactoryProvider.get());
        ActionBarController_MembersInjector.injectMModalView(instance, (Func0) this.mModalViewProvider.get());
        injectMDepositFiatPresenter(instance, (DepositFiatPresenter) this.mDepositFiatPresenterProvider.get());
        injectMCallingActivity(instance, (AppCompatActivity) this.mAppCompatActivityAndMCallingActivityProvider.get());
    }

    public static void injectMDepositFiatPresenter(DepositFiatController instance, DepositFiatPresenter mDepositFiatPresenter) {
        instance.mDepositFiatPresenter = mDepositFiatPresenter;
    }

    public static void injectMCallingActivity(DepositFiatController instance, AppCompatActivity mCallingActivity) {
        instance.mCallingActivity = mCallingActivity;
    }
}
