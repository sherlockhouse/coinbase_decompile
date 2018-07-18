package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcgd extends zzeyh<zzcgd> {
    private static volatile zzcgd[] zziyj;
    public String name;
    public Boolean zziyk;
    public Boolean zziyl;

    public zzcgd() {
        this.name = null;
        this.zziyk = null;
        this.zziyl = null;
        this.zzotl = null;
        this.zzomu = -1;
    }

    public static zzcgd[] zzbad() {
        if (zziyj == null) {
            synchronized (zzeyl.zzott) {
                if (zziyj == null) {
                    zziyj = new zzcgd[0];
                }
            }
        }
        return zziyj;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgd)) {
            return false;
        }
        zzcgd com_google_android_gms_internal_zzcgd = (zzcgd) obj;
        if (this.name == null) {
            if (com_google_android_gms_internal_zzcgd.name != null) {
                return false;
            }
        } else if (!this.name.equals(com_google_android_gms_internal_zzcgd.name)) {
            return false;
        }
        if (this.zziyk == null) {
            if (com_google_android_gms_internal_zzcgd.zziyk != null) {
                return false;
            }
        } else if (!this.zziyk.equals(com_google_android_gms_internal_zzcgd.zziyk)) {
            return false;
        }
        if (this.zziyl == null) {
            if (com_google_android_gms_internal_zzcgd.zziyl != null) {
                return false;
            }
        } else if (!this.zziyl.equals(com_google_android_gms_internal_zzcgd.zziyl)) {
            return false;
        }
        return (this.zzotl == null || this.zzotl.isEmpty()) ? com_google_android_gms_internal_zzcgd.zzotl == null || com_google_android_gms_internal_zzcgd.zzotl.isEmpty() : this.zzotl.equals(com_google_android_gms_internal_zzcgd.zzotl);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zziyl == null ? 0 : this.zziyl.hashCode()) + (((this.zziyk == null ? 0 : this.zziyk.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31;
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
                case 16:
                    this.zziyk = Boolean.valueOf(com_google_android_gms_internal_zzeye.zzcst());
                    continue;
                case 24:
                    this.zziyl = Boolean.valueOf(com_google_android_gms_internal_zzeye.zzcst());
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
        if (this.zziyk != null) {
            com_google_android_gms_internal_zzeyf.zzl(2, this.zziyk.booleanValue());
        }
        if (this.zziyl != null) {
            com_google_android_gms_internal_zzeyf.zzl(3, this.zziyl.booleanValue());
        }
        super.zza(com_google_android_gms_internal_zzeyf);
    }

    protected final int zzn() {
        int zzn = super.zzn();
        if (this.name != null) {
            zzn += zzeyf.zzn(1, this.name);
        }
        if (this.zziyk != null) {
            this.zziyk.booleanValue();
            zzn += zzeyf.zzkb(2) + 1;
        }
        if (this.zziyl == null) {
            return zzn;
        }
        this.zziyl.booleanValue();
        return zzn + (zzeyf.zzkb(3) + 1);
    }
}
