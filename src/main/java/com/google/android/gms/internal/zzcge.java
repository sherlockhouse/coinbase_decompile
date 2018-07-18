package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcge extends zzeyh<zzcge> {
    public String zzilu;
    public Long zziym;
    private Integer zziyn;
    public zzcgf[] zziyo;
    public zzcgd[] zziyp;
    public zzcfx[] zziyq;

    public zzcge() {
        this.zziym = null;
        this.zzilu = null;
        this.zziyn = null;
        this.zziyo = zzcgf.zzbae();
        this.zziyp = zzcgd.zzbad();
        this.zziyq = zzcfx.zzazz();
        this.zzotl = null;
        this.zzomu = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcge)) {
            return false;
        }
        zzcge com_google_android_gms_internal_zzcge = (zzcge) obj;
        if (this.zziym == null) {
            if (com_google_android_gms_internal_zzcge.zziym != null) {
                return false;
            }
        } else if (!this.zziym.equals(com_google_android_gms_internal_zzcge.zziym)) {
            return false;
        }
        if (this.zzilu == null) {
            if (com_google_android_gms_internal_zzcge.zzilu != null) {
                return false;
            }
        } else if (!this.zzilu.equals(com_google_android_gms_internal_zzcge.zzilu)) {
            return false;
        }
        if (this.zziyn == null) {
            if (com_google_android_gms_internal_zzcge.zziyn != null) {
                return false;
            }
        } else if (!this.zziyn.equals(com_google_android_gms_internal_zzcge.zziyn)) {
            return false;
        }
        return !zzeyl.equals(this.zziyo, com_google_android_gms_internal_zzcge.zziyo) ? false : !zzeyl.equals(this.zziyp, com_google_android_gms_internal_zzcge.zziyp) ? false : !zzeyl.equals(this.zziyq, com_google_android_gms_internal_zzcge.zziyq) ? false : (this.zzotl == null || this.zzotl.isEmpty()) ? com_google_android_gms_internal_zzcge.zzotl == null || com_google_android_gms_internal_zzcge.zzotl.isEmpty() : this.zzotl.equals(com_google_android_gms_internal_zzcge.zzotl);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((((this.zziyn == null ? 0 : this.zziyn.hashCode()) + (((this.zzilu == null ? 0 : this.zzilu.hashCode()) + (((this.zziym == null ? 0 : this.zziym.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31) + zzeyl.hashCode(this.zziyo)) * 31) + zzeyl.hashCode(this.zziyp)) * 31) + zzeyl.hashCode(this.zziyq)) * 31;
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
                    this.zziym = Long.valueOf(com_google_android_gms_internal_zzeye.zzcth());
                    continue;
                case 18:
                    this.zzilu = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 24:
                    this.zziyn = Integer.valueOf(com_google_android_gms_internal_zzeye.zzctc());
                    continue;
                case 34:
                    zzb = zzeyq.zzb(com_google_android_gms_internal_zzeye, 34);
                    zzcsn = this.zziyo == null ? 0 : this.zziyo.length;
                    obj = new zzcgf[(zzb + zzcsn)];
                    if (zzcsn != 0) {
                        System.arraycopy(this.zziyo, 0, obj, 0, zzcsn);
                    }
                    while (zzcsn < obj.length - 1) {
                        obj[zzcsn] = new zzcgf();
                        com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                        com_google_android_gms_internal_zzeye.zzcsn();
                        zzcsn++;
                    }
                    obj[zzcsn] = new zzcgf();
                    com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                    this.zziyo = obj;
                    continue;
                case 42:
                    zzb = zzeyq.zzb(com_google_android_gms_internal_zzeye, 42);
                    zzcsn = this.zziyp == null ? 0 : this.zziyp.length;
                    obj = new zzcgd[(zzb + zzcsn)];
                    if (zzcsn != 0) {
                        System.arraycopy(this.zziyp, 0, obj, 0, zzcsn);
                    }
                    while (zzcsn < obj.length - 1) {
                        obj[zzcsn] = new zzcgd();
                        com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                        com_google_android_gms_internal_zzeye.zzcsn();
                        zzcsn++;
                    }
                    obj[zzcsn] = new zzcgd();
                    com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                    this.zziyp = obj;
                    continue;
                case 50:
                    zzb = zzeyq.zzb(com_google_android_gms_internal_zzeye, 50);
                    zzcsn = this.zziyq == null ? 0 : this.zziyq.length;
                    obj = new zzcfx[(zzb + zzcsn)];
                    if (zzcsn != 0) {
                        System.arraycopy(this.zziyq, 0, obj, 0, zzcsn);
                    }
                    while (zzcsn < obj.length - 1) {
                        obj[zzcsn] = new zzcfx();
                        com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                        com_google_android_gms_internal_zzeye.zzcsn();
                        zzcsn++;
                    }
                    obj[zzcsn] = new zzcfx();
                    com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                    this.zziyq = obj;
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
        if (this.zziym != null) {
            com_google_android_gms_internal_zzeyf.zzf(1, this.zziym.longValue());
        }
        if (this.zzilu != null) {
            com_google_android_gms_internal_zzeyf.zzm(2, this.zzilu);
        }
        if (this.zziyn != null) {
            com_google_android_gms_internal_zzeyf.zzx(3, this.zziyn.intValue());
        }
        if (this.zziyo != null && this.zziyo.length > 0) {
            for (zzeyn com_google_android_gms_internal_zzeyn : this.zziyo) {
                if (com_google_android_gms_internal_zzeyn != null) {
                    com_google_android_gms_internal_zzeyf.zza(4, com_google_android_gms_internal_zzeyn);
                }
            }
        }
        if (this.zziyp != null && this.zziyp.length > 0) {
            for (zzeyn com_google_android_gms_internal_zzeyn2 : this.zziyp) {
                if (com_google_android_gms_internal_zzeyn2 != null) {
                    com_google_android_gms_internal_zzeyf.zza(5, com_google_android_gms_internal_zzeyn2);
                }
            }
        }
        if (this.zziyq != null && this.zziyq.length > 0) {
            while (i < this.zziyq.length) {
                zzeyn com_google_android_gms_internal_zzeyn3 = this.zziyq[i];
                if (com_google_android_gms_internal_zzeyn3 != null) {
                    com_google_android_gms_internal_zzeyf.zza(6, com_google_android_gms_internal_zzeyn3);
                }
                i++;
            }
        }
        super.zza(com_google_android_gms_internal_zzeyf);
    }

    protected final int zzn() {
        int i;
        int i2 = 0;
        int zzn = super.zzn();
        if (this.zziym != null) {
            zzn += zzeyf.zzc(1, this.zziym.longValue());
        }
        if (this.zzilu != null) {
            zzn += zzeyf.zzn(2, this.zzilu);
        }
        if (this.zziyn != null) {
            zzn += zzeyf.zzaa(3, this.zziyn.intValue());
        }
        if (this.zziyo != null && this.zziyo.length > 0) {
            i = zzn;
            for (zzeyn com_google_android_gms_internal_zzeyn : this.zziyo) {
                if (com_google_android_gms_internal_zzeyn != null) {
                    i += zzeyf.zzb(4, com_google_android_gms_internal_zzeyn);
                }
            }
            zzn = i;
        }
        if (this.zziyp != null && this.zziyp.length > 0) {
            i = zzn;
            for (zzeyn com_google_android_gms_internal_zzeyn2 : this.zziyp) {
                if (com_google_android_gms_internal_zzeyn2 != null) {
                    i += zzeyf.zzb(5, com_google_android_gms_internal_zzeyn2);
                }
            }
            zzn = i;
        }
        if (this.zziyq != null && this.zziyq.length > 0) {
            while (i2 < this.zziyq.length) {
                zzeyn com_google_android_gms_internal_zzeyn3 = this.zziyq[i2];
                if (com_google_android_gms_internal_zzeyn3 != null) {
                    zzn += zzeyf.zzb(6, com_google_android_gms_internal_zzeyn3);
                }
                i2++;
            }
        }
        return zzn;
    }
}
