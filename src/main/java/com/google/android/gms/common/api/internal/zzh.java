package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.zzbf;

public final class zzh<O extends ApiOptions> {
    private final Api<O> zzfdg;
    private final O zzfgr;
    private final boolean zzfii = true;
    private final int zzfij;

    private zzh(Api<O> api) {
        this.zzfdg = api;
        this.zzfgr = null;
        this.zzfij = System.identityHashCode(this);
    }

    public static <O extends ApiOptions> zzh<O> zzb(Api<O> api) {
        return new zzh(api);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzh)) {
            return false;
        }
        zzh com_google_android_gms_common_api_internal_zzh = (zzh) obj;
        return !this.zzfii && !com_google_android_gms_common_api_internal_zzh.zzfii && zzbf.equal(this.zzfdg, com_google_android_gms_common_api_internal_zzh.zzfdg) && zzbf.equal(this.zzfgr, com_google_android_gms_common_api_internal_zzh.zzfgr);
    }

    public final int hashCode() {
        return this.zzfij;
    }

    public final String zzafv() {
        return this.zzfdg.getName();
    }
}
