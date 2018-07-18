package com.coinbase.android.signin.state;

import com.coinbase.android.signin.StateDisclosureFinishedConnector;
import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class StateRegistrationRouter_Factory implements Factory<StateRegistrationRouter> {
    private final Provider<ActionBarController> controllerProvider;
    private final Provider<StateDisclosureFinishedConnector> stateDisclosureFinishedConnectorProvider;

    public StateRegistrationRouter_Factory(Provider<ActionBarController> controllerProvider, Provider<StateDisclosureFinishedConnector> stateDisclosureFinishedConnectorProvider) {
        this.controllerProvider = controllerProvider;
        this.stateDisclosureFinishedConnectorProvider = stateDisclosureFinishedConnectorProvider;
    }

    public StateRegistrationRouter get() {
        return provideInstance(this.controllerProvider, this.stateDisclosureFinishedConnectorProvider);
    }

    public static StateRegistrationRouter provideInstance(Provider<ActionBarController> controllerProvider, Provider<StateDisclosureFinishedConnector> stateDisclosureFinishedConnectorProvider) {
        return new StateRegistrationRouter((ActionBarController) controllerProvider.get(), (StateDisclosureFinishedConnector) stateDisclosureFinishedConnectorProvider.get());
    }

    public static StateRegistrationRouter_Factory create(Provider<ActionBarController> controllerProvider, Provider<StateDisclosureFinishedConnector> stateDisclosureFinishedConnectorProvider) {
        return new StateRegistrationRouter_Factory(controllerProvider, stateDisclosureFinishedConnectorProvider);
    }

    public static StateRegistrationRouter newStateRegistrationRouter(ActionBarController controller, StateDisclosureFinishedConnector stateDisclosureFinishedConnector) {
        return new StateRegistrationRouter(controller, stateDisclosureFinishedConnector);
    }
}
