package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.google.android.gms.common.util.zzq;

public final class FirebaseInstanceIdInternalReceiver extends WakefulBroadcastReceiver {
    private static boolean zzhqy = false;
    private static zzh zznfn;
    private static zzh zznfo;

    static synchronized zzh zzag(Context context, String str) {
        zzh com_google_firebase_iid_zzh;
        synchronized (FirebaseInstanceIdInternalReceiver.class) {
            if ("com.google.firebase.MESSAGING_EVENT".equals(str)) {
                if (zznfo == null) {
                    zznfo = new zzh(context, str);
                }
                com_google_firebase_iid_zzh = zznfo;
            } else {
                if (zznfn == null) {
                    zznfn = new zzh(context, str);
                }
                com_google_firebase_iid_zzh = zznfn;
            }
        }
        return com_google_firebase_iid_zzh;
    }

    static boolean zzel(Context context) {
        return zzq.isAtLeastO() && context.getApplicationInfo().targetSdkVersion > 25;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Parcelable parcelableExtra = intent.getParcelableExtra("wrapped_intent");
            if (parcelableExtra instanceof Intent) {
                Intent intent2 = (Intent) parcelableExtra;
                if (zzel(context)) {
                    zzag(context, intent.getAction()).zza(intent2, goAsync());
                    return;
                } else {
                    zzq.zzcge().zza(context, intent.getAction(), intent2);
                    return;
                }
            }
            Log.e("FirebaseInstanceId", "Missing or invalid wrapped intent");
        }
    }
}
