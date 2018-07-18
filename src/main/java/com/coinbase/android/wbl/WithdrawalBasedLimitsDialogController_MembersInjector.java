package com.coinbase.android.wbl;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.coinbase.android.ui.ActionBarController_MembersInjector;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.BackgroundDimmer;
import com.coinbase.android.ui.ControllerLifeCycleFactory;
import com.coinbase.android.ui.ControllerTransitionContainer;
import com.coinbase.android.ui.DialogController_MembersInjector;
import com.coinbase.android.ui.StatusBarUpdater;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;
import rx.functions.Func0;

public final class WithdrawalBasedLimitsDialogController_MembersInjector implements MembersInjector<WithdrawalBasedLimitsDialogController> {
    private final Provider<AppCompatActivity> mAppCompatActivityProvider;
    private final Provider<BackNavigationConnector> mBackNavigationConnectorProvider;
    private final Provider<BackgroundDimmer> mBackgroundDimmerProvider;
    private final Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider;
    private final Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<Func0<ViewGroup>> mModalViewProvider;
    private final Provider<WithdrawalBasedLimitsDialogPresenter> mPresenterProvider;
    private final Provider<StatusBarUpdater> mStatusBarUpdaterProvider;

    public WithdrawalBasedLimitsDialogController_MembersInjector(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<BackgroundDimmer> mBackgroundDimmerProvider, Provider<WithdrawalBasedLimitsDialogPresenter> mPresenterProvider) {
        this.mAppCompatActivityProvider = mAppCompatActivityProvider;
        this.mBackNavigationConnectorProvider = mBackNavigationConnectorProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mStatusBarUpdaterProvider = mStatusBarUpdaterProvider;
        this.mChildTransitionBehaviorProvider = mChildTransitionBehaviorProvider;
        this.mLifeCycleFactoryProvider = mLifeCycleFactoryProvider;
        this.mModalViewProvider = mModalViewProvider;
        this.mBackgroundDimmerProvider = mBackgroundDimmerProvider;
        this.mPresenterProvider = mPresenterProvider;
    }

    public static MembersInjector<WithdrawalBasedLimitsDialogController> create(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<BackgroundDimmer> mBackgroundDimmerProvider, Provider<WithdrawalBasedLimitsDialogPresenter> mPresenterProvider) {
        return new WithdrawalBasedLimitsDialogController_MembersInjector(mAppCompatActivityProvider, mBackNavigationConnectorProvider, mMainSchedulerProvider, mStatusBarUpdaterProvider, mChildTransitionBehaviorProvider, mLifeCycleFactoryProvider, mModalViewProvider, mBackgroundDimmerProvider, mPresenterProvider);
    }

    public void injectMembers(WithdrawalBasedLimitsDialogController instance) {
        ActionBarController_MembersInjector.injectMAppCompatActivity(instance, (AppCompatActivity) this.mAppCompatActivityProvider.get());
        ActionBarController_MembersInjector.injectMBackNavigationConnector(instance, (BackNavigationConnector) this.mBackNavigationConnectorProvider.get());
        ActionBarController_MembersInjector.injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        ActionBarController_MembersInjector.injectMStatusBarUpdater(instance, (StatusBarUpdater) this.mStatusBarUpdaterProvider.get());
        ActionBarController_MembersInjector.injectMChildTransitionBehavior(instance, (ControllerTransitionContainer) this.mChildTransitionBehaviorProvider.get());
        ActionBarController_MembersInjector.injectMLifeCycleFactory(instance, (ControllerLifeCycleFactory) this.mLifeCycleFactoryProvider.get());
        ActionBarController_MembersInjector.injectMModalView(instance, (Func0) this.mModalViewProvider.get());
        DialogController_MembersInjector.injectMBackgroundDimmer(instance, (BackgroundDimmer) this.mBackgroundDimmerProvider.get());
        injectMPresenter(instance, (WithdrawalBasedLimitsDialogPresenter) this.mPresenterProvider.get());
    }

    public static void injectMPresenter(WithdrawalBasedLimitsDialogController instance, WithdrawalBasedLimitsDialogPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }
}
