package com.coinbase.android.ui;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;
import rx.functions.Func0;

public final class ActionBarController_MembersInjector implements MembersInjector<ActionBarController> {
    private final Provider<AppCompatActivity> mAppCompatActivityProvider;
    private final Provider<BackNavigationConnector> mBackNavigationConnectorProvider;
    private final Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider;
    private final Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<Func0<ViewGroup>> mModalViewProvider;
    private final Provider<StatusBarUpdater> mStatusBarUpdaterProvider;

    public ActionBarController_MembersInjector(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider) {
        this.mAppCompatActivityProvider = mAppCompatActivityProvider;
        this.mBackNavigationConnectorProvider = mBackNavigationConnectorProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mStatusBarUpdaterProvider = mStatusBarUpdaterProvider;
        this.mChildTransitionBehaviorProvider = mChildTransitionBehaviorProvider;
        this.mLifeCycleFactoryProvider = mLifeCycleFactoryProvider;
        this.mModalViewProvider = mModalViewProvider;
    }

    public static MembersInjector<ActionBarController> create(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider) {
        return new ActionBarController_MembersInjector(mAppCompatActivityProvider, mBackNavigationConnectorProvider, mMainSchedulerProvider, mStatusBarUpdaterProvider, mChildTransitionBehaviorProvider, mLifeCycleFactoryProvider, mModalViewProvider);
    }

    public void injectMembers(ActionBarController instance) {
        injectMAppCompatActivity(instance, (AppCompatActivity) this.mAppCompatActivityProvider.get());
        injectMBackNavigationConnector(instance, (BackNavigationConnector) this.mBackNavigationConnectorProvider.get());
        injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        injectMStatusBarUpdater(instance, (StatusBarUpdater) this.mStatusBarUpdaterProvider.get());
        injectMChildTransitionBehavior(instance, (ControllerTransitionContainer) this.mChildTransitionBehaviorProvider.get());
        injectMLifeCycleFactory(instance, (ControllerLifeCycleFactory) this.mLifeCycleFactoryProvider.get());
        injectMModalView(instance, (Func0) this.mModalViewProvider.get());
    }

    public static void injectMAppCompatActivity(ActionBarController instance, AppCompatActivity mAppCompatActivity) {
        instance.mAppCompatActivity = mAppCompatActivity;
    }

    public static void injectMBackNavigationConnector(ActionBarController instance, BackNavigationConnector mBackNavigationConnector) {
        instance.mBackNavigationConnector = mBackNavigationConnector;
    }

    public static void injectMMainScheduler(ActionBarController instance, Scheduler mMainScheduler) {
        instance.mMainScheduler = mMainScheduler;
    }

    public static void injectMStatusBarUpdater(ActionBarController instance, StatusBarUpdater mStatusBarUpdater) {
        instance.mStatusBarUpdater = mStatusBarUpdater;
    }

    public static void injectMChildTransitionBehavior(ActionBarController instance, ControllerTransitionContainer mChildTransitionBehavior) {
        instance.mChildTransitionBehavior = mChildTransitionBehavior;
    }

    public static void injectMLifeCycleFactory(ActionBarController instance, ControllerLifeCycleFactory mLifeCycleFactory) {
        instance.mLifeCycleFactory = mLifeCycleFactory;
    }

    public static void injectMModalView(ActionBarController instance, Func0<ViewGroup> mModalView) {
        instance.mModalView = mModalView;
    }
}
