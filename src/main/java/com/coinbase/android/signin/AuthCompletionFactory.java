package com.coinbase.android.signin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.bluelinelabs.conductor.RouterTransaction;
import com.coinbase.android.MainActivity;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import javax.inject.Inject;
import rx.Observable;
import rx.Scheduler;

public class AuthCompletionFactory {
    private final AppCompatActivity mAppCompatActivity;
    private final AuthManager mAuthManager;
    private final ActionBarController mController;
    private final CredentialsApiRxWrapper mCredentialsApiRxWrapper;
    private final Scheduler mMainScheduler;
    private final MixpanelTracking mMixpanelTracking;
    private final SharedPreferences mSharedPrefs;

    public interface AuthCompletionHandler {
        Observable<Void> complete();
    }

    private class LoginCompletionHandler implements AuthCompletionHandler {
        private LoginCompletionHandler() {
        }

        public Observable<Void> complete() {
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_DEVICE_VERIFIED, new String[0]);
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_TRANSACTION_PAGE_SHOWN, new String[0]);
            Bundle rootArgs = ((RouterTransaction) AuthCompletionFactory.this.mController.getRouter().getBackstack().get(0)).controller().getArgs();
            return AuthCompletionFactory.this.mCredentialsApiRxWrapper.saveCredentials(rootArgs.getString("email"), rootArgs.getString("password"), "", "").onBackpressureLatest().observeOn(AuthCompletionFactory.this.mMainScheduler).map(AuthCompletionFactory$LoginCompletionHandler$$Lambda$1.lambdaFactory$(this));
        }
    }

    private class SignUpCompletionHandler implements AuthCompletionHandler {
        private SignUpCompletionHandler() {
        }

        public Observable<Void> complete() {
            AuthCompletionFactory.this.mMixpanelTracking.trackEvent(MixpanelTracking.EVENT_EMAIL_VERIFICATION, new String[0]);
            Bundle rootArgs = ((RouterTransaction) AuthCompletionFactory.this.mController.getRouter().getBackstack().get(0)).controller().getArgs();
            String email = rootArgs.getString("SIGN_UP_EMAIL");
            String password = rootArgs.getString("SIGN_UP_PASSWORD");
            String firstName = rootArgs.getString("FIRST_NAME");
            String lastName = rootArgs.getString("LAST_NAME");
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                email = AuthCompletionFactory.this.mSharedPrefs.getString("SIGN_UP_EMAIL", "");
                password = AuthCompletionFactory.this.mSharedPrefs.getString("SIGN_UP_PASSWORD", "");
                firstName = AuthCompletionFactory.this.mSharedPrefs.getString("FIRST_NAME", "");
                lastName = AuthCompletionFactory.this.mSharedPrefs.getString("LAST_NAME", "");
            }
            AuthCompletionFactory.this.mSharedPrefs.edit().putString("SIGN_UP_EMAIL", null).putString("SIGN_UP_PASSWORD", null).putString("FIRST_NAME", null).putString("LAST_NAME", null).apply();
            return AuthCompletionFactory.this.mCredentialsApiRxWrapper.saveCredentials(email, password, firstName, lastName).onBackpressureLatest().observeOn(AuthCompletionFactory.this.mMainScheduler).map(AuthCompletionFactory$SignUpCompletionHandler$$Lambda$1.lambdaFactory$(this));
        }
    }

    @Inject
    public AuthCompletionFactory(ActionBarController actionBarController, SharedPreferences sharedPreferences, AppCompatActivity appCompatActivity, CredentialsApiRxWrapper credentialsApiRxWrapper, MixpanelTracking mixpanelTracking, @MainScheduler Scheduler mainScheduler, AuthManager authManager) {
        this.mController = actionBarController;
        this.mSharedPrefs = sharedPreferences;
        this.mAppCompatActivity = appCompatActivity;
        this.mCredentialsApiRxWrapper = credentialsApiRxWrapper;
        this.mMixpanelTracking = mixpanelTracking;
        this.mMainScheduler = mainScheduler;
        this.mAuthManager = authManager;
    }

    public AuthCompletionHandler get() {
        if (this.mAuthManager.isSignUp()) {
            return new SignUpCompletionHandler();
        }
        return new LoginCompletionHandler();
    }

    protected void proceed() {
        this.mAuthManager.clearAuthScreen();
        Intent intent = new Intent(this.mAppCompatActivity, MainActivity.class);
        intent.setFlags(67108864);
        this.mAppCompatActivity.startActivity(intent);
        this.mAppCompatActivity.finish();
    }
}
