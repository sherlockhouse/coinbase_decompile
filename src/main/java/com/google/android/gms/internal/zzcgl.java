package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcgl extends zzeyh<zzcgl> {
    public long[] zzjag;
    public long[] zzjah;

    public zzcgl() {
        this.zzjag = zzeyq.zzotz;
        this.zzjah = zzeyq.zzotz;
        this.zzotl = null;
        this.zzomu = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgl)) {
            return false;
        }
        zzcgl com_google_android_gms_internal_zzcgl = (zzcgl) obj;
        return !zzeyl.equals(this.zzjag, com_google_android_gms_internal_zzcgl.zzjag) ? false : !zzeyl.equals(this.zzjah, com_google_android_gms_internal_zzcgl.zzjah) ? false : (this.zzotl == null || this.zzotl.isEmpty()) ? com_google_android_gms_internal_zzcgl.zzotl == null || com_google_android_gms_internal_zzcgl.zzotl.isEmpty() : this.zzotl.equals(com_google_android_gms_internal_zzcgl.zzotl);
    }

    public final int hashCode() {
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + zzeyl.hashCode(this.zzjag)) * 31) + zzeyl.hashCode(this.zzjah)) * 31;
        int hashCode2 = (this.zzotl == null || this.zzotl.isEmpty()) ? 0 : this.zzotl.hashCode();
        return hashCode2 + hashCode;
    }

    public final /* synthetic */ zzeyn zza(zzeye com_google_android_gms_internal_zzeye) throws IOException {
        while (true) {
            int zzcsn = com_google_android_gms_internal_zzeye.zzcsn();
            int zzb;
            Object obj;
            int zzjn;
            Object obj2;
            switch (zzcsn) {
                case 0:
                    break;
                case 8:
                    zzb = zzeyq.zzb(com_google_android_gms_internal_zzeye, 8);
                    zzcsn = this.zzjag == null ? 0 : this.zzjag.length;
                    obj = new long[(zzb + zzcsn)];
                    if (zzcsn != 0) {
                        System.arraycopy(this.zzjag, 0, obj, 0, zzcsn);
                    }
                    while (zzcsn < obj.length - 1) {
                        obj[zzcsn] = com_google_android_gms_internal_zzeye.zzcth();
                        com_google_android_gms_internal_zzeye.zzcsn();
                        zzcsn++;
                    }
                    obj[zzcsn] = com_google_android_gms_internal_zzeye.zzcth();
                    this.zzjag = obj;
                    continue;
                case 10:
                    zzjn = com_google_android_gms_internal_zzeye.zzjn(com_google_android_gms_internal_zzeye.zzctc());
                    zzb = com_google_android_gms_internal_zzeye.getPosition();
                    zzcsn = 0;
                    while (com_google_android_gms_internal_zzeye.zzcte() > 0) {
                        com_google_android_gms_internal_zzeye.zzcth();
                        zzcsn++;
                    }
                    com_google_android_gms_internal_zzeye.zzla(zzb);
                    zzb = this.zzjag == null ? 0 : this.zzjag.length;
                    obj2 = new long[(zzcsn + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzjag, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzeye.zzcth();
                        zzb++;
                    }
                    this.zzjag = obj2;
                    com_google_android_gms_internal_zzeye.zzjo(zzjn);
                    continue;
                case 16:
                    zzb = zzeyq.zzb(com_google_android_gms_internal_zzeye, 16);
                    zzcsn = this.zzjah == null ? 0 : this.zzjah.length;
                    obj = new long[(zzb + zzcsn)];
                    if (zzcsn != 0) {
                        System.arraycopy(this.zzjah, 0, obj, 0, zzcsn);
                    }
                    while (zzcsn < obj.length - 1) {
                        obj[zzcsn] = com_google_android_gms_internal_zzeye.zzcth();
                        com_google_android_gms_internal_zzeye.zzcsn();
                        zzcsn++;
                    }
                    obj[zzcsn] = com_google_android_gms_internal_zzeye.zzcth();
                    this.zzjah = obj;
                    continue;
                case 18:
                    zzjn = com_google_android_gms_internal_zzeye.zzjn(com_google_android_gms_internal_zzeye.zzctc());
                    zzb = com_google_android_gms_internal_zzeye.getPosition();
                    zzcsn = 0;
                    while (com_google_android_gms_internal_zzeye.zzcte() > 0) {
                        com_google_android_gms_internal_zzeye.zzcth();
                        zzcsn++;
                    }
                    com_google_android_gms_internal_zzeye.zzla(zzb);
                    zzb = this.zzjah == null ? 0 : this.zzjah.length;
                    obj2 = new long[(zzcsn + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzjah, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzeye.zzcth();
                        zzb++;
                    }
                    this.zzjah = obj2;
                    com_google_android_gms_internal_zzeye.zzjo(zzjn);
                    continue;
                default:
                    if (!super.zza(com_google_android_gms_internal_zzeye, zzcsn)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public final void zza(zzeyf com_google_android_gms_internal_zzeyf) throws IOException {
        int i = 0;
        if (this.zzjag != null && this.zzjag.length > 0) {
            for (long zza : this.zzjag) {
                com_google_android_gms_internal_zzeyf.zza(1, zza);
            }
        }
        if (this.zzjah != null && this.zzjah.length > 0) {
            while (i < this.zzjah.length) {
                com_google_android_gms_internal_zzeyf.zza(2, this.zzjah[i]);
                i++;
            }
        }
        super.zza(com_google_android_gms_internal_zzeyf);
    }

    protected final int zzn() {
        int i;
        int i2;
        int i3 = 0;
        int zzn = super.zzn();
        if (this.zzjag == null || this.zzjag.length <= 0) {
            i = zzn;
        } else {
            i2 = 0;
            for (long zzdg : this.zzjag) {
                i2 += zzeyf.zzdg(zzdg);
            }
            i = (zzn + i2) + (this.zzjag.length * 1);
        }
        if (this.zzjah == null || this.zzjah.length <= 0) {
            return i;
        }
        i2 = 0;
        while (i3 < this.zzjah.length) {
            i2 += zzeyf.zzdg(this.zzjah[i3]);
            i3++;
        }
        return (i + i2) + (this.zzjah.length * 1);
    }
}
