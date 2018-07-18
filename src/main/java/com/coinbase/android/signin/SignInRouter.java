package com.coinbase.android.signin;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.LoginManager;
import javax.inject.Inject;
import rx.functions.Action0;

@ControllerScope
public class SignInRouter {
    private final ActionBarController mActionBarController;
    private final AppCompatActivity mActivity;
    private final LoginManager mLoginManager;

    @Inject
    public SignInRouter(AppCompatActivity activity, LoginManager loginManager, ActionBarController actionBarController) {
        this.mActivity = activity;
        this.mLoginManager = loginManager;
        this.mActionBarController = actionBarController;
    }

    public void cancel(Action0 okAction) {
        cancel(okAction, R.string.abandon_signup_message);
    }

    public void cancel(Action0 okAction, int message) {
        cancel(okAction, message, R.string.cancel);
    }

    public void cancel(Action0 okAction, int message, int cancelText) {
        Builder alertBuilder = new Builder(this.mActivity);
        alertBuilder.setMessage(this.mActivity.getString(message));
        alertBuilder.setPositiveButton(R.string.ok, SignInRouter$$Lambda$1.lambdaFactory$(this, okAction));
        alertBuilder.setNegativeButton(cancelText, SignInRouter$$Lambda$2.lambdaFactory$());
        alertBuilder.show();
    }

    static /* synthetic */ void lambda$cancel$0(SignInRouter this_, Action0 okAction, DialogInterface dialog, int which) {
        this_.mLoginManager.signout();
        this_.mActionBarController.getRouter().popToRoot();
        okAction.call();
    }

    static /* synthetic */ void lambda$cancel$1(DialogInterface dialog, int which) {
    }
}
