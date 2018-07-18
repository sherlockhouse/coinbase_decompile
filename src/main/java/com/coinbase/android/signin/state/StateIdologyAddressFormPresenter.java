package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyAutoCompleteShownConnector;
import com.coinbase.android.idology.IdologyFormValidConnector;
import com.coinbase.android.idology.IdologyVerificationConnector;
import com.coinbase.android.idology.ProgressConnector;
import com.coinbase.android.settings.idology.IdologyRouter;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.api.internal.models.idology.Data;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class StateIdologyAddressFormPresenter extends AbstractStateIdologyFormPresenter {
    private final IdologyAutoCompleteShownConnector mIdologyAutoCompleteShownConnector;
    private final IdologyRouter mIdologyRouter;
    private final Logger mLogger = LoggerFactory.getLogger(StateIdologyAddressFormPresenter.class);
    private final Scheduler mMainScheduler;
    private final StateIdologyAddressFormScreen mScreen;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public StateIdologyAddressFormPresenter(StateIdologyAddressFormScreen screen, IdologyFormValidConnector idologyFormValidConnector, IdologyVerificationConnector idologyVerificationConnector, IdologyAutoCompleteShownConnector idologyAutoCompleteShownConnector, ProgressConnector progressConnector, IdologyRouter idologyRouter, @MainScheduler Scheduler mainScheduler) {
        super(screen, idologyFormValidConnector, idologyVerificationConnector, progressConnector, mainScheduler);
        this.mScreen = screen;
        this.mIdologyRouter = idologyRouter;
        this.mIdologyAutoCompleteShownConnector = idologyAutoCompleteShownConnector;
        this.mMainScheduler = mainScheduler;
    }

    void onShow() {
        super.onShow();
        this.mSubscription.add(this.mIdologyAutoCompleteShownConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(StateIdologyAddressFormPresenter$$Lambda$1.lambdaFactory$(this), StateIdologyAddressFormPresenter$$Lambda$2.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$onShow$0(StateIdologyAddressFormPresenter this_, Boolean b) {
        this_.mScreen.showContinueButton(!b.booleanValue());
    }

    void onHide() {
        super.onHide();
        this.mSubscription.clear();
    }

    public void processVerificationResult(Data idology) {
        if (idology != null && idology.getStatus() != null) {
            this.mIdologyRouter.routeToSourceOfFundsPage(idology);
        }
    }

    boolean onBackPressed() {
        return false;
    }
}
