package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcgj extends zzeyh<zzcgj> {
    public zzcgk[] zzizc;

    public zzcgj() {
        this.zzizc = zzcgk.zzbai();
        this.zzotl = null;
        this.zzomu = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgj)) {
            return false;
        }
        zzcgj com_google_android_gms_internal_zzcgj = (zzcgj) obj;
        return !zzeyl.equals(this.zzizc, com_google_android_gms_internal_zzcgj.zzizc) ? false : (this.zzotl == null || this.zzotl.isEmpty()) ? com_google_android_gms_internal_zzcgj.zzotl == null || com_google_android_gms_internal_zzcgj.zzotl.isEmpty() : this.zzotl.equals(com_google_android_gms_internal_zzcgj.zzotl);
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + zzeyl.hashCode(this.zzizc)) * 31;
        int hashCode2 = (this.zzotl == null || this.zzotl.isEmpty()) ? 0 : this.zzotl.hashCode();
        return hashCode2 + hashCode;
    }

    public final /* synthetic */ zzeyn zza(zzeye com_google_android_gms_internal_zzeye) throws IOException {
        while (true) {
            int zzcsn = com_google_android_gms_internal_zzeye.zzcsn();
            switch (zzcsn) {
                case 0:
                    break;
                case 10:
                    int zzb = zzeyq.zzb(com_google_android_gms_internal_zzeye, 10);
                    zzcsn = this.zzizc == null ? 0 : this.zzizc.length;
                    Object obj = new zzcgk[(zzb + zzcsn)];
                    if (zzcsn != 0) {
                        System.arraycopy(this.zzizc, 0, obj, 0, zzcsn);
                    }
                    while (zzcsn < obj.length - 1) {
                        obj[zzcsn] = new zzcgk();
                        com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                        com_google_android_gms_internal_zzeye.zzcsn();
                        zzcsn++;
                    }
                    obj[zzcsn] = new zzcgk();
                    com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                    this.zzizc = obj;
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
        if (this.zzizc != null && this.zzizc.length > 0) {
            for (zzeyn com_google_android_gms_internal_zzeyn : this.zzizc) {
                if (com_google_android_gms_internal_zzeyn != null) {
                    com_google_android_gms_internal_zzeyf.zza(1, com_google_android_gms_internal_zzeyn);
                }
            }
        }
        super.zza(com_google_android_gms_internal_zzeyf);
    }

    protected final int zzn() {
        int zzn = super.zzn();
        if (this.zzizc != null && this.zzizc.length > 0) {
            for (zzeyn com_google_android_gms_internal_zzeyn : this.zzizc) {
                if (com_google_android_gms_internal_zzeyn != null) {
                    zzn += zzeyf.zzb(1, com_google_android_gms_internal_zzeyn);
                }
            }
        }
        return zzn;
    }
}
