package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zzc;

public abstract class zzdcj<T> {
    private final Context mContext;
    private final Object mLock = new Object();
    private final String mTag;
    private boolean zzkjo = false;
    private T zzkjp;

    public zzdcj(Context context, String str) {
        this.mContext = context;
        this.mTag = str;
    }

    public final boolean isOperational() {
        return zzbip() != null;
    }

    protected abstract T zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, zzc;

    protected abstract void zzbim() throws RemoteException;

    public final void zzbio() {
        synchronized (this.mLock) {
            if (this.zzkjp == null) {
                return;
            }
            try {
                zzbim();
            } catch (Throwable e) {
                Log.e(this.mTag, "Could not finalize native handle", e);
            }
        }
    }

    protected final T zzbip() {
        T t;
        Throwable e;
        synchronized (this.mLock) {
            if (this.zzkjp != null) {
                t = this.zzkjp;
            } else {
                try {
                    this.zzkjp = zza(DynamiteModule.zza(this.mContext, DynamiteModule.zzgps, "com.google.android.gms.vision.dynamite"), this.mContext);
                } catch (zzc e2) {
                    e = e2;
                    Log.e(this.mTag, "Error creating remote native handle", e);
                    if (!!this.zzkjo) {
                    }
                    Log.w(this.mTag, "Native handle is now available.");
                    t = this.zzkjp;
                    return t;
                } catch (RemoteException e3) {
                    e = e3;
                    Log.e(this.mTag, "Error creating remote native handle", e);
                    if (!this.zzkjo) {
                    }
                    Log.w(this.mTag, "Native handle is now available.");
                    t = this.zzkjp;
                    return t;
                }
                if (!this.zzkjo && this.zzkjp == null) {
                    Log.w(this.mTag, "Native handle not yet available. Reverting to no-op handle.");
                    this.zzkjo = true;
                } else if (this.zzkjo && this.zzkjp != null) {
                    Log.w(this.mTag, "Native handle is now available.");
                }
                t = this.zzkjp;
            }
        }
        return t;
    }
}
