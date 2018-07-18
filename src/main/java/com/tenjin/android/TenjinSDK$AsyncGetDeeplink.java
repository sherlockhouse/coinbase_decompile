package com.tenjin.android;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import java.util.Map;
import org.json.JSONObject;

class TenjinSDK$AsyncGetDeeplink extends AsyncTask<Callback, Void, String> {
    private final Callback callback;
    final /* synthetic */ TenjinSDK this$0;

    public TenjinSDK$AsyncGetDeeplink(TenjinSDK tenjinSDK, Callback callback) {
        this.this$0 = tenjinSDK;
        this.callback = callback;
    }

    protected String doInBackground(Callback... params) {
        SharedPreferences preferences = TenjinSDK.access$1000(this.this$0).getSharedPreferences("tenjinInstallPreferences", 0);
        boolean firstLaunch = preferences.getBoolean("tenjinFirstLaunchKey", true);
        String result;
        if (TenjinSDK.access$600(this.this$0)) {
            if (firstLaunch) {
                preferences.edit().putBoolean("tenjinFirstLaunchKey", false).commit();
            }
            Map<String, String> postParams = TenjinSDK.access$3700(this.this$0);
            postParams.put("api_key", TenjinSDK.access$500(this.this$0));
            result = new HttpConnection().getUser("https://track.tenjin.io/v0/user", postParams);
            if (result == null) {
                return result;
            }
            TenjinSDK.access$4600(this.this$0, this.callback, result, firstLaunch);
            return result;
        }
        TenjinSDK.access$4702(this.this$0, this.callback);
        try {
            Thread.sleep(5000);
            if (firstLaunch) {
                preferences.edit().putBoolean("tenjinFirstLaunchKey", false).commit();
            }
            postParams = TenjinSDK.access$3700(this.this$0);
            postParams.put("api_key", TenjinSDK.access$500(this.this$0));
            result = new HttpConnection().getUser("https://track.tenjin.io/v0/user", postParams);
            if (result == null) {
                return result;
            }
            TenjinSDK.access$4600(this.this$0, this.callback, result, firstLaunch);
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void onPostExecute(String result) {
        boolean success = false;
        if (result != null) {
            try {
                if (new JSONObject(result).length() > 0) {
                    success = true;
                }
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        if (success) {
            TenjinSDK.access$3800(this.this$0, "eventGetDeeplink");
        } else {
            TenjinSDK.access$4800(this.this$0, "eventGetDeeplink", this.callback);
        }
    }
}
