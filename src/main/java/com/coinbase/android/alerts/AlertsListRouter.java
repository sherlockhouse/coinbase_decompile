package com.coinbase.android.alerts;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.ControllerScope;
import javax.inject.Inject;

@ControllerScope
public class AlertsListRouter {
    private final Activity mActivity;

    @Inject
    public AlertsListRouter(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    public boolean showLearnMore(Uri uri) {
        this.mActivity.startActivity(new Intent("android.intent.action.VIEW", uri));
        return true;
    }
}
