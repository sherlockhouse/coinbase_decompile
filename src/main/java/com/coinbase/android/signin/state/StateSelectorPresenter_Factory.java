package com.coinbase.android.signin.state;

import android.app.Application;
import com.coinbase.android.signin.AuthRouter;
import com.coinbase.android.signin.StateDisclosureFinishedConnector;
import com.coinbase.android.signin.StateListSelectorConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;
import rx.Scheduler;

public final class StateSelectorPresenter_Factory implements Factory<StateSelectorPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<AuthRouter> mAuthRouterProvider;
    private final Provider<LoginManager> mLoginManagerProvider;
    private final Provider<Scheduler> mMainSchedulerProvider;
    private final Provider<MixpanelTracking> mMixpanelTrackingProvider;
    private final Provider<StateRegistrationRouter> mRouterProvider;
    private final Provider<StateSelectorScreen> mScreenProvider;
    private final Provider<SnackBarWrapper> mSnackBarWrapperProvider;
    private final Provider<StateDisclosureFinishedConnector> mStateDisclosureFinishedConnectorProvider;
    private final Provider<StateListSelectorConnector> mStateListSelectorConnectorProvider;

    public StateSelectorPresenter_Factory(Provider<Application> applicationProvider, Provider<LoginManager> mLoginManagerProvider, Provider<StateSelectorScreen> mScreenProvider, Provider<StateRegistrationRouter> mRouterProvider, Provider<StateListSelectorConnector> mStateListSelectorConnectorProvider, Provider<SnackBarWrapper> mSnackBarWrapperProvider, Provider<StateDisclosureFinishedConnector> mStateDisclosureFinishedConnectorProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider, Provider<AuthRouter> mAuthRouterProvider, Provider<Scheduler> mMainSchedulerProvider) {
        this.applicationProvider = applicationProvider;
        this.mLoginManagerProvider = mLoginManagerProvider;
        this.mScreenProvider = mScreenProvider;
        this.mRouterProvider = mRouterProvider;
        this.mStateListSelectorConnectorProvider = mStateListSelectorConnectorProvider;
        this.mSnackBarWrapperProvider = mSnackBarWrapperProvider;
        this.mStateDisclosureFinishedConnectorProvider = mStateDisclosureFinishedConnectorProvider;
        this.mMixpanelTrackingProvider = mMixpanelTrackingProvider;
        this.mAuthRouterProvider = mAuthRouterProvider;
        this.mMainSchedulerProvider = mMainSchedulerProvider;
    }

    public StateSelectorPresenter get() {
        return provideInstance(this.applicationProvider, this.mLoginManagerProvider, this.mScreenProvider, this.mRouterProvider, this.mStateListSelectorConnectorProvider, this.mSnackBarWrapperProvider, this.mStateDisclosureFinishedConnectorProvider, this.mMixpanelTrackingProvider, this.mAuthRouterProvider, this.mMainSchedulerProvider);
    }

    public static StateSelectorPresenter provideInstance(Provider<Application> applicationProvider, Provider<LoginManager> mLoginManagerProvider, Provider<StateSelectorScreen> mScreenProvider, Provider<StateRegistrationRouter> mRouterProvider, Provider<StateListSelectorConnector> mStateListSelectorConnectorProvider, Provider<SnackBarWrapper> mSnackBarWrapperProvider, Provider<StateDisclosureFinishedConnector> mStateDisclosureFinishedConnectorProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider, Provider<AuthRouter> mAuthRouterProvider, Provider<Scheduler> mMainSchedulerProvider) {
        return new StateSelectorPresenter((Application) applicationProvider.get(), (LoginManager) mLoginManagerProvider.get(), (StateSelectorScreen) mScreenProvider.get(), (StateRegistrationRouter) mRouterProvider.get(), (StateListSelectorConnector) mStateListSelectorConnectorProvider.get(), (SnackBarWrapper) mSnackBarWrapperProvider.get(), (StateDisclosureFinishedConnector) mStateDisclosureFinishedConnectorProvider.get(), (MixpanelTracking) mMixpanelTrackingProvider.get(), (AuthRouter) mAuthRouterProvider.get(), (Scheduler) mMainSchedulerProvider.get());
    }

    public static StateSelectorPresenter_Factory create(Provider<Application> applicationProvider, Provider<LoginManager> mLoginManagerProvider, Provider<StateSelectorScreen> mScreenProvider, Provider<StateRegistrationRouter> mRouterProvider, Provider<StateListSelectorConnector> mStateListSelectorConnectorProvider, Provider<SnackBarWrapper> mSnackBarWrapperProvider, Provider<StateDisclosureFinishedConnector> mStateDisclosureFinishedConnectorProvider, Provider<MixpanelTracking> mMixpanelTrackingProvider, Provider<AuthRouter> mAuthRouterProvider, Provider<Scheduler> mMainSchedulerProvider) {
        return new StateSelectorPresenter_Factory(applicationProvider, mLoginManagerProvider, mScreenProvider, mRouterProvider, mStateListSelectorConnectorProvider, mSnackBarWrapperProvider, mStateDisclosureFinishedConnectorProvider, mMixpanelTrackingProvider, mAuthRouterProvider, mMainSchedulerProvider);
    }

    public static StateSelectorPresenter newStateSelectorPresenter(Application application, LoginManager mLoginManager, StateSelectorScreen mScreen, StateRegistrationRouter mRouter, StateListSelectorConnector mStateListSelectorConnector, SnackBarWrapper mSnackBarWrapper, StateDisclosureFinishedConnector mStateDisclosureFinishedConnector, MixpanelTracking mMixpanelTracking, AuthRouter mAuthRouter, Scheduler mMainScheduler) {
        return new StateSelectorPresenter(application, mLoginManager, mScreen, mRouter, mStateListSelectorConnector, mSnackBarWrapper, mStateDisclosureFinishedConnector, mMixpanelTracking, mAuthRouter, mMainScheduler);
    }
}
