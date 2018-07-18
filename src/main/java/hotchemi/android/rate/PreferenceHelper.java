package hotchemi.android.rate;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Date;

final class PreferenceHelper {
    static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences("android_rate_pref_file", 0);
    }

    static Editor getPreferencesEditor(Context context) {
        return getPreferences(context).edit();
    }

    static void clearSharedPreferences(Context context) {
        Editor editor = getPreferencesEditor(context);
        editor.remove("android_rate_install_date");
        editor.remove("android_rate_launch_times");
        editor.apply();
    }

    static void setAgreeShowDialog(Context context, boolean isAgree) {
        Editor editor = getPreferencesEditor(context);
        editor.putBoolean("android_rate_is_agree_show_dialog", isAgree);
        editor.apply();
    }

    static boolean getIsAgreeShowDialog(Context context) {
        return getPreferences(context).getBoolean("android_rate_is_agree_show_dialog", true);
    }

    static void setRemindInterval(Context context) {
        Editor editor = getPreferencesEditor(context);
        editor.remove("android_rate_remind_interval");
        editor.putLong("android_rate_remind_interval", new Date().getTime());
        editor.apply();
    }

    static long getRemindInterval(Context context) {
        return getPreferences(context).getLong("android_rate_remind_interval", 0);
    }

    static void setInstallDate(Context context) {
        Editor editor = getPreferencesEditor(context);
        editor.putLong("android_rate_install_date", new Date().getTime());
        editor.apply();
    }

    static long getInstallDate(Context context) {
        return getPreferences(context).getLong("android_rate_install_date", 0);
    }

    static void setLaunchTimes(Context context, int launchTimes) {
        Editor editor = getPreferencesEditor(context);
        editor.putInt("android_rate_launch_times", launchTimes);
        editor.apply();
    }

    static int getLaunchTimes(Context context) {
        return getPreferences(context).getInt("android_rate_launch_times", 0);
    }

    static boolean isFirstLaunch(Context context) {
        return getPreferences(context).getLong("android_rate_install_date", 0) == 0;
    }
}
