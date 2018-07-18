package com.coinbase.android.signin;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.signin.AuthManager.ViewType;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.MainScheduler;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Scheduler;

@ControllerScope
public class TwoFactorRouter {
    private final AuthRouter mAuthRouter;
    private final Scheduler mBackgroundScheduler;
    private final Context mContext;
    private final ActionBarController mController;
    private final Logger mLogger = LoggerFactory.getLogger(TwoFactorRouter.class);
    private final Scheduler mMainScheduler;

    @Inject
    public TwoFactorRouter(ActionBarController controller, Application app, AuthRouter authRouter, @BackgroundScheduler Scheduler backgroundScheduler, @MainScheduler Scheduler mainScheduler) {
        this.mController = controller;
        this.mContext = app;
        this.mAuthRouter = authRouter;
        this.mBackgroundScheduler = backgroundScheduler;
        this.mMainScheduler = mainScheduler;
    }

    boolean shouldRouteToNext(ViewType type) {
        return type == ViewType.LOGIN || type == ViewType.DEVICE_VERIFICATION || type == ViewType.TWO_FACTOR;
    }

    Observable<Pair<Boolean, String>> routeToNext(LoginAuthResult loginAuthResult) {
        String authCode = loginAuthResult.getAuthCode();
        ActionBarController nextController = loginAuthResult.getController();
        switch (loginAuthResult.getViewType()) {
            case LOGIN:
                if (!TextUtils.isEmpty(authCode)) {
                    return new OAuthTask(this.mContext, authCode).call().subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainScheduler).flatMap(TwoFactorRouter$$Lambda$1.lambdaFactory$(this, authCode));
                }
                routeBackToLogin();
                return Observable.never();
            case TWO_FACTOR:
                pushOrReplaceController(nextController);
                return Observable.never();
            case DEVICE_VERIFICATION:
                routeToDeviceVerify(nextController, authCode);
                return Observable.never();
            default:
                routeBackToLogin();
                return Observable.never();
        }
    }

    static /* synthetic */ Observable lambda$routeToNext$0(TwoFactorRouter this_, String authCode, Boolean result) {
        if (result.booleanValue()) {
            return Observable.just(new Pair(Boolean.valueOf(true), authCode));
        }
        this_.routeToDeviceVerify(authCode);
        return Observable.never();
    }

    void routeBackToLogin() {
        this.mController.getRouter().popToRoot();
    }

    private void routeToDeviceVerify(String authCode) {
        routeToDeviceVerify(this.mAuthRouter.getNextController(ViewType.DEVICE_VERIFICATION, false), authCode);
    }

    private void routeToDeviceVerify(ActionBarController deviceVerifyController, String authCode) {
        deviceVerifyController.getArgs().putString(LoginController.AUTH_CODE, authCode);
        pushOrReplaceController(deviceVerifyController);
    }

    private void pushOrReplaceController(ActionBarController nextController) {
        if (!this.mController.getClass().equals(nextController.getClass())) {
            if (this.mController.getRouter().getBackstackSize() <= 1) {
                this.mController.pushModalController(nextController);
            } else {
                this.mController.replaceModalController(nextController);
            }
        }
    }
}
