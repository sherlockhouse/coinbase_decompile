package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class zzeyk implements Cloneable {
    private Object value;
    private zzeyi<?, ?> zzotr;
    private List<zzeyp> zzots = new ArrayList();

    zzeyk() {
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzn()];
        zza(zzeyf.zzbf(bArr));
        return bArr;
    }

    private zzeyk zzcwb() {
        zzeyk com_google_android_gms_internal_zzeyk = new zzeyk();
        try {
            com_google_android_gms_internal_zzeyk.zzotr = this.zzotr;
            if (this.zzots == null) {
                com_google_android_gms_internal_zzeyk.zzots = null;
            } else {
                com_google_android_gms_internal_zzeyk.zzots.addAll(this.zzots);
            }
            if (this.value != null) {
                if (this.value instanceof zzeyn) {
                    com_google_android_gms_internal_zzeyk.value = (zzeyn) ((zzeyn) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    com_google_android_gms_internal_zzeyk.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    r4 = new byte[bArr.length][];
                    com_google_android_gms_internal_zzeyk.value = r4;
                    for (r2 = 0; r2 < bArr.length; r2++) {
                        r4[r2] = (byte[]) bArr[r2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    com_google_android_gms_internal_zzeyk.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    com_google_android_gms_internal_zzeyk.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    com_google_android_gms_internal_zzeyk.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    com_google_android_gms_internal_zzeyk.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    com_google_android_gms_internal_zzeyk.value = ((double[]) this.value).clone();
                } else if (this.value instanceof zzeyn[]) {
                    zzeyn[] com_google_android_gms_internal_zzeynArr = (zzeyn[]) this.value;
                    r4 = new zzeyn[com_google_android_gms_internal_zzeynArr.length];
                    com_google_android_gms_internal_zzeyk.value = r4;
                    for (r2 = 0; r2 < com_google_android_gms_internal_zzeynArr.length; r2++) {
                        r4[r2] = (zzeyn) com_google_android_gms_internal_zzeynArr[r2].clone();
                    }
                }
            }
            return com_google_android_gms_internal_zzeyk;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzcwb();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzeyk)) {
            return false;
        }
        zzeyk com_google_android_gms_internal_zzeyk = (zzeyk) obj;
        if (this.value != null && com_google_android_gms_internal_zzeyk.value != null) {
            return this.zzotr == com_google_android_gms_internal_zzeyk.zzotr ? !this.zzotr.zzmlw.isArray() ? this.value.equals(com_google_android_gms_internal_zzeyk.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) com_google_android_gms_internal_zzeyk.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) com_google_android_gms_internal_zzeyk.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) com_google_android_gms_internal_zzeyk.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) com_google_android_gms_internal_zzeyk.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) com_google_android_gms_internal_zzeyk.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) com_google_android_gms_internal_zzeyk.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) com_google_android_gms_internal_zzeyk.value) : false;
        } else {
            if (this.zzots != null && com_google_android_gms_internal_zzeyk.zzots != null) {
                return this.zzots.equals(com_google_android_gms_internal_zzeyk.zzots);
            }
            try {
                return Arrays.equals(toByteArray(), com_google_android_gms_internal_zzeyk.toByteArray());
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    final void zza(zzeyf com_google_android_gms_internal_zzeyf) throws IOException {
        if (this.value != null) {
            zzeyi com_google_android_gms_internal_zzeyi = this.zzotr;
            Object obj = this.value;
            if (com_google_android_gms_internal_zzeyi.zzotm) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    Object obj2 = Array.get(obj, i);
                    if (obj2 != null) {
                        com_google_android_gms_internal_zzeyi.zza(obj2, com_google_android_gms_internal_zzeyf);
                    }
                }
                return;
            }
            com_google_android_gms_internal_zzeyi.zza(obj, com_google_android_gms_internal_zzeyf);
            return;
        }
        for (zzeyp com_google_android_gms_internal_zzeyp : this.zzots) {
            com_google_android_gms_internal_zzeyf.zzlc(com_google_android_gms_internal_zzeyp.tag);
            com_google_android_gms_internal_zzeyf.zzbh(com_google_android_gms_internal_zzeyp.bytes);
        }
    }

    final void zza(zzeyp com_google_android_gms_internal_zzeyp) {
        this.zzots.add(com_google_android_gms_internal_zzeyp);
    }

    final int zzn() {
        int i = 0;
        int i2;
        if (this.value != null) {
            zzeyi com_google_android_gms_internal_zzeyi = this.zzotr;
            Object obj = this.value;
            if (!com_google_android_gms_internal_zzeyi.zzotm) {
                return com_google_android_gms_internal_zzeyi.zzcg(obj);
            }
            int length = Array.getLength(obj);
            for (i2 = 0; i2 < length; i2++) {
                if (Array.get(obj, i2) != null) {
                    i += com_google_android_gms_internal_zzeyi.zzcg(Array.get(obj, i2));
                }
            }
            return i;
        }
        i2 = 0;
        for (zzeyp com_google_android_gms_internal_zzeyp : this.zzots) {
            i2 = (com_google_android_gms_internal_zzeyp.bytes.length + (zzeyf.zzld(com_google_android_gms_internal_zzeyp.tag) + 0)) + i2;
        }
        return i2;
    }
}
