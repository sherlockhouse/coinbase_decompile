package com.coinbase.android.settings.idology;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.signin.state.StateIdologyAddressFormController;
import com.coinbase.android.signin.state.StateIdologyQuestionsController;
import com.coinbase.android.signin.state.StateIdologyRetryFormController;
import com.coinbase.android.signin.state.StateIdologySourceOfFundsFormController;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.api.internal.models.idology.Data;
import javax.inject.Inject;

@ControllerScope
public class IdologyRouter {
    private final BackNavigationConnector mBackNavigationConnector;
    private final ActionBarController mController;
    private final IdologyArgsBuilder mIdologyArgsBuilder;

    @Inject
    public IdologyRouter(ActionBarController controller, BackNavigationConnector backNavigationConnector, IdologyArgsBuilder idologyArgsBuilder) {
        this.mController = controller;
        this.mBackNavigationConnector = backNavigationConnector;
        this.mIdologyArgsBuilder = idologyArgsBuilder;
    }

    public void routeToQuestionsPage(Data idology) {
        if ((this.mController instanceof StateIdologySourceOfFundsFormController) || (this.mController instanceof StateIdologyRetryFormController)) {
            this.mController.pushModalController(new StateIdologyQuestionsController(this.mIdologyArgsBuilder.getIdologyArgs(idology)));
            return;
        }
        this.mController.pushModalController(new IdologySettingsQuestionsController(this.mIdologyArgsBuilder.getIdologyArgs(idology)));
    }

    public void routeToPreviousPage() {
        if (this.mController != null && this.mController.getRouter() != null) {
            this.mBackNavigationConnector.get().onNext(null);
        }
    }

    public void routeToStateRetryPage() {
        this.mController.pushModalController(new StateIdologyRetryFormController(this.mController.appendModalArgs(new Bundle(this.mController.getArgs()))));
    }

    public void routeToAddressPage(Data buildingIdology) {
        this.mController.pushModalController(new StateIdologyAddressFormController(this.mIdologyArgsBuilder.getBuildingIdologyArgs(buildingIdology)));
    }

    public void routeToSourceOfFundsPage(Data buildingIdology) {
        this.mController.pushModalController(new StateIdologySourceOfFundsFormController(this.mIdologyArgsBuilder.getBuildingIdologyArgs(buildingIdology)));
    }
}
