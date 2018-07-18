package com.coinbase.android.signin.state;

import com.coinbase.android.signin.StateDisclosureFinishedConnector;
import com.coinbase.api.LoginManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class StateSuspendedPresenter_Factory implements Factory<StateSuspendedPresenter> {
    private final Provider<LoginManager> loginManagerProvider;
    private final Provider<StateRegistrationRouter> routerProvider;
    private final Provider<StateSuspendedScreen> screenProvider;
    private final Provider<StateDisclosureFinishedConnector> stateDisclosureFinishedConnectorProvider;

    public StateSuspendedPresenter_Factory(Provider<LoginManager> loginManagerProvider, Provider<StateSuspendedScreen> screenProvider, Provider<StateDisclosureFinishedConnector> stateDisclosureFinishedConnectorProvider, Provider<StateRegistrationRouter> routerProvider) {
        this.loginManagerProvider = loginManagerProvider;
        this.screenProvider = screenProvider;
        this.stateDisclosureFinishedConnectorProvider = stateDisclosureFinishedConnectorProvider;
        this.routerProvider = routerProvider;
    }

    public StateSuspendedPresenter get() {
        return provideInstance(this.loginManagerProvider, this.screenProvider, this.stateDisclosureFinishedConnectorProvider, this.routerProvider);
    }

    public static StateSuspendedPresenter provideInstance(Provider<LoginManager> loginManagerProvider, Provider<StateSuspendedScreen> screenProvider, Provider<StateDisclosureFinishedConnector> stateDisclosureFinishedConnectorProvider, Provider<StateRegistrationRouter> routerProvider) {
        return new StateSuspendedPresenter((LoginManager) loginManagerProvider.get(), (StateSuspendedScreen) screenProvider.get(), (StateDisclosureFinishedConnector) stateDisclosureFinishedConnectorProvider.get(), (StateRegistrationRouter) routerProvider.get());
    }

    public static StateSuspendedPresenter_Factory create(Provider<LoginManager> loginManagerProvider, Provider<StateSuspendedScreen> screenProvider, Provider<StateDisclosureFinishedConnector> stateDisclosureFinishedConnectorProvider, Provider<StateRegistrationRouter> routerProvider) {
        return new StateSuspendedPresenter_Factory(loginManagerProvider, screenProvider, stateDisclosureFinishedConnectorProvider, routerProvider);
    }

    public static StateSuspendedPresenter newStateSuspendedPresenter(LoginManager loginManager, StateSuspendedScreen screen, StateDisclosureFinishedConnector stateDisclosureFinishedConnector, StateRegistrationRouter router) {
        return new StateSuspendedPresenter(loginManager, screen, stateDisclosureFinishedConnector, router);
    }
}
