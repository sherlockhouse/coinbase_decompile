package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcfz extends zzeyh<zzcfz> {
    private static volatile zzcfz[] zzixs;
    public zzcgc zzixt;
    public zzcga zzixu;
    public Boolean zzixv;
    public String zzixw;

    public zzcfz() {
        this.zzixt = null;
        this.zzixu = null;
        this.zzixv = null;
        this.zzixw = null;
        this.zzotl = null;
        this.zzomu = -1;
    }

    public static zzcfz[] zzbab() {
        if (zzixs == null) {
            synchronized (zzeyl.zzott) {
                if (zzixs == null) {
                    zzixs = new zzcfz[0];
                }
            }
        }
        return zzixs;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcfz)) {
            return false;
        }
        zzcfz com_google_android_gms_internal_zzcfz = (zzcfz) obj;
        if (this.zzixt == null) {
            if (com_google_android_gms_internal_zzcfz.zzixt != null) {
                return false;
            }
        } else if (!this.zzixt.equals(com_google_android_gms_internal_zzcfz.zzixt)) {
            return false;
        }
        if (this.zzixu == null) {
            if (com_google_android_gms_internal_zzcfz.zzixu != null) {
                return false;
            }
        } else if (!this.zzixu.equals(com_google_android_gms_internal_zzcfz.zzixu)) {
            return false;
        }
        if (this.zzixv == null) {
            if (com_google_android_gms_internal_zzcfz.zzixv != null) {
                return false;
            }
        } else if (!this.zzixv.equals(com_google_android_gms_internal_zzcfz.zzixv)) {
            return false;
        }
        if (this.zzixw == null) {
            if (com_google_android_gms_internal_zzcfz.zzixw != null) {
                return false;
            }
        } else if (!this.zzixw.equals(com_google_android_gms_internal_zzcfz.zzixw)) {
            return false;
        }
        return (this.zzotl == null || this.zzotl.isEmpty()) ? com_google_android_gms_internal_zzcfz.zzotl == null || com_google_android_gms_internal_zzcfz.zzotl.isEmpty() : this.zzotl.equals(com_google_android_gms_internal_zzcfz.zzotl);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = getClass().getName().hashCode() + 527;
        zzcgc com_google_android_gms_internal_zzcgc = this.zzixt;
        hashCode = (com_google_android_gms_internal_zzcgc == null ? 0 : com_google_android_gms_internal_zzcgc.hashCode()) + (hashCode * 31);
        zzcga com_google_android_gms_internal_zzcga = this.zzixu;
        hashCode = ((this.zzixw == null ? 0 : this.zzixw.hashCode()) + (((this.zzixv == null ? 0 : this.zzixv.hashCode()) + (((com_google_android_gms_internal_zzcga == null ? 0 : com_google_android_gms_internal_zzcga.hashCode()) + (hashCode * 31)) * 31)) * 31)) * 31;
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
                case 10:
                    if (this.zzixt == null) {
                        this.zzixt = new zzcgc();
                    }
                    com_google_android_gms_internal_zzeye.zza(this.zzixt);
                    continue;
                case 18:
                    if (this.zzixu == null) {
                        this.zzixu = new zzcga();
                    }
                    com_google_android_gms_internal_zzeye.zza(this.zzixu);
                    continue;
                case 24:
                    this.zzixv = Boolean.valueOf(com_google_android_gms_internal_zzeye.zzcst());
                    continue;
                case 34:
                    this.zzixw = com_google_android_gms_internal_zzeye.readString();
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
        if (this.zzixt != null) {
            com_google_android_gms_internal_zzeyf.zza(1, this.zzixt);
        }
        if (this.zzixu != null) {
            com_google_android_gms_internal_zzeyf.zza(2, this.zzixu);
        }
        if (this.zzixv != null) {
            com_google_android_gms_internal_zzeyf.zzl(3, this.zzixv.booleanValue());
        }
        if (this.zzixw != null) {
            com_google_android_gms_internal_zzeyf.zzm(4, this.zzixw);
        }
        super.zza(com_google_android_gms_internal_zzeyf);
    }

    protected final int zzn() {
        int zzn = super.zzn();
        if (this.zzixt != null) {
            zzn += zzeyf.zzb(1, this.zzixt);
        }
        if (this.zzixu != null) {
            zzn += zzeyf.zzb(2, this.zzixu);
        }
        if (this.zzixv != null) {
            this.zzixv.booleanValue();
            zzn += zzeyf.zzkb(3) + 1;
        }
        return this.zzixw != null ? zzn + zzeyf.zzn(4, this.zzixw) : zzn;
    }
}
