package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.signin.StateDisclosureFinishedConnector;
import com.coinbase.api.LoginManager;
import javax.inject.Inject;

@ControllerScope
public class StateSuspendedPresenter {
    private LoginManager mLoginManager;
    private StateRegistrationRouter mRouter;
    private final StateSuspendedScreen mScreen;
    private StateDisclosureFinishedConnector mStateDisclosureFinishedConnector;

    @Inject
    public StateSuspendedPresenter(LoginManager loginManager, StateSuspendedScreen screen, StateDisclosureFinishedConnector stateDisclosureFinishedConnector, StateRegistrationRouter router) {
        this.mLoginManager = loginManager;
        this.mScreen = screen;
        this.mStateDisclosureFinishedConnector = stateDisclosureFinishedConnector;
        this.mRouter = router;
    }

    void onBackButtonClicked() {
        this.mScreen.showCancelDialog();
    }

    void onConfirmCancelButtonClicked() {
        this.mLoginManager.signout();
        this.mRouter.cancelStateRegistration();
    }
}
