package com.coinbase.android.signin.state;

import android.app.Application;
import com.coinbase.android.signin.StateDisclosureFinishedConnector;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class StateLockedPresenter_Factory implements Factory<StateLockedPresenter> {
    private final Provider<Application> applicationProvider;
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<StateRegistrationRouter> routerProvider;
    private final Provider<StateLockedScreen> screenProvider;
    private final Provider<StateDisclosureFinishedConnector> stateDisclosureFinishedConnectorProvider;

    public StateLockedPresenter_Factory(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<StateLockedScreen> screenProvider, Provider<StateDisclosureFinishedConnector> stateDisclosureFinishedConnectorProvider, Provider<StateRegistrationRouter> routerProvider) {
        this.applicationProvider = applicationProvider;
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.stateDisclosureFinishedConnectorProvider = stateDisclosureFinishedConnectorProvider;
        this.routerProvider = routerProvider;
    }

    public StateLockedPresenter get() {
        return provideInstance(this.applicationProvider, this.loginManagerProvider, this.screenProvider, this.stateDisclosureFinishedConnectorProvider, this.routerProvider);
    }

    public static StateLockedPresenter provideInstance(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<StateLockedScreen> screenProvider, Provider<StateDisclosureFinishedConnector> stateDisclosureFinishedConnectorProvider, Provider<StateRegistrationRouter> routerProvider) {
        return new StateLockedPresenter((Application) applicationProvider.get(), (LoginManager) loginManagerProvider.get(), (StateLockedScreen) screenProvider.get(), (StateDisclosureFinishedConnector) stateDisclosureFinishedConnectorProvider.get(), (StateRegistrationRouter) routerProvider.get());
    }

    public static StateLockedPresenter_Factory create(Provider<Application> applicationProvider, Provider<LoginManager> loginManagerProvider, Provider<StateLockedScreen> screenProvider, Provider<StateDisclosureFinishedConnector> stateDisclosureFinishedConnectorProvider, Provider<StateRegistrationRouter> routerProvider) {
        return new StateLockedPresenter_Factory(applicationProvider, loginManagerProvider, screenProvider, stateDisclosureFinishedConnectorProvider, routerProvider);
    }

    public static StateLockedPresenter newStateLockedPresenter(Application application, LoginManager loginManager, StateLockedScreen screen, StateDisclosureFinishedConnector stateDisclosureFinishedConnector, StateRegistrationRouter router) {
        return new StateLockedPresenter(application, loginManager, screen, stateDisclosureFinishedConnector, router);
    }
}
