package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.HashSet;
import java.util.Set;

final class zzai implements ServiceConnection {
    private int mState = 2;
    private IBinder zzftq;
    private ComponentName zzfuw;
    private final Set<ServiceConnection> zzfvc = new HashSet();
    private boolean zzfvd;
    private final zzag zzfve;
    private /* synthetic */ zzah zzfvf;

    public zzai(zzah com_google_android_gms_common_internal_zzah, zzag com_google_android_gms_common_internal_zzag) {
        this.zzfvf = com_google_android_gms_common_internal_zzah;
        this.zzfve = com_google_android_gms_common_internal_zzag;
    }

    public final IBinder getBinder() {
        return this.zzftq;
    }

    public final ComponentName getComponentName() {
        return this.zzfuw;
    }

    public final int getState() {
        return this.mState;
    }

    public final boolean isBound() {
        return this.zzfvd;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.zzfvf.zzfuy) {
            this.zzfvf.mHandler.removeMessages(1, this.zzfve);
            this.zzftq = iBinder;
            this.zzfuw = componentName;
            for (ServiceConnection onServiceConnected : this.zzfvc) {
                onServiceConnected.onServiceConnected(componentName, iBinder);
            }
            this.mState = 1;
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.zzfvf.zzfuy) {
            this.zzfvf.mHandler.removeMessages(1, this.zzfve);
            this.zzftq = null;
            this.zzfuw = componentName;
            for (ServiceConnection onServiceDisconnected : this.zzfvc) {
                onServiceDisconnected.onServiceDisconnected(componentName);
            }
            this.mState = 2;
        }
    }

    public final void zza(ServiceConnection serviceConnection, String str) {
        this.zzfvf.zzfuz;
        this.zzfvf.mApplicationContext;
        this.zzfve.zzakh();
        this.zzfvc.add(serviceConnection);
    }

    public final boolean zza(ServiceConnection serviceConnection) {
        return this.zzfvc.contains(serviceConnection);
    }

    public final boolean zzaki() {
        return this.zzfvc.isEmpty();
    }

    public final void zzb(ServiceConnection serviceConnection, String str) {
        this.zzfvf.zzfuz;
        this.zzfvf.mApplicationContext;
        this.zzfvc.remove(serviceConnection);
    }

    public final void zzgc(String str) {
        this.mState = 3;
        this.zzfvd = this.zzfvf.zzfuz.zza(this.zzfvf.mApplicationContext, str, this.zzfve.zzakh(), this, this.zzfve.zzakg());
        if (this.zzfvd) {
            this.zzfvf.mHandler.sendMessageDelayed(this.zzfvf.mHandler.obtainMessage(1, this.zzfve), this.zzfvf.zzfvb);
            return;
        }
        this.mState = 2;
        try {
            this.zzfvf.zzfuz;
            this.zzfvf.mApplicationContext.unbindService(this);
        } catch (IllegalArgumentException e) {
        }
    }

    public final void zzgd(String str) {
        this.zzfvf.mHandler.removeMessages(1, this.zzfve);
        this.zzfvf.zzfuz;
        this.zzfvf.mApplicationContext.unbindService(this);
        this.zzfvd = false;
        this.mState = 2;
    }
}
