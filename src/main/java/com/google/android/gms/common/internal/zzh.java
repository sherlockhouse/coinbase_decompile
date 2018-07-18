package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

final class zzh extends Handler {
    private /* synthetic */ zzd zzftl;

    public zzh(zzd com_google_android_gms_common_internal_zzd, Looper looper) {
        this.zzftl = com_google_android_gms_common_internal_zzd;
        super(looper);
    }

    private static void zza(Message message) {
        ((zzi) message.obj).unregister();
    }

    private static boolean zzb(Message message) {
        return message.what == 2 || message.what == 1 || message.what == 7;
    }

    public final void handleMessage(Message message) {
        PendingIntent pendingIntent = null;
        if (this.zzftl.zzfti.get() != message.arg1) {
            if (zzb(message)) {
                zza(message);
            }
        } else if ((message.what == 1 || message.what == 7 || message.what == 4 || message.what == 5) && !this.zzftl.isConnecting()) {
            zza(message);
        } else if (message.what == 4) {
            this.zzftl.zzftg = new ConnectionResult(message.arg2);
            if (!this.zzftl.zzajm() || this.zzftl.zzfth) {
                r0 = this.zzftl.zzftg != null ? this.zzftl.zzftg : new ConnectionResult(8);
                this.zzftl.zzfsx.zzf(r0);
                this.zzftl.onConnectionFailed(r0);
                return;
            }
            this.zzftl.zza(3, null);
        } else if (message.what == 5) {
            r0 = this.zzftl.zzftg != null ? this.zzftl.zzftg : new ConnectionResult(8);
            this.zzftl.zzfsx.zzf(r0);
            this.zzftl.onConnectionFailed(r0);
        } else if (message.what == 3) {
            if (message.obj instanceof PendingIntent) {
                pendingIntent = (PendingIntent) message.obj;
            }
            ConnectionResult connectionResult = new ConnectionResult(message.arg2, pendingIntent);
            this.zzftl.zzfsx.zzf(connectionResult);
            this.zzftl.onConnectionFailed(connectionResult);
        } else if (message.what == 6) {
            this.zzftl.zza(5, null);
            if (this.zzftl.zzftc != null) {
                this.zzftl.zzftc.onConnectionSuspended(message.arg2);
            }
            this.zzftl.onConnectionSuspended(message.arg2);
            this.zzftl.zza(5, 1, null);
        } else if (message.what == 2 && !this.zzftl.isConnected()) {
            zza(message);
        } else if (zzb(message)) {
            ((zzi) message.obj).zzajo();
        } else {
            Log.wtf("GmsClient", "Don't know how to handle message: " + message.what, new Exception());
        }
    }
}
