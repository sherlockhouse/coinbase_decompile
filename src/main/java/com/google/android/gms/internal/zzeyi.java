package com.google.android.gms.internal;

public final class zzeyi<M extends zzeyh<M>, T> {
    public final int tag;
    private int type;
    protected final Class<T> zzmlw;
    protected final boolean zzotm;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzeyi)) {
            return false;
        }
        zzeyi com_google_android_gms_internal_zzeyi = (zzeyi) obj;
        return this.type == com_google_android_gms_internal_zzeyi.type && this.zzmlw == com_google_android_gms_internal_zzeyi.zzmlw && this.tag == com_google_android_gms_internal_zzeyi.tag && this.zzotm == com_google_android_gms_internal_zzeyi.zzotm;
    }

    public final int hashCode() {
        return (this.zzotm ? 1 : 0) + ((((((this.type + 1147) * 31) + this.zzmlw.hashCode()) * 31) + this.tag) * 31);
    }

    protected final void zza(Object obj, zzeyf com_google_android_gms_internal_zzeyf) {
        try {
            com_google_android_gms_internal_zzeyf.zzlc(this.tag);
            switch (this.type) {
                case 10:
                    int i = this.tag >>> 3;
                    ((zzeyn) obj).zza(com_google_android_gms_internal_zzeyf);
                    com_google_android_gms_internal_zzeyf.zzw(i, 4);
                    return;
                case 11:
                    com_google_android_gms_internal_zzeyf.zzb((zzeyn) obj);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
        throw new IllegalStateException(e);
    }

    protected final int zzcg(Object obj) {
        int i = this.tag >>> 3;
        switch (this.type) {
            case 10:
                return (zzeyf.zzkb(i) << 1) + ((zzeyn) obj).zzhi();
            case 11:
                return zzeyf.zzb(i, (zzeyn) obj);
            default:
                throw new IllegalArgumentException("Unknown type " + this.type);
        }
    }
}
