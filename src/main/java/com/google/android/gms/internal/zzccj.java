package com.google.android.gms.internal;

import android.content.SharedPreferences.Editor;
import com.google.android.gms.common.internal.zzbp;

public final class zzccj {
    private final String zzbff;
    private boolean zzfgq;
    private final boolean zziri = true;
    private boolean zzirj;
    private /* synthetic */ zzcch zzirk;

    public zzccj(zzcch com_google_android_gms_internal_zzcch, String str, boolean z) {
        this.zzirk = com_google_android_gms_internal_zzcch;
        zzbp.zzgg(str);
        this.zzbff = str;
    }

    public final boolean get() {
        if (!this.zzirj) {
            this.zzirj = true;
            this.zzfgq = this.zzirk.zzdtq.getBoolean(this.zzbff, this.zziri);
        }
        return this.zzfgq;
    }

    public final void set(boolean z) {
        Editor edit = this.zzirk.zzdtq.edit();
        edit.putBoolean(this.zzbff, z);
        edit.apply();
        this.zzfgq = z;
    }
}
