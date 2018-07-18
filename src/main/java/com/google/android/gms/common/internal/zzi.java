package com.google.android.gms.common.internal;

import android.util.Log;

public abstract class zzi<TListener> {
    private TListener mListener;
    private /* synthetic */ zzd zzftl;
    private boolean zzftm = false;

    public zzi(zzd com_google_android_gms_common_internal_zzd, TListener tListener) {
        this.zzftl = com_google_android_gms_common_internal_zzd;
        this.mListener = tListener;
    }

    public final void removeListener() {
        synchronized (this) {
            this.mListener = null;
        }
    }

    public final void unregister() {
        removeListener();
        synchronized (this.zzftl.zzfsz) {
            this.zzftl.zzfsz.remove(this);
        }
    }

    public final void zzajo() {
        synchronized (this) {
            Object obj = this.mListener;
            if (this.zzftm) {
                String valueOf = String.valueOf(this);
                Log.w("GmsClient", new StringBuilder(String.valueOf(valueOf).length() + 47).append("Callback proxy ").append(valueOf).append(" being reused. This is not safe.").toString());
            }
        }
        if (obj != null) {
            try {
                zzs(obj);
            } catch (RuntimeException e) {
                throw e;
            }
        }
        synchronized (this) {
            this.zzftm = true;
        }
        unregister();
    }

    protected abstract void zzs(TListener tListener);
}
