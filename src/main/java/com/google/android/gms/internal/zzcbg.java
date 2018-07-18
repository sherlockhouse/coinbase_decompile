package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbp;

final class zzcbg {
    final String mAppId;
    final String mName;
    final long zzinl;
    final long zzinm;
    final long zzinn;

    zzcbg(String str, String str2, long j, long j2, long j3) {
        boolean z = true;
        zzbp.zzgg(str);
        zzbp.zzgg(str2);
        zzbp.zzbh(j >= 0);
        if (j2 < 0) {
            z = false;
        }
        zzbp.zzbh(z);
        this.mAppId = str;
        this.mName = str2;
        this.zzinl = j;
        this.zzinm = j2;
        this.zzinn = j3;
    }

    final zzcbg zzaxy() {
        return new zzcbg(this.mAppId, this.mName, this.zzinl + 1, this.zzinm + 1, this.zzinn);
    }

    final zzcbg zzbb(long j) {
        return new zzcbg(this.mAppId, this.mName, this.zzinl, this.zzinm, j);
    }
}
