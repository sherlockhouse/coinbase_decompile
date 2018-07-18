package com.google.android.gms.common.util;

import android.os.Build.VERSION;

public final class zzq {
    public static boolean isAtLeastN() {
        return VERSION.SDK_INT >= 24;
    }

    public static boolean isAtLeastO() {
        return VERSION.SDK_INT >= 26 || "O".equals(VERSION.CODENAME) || VERSION.CODENAME.startsWith("OMR") || VERSION.CODENAME.startsWith("ODR");
    }

    public static boolean zzald() {
        return VERSION.SDK_INT >= 15;
    }

    public static boolean zzale() {
        return VERSION.SDK_INT >= 16;
    }

    public static boolean zzalg() {
        return VERSION.SDK_INT >= 18;
    }

    public static boolean zzalh() {
        return VERSION.SDK_INT >= 19;
    }

    public static boolean zzali() {
        return VERSION.SDK_INT >= 20;
    }

    public static boolean zzalj() {
        return VERSION.SDK_INT >= 21;
    }
}
