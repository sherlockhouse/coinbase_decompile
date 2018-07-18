package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.common.internal.zzbp;

class zzccf extends BroadcastReceiver {
    private static String zzdtj = zzccf.class.getName();
    private boolean mRegistered;
    private boolean zzdtk;
    private final zzccw zziki;

    zzccf(zzccw com_google_android_gms_internal_zzccw) {
        zzbp.zzu(com_google_android_gms_internal_zzccw);
        this.zziki = com_google_android_gms_internal_zzccw;
    }

    public void onReceive(Context context, Intent intent) {
        this.zziki.zzwk();
        String action = intent.getAction();
        this.zziki.zzaul().zzayj().zzj("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean zzyx = this.zziki.zzaza().zzyx();
            if (this.zzdtk != zzyx) {
                this.zzdtk = zzyx;
                this.zziki.zzauk().zzg(new zzccg(this, zzyx));
                return;
            }
            return;
        }
        this.zziki.zzaul().zzayf().zzj("NetworkBroadcastReceiver received unknown action", action);
    }

    public final void unregister() {
        this.zziki.zzwk();
        this.zziki.zzauk().zzuj();
        this.zziki.zzauk().zzuj();
        if (this.mRegistered) {
            this.zziki.zzaul().zzayj().log("Unregistering connectivity change receiver");
            this.mRegistered = false;
            this.zzdtk = false;
            try {
                this.zziki.getContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                this.zziki.zzaul().zzayd().zzj("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    public final void zzyu() {
        this.zziki.zzwk();
        this.zziki.zzauk().zzuj();
        if (!this.mRegistered) {
            this.zziki.getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.zzdtk = this.zziki.zzaza().zzyx();
            this.zziki.zzaul().zzayj().zzj("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzdtk));
            this.mRegistered = true;
        }
    }
}
