package com.google.android.gms.internal;

import android.content.SharedPreferences.Editor;
import com.google.android.gms.common.internal.zzbp;

public final class zzccm {
    private String mValue;
    private final String zzbff;
    private boolean zzirj;
    private /* synthetic */ zzcch zzirk;
    private final String zzirp = null;

    public zzccm(zzcch com_google_android_gms_internal_zzcch, String str, String str2) {
        this.zzirk = com_google_android_gms_internal_zzcch;
        zzbp.zzgg(str);
        this.zzbff = str;
    }

    public final String zzayr() {
        if (!this.zzirj) {
            this.zzirj = true;
            this.mValue = this.zzirk.zzdtq.getString(this.zzbff, null);
        }
        return this.mValue;
    }

    public final void zzjl(String str) {
        if (!zzcfw.zzas(str, this.mValue)) {
            Editor edit = this.zzirk.zzdtq.edit();
            edit.putString(this.zzbff, str);
            edit.apply();
            this.mValue = str;
        }
    }
}
