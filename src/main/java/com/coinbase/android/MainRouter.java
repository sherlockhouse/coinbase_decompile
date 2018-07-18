package com.coinbase.android;

import android.content.Intent;
import com.coinbase.android.signin.LaunchMessageActivity;
import javax.inject.Inject;

@ActivityScope
public class MainRouter {
    MainActivity mActivity;

    @Inject
    MainRouter(MainActivity activity) {
        this.mActivity = activity;
    }

    public void routeServerOutageMessage() {
        Intent intent = new Intent(this.mActivity, LaunchMessageActivity.class);
        intent.putExtra(LaunchMessageActivity.TITLE, this.mActivity.getString(R.string.error));
        intent.putExtra(LaunchMessageActivity.DESCRIPTION, this.mActivity.getString(R.string.server_outage_message));
        intent.putExtra(LaunchMessageActivity.DISMISSIBLE, true);
        intent.putExtra(LaunchMessageActivity.ACTION, this.mActivity.getString(R.string.ok));
        this.mActivity.startActivity(intent);
    }
}
