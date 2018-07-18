package com.coinbase.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.coinbase.android.googleappinvite.GoogleAppInviteUtils;
import com.coinbase.android.utils.analytics.MixpanelTracking;

public class ReferrerReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!(action == null || !TextUtils.equals(action, "com.android.vending.INSTALL_REFERRER") || intent.getStringExtra("referrer") == null)) {
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_INSTALL_REFERRER, MixpanelTracking.PROPERTY_INSTALL_SOURCE, referrer);
        }
        GoogleAppInviteUtils.setReferrerId(context, intent);
    }
}
