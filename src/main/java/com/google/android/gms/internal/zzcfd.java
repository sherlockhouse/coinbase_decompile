package com.google.android.gms.internal;

import android.content.ComponentName;

final class zzcfd implements Runnable {
    private /* synthetic */ ComponentName val$name;
    private /* synthetic */ zzcfb zziwp;

    zzcfd(zzcfb com_google_android_gms_internal_zzcfb, ComponentName componentName) {
        this.zziwp = com_google_android_gms_internal_zzcfb;
        this.val$name = componentName;
    }

    public final void run() {
        this.zziwp.zziwf.onServiceDisconnected(this.val$name);
    }
}
