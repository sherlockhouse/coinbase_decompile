package com.coinbase.android.settings.idology;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyFailureRouter;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.internal.models.idology.Data;
import javax.inject.Inject;

@ControllerScope
public class InAppIdologyFailureRouter implements IdologyFailureRouter {
    private final ActionBarController mController;
    private final IdologyArgsBuilder mIdologyArgsBuilder;

    @Inject
    public InAppIdologyFailureRouter(ActionBarController controller, IdologyArgsBuilder idologyArgsBuilder) {
        this.mController = controller;
        this.mIdologyArgsBuilder = idologyArgsBuilder;
    }

    public void routeToFailure(Data idologyData) {
        this.mController.pushModalController(new IdologyFailureController(this.mIdologyArgsBuilder.getIdologyArgs(idologyData)));
    }
}
