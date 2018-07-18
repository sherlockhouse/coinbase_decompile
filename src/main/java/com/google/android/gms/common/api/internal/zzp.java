package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbp;

final class zzp {
    private final int zzfiy;
    private final ConnectionResult zzfiz;

    zzp(ConnectionResult connectionResult, int i) {
        zzbp.zzu(connectionResult);
        this.zzfiz = connectionResult;
        this.zzfiy = i;
    }

    final int zzagc() {
        return this.zzfiy;
    }

    final ConnectionResult zzagd() {
        return this.zzfiz;
    }
}
