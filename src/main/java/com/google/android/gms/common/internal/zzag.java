package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Intent;
import java.util.Arrays;

public final class zzag {
    private final String zzdmr;
    private final String zzfuv;
    private final ComponentName zzfuw = null;
    private final int zzfux;

    public zzag(String str, String str2, int i) {
        this.zzdmr = zzbp.zzgg(str);
        this.zzfuv = zzbp.zzgg(str2);
        this.zzfux = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzag)) {
            return false;
        }
        zzag com_google_android_gms_common_internal_zzag = (zzag) obj;
        return zzbf.equal(this.zzdmr, com_google_android_gms_common_internal_zzag.zzdmr) && zzbf.equal(this.zzfuv, com_google_android_gms_common_internal_zzag.zzfuv) && zzbf.equal(this.zzfuw, com_google_android_gms_common_internal_zzag.zzfuw) && this.zzfux == com_google_android_gms_common_internal_zzag.zzfux;
    }

    public final ComponentName getComponentName() {
        return this.zzfuw;
    }

    public final String getPackage() {
        return this.zzfuv;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzdmr, this.zzfuv, this.zzfuw, Integer.valueOf(this.zzfux)});
    }

    public final String toString() {
        return this.zzdmr == null ? this.zzfuw.flattenToString() : this.zzdmr;
    }

    public final int zzakg() {
        return this.zzfux;
    }

    public final Intent zzakh() {
        return this.zzdmr != null ? new Intent(this.zzdmr).setPackage(this.zzfuv) : new Intent().setComponent(this.zzfuw);
    }
}
