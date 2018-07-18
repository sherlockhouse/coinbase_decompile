package com.google.android.gms.internal;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

final class zzasj extends zzasf {
    private /* synthetic */ zzasi zzebi;

    zzasj(zzasi com_google_android_gms_internal_zzasi) {
        this.zzebi = com_google_android_gms_internal_zzasi;
    }

    public final void zza(Status status, Credential credential) {
        this.zzebi.setResult(new zzasg(status, credential));
    }

    public final void zze(Status status) {
        this.zzebi.setResult(zzasg.zzf(status));
    }
}
