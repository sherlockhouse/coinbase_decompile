package com.coinbase.android.signin.state;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.signin.AuthRouter;
import com.coinbase.android.signin.SignInRouter;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.jumio.Data;
import com.coinbase.api.internal.models.jumio.FailureDescription;
import com.coinbase.api.internal.models.jumio.JumioProfiles;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import rx.Scheduler;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class UpfrontKycIdentityProcessingPresenter {
    public static final String IS_INTERSTITIAL = "is_interstitial";
    private static final long SERVER_ERROR_TIMEOUT = 30;
    private static final long WAITING_FOR_PROCESSING_TIMEOUT = 10;
    private Bundle mArgs;
    private final AuthRouter mAuthRouter;
    private final Logger mLogger = LoggerFactory.getLogger(UpfrontKycIdentityProcessingPresenter.class);
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final UpfrontKycIdentityProcessingRouter mRouter;
    private final UpfrontKycIdentityProcessingScreen mScreen;
    private final SignInRouter mSignInRouter;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public UpfrontKycIdentityProcessingPresenter(UpfrontKycIdentityProcessingScreen screen, LoginManager loginManager, AuthRouter authRouter, SignInRouter signInRouter, UpfrontKycIdentityProcessingRouter router, @MainScheduler Scheduler mainScheduler) {
        this.mScreen = screen;
        this.mLoginManager = loginManager;
        this.mAuthRouter = authRouter;
        this.mSignInRouter = signInRouter;
        this.mRouter = router;
        this.mMainScheduler = mainScheduler;
    }

    void onShow(Bundle args) {
        if (this.mAuthRouter.startedRouting()) {
            this.mSubscription.add(this.mAuthRouter.routeToNext());
            return;
        }
        this.mArgs = args;
        this.mSubscription.clear();
        this.mScreen.showProgress();
        waitForIdv();
    }

    void onHide() {
        this.mSubscription.clear();
    }

    boolean onBackPressed() {
        this.mSignInRouter.cancel(UpfrontKycIdentityProcessingPresenter$$Lambda$1.lambdaFactory$());
        return true;
    }

    static /* synthetic */ void lambda$onBackPressed$0() {
    }

    private void waitForIdv() {
        this.mSubscription.add(getJumioProfilesDelayed(0));
    }

    private Subscription getJumioProfilesDelayed(long delay) {
        return this.mLoginManager.getClient().getJumioProfilesRx().delay(delay, TimeUnit.SECONDS).observeOn(this.mMainScheduler).first().subscribe(UpfrontKycIdentityProcessingPresenter$$Lambda$2.lambdaFactory$(this), UpfrontKycIdentityProcessingPresenter$$Lambda$3.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$getJumioProfilesDelayed$1(UpfrontKycIdentityProcessingPresenter this_, Pair responseRetrofitPair) {
        this_.mScreen.hideProgress();
        this_.mSubscription.clear();
        Response<JumioProfiles> response = responseRetrofitPair.first;
        if (response.isSuccessful()) {
            switch (((JumioProfiles) response.body()).getJumioProfileStatus()) {
                case COMPLETED:
                    this_.mSubscription.add(this_.mAuthRouter.routeToNext());
                    return;
                case PENDING:
                    this_.mArgs.putBoolean(IS_INTERSTITIAL, false);
                    this_.mSubscription.add(this_.getJumioProfilesDelayed(WAITING_FOR_PROCESSING_TIMEOUT));
                    return;
                default:
                    if (this_.mArgs.getBoolean(IS_INTERSTITIAL, false)) {
                        this_.mRouter.routeToDocumentSelector();
                        return;
                    } else if (((JumioProfiles) response.body()).getData() == null) {
                        this_.mLogger.error("Jumio profiles empty in handle failure, shouldn't happen");
                        return;
                    } else {
                        this_.handleFailure(((JumioProfiles) response.body()).getData());
                        return;
                    }
            }
        }
        this_.mSubscription.add(this_.getJumioProfilesDelayed(SERVER_ERROR_TIMEOUT));
    }

    private void handleFailure(List<Data> jumioProfiles) {
        if (jumioProfiles.isEmpty()) {
            this.mLogger.error("Jumio profiles empty in handle failure, shouldn't happen");
            return;
        }
        Data failedAttempt = (Data) jumioProfiles.get(0);
        if (failedAttempt == null || failedAttempt.getFailureDescription() == null) {
            this.mLogger.error("Couldn't get failure description., shouldn't happen.");
            return;
        }
        FailureDescription failureDescription = failedAttempt.getFailureDescription();
        String header = failureDescription.getHeader();
        String hint = failureDescription.getHint();
        if (TextUtils.isEmpty(header) || TextUtils.isEmpty(hint)) {
            this.mLogger.error("Header or hint empty, shouldn't happen");
        } else {
            this.mRouter.routeFailure(header, hint);
        }
    }
}
