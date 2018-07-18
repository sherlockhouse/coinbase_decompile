package com.google.android.gms.internal;

import android.content.SharedPreferences.Editor;
import com.google.android.gms.common.internal.zzbp;

public final class zzcck {
    private final String zzbff;
    private long zzdmt;
    private boolean zzirj;
    private /* synthetic */ zzcch zzirk;
    private final long zzirl;

    public zzcck(zzcch com_google_android_gms_internal_zzcch, String str, long j) {
        this.zzirk = com_google_android_gms_internal_zzcch;
        zzbp.zzgg(str);
        this.zzbff = str;
        this.zzirl = j;
    }

    public final long get() {
        if (!this.zzirj) {
            this.zzirj = true;
            this.zzdmt = this.zzirk.zzdtq.getLong(this.zzbff, this.zzirl);
        }
        return this.zzdmt;
    }

    public final void set(long j) {
        Editor edit = this.zzirk.zzdtq.edit();
        edit.putLong(this.zzbff, j);
        edit.apply();
        this.zzdmt = j;
    }
}
