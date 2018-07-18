package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcgi extends zzeyh<zzcgi> {
    private static volatile zzcgi[] zziza;
    public String name;
    public String zzfwo;
    private Float zzixb;
    public Double zzixc;
    public Long zzizb;

    public zzcgi() {
        this.name = null;
        this.zzfwo = null;
        this.zzizb = null;
        this.zzixb = null;
        this.zzixc = null;
        this.zzotl = null;
        this.zzomu = -1;
    }

    public static zzcgi[] zzbah() {
        if (zziza == null) {
            synchronized (zzeyl.zzott) {
                if (zziza == null) {
                    zziza = new zzcgi[0];
                }
            }
        }
        return zziza;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgi)) {
            return false;
        }
        zzcgi com_google_android_gms_internal_zzcgi = (zzcgi) obj;
        if (this.name == null) {
            if (com_google_android_gms_internal_zzcgi.name != null) {
                return false;
            }
        } else if (!this.name.equals(com_google_android_gms_internal_zzcgi.name)) {
            return false;
        }
        if (this.zzfwo == null) {
            if (com_google_android_gms_internal_zzcgi.zzfwo != null) {
                return false;
            }
        } else if (!this.zzfwo.equals(com_google_android_gms_internal_zzcgi.zzfwo)) {
            return false;
        }
        if (this.zzizb == null) {
            if (com_google_android_gms_internal_zzcgi.zzizb != null) {
                return false;
            }
        } else if (!this.zzizb.equals(com_google_android_gms_internal_zzcgi.zzizb)) {
            return false;
        }
        if (this.zzixb == null) {
            if (com_google_android_gms_internal_zzcgi.zzixb != null) {
                return false;
            }
        } else if (!this.zzixb.equals(com_google_android_gms_internal_zzcgi.zzixb)) {
            return false;
        }
        if (this.zzixc == null) {
            if (com_google_android_gms_internal_zzcgi.zzixc != null) {
                return false;
            }
        } else if (!this.zzixc.equals(com_google_android_gms_internal_zzcgi.zzixc)) {
            return false;
        }
        return (this.zzotl == null || this.zzotl.isEmpty()) ? com_google_android_gms_internal_zzcgi.zzotl == null || com_google_android_gms_internal_zzcgi.zzotl.isEmpty() : this.zzotl.equals(com_google_android_gms_internal_zzcgi.zzotl);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zzixc == null ? 0 : this.zzixc.hashCode()) + (((this.zzixb == null ? 0 : this.zzixb.hashCode()) + (((this.zzizb == null ? 0 : this.zzizb.hashCode()) + (((this.zzfwo == null ? 0 : this.zzfwo.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
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
                    this.name = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 18:
                    this.zzfwo = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 24:
                    this.zzizb = Long.valueOf(com_google_android_gms_internal_zzeye.zzcth());
                    continue;
                case 37:
                    this.zzixb = Float.valueOf(Float.intBitsToFloat(com_google_android_gms_internal_zzeye.zzcti()));
                    continue;
                case 41:
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
        if (this.name != null) {
            com_google_android_gms_internal_zzeyf.zzm(1, this.name);
        }
        if (this.zzfwo != null) {
            com_google_android_gms_internal_zzeyf.zzm(2, this.zzfwo);
        }
        if (this.zzizb != null) {
            com_google_android_gms_internal_zzeyf.zzf(3, this.zzizb.longValue());
        }
        if (this.zzixb != null) {
            com_google_android_gms_internal_zzeyf.zzc(4, this.zzixb.floatValue());
        }
        if (this.zzixc != null) {
            com_google_android_gms_internal_zzeyf.zza(5, this.zzixc.doubleValue());
        }
        super.zza(com_google_android_gms_internal_zzeyf);
    }

    protected final int zzn() {
        int zzn = super.zzn();
        if (this.name != null) {
            zzn += zzeyf.zzn(1, this.name);
        }
        if (this.zzfwo != null) {
            zzn += zzeyf.zzn(2, this.zzfwo);
        }
        if (this.zzizb != null) {
            zzn += zzeyf.zzc(3, this.zzizb.longValue());
        }
        if (this.zzixb != null) {
            this.zzixb.floatValue();
            zzn += zzeyf.zzkb(4) + 4;
        }
        if (this.zzixc == null) {
            return zzn;
        }
        this.zzixc.doubleValue();
        return zzn + (zzeyf.zzkb(5) + 8);
    }
}
