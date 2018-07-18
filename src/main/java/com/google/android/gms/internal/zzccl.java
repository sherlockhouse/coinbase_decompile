package com.google.android.gms.internal;

import android.content.SharedPreferences.Editor;
import android.util.Pair;
import com.google.android.gms.common.internal.zzbp;

public final class zzccl {
    private final long zzdtu;
    private /* synthetic */ zzcch zzirk;
    private String zzirm;
    private final String zzirn;
    private final String zziro;

    private zzccl(zzcch com_google_android_gms_internal_zzcch, String str, long j) {
        this.zzirk = com_google_android_gms_internal_zzcch;
        zzbp.zzgg(str);
        zzbp.zzbh(j > 0);
        this.zzirm = String.valueOf(str).concat(":start");
        this.zzirn = String.valueOf(str).concat(":count");
        this.zziro = String.valueOf(str).concat(":value");
        this.zzdtu = j;
    }

    private final void zzzh() {
        this.zzirk.zzuj();
        long currentTimeMillis = this.zzirk.zzvx().currentTimeMillis();
        Editor edit = this.zzirk.zzdtq.edit();
        edit.remove(this.zzirn);
        edit.remove(this.zziro);
        edit.putLong(this.zzirm, currentTimeMillis);
        edit.apply();
    }

    private final long zzzj() {
        return this.zzirk.zzayl().getLong(this.zzirm, 0);
    }

    public final void zzf(String str, long j) {
        this.zzirk.zzuj();
        if (zzzj() == 0) {
            zzzh();
        }
        if (str == null) {
            str = "";
        }
        long j2 = this.zzirk.zzdtq.getLong(this.zzirn, 0);
        if (j2 <= 0) {
            Editor edit = this.zzirk.zzdtq.edit();
            edit.putString(this.zziro, str);
            edit.putLong(this.zzirn, 1);
            edit.apply();
            return;
        }
        Object obj = (this.zzirk.zzauh().zzazy().nextLong() & Long.MAX_VALUE) < Long.MAX_VALUE / (j2 + 1) ? 1 : null;
        Editor edit2 = this.zzirk.zzdtq.edit();
        if (obj != null) {
            edit2.putString(this.zziro, str);
        }
        edit2.putLong(this.zzirn, j2 + 1);
        edit2.apply();
    }

    public final Pair<String, Long> zzzi() {
        this.zzirk.zzuj();
        this.zzirk.zzuj();
        long zzzj = zzzj();
        if (zzzj == 0) {
            zzzh();
            zzzj = 0;
        } else {
            zzzj = Math.abs(zzzj - this.zzirk.zzvx().currentTimeMillis());
        }
        if (zzzj < this.zzdtu) {
            return null;
        }
        if (zzzj > (this.zzdtu << 1)) {
            zzzh();
            return null;
        }
        String string = this.zzirk.zzayl().getString(this.zziro, null);
        long j = this.zzirk.zzayl().getLong(this.zzirn, 0);
        zzzh();
        return (string == null || j <= 0) ? zzcch.zziqn : new Pair(string, Long.valueOf(j));
    }
}
