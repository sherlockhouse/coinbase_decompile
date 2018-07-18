package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcgm extends zzeyh<zzcgm> {
    private static volatile zzcgm[] zzjai;
    public String name;
    public String zzfwo;
    private Float zzixb;
    public Double zzixc;
    public Long zzizb;
    public Long zzjaj;

    public zzcgm() {
        this.zzjaj = null;
        this.name = null;
        this.zzfwo = null;
        this.zzizb = null;
        this.zzixb = null;
        this.zzixc = null;
        this.zzotl = null;
        this.zzomu = -1;
    }

    public static zzcgm[] zzbaj() {
        if (zzjai == null) {
            synchronized (zzeyl.zzott) {
                if (zzjai == null) {
                    zzjai = new zzcgm[0];
                }
            }
        }
        return zzjai;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgm)) {
            return false;
        }
        zzcgm com_google_android_gms_internal_zzcgm = (zzcgm) obj;
        if (this.zzjaj == null) {
            if (com_google_android_gms_internal_zzcgm.zzjaj != null) {
                return false;
            }
        } else if (!this.zzjaj.equals(com_google_android_gms_internal_zzcgm.zzjaj)) {
            return false;
        }
        if (this.name == null) {
            if (com_google_android_gms_internal_zzcgm.name != null) {
                return false;
            }
        } else if (!this.name.equals(com_google_android_gms_internal_zzcgm.name)) {
            return false;
        }
        if (this.zzfwo == null) {
            if (com_google_android_gms_internal_zzcgm.zzfwo != null) {
                return false;
            }
        } else if (!this.zzfwo.equals(com_google_android_gms_internal_zzcgm.zzfwo)) {
            return false;
        }
        if (this.zzizb == null) {
            if (com_google_android_gms_internal_zzcgm.zzizb != null) {
                return false;
            }
        } else if (!this.zzizb.equals(com_google_android_gms_internal_zzcgm.zzizb)) {
            return false;
        }
        if (this.zzixb == null) {
            if (com_google_android_gms_internal_zzcgm.zzixb != null) {
                return false;
            }
        } else if (!this.zzixb.equals(com_google_android_gms_internal_zzcgm.zzixb)) {
            return false;
        }
        if (this.zzixc == null) {
            if (com_google_android_gms_internal_zzcgm.zzixc != null) {
                return false;
            }
        } else if (!this.zzixc.equals(com_google_android_gms_internal_zzcgm.zzixc)) {
            return false;
        }
        return (this.zzotl == null || this.zzotl.isEmpty()) ? com_google_android_gms_internal_zzcgm.zzotl == null || com_google_android_gms_internal_zzcgm.zzotl.isEmpty() : this.zzotl.equals(com_google_android_gms_internal_zzcgm.zzotl);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zzixc == null ? 0 : this.zzixc.hashCode()) + (((this.zzixb == null ? 0 : this.zzixb.hashCode()) + (((this.zzizb == null ? 0 : this.zzizb.hashCode()) + (((this.zzfwo == null ? 0 : this.zzfwo.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + (((this.zzjaj == null ? 0 : this.zzjaj.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
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
                    this.zzjaj = Long.valueOf(com_google_android_gms_internal_zzeye.zzcth());
                    continue;
                case 18:
                    this.name = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 26:
                    this.zzfwo = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 32:
                    this.zzizb = Long.valueOf(com_google_android_gms_internal_zzeye.zzcth());
                    continue;
                case 45:
                    this.zzixb = Float.valueOf(Float.intBitsToFloat(com_google_android_gms_internal_zzeye.zzcti()));
                    continue;
                case 49:
                    this.zzixc = Double.valueOf(Double.longBitsToDouble(com_google_android_gms_internal_zzeye.zzctj()));
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
        if (this.zzjaj != null) {
            com_google_android_gms_internal_zzeyf.zzf(1, this.zzjaj.longValue());
        }
        if (this.name != null) {
            com_google_android_gms_internal_zzeyf.zzm(2, this.name);
        }
        if (this.zzfwo != null) {
            com_google_android_gms_internal_zzeyf.zzm(3, this.zzfwo);
        }
        if (this.zzizb != null) {
            com_google_android_gms_internal_zzeyf.zzf(4, this.zzizb.longValue());
        }
        if (this.zzixb != null) {
            com_google_android_gms_internal_zzeyf.zzc(5, this.zzixb.floatValue());
        }
        if (this.zzixc != null) {
            com_google_android_gms_internal_zzeyf.zza(6, this.zzixc.doubleValue());
        }
        super.zza(com_google_android_gms_internal_zzeyf);
    }

    protected final int zzn() {
        int zzn = super.zzn();
        if (this.zzjaj != null) {
            zzn += zzeyf.zzc(1, this.zzjaj.longValue());
        }
        if (this.name != null) {
            zzn += zzeyf.zzn(2, this.name);
        }
        if (this.zzfwo != null) {
            zzn += zzeyf.zzn(3, this.zzfwo);
        }
        if (this.zzizb != null) {
            zzn += zzeyf.zzc(4, this.zzizb.longValue());
        }
        if (this.zzixb != null) {
            this.zzixb.floatValue();
            zzn += zzeyf.zzkb(5) + 4;
        }
        if (this.zzixc == null) {
            return zzn;
        }
        this.zzixc.doubleValue();
        return zzn + (zzeyf.zzkb(6) + 8);
    }
}
