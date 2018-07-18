package com.coinbase.android.signin;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Pair;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.signin.EmailVerifiedConnector.Type;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.ActivityPermissionCheckUtils;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.user.User;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import rx.Scheduler;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class EmailVerifyPresenter {
    public static final String EMAIL = "EMAIL";
    public static final String EMAIL_SCHEME = "com.coinbase.android.consumer";
    private static final int RETRY_WAIT_TIME = 2000;
    private final AppCompatActivity mActivity;
    private final AuthRouter mAuthRouter;
    private final Context mContext;
    private final EmailVerifiedConnector mEmailVerifiedConnector;
    private final EmailVerifiedRouter mEmailVerifiedRouter;
    volatile Timer mEmailVerifyRetryTimer = null;
    Subscription mGetUserTask;
    private final Logger mLogger = LoggerFactory.getLogger(EmailVerifyPresenter.class);
    private final LoginManager mLoginManager;
    private final MixpanelTracking mMixpanelTracking;
    private final ActivityPermissionCheckUtils mPermissionCheckUtils;
    private final Scheduler mScheduler;
    private final EmailVerifyScreen mScreen;
    private final SignInRouter mSignInRouter;
    private final SplitTesting mSplitTesting;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Inject
    public EmailVerifyPresenter(EmailVerifyScreen screen, EmailVerifiedRouter emailVerifiedRouter, AuthRouter authRouter, SignInRouter signInRouter, MixpanelTracking mixpanelTracking, SplitTesting splitTesting, EmailVerifiedConnector emailVerifiedConnector, AppCompatActivity appCompatActivity, ActivityPermissionCheckUtils permissionCheckUtils, Application app, LoginManager loginManager, @MainScheduler Scheduler scheduler) {
        this.mScreen = screen;
        this.mEmailVerifiedRouter = emailVerifiedRouter;
        this.mAuthRouter = authRouter;
        this.mSignInRouter = signInRouter;
        this.mMixpanelTracking = mixpanelTracking;
        this.mSplitTesting = splitTesting;
        this.mEmailVerifiedConnector = emailVerifiedConnector;
        this.mActivity = appCompatActivity;
        this.mPermissionCheckUtils = permissionCheckUtils;
        this.mContext = app;
        this.mLoginManager = loginManager;
        this.mScheduler = scheduler;
    }

    void onCreate(Bundle args) {
        this.mSubscription.add(this.mSplitTesting.splitTestsInitialized().first().observeOn(this.mScheduler).onBackpressureLatest().subscribe(EmailVerifyPresenter$$Lambda$1.lambdaFactory$(this), EmailVerifyPresenter$$Lambda$2.lambdaFactory$(this)));
        checkSecurityAndSetMessage(args);
    }

    void onCancelClicked() {
        cancel(true);
    }

    boolean onBackPressed() {
        cancel(false);
        return true;
    }

    void onShow() {
        if (this.mAuthRouter.startedRouting()) {
            this.mScreen.showProgress();
            this.mScreen.hideEmailView();
            this.mSubscription.add(this.mAuthRouter.routeToNext());
            return;
        }
        this.mScreen.hideProgress();
        this.mScreen.showEmailView();
        execute();
    }

    void onHide() {
        cancelTimer();
        this.mSubscription.clear();
    }

    private void cancel(boolean changeEmail) {
        this.mSignInRouter.cancel(EmailVerifyPresenter$$Lambda$3.lambdaFactory$(this, changeEmail), changeEmail ? R.string.abandon_email_message : R.string.abandon_signup_message);
    }

    static /* synthetic */ void lambda$cancel$2(EmailVerifyPresenter this_, boolean changeEmail) {
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_EMAIL_TAPPED_CANCEL, new String[0]);
        this_.mEmailVerifiedConnector.get().onNext(new ClassConsumableEvent(changeEmail ? Type.CHANGE_REQUESTED : Type.FAILED));
    }

    private void checkSecurityAndSetMessage(Bundle bundle) {
        Bundle intentExtras = this.mActivity.getIntent().getExtras();
        String email = "";
        if (intentExtras != null) {
            if (!intentExtras.containsKey(EMAIL) || this.mPermissionCheckUtils.checkCallingPackage(this.mActivity)) {
                email = intentExtras.getString(EMAIL);
            } else {
                this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_PERMISSION_CHECK_FAILED, new String[0]);
                throw new SecurityException("Invalid calling package [" + this.mActivity.getCallingPackage() + "]");
            }
        } else if (bundle.containsKey(EMAIL)) {
            email = bundle.getString(EMAIL);
        }
        this.mScreen.setMessage(Html.fromHtml(String.format(this.mContext.getString(R.string.signup_email_verification_instructions), new Object[]{email})));
    }

    public void cancelTimer() {
        if (this.mEmailVerifyRetryTimer != null) {
            this.mEmailVerifyRetryTimer.cancel();
            this.mEmailVerifyRetryTimer.purge();
            this.mEmailVerifyRetryTimer = null;
        }
        AsyncTask.execute(new Runnable() {
            public void run() {
                if (EmailVerifyPresenter.this.mGetUserTask != null) {
                    EmailVerifyPresenter.this.mGetUserTask.unsubscribe();
                    EmailVerifyPresenter.this.mGetUserTask = null;
                }
            }
        });
    }

    public void execute() {
        cancelTimer();
        this.mEmailVerifyRetryTimer = new Timer();
        this.mEmailVerifyRetryTimer.schedule(new TimerTask() {
            public void run() {
                EmailVerifyPresenter.this.mActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        EmailVerifyPresenter.this.mGetUserTask = EmailVerifyPresenter.this.getUser();
                    }
                });
            }
        }, 2000);
    }

    private Subscription getUser() {
        return this.mLoginManager.getClient().getUserRx().onBackpressureLatest().observeOn(this.mScheduler).first().subscribe(EmailVerifyPresenter$$Lambda$4.lambdaFactory$(this), EmailVerifyPresenter$$Lambda$5.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$getUser$5(EmailVerifyPresenter this_, Pair pair) {
        Response<User> response = pair.first;
        this_.cancelTimer();
        if (!response.isSuccessful()) {
            this_.mEmailVerifiedConnector.get().onNext(new ClassConsumableEvent(Type.FAILED));
            this_.mEmailVerifiedRouter.routCheckEmailVerifiedError();
        } else if (Utils.isUserEmailVerified(((User) response.body()).getData().getRestrictions())) {
            this_.mSubscription.add(this_.mAuthRouter.routeToNext((User) response.body()).observeOn(this_.mScheduler).onBackpressureLatest().subscribe(EmailVerifyPresenter$$Lambda$6.lambdaFactory$(), EmailVerifyPresenter$$Lambda$7.lambdaFactory$()));
        } else {
            this_.execute();
        }
    }

    static /* synthetic */ void lambda$null$3(Void _unused) {
    }

    static /* synthetic */ void lambda$null$4(Throwable t) {
    }
}
