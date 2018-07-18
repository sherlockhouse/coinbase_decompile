package com.coinbase.android.utils;

import android.content.Context;
import android.provider.Settings.Secure;
import com.google.firebase.iid.FirebaseInstanceId;

public class DeviceUtils {
    public static String FCMToken() {
        return FirebaseInstanceId.getInstance().getToken();
    }

    public static String deviceId(Context context) {
        return Secure.getString(context.getContentResolver(), "android_id");
    }
}
