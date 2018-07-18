package com.coinbase.android.signin;

import android.text.Html;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.agreement.UserAgreement;
import javax.inject.Inject;
import retrofit2.Response;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class AcceptTermsPresenter {
    private final AuthRouter mAuthRouter;
    private final Scheduler mBackgroundScheduler;
    private final LoginManager mLoginManager;
    private final MixpanelTracking mMixpanelTracking;
    private final Scheduler mScheduler;
    private final AcceptTermsScreen mScreen;
    private final SignInRouter mSignInRouter;
    private final SnackBarWrapper mSnackbarWrapper;
    private CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public AcceptTermsPresenter(AcceptTermsScreen screen, AuthRouter authRouter, LoginManager loginManager, SnackBarWrapper snackBarWrapper, SignInRouter signInRouter, MixpanelTracking mixpanelTracking, @BackgroundScheduler Scheduler backgroundScheduler, @MainScheduler Scheduler scheduler) {
        this.mScreen = screen;
        this.mAuthRouter = authRouter;
        this.mLoginManager = loginManager;
        this.mSnackbarWrapper = snackBarWrapper;
        this.mSignInRouter = signInRouter;
        this.mMixpanelTracking = mixpanelTracking;
        this.mBackgroundScheduler = backgroundScheduler;
        this.mScheduler = scheduler;
    }

    void onShow() {
        setTNCDescription();
    }

    void onHide() {
        this.mSubscription.clear();
        this.mScreen.hideProgressDialog();
    }

    boolean onBackPressed() {
        this.mSignInRouter.cancel(AcceptTermsPresenter$$Lambda$1.lambdaFactory$(this));
        return true;
    }

    private void setTNCDescription() {
        this.mScreen.showProgressDialog();
        this.mSubscription.add(this.mLoginManager.getClient().getUserAgreementRx().onBackpressureLatest().map(AcceptTermsPresenter$$Lambda$2.lambdaFactory$()).subscribeOn(this.mBackgroundScheduler).observeOn(this.mScheduler).subscribe(AcceptTermsPresenter$$Lambda$3.lambdaFactory$(this), AcceptTermsPresenter$$Lambda$4.lambdaFactory$(this)));
    }

    static /* synthetic */ Pair lambda$setTNCDescription$1(Pair pair) {
        Response response = pair.first;
        if (!response.isSuccessful()) {
            return new Pair(null, Utils.getErrorMessage(response));
        }
        if (response.body() == null || ((UserAgreement) response.body()).getData() == null) {
            return new Pair(null, null);
        }
        Pair<Spanned, String> result;
        try {
            result = new Pair(Html.fromHtml(((UserAgreement) response.body()).getData().getHtml()), null);
        } catch (IndexOutOfBoundsException e) {
            result = new Pair(new SpannedString(((UserAgreement) response.body()).getData().getHtml()), null);
        }
        return result;
    }

    static /* synthetic */ void lambda$setTNCDescription$2(AcceptTermsPresenter this_, Pair pair) {
        Spanned html = pair.first;
        String error = pair.second;
        this_.mScreen.hideProgressDialog();
        if (!TextUtils.isEmpty(error)) {
            this_.mSnackbarWrapper.show(error);
        } else if (!TextUtils.isEmpty(html)) {
            this_.mScreen.setTermsDescription(html);
        }
    }

    static /* synthetic */ void lambda$setTNCDescription$3(AcceptTermsPresenter this_, Throwable t) {
        this_.mSnackbarWrapper.showFailure(t);
        this_.mScreen.hideProgressDialog();
    }

    void onAcceptTermsClicked() {
        this.mScreen.showProgressDialog();
        this.mSubscription.add(this.mLoginManager.getClient().acceptUserAgreementRx().observeOn(this.mScheduler).subscribe(AcceptTermsPresenter$$Lambda$5.lambdaFactory$(this), AcceptTermsPresenter$$Lambda$6.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$onAcceptTermsClicked$4(AcceptTermsPresenter this_, Pair pair) {
        Response<Void> response = pair.first;
        this_.mScreen.hideProgressDialog();
        if (response.isSuccessful()) {
            this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_ACCEPT_TOS, new String[0]);
            this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_TRANSACTION_PAGE_SHOWN, new String[0]);
            this_.mSubscription.add(this_.mAuthRouter.routeToNext());
            return;
        }
        this_.mSnackbarWrapper.showError(response);
    }

    static /* synthetic */ void lambda$onAcceptTermsClicked$5(AcceptTermsPresenter this_, Throwable t) {
        this_.mSnackbarWrapper.showFailure(t);
        this_.mScreen.hideProgressDialog();
    }

    void onCancelClicked() {
        this.mSignInRouter.cancel(AcceptTermsPresenter$$Lambda$7.lambdaFactory$(this));
    }
}
