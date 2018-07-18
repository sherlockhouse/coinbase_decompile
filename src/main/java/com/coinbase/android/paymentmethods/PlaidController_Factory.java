package com.coinbase.android.paymentmethods;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.coinbase.android.ui.ActionBarController_MembersInjector;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.ControllerLifeCycleFactory;
import com.coinbase.android.ui.ControllerTransitionContainer;
import com.coinbase.android.ui.StatusBarUpdater;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;
import rx.functions.Func0;

public final class PlaidController_Factory implements Factory<PlaidController> {
    private final Provider<Bundle> argsProvider;
    private final Provider<AppCompatActivity> mAppCompatActivityProvider;
    private final Provider<BackNavigationConnector> mBackNavigationConnectorProvider;
    private final Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider;
    private final Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<Func0<ViewGroup>> mModalViewProvider;
    private final Provider<PlaidPresenter> mPresenterProvider;
    private final Provider<StatusBarUpdater> mStatusBarUpdaterProvider;

    public PlaidController_Factory(Provider<Bundle> argsProvider, Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<PlaidPresenter> mPresenterProvider) {
        this.argsProvider = argsProvider;
        this.mAppCompatActivityProvider = mAppCompatActivityProvider;
        this.mBackNavigationConnectorProvider = mBackNavigationConnectorProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mStatusBarUpdaterProvider = mStatusBarUpdaterProvider;
        this.mChildTransitionBehaviorProvider = mChildTransitionBehaviorProvider;
        this.mLifeCycleFactoryProvider = mLifeCycleFactoryProvider;
        this.mModalViewProvider = mModalViewProvider;
        this.mPresenterProvider = mPresenterProvider;
    }

    public PlaidController get() {
        return provideInstance(this.argsProvider, this.mAppCompatActivityProvider, this.mBackNavigationConnectorProvider, this.mMainSchedulerProvider, this.mStatusBarUpdaterProvider, this.mChildTransitionBehaviorProvider, this.mLifeCycleFactoryProvider, this.mModalViewProvider, this.mPresenterProvider);
    }

    public static PlaidController provideInstance(Provider<Bundle> argsProvider, Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<PlaidPresenter> mPresenterProvider) {
        PlaidController instance = new PlaidController((Bundle) argsProvider.get());
        ActionBarController_MembersInjector.injectMAppCompatActivity(instance, (AppCompatActivity) mAppCompatActivityProvider.get());
        ActionBarController_MembersInjector.injectMBackNavigationConnector(instance, (BackNavigationConnector) mBackNavigationConnectorProvider.get());
        ActionBarController_MembersInjector.injectMMainScheduler(instance, (Scheduler) mMainSchedulerProvider.get());
        ActionBarController_MembersInjector.injectMStatusBarUpdater(instance, (StatusBarUpdater) mStatusBarUpdaterProvider.get());
        ActionBarController_MembersInjector.injectMChildTransitionBehavior(instance, (ControllerTransitionContainer) mChildTransitionBehaviorProvider.get());
        ActionBarController_MembersInjector.injectMLifeCycleFactory(instance, (ControllerLifeCycleFactory) mLifeCycleFactoryProvider.get());
        ActionBarController_MembersInjector.injectMModalView(instance, (Func0) mModalViewProvider.get());
        PlaidController_MembersInjector.injectMPresenter(instance, (PlaidPresenter) mPresenterProvider.get());
        return instance;
    }

    public static PlaidController_Factory create(Provider<Bundle> argsProvider, Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<PlaidPresenter> mPresenterProvider) {
        return new PlaidController_Factory(argsProvider, mAppCompatActivityProvider, mBackNavigationConnectorProvider, mMainSchedulerProvider, mStatusBarUpdaterProvider, mChildTransitionBehaviorProvider, mLifeCycleFactoryProvider, mModalViewProvider, mPresenterProvider);
    }

    public static PlaidController newPlaidController(Bundle args) {
        return new PlaidController(args);
    }
}
