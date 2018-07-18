package com.tenjin.android;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Base64;
import java.util.HashMap;
import java.util.Map;

class TenjinSDK$AsyncConnect extends AsyncTask<Void, Void, Boolean> {
    final /* synthetic */ TenjinSDK this$0;

    private TenjinSDK$AsyncConnect(TenjinSDK tenjinSDK) {
        this.this$0 = tenjinSDK;
    }

    protected Boolean doInBackground(Void... params) {
        boolean success = false;
        try {
            String type = "Basic " + Base64.encodeToString(TenjinSDK.access$500(this.this$0).getBytes(), 10);
            Map<String, String> headers = new HashMap();
            headers.put("Authorization", type);
            if (TenjinSDK.access$600(this.this$0) || TenjinSDK.access$700().get()) {
                return Boolean.valueOf(new HttpConnection().connect("https://track.tenjin.io/v0/event", TenjinSDK.access$800(this.this$0, "connect"), headers));
            }
            Boolean valueOf;
            synchronized (TenjinSDK.access$900(this.this$0)) {
                try {
                    TenjinSDK.access$900(this.this$0).wait(15000);
                    success = new HttpConnection().connect("https://track.tenjin.io/v0/event", TenjinSDK.access$800(this.this$0, "connect"), headers);
                    valueOf = Boolean.valueOf(success);
                } catch (InterruptedException e) {
                    return Boolean.valueOf(false);
                }
            }
            return valueOf;
        } catch (Exception e2) {
            e2.printStackTrace();
            return Boolean.valueOf(success);
        }
    }

    protected void onPostExecute(Boolean success) {
        SharedPreferences settings = TenjinSDK.access$1000(this.this$0).getSharedPreferences("tenjinInstallPreferences", 0);
        boolean referrerSent = settings.getBoolean("tenjinInstallReferrerSent", false);
        if (success.booleanValue() && !referrerSent) {
            settings.edit().putBoolean("tenjinInstallReferrerSent", true).commit();
        }
        boolean googleInstallReferrerSent = settings.getBoolean("tenjinGoogleInstallReferrerSent", false);
        if (success.booleanValue() && !googleInstallReferrerSent) {
            settings.edit().putBoolean("tenjinGoogleInstallReferrerSent", true).commit();
        }
        synchronized (TenjinSDK.access$1100()) {
            TenjinSDK.access$1100().set(success.booleanValue());
        }
        TenjinSDK.access$1202(this.this$0, null);
        if (success.booleanValue()) {
            TenjinSDK.access$1300(this.this$0);
        }
    }
}
