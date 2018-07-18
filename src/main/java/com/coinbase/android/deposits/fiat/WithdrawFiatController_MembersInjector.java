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

public final class WithdrawFiatController_MembersInjector implements MembersInjector<WithdrawFiatController> {
    private final Provider<AppCompatActivity> mAppCompatActivityAndMCallingActivityProvider;
    private final Provider<BackNavigationConnector> mBackNavigationConnectorProvider;
    private final Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider;
    private final Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<Func0<ViewGroup>> mModalViewProvider;
    private final Provider<StatusBarUpdater> mStatusBarUpdaterProvider;
    private final Provider<WithdrawFiatPresenter> mWithdrawFiatPresenterProvider;

    public WithdrawFiatController_MembersInjector(Provider<AppCompatActivity> mAppCompatActivityAndMCallingActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<WithdrawFiatPresenter> mWithdrawFiatPresenterProvider) {
        this.mAppCompatActivityAndMCallingActivityProvider = mAppCompatActivityAndMCallingActivityProvider;
        this.mBackNavigationConnectorProvider = mBackNavigationConnectorProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mStatusBarUpdaterProvider = mStatusBarUpdaterProvider;
        this.mChildTransitionBehaviorProvider = mChildTransitionBehaviorProvider;
        this.mLifeCycleFactoryProvider = mLifeCycleFactoryProvider;
        this.mModalViewProvider = mModalViewProvider;
        this.mWithdrawFiatPresenterProvider = mWithdrawFiatPresenterProvider;
    }

    public static MembersInjector<WithdrawFiatController> create(Provider<AppCompatActivity> mAppCompatActivityAndMCallingActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<WithdrawFiatPresenter> mWithdrawFiatPresenterProvider) {
        return new WithdrawFiatController_MembersInjector(mAppCompatActivityAndMCallingActivityProvider, mBackNavigationConnectorProvider, mMainSchedulerProvider, mStatusBarUpdaterProvider, mChildTransitionBehaviorProvider, mLifeCycleFactoryProvider, mModalViewProvider, mWithdrawFiatPresenterProvider);
    }

    public void injectMembers(WithdrawFiatController instance) {
        ActionBarController_MembersInjector.injectMAppCompatActivity(instance, (AppCompatActivity) this.mAppCompatActivityAndMCallingActivityProvider.get());
        ActionBarController_MembersInjector.injectMBackNavigationConnector(instance, (BackNavigationConnector) this.mBackNavigationConnectorProvider.get());
        ActionBarController_MembersInjector.injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        ActionBarController_MembersInjector.injectMStatusBarUpdater(instance, (StatusBarUpdater) this.mStatusBarUpdaterProvider.get());
        ActionBarController_MembersInjector.injectMChildTransitionBehavior(instance, (ControllerTransitionContainer) this.mChildTransitionBehaviorProvider.get());
        ActionBarController_MembersInjector.injectMLifeCycleFactory(instance, (ControllerLifeCycleFactory) this.mLifeCycleFactoryProvider.get());
        ActionBarController_MembersInjector.injectMModalView(instance, (Func0) this.mModalViewProvider.get());
        injectMWithdrawFiatPresenter(instance, (WithdrawFiatPresenter) this.mWithdrawFiatPresenterProvider.get());
        injectMCallingActivity(instance, (AppCompatActivity) this.mAppCompatActivityAndMCallingActivityProvider.get());
    }

    public static void injectMWithdrawFiatPresenter(WithdrawFiatController instance, WithdrawFiatPresenter mWithdrawFiatPresenter) {
        instance.mWithdrawFiatPresenter = mWithdrawFiatPresenter;
    }

    public static void injectMCallingActivity(WithdrawFiatController instance, AppCompatActivity mCallingActivity) {
        instance.mCallingActivity = mCallingActivity;
    }
}
