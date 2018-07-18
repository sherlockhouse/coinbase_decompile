package com.coinbase.android.settings.gdpr;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.SuccessRouter;
import javax.inject.Inject;

@ControllerScope
public class GdprRequestSentPresenter {
    private final SuccessRouter mSuccessRouter;

    @Inject
    public GdprRequestSentPresenter(SuccessRouter successRouter) {
        this.mSuccessRouter = successRouter;
    }

    void gotoSettings() {
        if (this.mSuccessRouter.shouldRouteSuccess()) {
            this.mSuccessRouter.routeSuccess();
        }
    }
}
