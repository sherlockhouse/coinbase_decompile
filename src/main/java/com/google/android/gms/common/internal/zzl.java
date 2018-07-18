package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;

public final class zzl implements ServiceConnection {
    private /* synthetic */ zzd zzftl;
    private final int zzfto;

    public zzl(zzd com_google_android_gms_common_internal_zzd, int i) {
        this.zzftl = com_google_android_gms_common_internal_zzd;
        this.zzfto = i;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            this.zzftl.zzcd(16);
            return;
        }
        synchronized (this.zzftl.zzfsv) {
            zzax com_google_android_gms_common_internal_zzax;
            zzd com_google_android_gms_common_internal_zzd = this.zzftl;
            if (iBinder == null) {
                com_google_android_gms_common_internal_zzax = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                com_google_android_gms_common_internal_zzax = (queryLocalInterface == null || !(queryLocalInterface instanceof zzax)) ? new zzay(iBinder) : (zzax) queryLocalInterface;
            }
            com_google_android_gms_common_internal_zzd.zzfsw = com_google_android_gms_common_internal_zzax;
        }
        this.zzftl.zza(0, null, this.zzfto);
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.zzftl.zzfsv) {
            this.zzftl.zzfsw = null;
        }
        this.zzftl.mHandler.sendMessage(this.zzftl.mHandler.obtainMessage(6, this.zzfto, 1));
    }
}
