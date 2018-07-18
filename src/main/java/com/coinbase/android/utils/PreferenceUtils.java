package com.coinbase.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtils {
    public static String getPrefsString(Context c, String key, String def) {
        return c == null ? def : PreferenceManager.getDefaultSharedPreferences(c).getString(key, def);
    }

    public static boolean getPrefsBool(Context c, String key, boolean def) {
        return c == null ? def : PreferenceManager.getDefaultSharedPreferences(c).getBoolean(key, def);
    }

    public static boolean putPrefsString(Context c, String key, String newValue) {
        if (c == null) {
            return false;
        }
        return PreferenceManager.getDefaultSharedPreferences(c).edit().putString(key, newValue).commit();
    }

    public static int getPrefsInt(Context c, String key, int def) {
        return c == null ? def : PreferenceManager.getDefaultSharedPreferences(c).getInt(key, def);
    }

    public static void putPrefsInt(Context c, String key, int newValue) {
        if (c != null) {
            PreferenceManager.getDefaultSharedPreferences(c).edit().putInt(key, newValue).apply();
        }
    }

    public static boolean putPrefsBool(Context c, String key, boolean newValue) {
        if (c == null) {
            return false;
        }
        return PreferenceManager.getDefaultSharedPreferences(c).edit().putBoolean(key, newValue).commit();
    }

    public static boolean incrementPrefsInt(Context c, String key) {
        if (c == null) {
            return false;
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
        return prefs.edit().putInt(key, prefs.getInt(key, 0) + 1).commit();
    }
}
