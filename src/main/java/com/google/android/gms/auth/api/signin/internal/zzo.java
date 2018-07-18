package com.google.android.gms.auth.api.signin.internal;

public final class zzo {
    private static int zzeda = 31;
    private int zzedb = 1;

    public final int zzaao() {
        return this.zzedb;
    }

    public final zzo zzaq(boolean z) {
        this.zzedb = (z ? 1 : 0) + (this.zzedb * zzeda);
        return this;
    }

    public final zzo zzo(Object obj) {
        this.zzedb = (obj == null ? 0 : obj.hashCode()) + (this.zzedb * zzeda);
        return this;
    }
}
