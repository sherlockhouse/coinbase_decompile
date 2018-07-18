package com.coinbase.android.settings;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.coinbase.android.ui.ActionBarController_MembersInjector;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.BackgroundDimmer;
import com.coinbase.android.ui.BottomNavigationConnector;
import com.coinbase.android.ui.ControllerLifeCycleFactory;
import com.coinbase.android.ui.ControllerTransitionContainer;
import com.coinbase.android.ui.DialogController_MembersInjector;
import com.coinbase.android.ui.StatusBarUpdater;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import dagger.MembersInjector;
import javax.inject.Provider;
import rx.Scheduler;
import rx.functions.Func0;

public final class GoToSettingsDialogController_MembersInjector implements MembersInjector<GoToSettingsDialogController> {
    private final Provider<AppCompatActivity> mAppCompatActivityProvider;
    private final Provider<BackNavigationConnector> mBackNavigationConnectorProvider;
    private final Provider<BackgroundDimmer> mBackgroundDimmerProvider;
    private final Provider<BottomNavigationConnector> mBottomNavigationConnectorProvider;
    private final Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider;
    private final Provider<GoToSettingsConnector> mGoToSettingsConnectorProvider;
    private final Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<MixpanelTracking> mMixpanelTrackingProvider;
    private final Provider<Func0<ViewGroup>> mModalViewProvider;
    private final Provider<StatusBarUpdater> mStatusBarUpdaterProvider;

    public GoToSettingsDialogController_MembersInjector(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<BackgroundDimmer> mBackgroundDimmerProvider, Provider<BottomNavigationConnector> mBottomNavigationConnectorProvider, Provider<GoToSettingsConnector> mGoToSettingsConnectorProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider) {
        this.mAppCompatActivityProvider = mAppCompatActivityProvider;
        this.mBackNavigationConnectorProvider = mBackNavigationConnectorProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
        this.mStatusBarUpdaterProvider = mStatusBarUpdaterProvider;
        this.mChildTransitionBehaviorProvider = mChildTransitionBehaviorProvider;
        this.mLifeCycleFactoryProvider = mLifeCycleFactoryProvider;
        this.mModalViewProvider = mModalViewProvider;
        this.mBackgroundDimmerProvider = mBackgroundDimmerProvider;
        this.mBottomNavigationConnectorProvider = mBottomNavigationConnectorProvider;
        this.mGoToSettingsConnectorProvider = mGoToSettingsConnectorProvider;
        this.mMixpanelTrackingProvider = mMixpanelTrackingProvider;
    }

    public static MembersInjector<GoToSettingsDialogController> create(Provider<AppCompatActivity> mAppCompatActivityProvider, Provider<BackNavigationConnector> mBackNavigationConnectorProvider, Provider<Scheduler> mMainSchedulerProvider, Provider<StatusBarUpdater> mStatusBarUpdaterProvider, Provider<ControllerTransitionContainer> mChildTransitionBehaviorProvider, Provider<ControllerLifeCycleFactory> mLifeCycleFactoryProvider, Provider<Func0<ViewGroup>> mModalViewProvider, Provider<BackgroundDimmer> mBackgroundDimmerProvider, Provider<BottomNavigationConnector> mBottomNavigationConnectorProvider, Provider<GoToSettingsConnector> mGoToSettingsConnectorProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider) {
        return new GoToSettingsDialogController_MembersInjector(mAppCompatActivityProvider, mBackNavigationConnectorProvider, mMainSchedulerProvider, mStatusBarUpdaterProvider, mChildTransitionBehaviorProvider, mLifeCycleFactoryProvider, mModalViewProvider, mBackgroundDimmerProvider, mBottomNavigationConnectorProvider, mGoToSettingsConnectorProvider, mMixpanelTrackingProvider);
    }

    public void injectMembers(GoToSettingsDialogController instance) {
        ActionBarController_MembersInjector.injectMAppCompatActivity(instance, (AppCompatActivity) this.mAppCompatActivityProvider.get());
        ActionBarController_MembersInjector.injectMBackNavigationConnector(instance, (BackNavigationConnector) this.mBackNavigationConnectorProvider.get());
        ActionBarController_MembersInjector.injectMMainScheduler(instance, (Scheduler) this.mMainSchedulerProvider.get());
        ActionBarController_MembersInjector.injectMStatusBarUpdater(instance, (StatusBarUpdater) this.mStatusBarUpdaterProvider.get());
        ActionBarController_MembersInjector.injectMChildTransitionBehavior(instance, (ControllerTransitionContainer) this.mChildTransitionBehaviorProvider.get());
        ActionBarController_MembersInjector.injectMLifeCycleFactory(instance, (ControllerLifeCycleFactory) this.mLifeCycleFactoryProvider.get());
        ActionBarController_MembersInjector.injectMModalView(instance, (Func0) this.mModalViewProvider.get());
        DialogController_MembersInjector.injectMBackgroundDimmer(instance, (BackgroundDimmer) this.mBackgroundDimmerProvider.get());
        injectMBottomNavigationConnector(instance, (BottomNavigationConnector) this.mBottomNavigationConnectorProvider.get());
        injectMGoToSettingsConnector(instance, (GoToSettingsConnector) this.mGoToSettingsConnectorProvider.get());
        injectMMixpanelTracking(instance, (MixpanelTracking) this.mMixpanelTrackingProvider.get());
    }

    public static void injectMBottomNavigationConnector(GoToSettingsDialogController instance, BottomNavigationConnector mBottomNavigationConnector) {
        instance.mBottomNavigationConnector = mBottomNavigationConnector;
    }

    public static void injectMGoToSettingsConnector(GoToSettingsDialogController instance, GoToSettingsConnector mGoToSettingsConnector) {
        instance.mGoToSettingsConnector = mGoToSettingsConnector;
    }

    public static void injectMMixpanelTracking(GoToSettingsDialogController instance, MixpanelTracking mMixpanelTracking) {
        instance.mMixpanelTracking = mMixpanelTracking;
    }
}
