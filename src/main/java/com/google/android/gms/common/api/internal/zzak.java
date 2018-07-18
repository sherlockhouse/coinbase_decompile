package com.google.android.gms.common.api.internal;

import android.support.v4.util.ArraySet;
import com.google.android.gms.common.ConnectionResult;

public class zzak extends zzo {
    private zzbp zzfgv;
    private final ArraySet<zzh<?>> zzfle;

    private final void zzagw() {
        if (!this.zzfle.isEmpty()) {
            this.zzfgv.zza(this);
        }
    }

    public final void onResume() {
        super.onResume();
        zzagw();
    }

    public final void onStart() {
        super.onStart();
        zzagw();
    }

    public final void onStop() {
        super.onStop();
        this.zzfgv.zzb(this);
    }

    protected final void zza(ConnectionResult connectionResult, int i) {
        this.zzfgv.zza(connectionResult, i);
    }

    protected final void zzafw() {
        this.zzfgv.zzafw();
    }

    final ArraySet<zzh<?>> zzagv() {
        return this.zzfle;
    }
}
