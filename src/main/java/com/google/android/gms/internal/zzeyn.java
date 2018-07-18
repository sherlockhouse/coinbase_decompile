package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzeyn {
    protected volatile int zzomu = -1;

    public static final <T extends zzeyn> T zza(T t, byte[] bArr) throws zzeym {
        return zza(t, bArr, 0, bArr.length);
    }

    private static <T extends zzeyn> T zza(T t, byte[] bArr, int i, int i2) throws zzeym {
        try {
            zzeye zzm = zzeye.zzm(bArr, 0, i2);
            t.zza(zzm);
            zzm.zzjk(0);
            return t;
        } catch (zzeym e) {
            throw e;
        } catch (Throwable e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzcwa();
    }

    public String toString() {
        return zzeyo.zzd(this);
    }

    public abstract zzeyn zza(zzeye com_google_android_gms_internal_zzeye) throws IOException;

    public void zza(zzeyf com_google_android_gms_internal_zzeyf) throws IOException {
    }

    public zzeyn zzcwa() throws CloneNotSupportedException {
        return (zzeyn) super.clone();
    }

    public final int zzcwg() {
        if (this.zzomu < 0) {
            zzhi();
        }
        return this.zzomu;
    }

    public final int zzhi() {
        int zzn = zzn();
        this.zzomu = zzn;
        return zzn;
    }

    protected int zzn() {
        return 0;
    }
}
