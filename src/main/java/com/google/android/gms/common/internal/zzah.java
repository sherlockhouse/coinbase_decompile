package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.stats.zza;
import java.util.HashMap;

final class zzah extends zzaf implements Callback {
    private final Context mApplicationContext;
    private final Handler mHandler;
    private final HashMap<zzag, zzai> zzfuy = new HashMap();
    private final zza zzfuz;
    private final long zzfva;
    private final long zzfvb;

    zzah(Context context) {
        this.mApplicationContext = context.getApplicationContext();
        this.mHandler = new Handler(context.getMainLooper(), this);
        this.zzfuz = zza.zzaky();
        this.zzfva = 5000;
        this.zzfvb = 300000;
    }

    public final boolean handleMessage(Message message) {
        zzag com_google_android_gms_common_internal_zzag;
        zzai com_google_android_gms_common_internal_zzai;
        switch (message.what) {
            case 0:
                synchronized (this.zzfuy) {
                    com_google_android_gms_common_internal_zzag = (zzag) message.obj;
                    com_google_android_gms_common_internal_zzai = (zzai) this.zzfuy.get(com_google_android_gms_common_internal_zzag);
                    if (com_google_android_gms_common_internal_zzai != null && com_google_android_gms_common_internal_zzai.zzaki()) {
                        if (com_google_android_gms_common_internal_zzai.isBound()) {
                            com_google_android_gms_common_internal_zzai.zzgd("GmsClientSupervisor");
                        }
                        this.zzfuy.remove(com_google_android_gms_common_internal_zzag);
                    }
                }
                return true;
            case 1:
                synchronized (this.zzfuy) {
                    com_google_android_gms_common_internal_zzag = (zzag) message.obj;
                    com_google_android_gms_common_internal_zzai = (zzai) this.zzfuy.get(com_google_android_gms_common_internal_zzag);
                    if (com_google_android_gms_common_internal_zzai != null && com_google_android_gms_common_internal_zzai.getState() == 3) {
                        String valueOf = String.valueOf(com_google_android_gms_common_internal_zzag);
                        Log.wtf("GmsClientSupervisor", new StringBuilder(String.valueOf(valueOf).length() + 47).append("Timeout waiting for ServiceConnection callback ").append(valueOf).toString(), new Exception());
                        ComponentName componentName = com_google_android_gms_common_internal_zzai.getComponentName();
                        if (componentName == null) {
                            componentName = com_google_android_gms_common_internal_zzag.getComponentName();
                        }
                        com_google_android_gms_common_internal_zzai.onServiceDisconnected(componentName == null ? new ComponentName(com_google_android_gms_common_internal_zzag.getPackage(), "unknown") : componentName);
                    }
                }
                return true;
            default:
                return false;
        }
    }

    protected final boolean zza(zzag com_google_android_gms_common_internal_zzag, ServiceConnection serviceConnection, String str) {
        boolean isBound;
        zzbp.zzb((Object) serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.zzfuy) {
            zzai com_google_android_gms_common_internal_zzai = (zzai) this.zzfuy.get(com_google_android_gms_common_internal_zzag);
            if (com_google_android_gms_common_internal_zzai != null) {
                this.mHandler.removeMessages(0, com_google_android_gms_common_internal_zzag);
                if (!com_google_android_gms_common_internal_zzai.zza(serviceConnection)) {
                    com_google_android_gms_common_internal_zzai.zza(serviceConnection, str);
                    switch (com_google_android_gms_common_internal_zzai.getState()) {
                        case 1:
                            serviceConnection.onServiceConnected(com_google_android_gms_common_internal_zzai.getComponentName(), com_google_android_gms_common_internal_zzai.getBinder());
                            break;
                        case 2:
                            com_google_android_gms_common_internal_zzai.zzgc(str);
                            break;
                        default:
                            break;
                    }
                }
                String valueOf = String.valueOf(com_google_android_gms_common_internal_zzag);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 81).append("Trying to bind a GmsServiceConnection that was already connected before.  config=").append(valueOf).toString());
            }
            com_google_android_gms_common_internal_zzai = new zzai(this, com_google_android_gms_common_internal_zzag);
            com_google_android_gms_common_internal_zzai.zza(serviceConnection, str);
            com_google_android_gms_common_internal_zzai.zzgc(str);
            this.zzfuy.put(com_google_android_gms_common_internal_zzag, com_google_android_gms_common_internal_zzai);
            isBound = com_google_android_gms_common_internal_zzai.isBound();
        }
        return isBound;
    }

    protected final void zzb(zzag com_google_android_gms_common_internal_zzag, ServiceConnection serviceConnection, String str) {
        zzbp.zzb((Object) serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.zzfuy) {
            zzai com_google_android_gms_common_internal_zzai = (zzai) this.zzfuy.get(com_google_android_gms_common_internal_zzag);
            String valueOf;
            if (com_google_android_gms_common_internal_zzai == null) {
                valueOf = String.valueOf(com_google_android_gms_common_internal_zzag);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 50).append("Nonexistent connection status for service config: ").append(valueOf).toString());
            } else if (com_google_android_gms_common_internal_zzai.zza(serviceConnection)) {
                com_google_android_gms_common_internal_zzai.zzb(serviceConnection, str);
                if (com_google_android_gms_common_internal_zzai.zzaki()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, com_google_android_gms_common_internal_zzag), this.zzfva);
                }
            } else {
                valueOf = String.valueOf(com_google_android_gms_common_internal_zzag);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 76).append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=").append(valueOf).toString());
            }
        }
    }
}
