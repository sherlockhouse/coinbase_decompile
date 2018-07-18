package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyFormValidConnector;
import com.coinbase.android.idology.IdologyVerificationConnector;
import com.coinbase.android.idology.ProgressConnector;
import com.coinbase.android.settings.idology.IdologyRouter;
import com.coinbase.android.signin.SignInRouter;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.internal.models.idology.Data;
import javax.inject.Inject;
import rx.Scheduler;

@ControllerScope
public class StateIdologyNameFormPresenter extends AbstractStateIdologyFormPresenter {
    private final IdologyRouter mIdologyRouter;
    private final MixpanelTracking mMixpanelTracking;
    private final SignInRouter mSignInRouter;

    @Inject
    public StateIdologyNameFormPresenter(StateIdologyFormScreen screen, IdologyFormValidConnector idologyFormValidConnector, IdologyVerificationConnector idologyVerificationConnector, ProgressConnector progressConnector, IdologyRouter idologyRouter, SignInRouter signInRouter, MixpanelTracking mixpanelTracking, @MainScheduler Scheduler mainScheduler) {
        super(screen, idologyFormValidConnector, idologyVerificationConnector, progressConnector, mainScheduler);
        this.mIdologyRouter = idologyRouter;
        this.mSignInRouter = signInRouter;
        this.mMixpanelTracking = mixpanelTracking;
    }

    public void processVerificationResult(Data idology) {
        if (idology != null && idology.getStatus() != null) {
            this.mIdologyRouter.routeToAddressPage(idology);
        }
    }

    boolean onBackPressed() {
        this.mSignInRouter.cancel(StateIdologyNameFormPresenter$$Lambda$1.lambdaFactory$(this));
        return true;
    }
}
