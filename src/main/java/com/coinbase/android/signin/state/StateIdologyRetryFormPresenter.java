package com.coinbase.android.signin.state;

import android.os.Bundle;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.idology.IdologyFailureRouter;
import com.coinbase.android.idology.IdologyFormValidConnector;
import com.coinbase.android.idology.IdologyRetryConnector;
import com.coinbase.android.idology.IdologyTrackingContextProvider;
import com.coinbase.android.idology.IdologyVerificationConnector;
import com.coinbase.android.settings.idology.IdologyArgsBuilder;
import com.coinbase.android.settings.idology.IdologyRouter;
import com.coinbase.android.signin.AuthRouter;
import com.coinbase.android.signin.SignInRouter;
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
public class StateIdologyRetryFormPresenter {
    private Bundle mArgs;
    private final AuthRouter mAuthRouter;
    private final IdologyFailureRouter mIdologyFailureRouter;
    private final IdologyFormValidConnector mIdologyFormValidConnector;
    private final IdologyRetryConnector mIdologyRetryConnector;
    private final IdologyRouter mIdologyRouter;
    private final IdologyTrackingContextProvider mIdologyTrackingContext;
    private final IdologyVerificationConnector mIdologyVerificationConnector;
    private final Logger mLogger = LoggerFactory.getLogger(StateIdologyRetryFormPresenter.class);
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final StateIdologyRetryFormScreen mScreen;
    private final SignInRouter mSignInRouter;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public StateIdologyRetryFormPresenter(StateIdologyRetryFormScreen screen, IdologyFormValidConnector idologyFormValidConnector, IdologyVerificationConnector idologyVerificationConnector, IdologyRetryConnector idologyRetryConnector, IdologyRouter idologyRouter, IdologyFailureRouter idologyFailureRouter, SignInRouter signInRouter, AuthRouter authRouter, MixpanelTracking mixpanelTracking, IdologyTrackingContextProvider idologyTrackingContext, @MainScheduler Scheduler mainScheduler) {
        this.mScreen = screen;
        this.mIdologyFormValidConnector = idologyFormValidConnector;
        this.mIdologyVerificationConnector = idologyVerificationConnector;
        this.mIdologyRetryConnector = idologyRetryConnector;
        this.mIdologyRouter = idologyRouter;
        this.mIdologyFailureRouter = idologyFailureRouter;
        this.mSignInRouter = signInRouter;
        this.mAuthRouter = authRouter;
        this.mMixpanelTracking = mixpanelTracking;
        this.mIdologyTrackingContext = idologyTrackingContext;
        this.mMainScheduler = mainScheduler;
    }

    void onViewCreated(Bundle args) {
        this.mArgs = args;
        if (this.mArgs != null) {
            String idologyJson = args.getString(IdologyArgsBuilder.IDOLOGY_DATA);
            if (idologyJson != null) {
                Data idologyData = (Data) new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create().fromJson(idologyJson, Data.class);
                if (idologyData != null) {
                    this.mScreen.setIdologyData(idologyData);
                }
            }
        }
    }

    void onShow() {
        this.mSubscription.clear();
        if (this.mAuthRouter.startedRouting()) {
            this.mScreen.showProgress();
            this.mSubscription.add(this.mAuthRouter.routeToNext());
            return;
        }
        this.mSubscription.add(this.mIdologyFormValidConnector.get().onBackpressureLatest().filter(StateIdologyRetryFormPresenter$$Lambda$1.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(StateIdologyRetryFormPresenter$$Lambda$2.lambdaFactory$(this), StateIdologyRetryFormPresenter$$Lambda$3.lambdaFactory$(this)));
        this.mSubscription.add(this.mIdologyRetryConnector.get().onBackpressureLatest().filter(StateIdologyRetryFormPresenter$$Lambda$4.lambdaFactory$()).observeOn(this.mMainScheduler).subscribe(StateIdologyRetryFormPresenter$$Lambda$5.lambdaFactory$(this), StateIdologyRetryFormPresenter$$Lambda$6.lambdaFactory$(this)));
        this.mSubscription.add(this.mIdologyVerificationConnector.get().onBackpressureLatest().observeOn(this.mMainScheduler).subscribe(StateIdologyRetryFormPresenter$$Lambda$7.lambdaFactory$(this), StateIdologyRetryFormPresenter$$Lambda$8.lambdaFactory$(this)));
    }

    static /* synthetic */ Boolean lambda$onShow$0(Boolean isValid) {
        return Boolean.valueOf(isValid != null);
    }

    static /* synthetic */ Boolean lambda$onShow$3(Boolean isValid) {
        return Boolean.valueOf(isValid != null);
    }

    static /* synthetic */ void lambda$onShow$4(StateIdologyRetryFormPresenter this_, Boolean isValid) {
        this_.setContinueButtonEnabled(isValid.booleanValue());
        this_.mScreen.hideProgress();
    }

    void onHide() {
        this.mSubscription.clear();
    }

    boolean onBackPressed() {
        this.mSignInRouter.cancel(StateIdologyRetryFormPresenter$$Lambda$9.lambdaFactory$(this));
        return true;
    }

    void onContinueButtonClicked() {
        if (this.mScreen.isContinueButtonEnabled()) {
            this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_IDENTITY_TAPPED_RESUBMIT, MixpanelTracking.EVENT_VERIFY_IDENTITY_CONTEXT_PROPERTY, this.mIdologyTrackingContext.getContext());
            setContinueButtonEnabled(false);
            this.mScreen.showProgress();
            this.mScreen.submitForm();
        }
    }

    private void setContinueButtonEnabled(boolean isEnabled) {
        this.mScreen.setContinueButtonEnabled(isEnabled);
    }

    private void processVerificationResult(Data idology) {
        if (idology != null && idology.getStatus() != null) {
            saveArgs(idology);
            switch (idology.getStatus()) {
                case HAS_QUESTIONS:
                    this.mIdologyRouter.routeToQuestionsPage(idology);
                    return;
                case SUCCESS:
                    this.mScreen.showProgress();
                    this.mSubscription.add(this.mAuthRouter.routeToNext());
                    return;
                default:
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
