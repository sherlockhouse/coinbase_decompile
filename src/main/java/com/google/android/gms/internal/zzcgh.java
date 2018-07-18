package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcgh extends zzeyh<zzcgh> {
    private static volatile zzcgh[] zziyw;
    public Integer count;
    public String name;
    public zzcgi[] zziyx;
    public Long zziyy;
    public Long zziyz;

    public zzcgh() {
        this.zziyx = zzcgi.zzbah();
        this.name = null;
        this.zziyy = null;
        this.zziyz = null;
        this.count = null;
        this.zzotl = null;
        this.zzomu = -1;
    }

    public static zzcgh[] zzbag() {
        if (zziyw == null) {
            synchronized (zzeyl.zzott) {
                if (zziyw == null) {
                    zziyw = new zzcgh[0];
                }
            }
        }
        return zziyw;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgh)) {
            return false;
        }
        zzcgh com_google_android_gms_internal_zzcgh = (zzcgh) obj;
        if (!zzeyl.equals(this.zziyx, com_google_android_gms_internal_zzcgh.zziyx)) {
            return false;
        }
        if (this.name == null) {
            if (com_google_android_gms_internal_zzcgh.name != null) {
                return false;
            }
        } else if (!this.name.equals(com_google_android_gms_internal_zzcgh.name)) {
            return false;
        }
        if (this.zziyy == null) {
            if (com_google_android_gms_internal_zzcgh.zziyy != null) {
                return false;
            }
        } else if (!this.zziyy.equals(com_google_android_gms_internal_zzcgh.zziyy)) {
            return false;
        }
        if (this.zziyz == null) {
            if (com_google_android_gms_internal_zzcgh.zziyz != null) {
                return false;
            }
        } else if (!this.zziyz.equals(com_google_android_gms_internal_zzcgh.zziyz)) {
            return false;
        }
        if (this.count == null) {
            if (com_google_android_gms_internal_zzcgh.count != null) {
                return false;
            }
        } else if (!this.count.equals(com_google_android_gms_internal_zzcgh.count)) {
            return false;
        }
        return (this.zzotl == null || this.zzotl.isEmpty()) ? com_google_android_gms_internal_zzcgh.zzotl == null || com_google_android_gms_internal_zzcgh.zzotl.isEmpty() : this.zzotl.equals(com_google_android_gms_internal_zzcgh.zzotl);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.count == null ? 0 : this.count.hashCode()) + (((this.zziyz == null ? 0 : this.zziyz.hashCode()) + (((this.zziyy == null ? 0 : this.zziyy.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + zzeyl.hashCode(this.zziyx)) * 31)) * 31)) * 31)) * 31)) * 31;
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
                    int zzb = zzeyq.zzb(com_google_android_gms_internal_zzeye, 10);
                    zzcsn = this.zziyx == null ? 0 : this.zziyx.length;
                    Object obj = new zzcgi[(zzb + zzcsn)];
                    if (zzcsn != 0) {
                        System.arraycopy(this.zziyx, 0, obj, 0, zzcsn);
                    }
                    while (zzcsn < obj.length - 1) {
                        obj[zzcsn] = new zzcgi();
                        com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                        com_google_android_gms_internal_zzeye.zzcsn();
                        zzcsn++;
                    }
                    obj[zzcsn] = new zzcgi();
                    com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                    this.zziyx = obj;
                    continue;
                case 18:
                    this.name = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 24:
                    this.zziyy = Long.valueOf(com_google_android_gms_internal_zzeye.zzcth());
                    continue;
                case 32:
                    this.zziyz = Long.valueOf(com_google_android_gms_internal_zzeye.zzcth());
                    continue;
                case 40:
                    this.count = Integer.valueOf(com_google_android_gms_internal_zzeye.zzctc());
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
        if (this.zziyx != null && this.zziyx.length > 0) {
            for (zzeyn com_google_android_gms_internal_zzeyn : this.zziyx) {
                if (com_google_android_gms_internal_zzeyn != null) {
                    com_google_android_gms_internal_zzeyf.zza(1, com_google_android_gms_internal_zzeyn);
                }
            }
        }
        if (this.name != null) {
            com_google_android_gms_internal_zzeyf.zzm(2, this.name);
        }
        if (this.zziyy != null) {
            com_google_android_gms_internal_zzeyf.zzf(3, this.zziyy.longValue());
        }
        if (this.zziyz != null) {
            com_google_android_gms_internal_zzeyf.zzf(4, this.zziyz.longValue());
        }
        if (this.count != null) {
            com_google_android_gms_internal_zzeyf.zzx(5, this.count.intValue());
        }
        super.zza(com_google_android_gms_internal_zzeyf);
    }

    protected final int zzn() {
        int zzn = super.zzn();
        if (this.zziyx != null && this.zziyx.length > 0) {
            for (zzeyn com_google_android_gms_internal_zzeyn : this.zziyx) {
                if (com_google_android_gms_internal_zzeyn != null) {
                    zzn += zzeyf.zzb(1, com_google_android_gms_internal_zzeyn);
                }
            }
        }
        if (this.name != null) {
            zzn += zzeyf.zzn(2, this.name);
        }
        if (this.zziyy != null) {
            zzn += zzeyf.zzc(3, this.zziyy.longValue());
        }
        if (this.zziyz != null) {
            zzn += zzeyf.zzc(4, this.zziyz.longValue());
        }
        return this.count != null ? zzn + zzeyf.zzaa(5, this.count.intValue()) : zzn;
    }
}
