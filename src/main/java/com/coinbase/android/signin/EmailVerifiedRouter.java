package com.coinbase.android.signin;

import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.ControllerScope;
import javax.inject.Inject;

@ControllerScope
public class EmailVerifiedRouter {
    private final AppCompatActivity mActivity;

    @Inject
    EmailVerifiedRouter(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    void routCheckEmailVerifiedError() {
        this.mActivity.finish();
    }
}
