package com.google.android.gms.appinvite;

import android.content.Intent;
import android.os.Bundle;

@Deprecated
public class AppInviteReferral {
    public static String getDeepLink(Intent intent) {
        if (intent != null) {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE");
            if (bundleExtra != null) {
                return bundleExtra.getString("com.google.android.gms.appinvite.DEEP_LINK");
            }
        }
        return null;
    }

    public static boolean hasReferral(Intent intent) {
        return (intent == null || intent.getBundleExtra("com.google.android.gms.appinvite.REFERRAL_BUNDLE") == null) ? false : true;
    }
}
