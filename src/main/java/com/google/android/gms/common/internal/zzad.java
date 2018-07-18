package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzad implements Callback {
    private final Handler mHandler;
    private final Object mLock = new Object();
    private final zzae zzfum;
    private final ArrayList<ConnectionCallbacks> zzfun = new ArrayList();
    private ArrayList<ConnectionCallbacks> zzfuo = new ArrayList();
    private final ArrayList<OnConnectionFailedListener> zzfup = new ArrayList();
    private volatile boolean zzfuq = false;
    private final AtomicInteger zzfur = new AtomicInteger(0);
    private boolean zzfus = false;

    public zzad(Looper looper, zzae com_google_android_gms_common_internal_zzae) {
        this.zzfum = com_google_android_gms_common_internal_zzae;
        this.mHandler = new Handler(looper, this);
    }

    public final boolean handleMessage(Message message) {
        if (message.what == 1) {
            ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) message.obj;
            synchronized (this.mLock) {
                if (this.zzfuq && this.zzfum.isConnected() && this.zzfun.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(this.zzfum.zzaeh());
                }
            }
            return true;
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle message: " + message.what, new Exception());
        return false;
    }

    public final void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        zzbp.zzu(connectionCallbacks);
        synchronized (this.mLock) {
            if (this.zzfun.contains(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 62).append("registerConnectionCallbacks(): listener ").append(valueOf).append(" is already registered").toString());
            } else {
                this.zzfun.add(connectionCallbacks);
            }
        }
        if (this.zzfum.isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, connectionCallbacks));
        }
    }

    public final void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        zzbp.zzu(onConnectionFailedListener);
        synchronized (this.mLock) {
            if (this.zzfup.contains(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 67).append("registerConnectionFailedListener(): listener ").append(valueOf).append(" is already registered").toString());
            } else {
                this.zzfup.add(onConnectionFailedListener);
            }
        }
    }

    public final void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        zzbp.zzu(onConnectionFailedListener);
        synchronized (this.mLock) {
            if (!this.zzfup.remove(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 57).append("unregisterConnectionFailedListener(): listener ").append(valueOf).append(" not found").toString());
            }
        }
    }

    public final void zzake() {
        this.zzfuq = false;
        this.zzfur.incrementAndGet();
    }

    public final void zzakf() {
        this.zzfuq = true;
    }

    public final void zzce(int i) {
        int i2 = 0;
        zzbp.zza(Looper.myLooper() == this.mHandler.getLooper(), "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.mLock) {
            this.zzfus = true;
            ArrayList arrayList = new ArrayList(this.zzfun);
            int i3 = this.zzfur.get();
            arrayList = arrayList;
            int size = arrayList.size();
            while (i2 < size) {
                Object obj = arrayList.get(i2);
                i2++;
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) obj;
                if (this.zzfuq && this.zzfur.get() == i3) {
                    if (this.zzfun.contains(connectionCallbacks)) {
                        connectionCallbacks.onConnectionSuspended(i);
                    }
                }
            }
            this.zzfuo.clear();
            this.zzfus = false;
        }
    }

    public final void zzk(Bundle bundle) {
        boolean z = true;
        int i = 0;
        zzbp.zza(Looper.myLooper() == this.mHandler.getLooper(), "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.mLock) {
            zzbp.zzbg(!this.zzfus);
            this.mHandler.removeMessages(1);
            this.zzfus = true;
            if (this.zzfuo.size() != 0) {
                z = false;
            }
            zzbp.zzbg(z);
            ArrayList arrayList = new ArrayList(this.zzfun);
            int i2 = this.zzfur.get();
            arrayList = arrayList;
            int size = arrayList.size();
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) obj;
                if (this.zzfuq && this.zzfum.isConnected() && this.zzfur.get() == i2) {
                    if (!this.zzfuo.contains(connectionCallbacks)) {
                        connectionCallbacks.onConnected(bundle);
                    }
                }
            }
            this.zzfuo.clear();
            this.zzfus = false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzk(ConnectionResult connectionResult) {
        int i = 0;
        zzbp.zza(Looper.myLooper() == this.mHandler.getLooper(), "onConnectionFailure must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.mLock) {
            ArrayList arrayList = new ArrayList(this.zzfup);
            int i2 = this.zzfur.get();
            arrayList = arrayList;
            int size = arrayList.size();
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                OnConnectionFailedListener onConnectionFailedListener = (OnConnectionFailedListener) obj;
                if (!this.zzfuq || this.zzfur.get() != i2) {
                } else if (this.zzfup.contains(onConnectionFailedListener)) {
                    onConnectionFailedListener.onConnectionFailed(connectionResult);
                }
            }
        }
    }
}
