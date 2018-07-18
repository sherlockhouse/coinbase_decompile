package com.coinbase.android.signin;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.signin.AuthManager.ViewType;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class TwoFactorPresenter {
    public static String PASSWORD = "password";
    public static String TYPE = "type";
    public static String USERNAME = ApiConstants.USERNAME;
    protected String m2faToken;
    private final AuthRouter mAuthRouter;
    private final Scheduler mBackgroundScheduler;
    private final Context mContext;
    private final Logger mLogger = LoggerFactory.getLogger(TwoFactorPresenter.class);
    private final LoginAuthManager mLoginAuthManager;
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    protected String mPassword;
    private String mReferrerId;
    private final TwoFactorRouter mRouter;
    private final TwoFactorScreen mScreen;
    private final SharedPreferences mSharedPrefs;
    private final SignInRouter mSignInRouter;
    private final SnackBarWrapper mSnackBarWrapper;
    private final CompositeSubscription mSubscription = new CompositeSubscription();
    protected String mType;
    protected String mUsername;

    private class SendSMSTask {
        String mEmail;
        String mPassword;
        boolean mShowToast;

        protected SendSMSTask(String email, String password, boolean showToast) {
            this.mEmail = email;
            this.mPassword = password;
            this.mShowToast = showToast;
        }

        protected void onPreExecute() {
            TwoFactorPresenter.this.mScreen.setActionClickable(false);
        }

        public Observable<Void> call() {
            return Observable.create(TwoFactorPresenter$SendSMSTask$$Lambda$1.lambdaFactory$(this)).observeOn(TwoFactorPresenter.this.mMainScheduler).subscribeOn(TwoFactorPresenter.this.mBackgroundScheduler);
        }

        static /* synthetic */ void lambda$call$0(SendSMSTask this_, Subscriber subscriber) {
            try {
                TwoFactorPresenter.this.mLoginManager.sendSMS(this_.mEmail, this_.mPassword);
                subscriber.onNext(null);
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }

        public void onSuccess() {
            if (this.mShowToast) {
                TwoFactorPresenter.this.mSnackBarWrapper.show((int) R.string.login_sms_sent_text);
            }
        }

        public void onException(Exception ex) {
            if (this.mShowToast) {
                TwoFactorPresenter.this.mSnackBarWrapper.show((int) R.string.login_sms_error_text);
            }
        }

        protected void onFinally() {
            TwoFactorPresenter.this.mScreen.setActionClickable(true);
        }
    }

    @Inject
    public TwoFactorPresenter(TwoFactorScreen screen, TwoFactorRouter router, SignInRouter signInRouter, AuthRouter authRouter, LoginAuthManager loginAuthManager, LoginManager loginManager, SnackBarWrapper snackBarWrapper, Application app, MixpanelTracking mixpanelTracking, SharedPreferences sharedPreferences, @MainScheduler Scheduler scheduler, @BackgroundScheduler Scheduler backgroundScheduler) {
        this.mScreen = screen;
        this.mRouter = router;
        this.mSignInRouter = signInRouter;
        this.mAuthRouter = authRouter;
        this.mLoginAuthManager = loginAuthManager;
        this.mLoginManager = loginManager;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mContext = app;
        this.mMixpanelTracking = mixpanelTracking;
        this.mSharedPrefs = sharedPreferences;
        this.mMainScheduler = scheduler;
        this.mBackgroundScheduler = backgroundScheduler;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void onCreate(Bundle args) {
        boolean z = false;
        this.mUsername = args.getString(USERNAME);
        this.mPassword = args.getString(PASSWORD);
        this.mType = args.getString(TYPE);
        runSmsTask(this.mUsername, this.mPassword, false);
        if (this.mType == null) {
            this.mScreen.setActionText("");
            return;
        }
        String str = this.mType;
        switch (str.hashCode()) {
            case -382857747:
                if (str.equals(ApiConstants.GOOGLE_AUTHENTICATOR)) {
                    z = true;
                    break;
                }
            case 114009:
                if (str.equals(ApiConstants.SMS)) {
                    break;
                }
            case 353110498:
                if (str.equals(ApiConstants.AUTHY_APPLICATION)) {
                    z = true;
                    break;
                }
            default:
                z = true;
                break;
        }
        switch (z) {
            case false:
                this.mScreen.setActionText(this.mContext.getString(R.string.login_sms_button));
                this.mScreen.setActionOnClickListener(TwoFactorPresenter$$Lambda$1.lambdaFactory$(this));
                return;
            case true:
                this.mScreen.setActionText(this.mContext.getString(R.string.login_2fa_authy));
                return;
            case true:
                this.mScreen.setActionText(this.mContext.getString(R.string.login_2fa_google));
                return;
            default:
                this.mScreen.setActionText("");
                return;
        }
    }

    void onShow() {
        if (this.mAuthRouter.startedRouting()) {
            this.mScreen.showProgress();
            this.mScreen.hideTwoFactorView();
            this.mSubscription.add(this.mAuthRouter.routeToNext());
            return;
        }
        this.mScreen.hideProgress();
        this.mScreen.showTwoFactorView();
    }

    void onHide() {
        this.mSubscription.clear();
    }

    private void runSmsTask(String userName, String password, boolean showToast) {
        SendSMSTask task = new SendSMSTask(userName, password, showToast);
        task.onPreExecute();
        task.call().observeOn(this.mMainScheduler).subscribe(TwoFactorPresenter$$Lambda$2.lambdaFactory$(task), TwoFactorPresenter$$Lambda$3.lambdaFactory$(this, task), TwoFactorPresenter$$Lambda$4.lambdaFactory$(task));
    }

    static /* synthetic */ void lambda$runSmsTask$2(TwoFactorPresenter this_, SendSMSTask task, Throwable t) {
        if (t instanceof Exception) {
            task.onException((Exception) t);
            task.onFinally();
            return;
        }
        this_.mLogger.error("Throwable not an exception", t);
    }

    void onSubmit() {
        this.m2faToken = this.mScreen.getEnteredText();
        login();
        this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_TWO_STEP_VERIFICATION, new String[0]);
    }

    boolean onBackPressed() {
        this.mSignInRouter.cancel(TwoFactorPresenter$$Lambda$5.lambdaFactory$(this), R.string.abandon_login_message, R.string.no);
        return true;
    }

    static /* synthetic */ void lambda$onBackPressed$4(TwoFactorPresenter this_) {
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_EMAIL_TAPPED_CANCEL, new String[0]);
        this_.m2faToken = null;
    }

    private void login() {
        if (Utils.isConnectedOrConnecting(this.mContext)) {
            this.mScreen.showProgress();
            this.mReferrerId = this.mSharedPrefs.getString("referral", null);
            this.mSubscription.add(this.mLoginAuthManager.getAuthTypeForLogin(this.mUsername, this.mPassword, this.m2faToken, this.mReferrerId).observeOn(this.mMainScheduler).onBackpressureLatest().filter(TwoFactorPresenter$$Lambda$6.lambdaFactory$(this)).flatMap(TwoFactorPresenter$$Lambda$7.lambdaFactory$(this)).subscribe(TwoFactorPresenter$$Lambda$8.lambdaFactory$(this), TwoFactorPresenter$$Lambda$9.lambdaFactory$(this)));
            return;
        }
        Utils.showMessage(this.mContext, (int) R.string.no_internet, 1);
    }

    static /* synthetic */ Boolean lambda$login$5(TwoFactorPresenter this_, LoginAuthResult loginAuthResult) {
        ViewType viewType = loginAuthResult.getViewType();
        if (this_.mRouter.shouldRouteToNext(viewType)) {
            if (viewType == ViewType.TWO_FACTOR) {
                Utils.showMessage(this_.mContext, (int) R.string.login_2fa_invalid, 1);
            }
            return Boolean.valueOf(true);
        }
        this_.mLogger.error("Unexpected view type encountered [" + viewType + "]");
        this_.mRouter.routeBackToLogin();
        return Boolean.valueOf(false);
    }

    static /* synthetic */ void lambda$login$7(TwoFactorPresenter this_, Pair authCode) {
        this_.mScreen.hideTwoFactorView();
        this_.mScreen.showProgress();
        this_.mSubscription.add(this_.mAuthRouter.routeToNext());
    }

    static /* synthetic */ void lambda$login$8(TwoFactorPresenter this_, Throwable t) {
        Utils.showMessage(this_.mContext, t, 1);
        this_.mRouter.routeBackToLogin();
    }
}
