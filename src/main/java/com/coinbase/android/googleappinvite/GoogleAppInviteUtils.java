package com.coinbase.android.googleappinvite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.coinbase.android.R;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.GoogleApiAvailability;

public class GoogleAppInviteUtils {
    public static void setReferrerId(Context context, Intent intent) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (AppInviteReferral.hasReferral(intent)) {
            String deepLink = AppInviteReferral.getDeepLink(intent);
            if (deepLink != null) {
                prefs.edit().putString("referral", deepLink.substring(context.getString(R.string.app_inivite_deeplink).length())).apply();
            }
        }
    }

    public static boolean supportedGooglePlayServices(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0) {
            return true;
        }
        return false;
    }
}
