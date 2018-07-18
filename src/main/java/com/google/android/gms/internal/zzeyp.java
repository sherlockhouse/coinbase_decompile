package com.google.android.gms.internal;

import java.util.Arrays;

final class zzeyp {
    final byte[] bytes;
    final int tag;

    zzeyp(int i, byte[] bArr) {
        this.tag = i;
        this.bytes = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzeyp)) {
            return false;
        }
        zzeyp com_google_android_gms_internal_zzeyp = (zzeyp) obj;
        return this.tag == com_google_android_gms_internal_zzeyp.tag && Arrays.equals(this.bytes, com_google_android_gms_internal_zzeyp.bytes);
    }

    public final int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.bytes);
    }
}
