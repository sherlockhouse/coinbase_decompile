package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzbz;

@Deprecated
public final class zzca {
    private static final Object zzaqd = new Object();
    private static zzca zzfoj;
    private final String mAppId;
    private final Status zzfok;
    private final boolean zzfol;
    private final boolean zzfom;

    private zzca(Context context) {
        boolean z = true;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue));
        if (identifier != 0) {
            boolean z2 = resources.getInteger(identifier) != 0;
            if (z2) {
                z = false;
            }
            this.zzfom = z;
            z = z2;
        } else {
            this.zzfom = false;
        }
        this.zzfol = z;
        Object zzcf = zzbe.zzcf(context);
        if (zzcf == null) {
            zzcf = new zzbz(context).getString("google_app_id");
        }
        if (TextUtils.isEmpty(zzcf)) {
            this.zzfok = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.mAppId = null;
            return;
        }
        this.mAppId = zzcf;
        this.zzfok = Status.zzfhv;
    }

    public static String zzaie() {
        return zzft("getGoogleAppId").mAppId;
    }

    public static boolean zzaif() {
        return zzft("isMeasurementExplicitlyDisabled").zzfom;
    }

    public static Status zzcb(Context context) {
        Status status;
        zzbp.zzb((Object) context, (Object) "Context must not be null.");
        synchronized (zzaqd) {
            if (zzfoj == null) {
                zzfoj = new zzca(context);
            }
            status = zzfoj.zzfok;
        }
        return status;
    }

    private static zzca zzft(String str) {
        zzca com_google_android_gms_common_api_internal_zzca;
        synchronized (zzaqd) {
            if (zzfoj == null) {
                throw new IllegalStateException(new StringBuilder(String.valueOf(str).length() + 34).append("Initialize must be called before ").append(str).append(".").toString());
            }
            com_google_android_gms_common_api_internal_zzca = zzfoj;
        }
        return com_google_android_gms_common_api_internal_zzca;
    }
}
