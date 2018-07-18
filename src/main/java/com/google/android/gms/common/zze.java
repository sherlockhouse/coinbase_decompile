package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzaj;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzbed;

public class zze {
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zzo.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final zze zzffk = new zze();

    zze() {
    }

    public static Intent zza(Context context, int i, String str) {
        switch (i) {
            case 1:
            case 2:
                return (context == null || !zzi.zzcj(context)) ? zzaj.zzu("com.google.android.gms", zzw(context, str)) : zzaj.zzakj();
            case 3:
                return zzaj.zzge("com.google.android.gms");
            default:
                return null;
        }
    }

    public static zze zzaex() {
        return zzffk;
    }

    public static void zzbu(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        zzo.zzbj(context);
    }

    public static void zzbv(Context context) {
        zzo.zzbv(context);
    }

    public static boolean zze(Context context, int i) {
        return zzo.zze(context, i);
    }

    private static String zzw(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("gcore_");
        stringBuilder.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
        stringBuilder.append("-");
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(str);
        }
        stringBuilder.append("-");
        if (context != null) {
            stringBuilder.append(context.getPackageName());
        }
        stringBuilder.append("-");
        if (context != null) {
            try {
                stringBuilder.append(zzbed.zzcr(context).getPackageInfo(context.getPackageName(), 0).versionCode);
            } catch (NameNotFoundException e) {
            }
        }
        return stringBuilder.toString();
    }

    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int i2) {
        return zza(context, i, i2, null);
    }

    public String getErrorString(int i) {
        return zzo.getErrorString(i);
    }

    public int isGooglePlayServicesAvailable(Context context) {
        int isGooglePlayServicesAvailable = zzo.isGooglePlayServicesAvailable(context);
        return zzo.zze(context, isGooglePlayServicesAvailable) ? 18 : isGooglePlayServicesAvailable;
    }

    public boolean isUserResolvableError(int i) {
        return zzo.isUserRecoverableError(i);
    }

    public final PendingIntent zza(Context context, int i, int i2, String str) {
        Intent zza = zza(context, i, str);
        return zza == null ? null : PendingIntent.getActivity(context, i2, zza, 268435456);
    }

    @Deprecated
    public final Intent zzbn(int i) {
        return zza(null, i, null);
    }
}
