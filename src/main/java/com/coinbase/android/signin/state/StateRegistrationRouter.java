package com.coinbase.android.signin.state;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.signin.StateDisclosureFinishedConnector;
import com.coinbase.android.ui.ActionBarController;
import javax.inject.Inject;

@ControllerScope
public class StateRegistrationRouter {
    private final ActionBarController mController;
    private final StateDisclosureFinishedConnector mStateDisclosureFinishedConnector;

    @Inject
    StateRegistrationRouter(ActionBarController controller, StateDisclosureFinishedConnector stateDisclosureFinishedConnector) {
        this.mController = controller;
        this.mStateDisclosureFinishedConnector = stateDisclosureFinishedConnector;
    }

    void routeToIdology() {
        this.mController.pushModalController(new StateIdologyNameFormController(this.mController.appendModalArgsWithRoot(new Bundle())));
    }

    public void cancelStateRegistration() {
        this.mStateDisclosureFinishedConnector.get().onNext(new ClassConsumableEvent(null));
        if (this.mController != null) {
            this.mController.getRouter().popToRoot();
        }
    }
}
