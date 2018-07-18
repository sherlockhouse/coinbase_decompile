package com.tenjin.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import java.net.URLEncoder;

public class TenjinReferrerReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            String referrerString = intent.getStringExtra("referrer");
            Log.d("REF", referrerString);
            SharedPreferences preferences = context.getSharedPreferences("tenjinInstallPreferences", 0);
            if (referrerString != null) {
                referrerString = URLEncoder.encode(referrerString, "UTF-8");
                Log.d("REF", referrerString);
                preferences.edit().putString("tenjinInstallReferrer", referrerString).commit();
            }
            preferences.edit().putBoolean("containsReferrerKey", true).commit();
            ReferrerUpdater.getInstance().notifyReferrerUpdated();
        } catch (Exception e) {
        }
    }
}
