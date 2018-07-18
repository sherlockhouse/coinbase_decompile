package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.internal.zzbp;

public final class zzccn {
    private final zzccp zzirq;

    public zzccn(zzccp com_google_android_gms_internal_zzccp) {
        zzbp.zzu(com_google_android_gms_internal_zzccp);
        this.zzirq = com_google_android_gms_internal_zzccp;
    }

    public static boolean zzj(Context context, boolean z) {
        zzbp.zzu(context);
        return zzcfw.zza(context, "com.google.android.gms.measurement.AppMeasurementReceiver", false);
    }

    public final void onReceive(Context context, Intent intent) {
        zzccw zzdn = zzccw.zzdn(context);
        zzcbw zzaul = zzdn.zzaul();
        if (intent == null) {
            zzaul.zzayf().log("Receiver called with null intent");
            return;
        }
        zzcax.zzawk();
        String action = intent.getAction();
        zzaul.zzayj().zzj("Local receiver got", action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zzcfh.zzk(context, false);
            Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            className.setAction("com.google.android.gms.measurement.UPLOAD");
            this.zzirq.doStartService(context, className);
        } else if ("com.android.vending.INSTALL_REFERRER".equals(action)) {
            action = intent.getStringExtra("referrer");
            if (action == null) {
                zzaul.zzayj().log("Install referrer extras are null");
                return;
            }
            zzaul.zzayh().zzj("Install referrer extras are", action);
            if (!action.contains("?")) {
                String str = "?";
                action = String.valueOf(action);
                action = action.length() != 0 ? str.concat(action) : new String(str);
            }
            Bundle zzp = zzdn.zzauh().zzp(Uri.parse(action));
            if (zzp == null) {
                zzaul.zzayj().log("No campaign defined in install referrer broadcast");
                return;
            }
            long longExtra = 1000 * intent.getLongExtra("referrer_timestamp_seconds", 0);
            if (longExtra == 0) {
                zzaul.zzayf().log("Install referrer is missing timestamp");
            }
            zzdn.zzauk().zzg(new zzcco(this, zzdn, longExtra, zzp, context, zzaul));
        }
    }
}
