package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcgg extends zzeyh<zzcgg> {
    private static volatile zzcgg[] zziys;
    public Integer zzixj;
    public zzcgl zziyt;
    public zzcgl zziyu;
    public Boolean zziyv;

    public zzcgg() {
        this.zzixj = null;
        this.zziyt = null;
        this.zziyu = null;
        this.zziyv = null;
        this.zzotl = null;
        this.zzomu = -1;
    }

    public static zzcgg[] zzbaf() {
        if (zziys == null) {
            synchronized (zzeyl.zzott) {
                if (zziys == null) {
                    zziys = new zzcgg[0];
                }
            }
        }
        return zziys;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgg)) {
            return false;
        }
        zzcgg com_google_android_gms_internal_zzcgg = (zzcgg) obj;
        if (this.zzixj == null) {
            if (com_google_android_gms_internal_zzcgg.zzixj != null) {
                return false;
            }
        } else if (!this.zzixj.equals(com_google_android_gms_internal_zzcgg.zzixj)) {
            return false;
        }
        if (this.zziyt == null) {
            if (com_google_android_gms_internal_zzcgg.zziyt != null) {
                return false;
            }
        } else if (!this.zziyt.equals(com_google_android_gms_internal_zzcgg.zziyt)) {
            return false;
        }
        if (this.zziyu == null) {
            if (com_google_android_gms_internal_zzcgg.zziyu != null) {
                return false;
            }
        } else if (!this.zziyu.equals(com_google_android_gms_internal_zzcgg.zziyu)) {
            return false;
        }
        if (this.zziyv == null) {
            if (com_google_android_gms_internal_zzcgg.zziyv != null) {
                return false;
            }
        } else if (!this.zziyv.equals(com_google_android_gms_internal_zzcgg.zziyv)) {
            return false;
        }
        return (this.zzotl == null || this.zzotl.isEmpty()) ? com_google_android_gms_internal_zzcgg.zzotl == null || com_google_android_gms_internal_zzcgg.zzotl.isEmpty() : this.zzotl.equals(com_google_android_gms_internal_zzcgg.zzotl);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.zzixj == null ? 0 : this.zzixj.hashCode()) + ((getClass().getName().hashCode() + 527) * 31);
        zzcgl com_google_android_gms_internal_zzcgl = this.zziyt;
        hashCode = (com_google_android_gms_internal_zzcgl == null ? 0 : com_google_android_gms_internal_zzcgl.hashCode()) + (hashCode * 31);
        com_google_android_gms_internal_zzcgl = this.zziyu;
        hashCode = ((this.zziyv == null ? 0 : this.zziyv.hashCode()) + (((com_google_android_gms_internal_zzcgl == null ? 0 : com_google_android_gms_internal_zzcgl.hashCode()) + (hashCode * 31)) * 31)) * 31;
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
                    this.zzixj = Integer.valueOf(com_google_android_gms_internal_zzeye.zzctc());
                    continue;
                case 18:
                    if (this.zziyt == null) {
                        this.zziyt = new zzcgl();
                    }
                    com_google_android_gms_internal_zzeye.zza(this.zziyt);
                    continue;
                case 26:
                    if (this.zziyu == null) {
                        this.zziyu = new zzcgl();
                    }
                    com_google_android_gms_internal_zzeye.zza(this.zziyu);
                    continue;
                case 32:
                    this.zziyv = Boolean.valueOf(com_google_android_gms_internal_zzeye.zzcst());
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
        if (this.zzixj != null) {
            com_google_android_gms_internal_zzeyf.zzx(1, this.zzixj.intValue());
        }
        if (this.zziyt != null) {
            com_google_android_gms_internal_zzeyf.zza(2, this.zziyt);
        }
        if (this.zziyu != null) {
            com_google_android_gms_internal_zzeyf.zza(3, this.zziyu);
        }
        if (this.zziyv != null) {
            com_google_android_gms_internal_zzeyf.zzl(4, this.zziyv.booleanValue());
        }
        super.zza(com_google_android_gms_internal_zzeyf);
    }

    protected final int zzn() {
        int zzn = super.zzn();
        if (this.zzixj != null) {
            zzn += zzeyf.zzaa(1, this.zzixj.intValue());
        }
        if (this.zziyt != null) {
            zzn += zzeyf.zzb(2, this.zziyt);
        }
        if (this.zziyu != null) {
            zzn += zzeyf.zzb(3, this.zziyu);
        }
        if (this.zziyv == null) {
            return zzn;
        }
        this.zziyv.booleanValue();
        return zzn + (zzeyf.zzkb(4) + 1);
    }
}
