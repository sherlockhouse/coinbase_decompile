package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcgf extends zzeyh<zzcgf> {
    private static volatile zzcgf[] zziyr;
    public String key;
    public String value;

    public zzcgf() {
        this.key = null;
        this.value = null;
        this.zzotl = null;
        this.zzomu = -1;
    }

    public static zzcgf[] zzbae() {
        if (zziyr == null) {
            synchronized (zzeyl.zzott) {
                if (zziyr == null) {
                    zziyr = new zzcgf[0];
                }
            }
        }
        return zziyr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgf)) {
            return false;
        }
        zzcgf com_google_android_gms_internal_zzcgf = (zzcgf) obj;
        if (this.key == null) {
            if (com_google_android_gms_internal_zzcgf.key != null) {
                return false;
            }
        } else if (!this.key.equals(com_google_android_gms_internal_zzcgf.key)) {
            return false;
        }
        if (this.value == null) {
            if (com_google_android_gms_internal_zzcgf.value != null) {
                return false;
            }
        } else if (!this.value.equals(com_google_android_gms_internal_zzcgf.value)) {
            return false;
        }
        return (this.zzotl == null || this.zzotl.isEmpty()) ? com_google_android_gms_internal_zzcgf.zzotl == null || com_google_android_gms_internal_zzcgf.zzotl.isEmpty() : this.zzotl.equals(com_google_android_gms_internal_zzcgf.zzotl);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.value == null ? 0 : this.value.hashCode()) + (((this.key == null ? 0 : this.key.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
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
                    this.key = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 18:
                    this.value = com_google_android_gms_internal_zzeye.readString();
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
        if (this.key != null) {
            com_google_android_gms_internal_zzeyf.zzm(1, this.key);
        }
        if (this.value != null) {
            com_google_android_gms_internal_zzeyf.zzm(2, this.value);
        }
        super.zza(com_google_android_gms_internal_zzeyf);
    }

    protected final int zzn() {
        int zzn = super.zzn();
        if (this.key != null) {
            zzn += zzeyf.zzn(1, this.key);
        }
        return this.value != null ? zzn + zzeyf.zzn(2, this.value) : zzn;
    }
}
