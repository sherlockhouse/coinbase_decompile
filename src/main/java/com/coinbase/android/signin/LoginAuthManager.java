package com.coinbase.android.signin;

import android.os.Build;
import android.util.Pair;
import com.coinbase.android.Constants;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.signin.AuthManager.ViewType;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.models.auth.Auth;
import com.coinbase.api.internal.models.auth.Error;
import java.util.HashMap;
import javax.inject.Inject;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;

@ControllerScope
public class LoginAuthManager {
    private final AuthRouter mAuthRouter;
    private final LoginManager mLoginManager;
    private final Scheduler mMainScheduler;

    @Inject
    public LoginAuthManager(LoginManager loginManager, AuthRouter authRouter, @MainScheduler Scheduler mainScheduler) {
        this.mLoginManager = loginManager;
        this.mAuthRouter = authRouter;
        this.mMainScheduler = mainScheduler;
    }

    public Observable<LoginAuthResult> getAuthTypeForLogin(String email, String password, String twoFactorToken, String referral) {
        HashMap<String, Object> params = new HashMap();
        params.put(ApiConstants.USERNAME, email);
        params.put("password", password);
        params.put("token", twoFactorToken);
        params.put("referral", referral);
        params.put("client_id", Constants.CLIENT_ID);
        params.put("client_secret", Constants.CLIENT_SECRET);
        params.put(ApiConstants.REDIRECT_URI, this.mLoginManager.getRedirectUri());
        params.put(ApiConstants.SCOPE, "all");
        params.put(ApiConstants.DEVICE, Build.MODEL.startsWith(Build.MANUFACTURER) ? Build.MODEL : Build.MANUFACTURER + " " + Build.MODEL);
        return this.mLoginManager.getClient().getAuthCodeRx(params).observeOn(this.mMainScheduler).map(LoginAuthManager$$Lambda$1.lambdaFactory$(this, email, password));
    }

    static /* synthetic */ LoginAuthResult lambda$getAuthTypeForLogin$0(LoginAuthManager this_, String email, String password, Pair pair) {
        Response<Auth> response = pair.first;
        Retrofit retrofit = pair.second;
        if (response.isSuccessful()) {
            return LoginAuthResult.builder().setViewType(ViewType.LOGIN).setAuthCode(((Auth) response.body()).getCode()).build();
        }
        Error error = Utils.getError(response, retrofit);
        if (error == null || error.getError() == null) {
            return LoginAuthResult.builder().setViewType(ViewType.NONE).build();
        }
        String error2 = error.getError();
        boolean z = true;
        switch (error2.hashCode()) {
            case -1625724613:
                if (error2.equals(ApiConstants.RATE_LIMITED)) {
                    z = true;
                    break;
                }
                break;
            case -1595954846:
                if (error2.equals(ApiConstants.INCORRECT_CREDENTIALS)) {
                    z = false;
                    break;
                }
                break;
            case -1535058993:
                if (error2.equals(ApiConstants.UNCONFIRMED_DEVICE)) {
                    z = true;
                    break;
                }
                break;
            case -608131522:
                if (error2.equals(ApiConstants.UNVERIFIED_EMAIL)) {
                    z = true;
                    break;
                }
                break;
            case 1369161521:
                if (error2.equals(ApiConstants.TWO_FACTOR_REQUIRED)) {
                    z = true;
                    break;
                }
                break;
            case 1678333523:
                if (error2.equals(ApiConstants.TWO_FACTOR_INCORRECT)) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return LoginAuthResult.builder().setViewType(ViewType.INCORRECT_CREDENTIALS).build();
            case true:
                return LoginAuthResult.builder().setViewType(ViewType.RATE_LIMITED).build();
            case true:
            case true:
                ActionBarController nextController = this_.mAuthRouter.getNextController(ViewType.TWO_FACTOR, false);
                nextController.getArgs().putString(TwoFactorPresenter.TYPE, error.get_2faAuthentication());
                nextController.getArgs().putString(TwoFactorPresenter.USERNAME, email);
                nextController.getArgs().putString(TwoFactorPresenter.PASSWORD, password);
                return LoginAuthResult.builder().setController(nextController).setViewType(ViewType.TWO_FACTOR).build();
            case true:
                return LoginAuthResult.builder().setController(this_.mAuthRouter.getNextController(ViewType.DEVICE_VERIFICATION, false)).setViewType(ViewType.DEVICE_VERIFICATION).build();
            case true:
                return LoginAuthResult.builder().setController(this_.mAuthRouter.getNextController(ViewType.EMAIL_VERIFICATION, false)).setViewType(ViewType.EMAIL_VERIFICATION).build();
            default:
                return LoginAuthResult.builder().setViewType(ViewType.NONE).build();
        }
    }
}
