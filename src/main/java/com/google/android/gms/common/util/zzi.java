package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;

public final class zzi {
    private static Boolean zzfyu;
    private static Boolean zzfyv;
    private static Boolean zzfyw;

    @TargetApi(20)
    public static boolean zzci(Context context) {
        if (zzfyu == null) {
            boolean z = zzq.zzali() && context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
            zzfyu = Boolean.valueOf(z);
        }
        return zzfyu.booleanValue();
    }

    @TargetApi(24)
    public static boolean zzcj(Context context) {
        return (!zzq.isAtLeastN() || zzck(context)) && zzci(context);
    }

    @TargetApi(21)
    public static boolean zzck(Context context) {
        if (zzfyv == null) {
            boolean z = zzq.zzalj() && context.getPackageManager().hasSystemFeature("cn.google");
            zzfyv = Boolean.valueOf(z);
        }
        return zzfyv.booleanValue();
    }

    public static boolean zzcl(Context context) {
        if (zzfyw == null) {
            boolean z = context.getPackageManager().hasSystemFeature("android.hardware.type.iot") || context.getPackageManager().hasSystemFeature("android.hardware.type.embedded");
            zzfyw = Boolean.valueOf(z);
        }
        return zzfyw.booleanValue();
    }
}
