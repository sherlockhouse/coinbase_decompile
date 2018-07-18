package com.coinbase.android.signin.state;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyFailureRouter;
import com.coinbase.android.idology.IdologyFormValidConnector;
import com.coinbase.android.idology.IdologyRetryConnector;
import com.coinbase.android.idology.IdologyTrackingContextProvider;
import com.coinbase.android.idology.IdologyVerificationConnector;
import com.coinbase.android.idology.ProgressConnector;
import com.coinbase.android.settings.idology.IdologyArgsBuilder;
import com.coinbase.android.settings.idology.IdologyRouter;
import com.coinbase.android.signin.AuthRouter;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.coinbase.api.internal.models.idology.Data;
import com.google.gson.GsonBuilder;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class StateIdologySourceOfFundsFormPresenter extends AbstractStateIdologyFormPresenter {
    private final AuthRouter mAuthRouter;
    private final IdologyFailureRouter mIdologyFailureRouter;
    private final IdologyRetryConnector mIdologyRetryConnector;
    private final IdologyRouter mIdologyRouter;
    private final IdologyTrackingContextProvider mIdologyTrackingContext;
    private final Logger mLogger = LoggerFactory.getLogger(StateIdologySourceOfFundsFormPresenter.class);
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final StateIdologySourceOfFundsScreen mScreen;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public StateIdologySourceOfFundsFormPresenter(StateIdologySourceOfFundsScreen screen, IdologyFormValidConnector idologyFormValidConnector, IdologyVerificationConnector idologyVerificationConnector, IdologyRetryConnector idologyRetryConnector, ProgressConnector progressConnector, IdologyRouter idologyRouter, IdologyFailureRouter idologyFailureRouter, AuthRouter authRouter, MixpanelTracking mixpanelTracking, IdologyTrackingContextProvider idologyTrackingContextProvider, @MainScheduler Scheduler mainScheduler) {
        super(screen, idologyFormValidConnector, idologyVerificationConnector, progressConnector, mainScheduler);
        this.mScreen = screen;
        this.mIdologyRouter = idologyRouter;
        this.mIdologyFailureRouter = idologyFailureRouter;
        this.mAuthRouter = authRouter;
        this.mIdologyRetryConnector = idologyRetryConnector;
        this.mMixpanelTracking = mixpanelTracking;
        this.mIdologyTrackingContext = idologyTrackingContextProvider;
        this.mMainScheduler = mainScheduler;
    }

    void onShow() {
        if (this.mAuthRouter.startedRouting()) {
            this.mScreen.showProgress();
            this.mSubscription.add(this.mAuthRouter.routeToNext());
            return;
        }
        super.onShow();
        this.mSubscription.add(this.mIdologyRetryConnector.get().onBackpressureLatest().filter(StateIdologySourceOfFundsFormPresenter$$Lambda$1.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(StateIdologySourceOfFundsFormPresenter$$Lambda$2.lambdaFactory$(this), StateIdologySourceOfFundsFormPresenter$$Lambda$3.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$onShow$0(Boolean isValid) {
        return Boolean.valueOf(isValid != null);
    }

    void onHide() {
        super.onHide();
        this.mSubscription.clear();
    }

    void onContinueButtonClicked() {
        if (this.mScreen.isContinueButtonEnabled()) {
            super.onContinueButtonClicked();
        }
    }

    public void processVerificationResult(Data idology) {
        if (idology != null && idology.getStatus() != null) {
            saveArgs(idology);
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_VIEWED_ERROR, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext.getContext());
            switch (idology.getStatus()) {
                case HAS_QUESTIONS:
                    this.mIdologyRouter.routeToQuestionsPage(idology);
                    return;
                case SUCCESS:
                    this.mScreen.showProgress();
                    this.mSubscription.add(this.mAuthRouter.routeToNext());
                    return;
                default:
                    this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_VIEWED_ERROR, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext.getContext());
                    this.mIdologyFailureRouter.routeToFailure(idology);
                    return;
            }
        }
    }

    private void saveArgs(Data idology) {
        if (this.mArgs != null && idology != null) {
            String idologyStr = new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().toJson((Object) idology);
            if (idologyStr != null) {
                this.mArgs.putString(IdologyArgsBuilder.IDOLOGY_DATA, idologyStr);
            }
        }
    }
}
