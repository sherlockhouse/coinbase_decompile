package com.coinbase.android.settings.tiers;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.coinbase.android.ui.ActionBarController_MembersInjector;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.BackgroundDimmer;
import com.coinbase.android.ui.ControllerLifeCycleFactory;
import com.coinbase.android.ui.ControllerTransitionContainer;
import com.coinbase.android.ui.StatusBarUpdater;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;
import rx.functions.Func0;

public final class InvestmentTiersSettingsController_MembersInjector implements MembersInjector<InvestmentTiersSettingsController> {
    private final Provider<AppCompatActivity> mAppCompatActivityProvider;
    private final Provider<BackNavigationConnector> mBackNavigationConnectorProvider;
    private final Provider<BackgroundDimmer> mBackgroundDimmerProvider;
    private final Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider;
    private final Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<Func0<ViewGroup>> mModalViewProvider;
    private final Provider<InvestmentTiersSettingsPresenter> mPresenterProvider;
    private final Provider<StatusBarUpdater> mStatusBarUpdaterProvider;

    public InvestmentTiersSettingsController_MembersInjector(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<InvestmentTiersSettingsPresenter> mPresenterProvider, Provider<BackgroundDimmer> mBackgroundDimmerProvider) {
        this.mAppCompatActivityProvider = mAppCompatActivityProvider;
        this.mBackNavigationConnectorProvider = mBackNavigationConnectorProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mStatusBarUpdaterProvider = mStatusBarUpdaterProvider;
        this.mChildTransitionBehaviorProvider = mChildTransitionBehaviorProvider;
        this.mLifeCycleFactoryProvider = mLifeCycleFactoryProvider;
        this.mModalViewProvider = mModalViewProvider;
        this.mPresenterProvider = mPresenterProvider;
        this.mBackgroundDimmerProvider = mBackgroundDimmerProvider;
    }

    public static MembersInjector<InvestmentTiersSettingsController> create(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<InvestmentTiersSettingsPresenter> mPresenterProvider, Provider<BackgroundDimmer> mBackgroundDimmerProvider) {
        return new InvestmentTiersSettingsController_MembersInjector(mAppCompatActivityProvider, mBackNavigationConnectorProvider, mMainSchedulerProvider, mStatusBarUpdaterProvider, mChildTransitionBehaviorProvider, mLifeCycleFactoryProvider, mModalViewProvider, mPresenterProvider, mBackgroundDimmerProvider);
    }

    public void injectMembers(InvestmentTiersSettingsController instance) {
        ActionBarController_MembersInjector.injectMAppCompatActivity(instance, (AppCompatActivity) this.mAppCompatActivityProvider.get());
        ActionBarController_MembersInjector.injectMBackNavigationConnector(instance, (BackNavigationConnector) this.mBackNavigationConnectorProvider.get());
        ActionBarController_MembersInjector.injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        ActionBarController_MembersInjector.injectMStatusBarUpdater(instance, (StatusBarUpdater) this.mStatusBarUpdaterProvider.get());
        ActionBarController_MembersInjector.injectMChildTransitionBehavior(instance, (ControllerTransitionContainer) this.mChildTransitionBehaviorProvider.get());
        ActionBarController_MembersInjector.injectMLifeCycleFactory(instance, (ControllerLifeCycleFactory) this.mLifeCycleFactoryProvider.get());
        ActionBarController_MembersInjector.injectMModalView(instance, (Func0) this.mModalViewProvider.get());
        injectMPresenter(instance, (InvestmentTiersSettingsPresenter) this.mPresenterProvider.get());
        injectMBackgroundDimmer(instance, (BackgroundDimmer) this.mBackgroundDimmerProvider.get());
    }

    public static void injectMPresenter(InvestmentTiersSettingsController instance, InvestmentTiersSettingsPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }

    public static void injectMBackgroundDimmer(InvestmentTiersSettingsController instance, BackgroundDimmer mBackgroundDimmer) {
        instance.mBackgroundDimmer = mBackgroundDimmer;
    }
}
