package com.coinbase.android.signin.state;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.signin.StateDisclosureFinishedConnector;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import javax.inject.Inject;

@ControllerScope
public class StateLockedPresenter {
    private final Context mContext;
    private final LoginManager mLoginManager;
    private final StateRegistrationRouter mRouter;
    private final StateLockedScreen mScreen;
    private final StateDisclosureFinishedConnector mStateDisclosureFinishedConnector;

    @Inject
    public StateLockedPresenter(Application application, LoginManager loginManager, StateLockedScreen screen, StateDisclosureFinishedConnector stateDisclosureFinishedConnector, StateRegistrationRouter router) {
        this.mContext = application;
        this.mLoginManager = loginManager;
        this.mScreen = screen;
        this.mStateDisclosureFinishedConnector = stateDisclosureFinishedConnector;
        this.mRouter = router;
    }

    void onBackButtonClicked() {
        this.mScreen.showCancelDialog();
    }

    void onSupportButtonClicked() {
        Intent contactSupportIntent = Utils.supportEmailIntent(this.mLoginManager.getActiveUserEmail(), this.mContext.getString(R.string.wyoming_retrieve_funds));
        if (contactSupportIntent != null) {
            this.mScreen.showSupportActivity(contactSupportIntent);
        }
    }

    void onConfirmCancelButtonClicked() {
        this.mLoginManager.signout();
        this.mRouter.cancelStateRegistration();
    }
}
