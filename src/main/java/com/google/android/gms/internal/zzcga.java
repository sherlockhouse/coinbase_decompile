package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcga extends zzeyh<zzcga> {
    public Integer zzixx;
    public Boolean zzixy;
    public String zzixz;
    public String zziya;
    public String zziyb;

    public zzcga() {
        this.zzixx = null;
        this.zzixy = null;
        this.zzixz = null;
        this.zziya = null;
        this.zziyb = null;
        this.zzotl = null;
        this.zzomu = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcga)) {
            return false;
        }
        zzcga com_google_android_gms_internal_zzcga = (zzcga) obj;
        if (this.zzixx == null) {
            if (com_google_android_gms_internal_zzcga.zzixx != null) {
                return false;
            }
        } else if (!this.zzixx.equals(com_google_android_gms_internal_zzcga.zzixx)) {
            return false;
        }
        if (this.zzixy == null) {
            if (com_google_android_gms_internal_zzcga.zzixy != null) {
                return false;
            }
        } else if (!this.zzixy.equals(com_google_android_gms_internal_zzcga.zzixy)) {
            return false;
        }
        if (this.zzixz == null) {
            if (com_google_android_gms_internal_zzcga.zzixz != null) {
                return false;
            }
        } else if (!this.zzixz.equals(com_google_android_gms_internal_zzcga.zzixz)) {
            return false;
        }
        if (this.zziya == null) {
            if (com_google_android_gms_internal_zzcga.zziya != null) {
                return false;
            }
        } else if (!this.zziya.equals(com_google_android_gms_internal_zzcga.zziya)) {
            return false;
        }
        if (this.zziyb == null) {
            if (com_google_android_gms_internal_zzcga.zziyb != null) {
                return false;
            }
        } else if (!this.zziyb.equals(com_google_android_gms_internal_zzcga.zziyb)) {
            return false;
        }
        return (this.zzotl == null || this.zzotl.isEmpty()) ? com_google_android_gms_internal_zzcga.zzotl == null || com_google_android_gms_internal_zzcga.zzotl.isEmpty() : this.zzotl.equals(com_google_android_gms_internal_zzcga.zzotl);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zziyb == null ? 0 : this.zziyb.hashCode()) + (((this.zziya == null ? 0 : this.zziya.hashCode()) + (((this.zzixz == null ? 0 : this.zzixz.hashCode()) + (((this.zzixy == null ? 0 : this.zzixy.hashCode()) + (((this.zzixx == null ? 0 : this.zzixx.intValue()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
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
                    int position = com_google_android_gms_internal_zzeye.getPosition();
                    int zzctc = com_google_android_gms_internal_zzeye.zzctc();
                    switch (zzctc) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                            this.zzixx = Integer.valueOf(zzctc);
                            break;
                        default:
                            com_google_android_gms_internal_zzeye.zzla(position);
                            zza(com_google_android_gms_internal_zzeye, zzcsn);
                            continue;
                    }
                case 16:
                    this.zzixy = Boolean.valueOf(com_google_android_gms_internal_zzeye.zzcst());
                    continue;
                case 26:
                    this.zzixz = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 34:
                    this.zziya = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 42:
                    this.zziyb = com_google_android_gms_internal_zzeye.readString();
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
        if (this.zzixx != null) {
            com_google_android_gms_internal_zzeyf.zzx(1, this.zzixx.intValue());
        }
        if (this.zzixy != null) {
            com_google_android_gms_internal_zzeyf.zzl(2, this.zzixy.booleanValue());
        }
        if (this.zzixz != null) {
            com_google_android_gms_internal_zzeyf.zzm(3, this.zzixz);
        }
        if (this.zziya != null) {
            com_google_android_gms_internal_zzeyf.zzm(4, this.zziya);
        }
        if (this.zziyb != null) {
            com_google_android_gms_internal_zzeyf.zzm(5, this.zziyb);
        }
        super.zza(com_google_android_gms_internal_zzeyf);
    }

    protected final int zzn() {
        int zzn = super.zzn();
        if (this.zzixx != null) {
            zzn += zzeyf.zzaa(1, this.zzixx.intValue());
        }
        if (this.zzixy != null) {
            this.zzixy.booleanValue();
            zzn += zzeyf.zzkb(2) + 1;
        }
        if (this.zzixz != null) {
            zzn += zzeyf.zzn(3, this.zzixz);
        }
        if (this.zziya != null) {
            zzn += zzeyf.zzn(4, this.zziya);
        }
        return this.zziyb != null ? zzn + zzeyf.zzn(5, this.zziyb) : zzn;
    }
}
