package com.coinbase.android.utils;

import android.content.ComponentName;
import android.os.Build.VERSION;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.coinbase.android.ApplicationScope;
import com.coinbase.android.BuildConfig;

@ApplicationScope
public class ActivityPermissionCheckUtils {
    public static final int ACTIVITY_PERMISSION_REQUEST_CODE = 137;

    public boolean checkCallingPackage(AppCompatActivity activity) {
        if (VERSION.SDK_INT < 21) {
            return true;
        }
        ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity == null) {
            return false;
        }
        String callingPackage = callingActivity.getPackageName();
        if (TextUtils.isEmpty(callingPackage)) {
            return false;
        }
        if (TextUtils.equals(callingPackage, BuildConfig.APPLICATION_ID)) {
            return true;
        }
        return false;
    }
}
