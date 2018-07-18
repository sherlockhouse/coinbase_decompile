package com.coinbase.android.signin;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.Constants;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.PreferenceUtils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

@ControllerScope
public class DeviceVerifyPresenter {
    private static final int RETRY_WAIT_TIME = 2000;
    private final AppCompatActivity mActivity;
    private String mAuthCode;
    private final AuthRouter mAuthRouter;
    private final Context mContext;
    private final DeviceVerifyConnector mDeviceVerifyConnector;
    private Timer mDeviceVerifyTimer = null;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final DeviceVerifyRouter mRouter;
    private final DeviceVerifyScreen mScreen;
    private final SignInRouter mSignInRouter;
    CompositeSubscription mSubscription = new CompositeSubscription();
    private OAuthTask oauthTask;
    private final Logger sLogger = LoggerFactory.getLogger(DeviceVerifyPresenter.class);

    @Inject
    public DeviceVerifyPresenter(DeviceVerifyScreen screen, DeviceVerifyRouter router, AuthRouter authRouter, SignInRouter signInRouter, AppCompatActivity activity, Application app, DeviceVerifyConnector deviceVerifyConnector, MixpanelTracking mixpanelTracking, @MainScheduler Scheduler mainScheduler) {
        this.mScreen = screen;
        this.mRouter = router;
        this.mAuthRouter = authRouter;
        this.mSignInRouter = signInRouter;
        this.mActivity = activity;
        this.mContext = app;
        this.mDeviceVerifyConnector = deviceVerifyConnector;
        this.mMixpanelTracking = mixpanelTracking;
        this.mMainScheduler = mainScheduler;
    }

    void onCreate(Bundle bundle) {
        if (bundle != null) {
            this.mAuthCode = bundle.getString(LoginController.AUTH_CODE);
            PreferenceUtils.putPrefsString(this.mContext, Constants.KEY_LOGIN_CODE_WAITING_FOR_DV, this.mAuthCode);
        }
        execute();
    }

    void onShow() {
        if (this.mAuthRouter.startedRouting()) {
            this.mScreen.showProgress();
            this.mScreen.hideDeviceVerifyView();
            this.mSubscription.add(this.mAuthRouter.routeToNext());
            return;
        }
        this.mScreen.hideProgress();
        this.mScreen.showDeviceVerifyView();
    }

    void onHide() {
        this.mSubscription.clear();
    }

    boolean onBackPressed() {
        this.mSignInRouter.cancel(DeviceVerifyPresenter$$Lambda$1.lambdaFactory$(this), R.string.abandon_login_message);
        return true;
    }

    static /* synthetic */ void lambda$onBackPressed$0(DeviceVerifyPresenter this_) {
        this_.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_VERIFY_DEVICE_TAPPED_EXIT_LOGIN_FLOW, new String[0]);
        PreferenceUtils.putPrefsString(this_.mContext, Constants.KEY_LOGIN_CODE_WAITING_FOR_DV, null);
    }

    private void onOAuthSuccess() {
        PreferenceUtils.putPrefsString(this.mContext, Constants.KEY_LOGIN_CODE_WAITING_FOR_DV, null);
        cancelTimer();
        this.mScreen.hideDeviceVerifyView();
        this.mScreen.showProgress();
        this.mSubscription.add(this.mAuthRouter.routeToNext());
    }

    private void onOAuthFailed(Exception e) {
        this.mDeviceVerifyConnector.getFailed().onNext(new ClassConsumableEvent(e));
        this.mRouter.routeToFailure();
    }

    private void execute() {
        cancelTimer();
        this.mDeviceVerifyTimer = new Timer();
        this.mDeviceVerifyTimer.schedule(new TimerTask() {
            public void run() {
                DeviceVerifyPresenter.this.mActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        DeviceVerifyPresenter.this.oauthTask = new OAuthTask(DeviceVerifyPresenter.this.mContext, DeviceVerifyPresenter.this.mAuthCode);
                        DeviceVerifyPresenter.this.mSubscription.add(DeviceVerifyPresenter.this.oauthTask.call().observeOn(DeviceVerifyPresenter.this.mMainScheduler).subscribe(DeviceVerifyPresenter$1$1$$Lambda$1.lambdaFactory$(this), DeviceVerifyPresenter$1$1$$Lambda$2.lambdaFactory$(this)));
                    }

                    static /* synthetic */ void lambda$run$0(AnonymousClass1 this_, Boolean success) {
                        if (success.booleanValue()) {
                            DeviceVerifyPresenter.this.onOAuthSuccess();
                        } else {
                            DeviceVerifyPresenter.this.execute();
                        }
                    }

                    static /* synthetic */ void lambda$run$4(AnonymousClass1 this_, Throwable t) {
                        if (t instanceof Exception) {
                            DeviceVerifyPresenter.this.mSubscription.add(Observable.create(DeviceVerifyPresenter$1$1$$Lambda$3.lambdaFactory$(this_, t)).subscribeOn(DeviceVerifyPresenter.this.mMainScheduler).subscribe(DeviceVerifyPresenter$1$1$$Lambda$4.lambdaFactory$(), DeviceVerifyPresenter$1$1$$Lambda$5.lambdaFactory$()));
                        } else {
                            DeviceVerifyPresenter.this.sLogger.error("Throwable not an exception", t);
                        }
                    }

                    static /* synthetic */ void lambda$null$1(AnonymousClass1 this_, Throwable t, Subscriber subscriber) {
                        DeviceVerifyPresenter.this.onOAuthFailed((Exception) t);
                        subscriber.onNext(null);
                        subscriber.onCompleted();
                    }

                    static /* synthetic */ void lambda$null$2(Object _unused) {
                    }

                    static /* synthetic */ void lambda$null$3(Throwable t2) {
                    }
                });
            }
        }, 2000);
    }

    private void cancelTimer() {
        if (this.mDeviceVerifyTimer != null) {
            this.mDeviceVerifyTimer.cancel();
            this.mDeviceVerifyTimer.purge();
        }
        if (this.oauthTask != null) {
            this.mSubscription.clear();
            this.oauthTask = null;
        }
    }
}
