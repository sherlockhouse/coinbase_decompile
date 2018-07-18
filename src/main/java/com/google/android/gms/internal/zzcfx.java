package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcfx extends zzeyh<zzcfx> {
    private static volatile zzcfx[] zzixi;
    public Integer zzixj;
    public zzcgb[] zzixk;
    public zzcfy[] zzixl;

    public zzcfx() {
        this.zzixj = null;
        this.zzixk = zzcgb.zzbac();
        this.zzixl = zzcfy.zzbaa();
        this.zzotl = null;
        this.zzomu = -1;
    }

    public static zzcfx[] zzazz() {
        if (zzixi == null) {
            synchronized (zzeyl.zzott) {
                if (zzixi == null) {
                    zzixi = new zzcfx[0];
                }
            }
        }
        return zzixi;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcfx)) {
            return false;
        }
        zzcfx com_google_android_gms_internal_zzcfx = (zzcfx) obj;
        if (this.zzixj == null) {
            if (com_google_android_gms_internal_zzcfx.zzixj != null) {
                return false;
            }
        } else if (!this.zzixj.equals(com_google_android_gms_internal_zzcfx.zzixj)) {
            return false;
        }
        return !zzeyl.equals(this.zzixk, com_google_android_gms_internal_zzcfx.zzixk) ? false : !zzeyl.equals(this.zzixl, com_google_android_gms_internal_zzcfx.zzixl) ? false : (this.zzotl == null || this.zzotl.isEmpty()) ? com_google_android_gms_internal_zzcfx.zzotl == null || com_google_android_gms_internal_zzcfx.zzotl.isEmpty() : this.zzotl.equals(com_google_android_gms_internal_zzcfx.zzotl);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((this.zzixj == null ? 0 : this.zzixj.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + zzeyl.hashCode(this.zzixk)) * 31) + zzeyl.hashCode(this.zzixl)) * 31;
        if (!(this.zzotl == null || this.zzotl.isEmpty())) {
            i = this.zzotl.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ zzeyn zza(zzeye com_google_android_gms_internal_zzeye) throws IOException {
        while (true) {
            int zzcsn = com_google_android_gms_internal_zzeye.zzcsn();
            int zzb;
            Object obj;
            switch (zzcsn) {
                case 0:
                    break;
                case 8:
                    this.zzixj = Integer.valueOf(com_google_android_gms_internal_zzeye.zzctc());
                    continue;
                case 18:
                    zzb = zzeyq.zzb(com_google_android_gms_internal_zzeye, 18);
                    zzcsn = this.zzixk == null ? 0 : this.zzixk.length;
                    obj = new zzcgb[(zzb + zzcsn)];
                    if (zzcsn != 0) {
                        System.arraycopy(this.zzixk, 0, obj, 0, zzcsn);
                    }
                    while (zzcsn < obj.length - 1) {
                        obj[zzcsn] = new zzcgb();
                        com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                        com_google_android_gms_internal_zzeye.zzcsn();
                        zzcsn++;
                    }
                    obj[zzcsn] = new zzcgb();
                    com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                    this.zzixk = obj;
                    continue;
                case 26:
                    zzb = zzeyq.zzb(com_google_android_gms_internal_zzeye, 26);
                    zzcsn = this.zzixl == null ? 0 : this.zzixl.length;
                    obj = new zzcfy[(zzb + zzcsn)];
                    if (zzcsn != 0) {
                        System.arraycopy(this.zzixl, 0, obj, 0, zzcsn);
                    }
                    while (zzcsn < obj.length - 1) {
                        obj[zzcsn] = new zzcfy();
                        com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                        com_google_android_gms_internal_zzeye.zzcsn();
                        zzcsn++;
                    }
                    obj[zzcsn] = new zzcfy();
                    com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                    this.zzixl = obj;
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
        if (this.zzixj != null) {
            com_google_android_gms_internal_zzeyf.zzx(1, this.zzixj.intValue());
        }
        if (this.zzixk != null && this.zzixk.length > 0) {
            for (zzeyn com_google_android_gms_internal_zzeyn : this.zzixk) {
                if (com_google_android_gms_internal_zzeyn != null) {
                    com_google_android_gms_internal_zzeyf.zza(2, com_google_android_gms_internal_zzeyn);
                }
            }
        }
        if (this.zzixl != null && this.zzixl.length > 0) {
            while (i < this.zzixl.length) {
                zzeyn com_google_android_gms_internal_zzeyn2 = this.zzixl[i];
                if (com_google_android_gms_internal_zzeyn2 != null) {
                    com_google_android_gms_internal_zzeyf.zza(3, com_google_android_gms_internal_zzeyn2);
                }
                i++;
            }
        }
        super.zza(com_google_android_gms_internal_zzeyf);
    }

    protected final int zzn() {
        int i = 0;
        int zzn = super.zzn();
        if (this.zzixj != null) {
            zzn += zzeyf.zzaa(1, this.zzixj.intValue());
        }
        if (this.zzixk != null && this.zzixk.length > 0) {
            int i2 = zzn;
            for (zzeyn com_google_android_gms_internal_zzeyn : this.zzixk) {
                if (com_google_android_gms_internal_zzeyn != null) {
                    i2 += zzeyf.zzb(2, com_google_android_gms_internal_zzeyn);
                }
            }
            zzn = i2;
        }
        if (this.zzixl != null && this.zzixl.length > 0) {
            while (i < this.zzixl.length) {
                zzeyn com_google_android_gms_internal_zzeyn2 = this.zzixl[i];
                if (com_google_android_gms_internal_zzeyn2 != null) {
                    zzn += zzeyf.zzb(3, com_google_android_gms_internal_zzeyn2);
                }
                i++;
            }
        }
        return zzn;
    }
}
