package com.mixpanel.android.mpmetrics;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.util.Log;
import com.mixpanel.android.surveys.SurveyActivity;

class ConfigurationChecker {
    public static String LOGTAG = "MixpanelAPI.ConfigurationChecker";

    public static boolean checkSurveyActivityAvailable(Context context) {
        if (VERSION.SDK_INT < 14) {
            return false;
        }
        Intent surveyIntent = new Intent(context, SurveyActivity.class);
        surveyIntent.addFlags(268435456);
        surveyIntent.addFlags(131072);
        if (context.getPackageManager().queryIntentActivities(surveyIntent, 0).size() != 0) {
            return true;
        }
        Log.w(LOGTAG, SurveyActivity.class.getName() + " is not registered as an activity in your application, so surveys can't be shown.");
        Log.i(LOGTAG, "Please add the child tag <activity android:name=\"com.mixpanel.android.surveys.SurveyActivity\" /> to your <application> tag.");
        return false;
    }
}
