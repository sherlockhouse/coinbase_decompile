package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcgb extends zzeyh<zzcgb> {
    private static volatile zzcgb[] zziyc;
    public Integer zzixn;
    public String zziyd;
    public zzcfz zziye;

    public zzcgb() {
        this.zzixn = null;
        this.zziyd = null;
        this.zziye = null;
        this.zzotl = null;
        this.zzomu = -1;
    }

    public static zzcgb[] zzbac() {
        if (zziyc == null) {
            synchronized (zzeyl.zzott) {
                if (zziyc == null) {
                    zziyc = new zzcgb[0];
                }
            }
        }
        return zziyc;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgb)) {
            return false;
        }
        zzcgb com_google_android_gms_internal_zzcgb = (zzcgb) obj;
        if (this.zzixn == null) {
            if (com_google_android_gms_internal_zzcgb.zzixn != null) {
                return false;
            }
        } else if (!this.zzixn.equals(com_google_android_gms_internal_zzcgb.zzixn)) {
            return false;
        }
        if (this.zziyd == null) {
            if (com_google_android_gms_internal_zzcgb.zziyd != null) {
                return false;
            }
        } else if (!this.zziyd.equals(com_google_android_gms_internal_zzcgb.zziyd)) {
            return false;
        }
        if (this.zziye == null) {
            if (com_google_android_gms_internal_zzcgb.zziye != null) {
                return false;
            }
        } else if (!this.zziye.equals(com_google_android_gms_internal_zzcgb.zziye)) {
            return false;
        }
        return (this.zzotl == null || this.zzotl.isEmpty()) ? com_google_android_gms_internal_zzcgb.zzotl == null || com_google_android_gms_internal_zzcgb.zzotl.isEmpty() : this.zzotl.equals(com_google_android_gms_internal_zzcgb.zzotl);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.zziyd == null ? 0 : this.zziyd.hashCode()) + (((this.zzixn == null ? 0 : this.zzixn.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31);
        zzcfz com_google_android_gms_internal_zzcfz = this.zziye;
        hashCode = ((com_google_android_gms_internal_zzcfz == null ? 0 : com_google_android_gms_internal_zzcfz.hashCode()) + (hashCode * 31)) * 31;
        if (!(this.zzotl == null || this.zzotl.isEmpty())) {
            i = this.zzotl.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ zzeyn zza(zzeye com_google_android_gms_internal_zzeye) throws IOException {
        while (true) {
            int zzcsn = com_google_android_gms_internal_zzeye.zzcsn();
            switch (zzcsn) {
                case 0:
                    break;
                case 8:
                    this.zzixn = Integer.valueOf(com_google_android_gms_internal_zzeye.zzctc());
                    continue;
                case 18:
                    this.zziyd = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 26:
                    if (this.zziye == null) {
                        this.zziye = new zzcfz();
                    }
                    com_google_android_gms_internal_zzeye.zza(this.zziye);
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
        if (this.zzixn != null) {
            com_google_android_gms_internal_zzeyf.zzx(1, this.zzixn.intValue());
        }
        if (this.zziyd != null) {
            com_google_android_gms_internal_zzeyf.zzm(2, this.zziyd);
        }
        if (this.zziye != null) {
            com_google_android_gms_internal_zzeyf.zza(3, this.zziye);
        }
        super.zza(com_google_android_gms_internal_zzeyf);
    }

    protected final int zzn() {
        int zzn = super.zzn();
        if (this.zzixn != null) {
            zzn += zzeyf.zzaa(1, this.zzixn.intValue());
        }
        if (this.zziyd != null) {
            zzn += zzeyf.zzn(2, this.zziyd);
        }
        return this.zziye != null ? zzn + zzeyf.zzb(3, this.zziye) : zzn;
    }
}
