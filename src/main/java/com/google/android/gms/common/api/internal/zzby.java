package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public final class zzby extends BroadcastReceiver {
    private Context mContext;
    private final zzbz zzfoi;

    public zzby(zzbz com_google_android_gms_common_api_internal_zzbz) {
        this.zzfoi = com_google_android_gms_common_api_internal_zzbz;
    }

    public final void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        Object obj = null;
        if (data != null) {
            obj = data.getSchemeSpecificPart();
        }
        if ("com.google.android.gms".equals(obj)) {
            this.zzfoi.zzage();
            unregister();
        }
    }

    public final void setContext(Context context) {
        this.mContext = context;
    }

    public final synchronized void unregister() {
        if (this.mContext != null) {
            this.mContext.unregisterReceiver(this);
        }
        this.mContext = null;
    }
}
