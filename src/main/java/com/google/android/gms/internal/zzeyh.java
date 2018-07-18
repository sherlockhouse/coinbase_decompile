package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzeyh<M extends zzeyh<M>> extends zzeyn {
    protected zzeyj zzotl;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzcvz();
    }

    public void zza(zzeyf com_google_android_gms_internal_zzeyf) throws IOException {
        if (this.zzotl != null) {
            for (int i = 0; i < this.zzotl.size(); i++) {
                this.zzotl.zzlf(i).zza(com_google_android_gms_internal_zzeyf);
            }
        }
    }

    protected final boolean zza(zzeye com_google_android_gms_internal_zzeye, int i) throws IOException {
        int position = com_google_android_gms_internal_zzeye.getPosition();
        if (!com_google_android_gms_internal_zzeye.zzjl(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzeyp com_google_android_gms_internal_zzeyp = new zzeyp(i, com_google_android_gms_internal_zzeye.zzai(position, com_google_android_gms_internal_zzeye.getPosition() - position));
        zzeyk com_google_android_gms_internal_zzeyk = null;
        if (this.zzotl == null) {
            this.zzotl = new zzeyj();
        } else {
            com_google_android_gms_internal_zzeyk = this.zzotl.zzle(i2);
        }
        if (com_google_android_gms_internal_zzeyk == null) {
            com_google_android_gms_internal_zzeyk = new zzeyk();
            this.zzotl.zza(i2, com_google_android_gms_internal_zzeyk);
        }
        com_google_android_gms_internal_zzeyk.zza(com_google_android_gms_internal_zzeyp);
        return true;
    }

    public M zzcvz() throws CloneNotSupportedException {
        zzeyh com_google_android_gms_internal_zzeyh = (zzeyh) super.zzcwa();
        zzeyl.zza(this, com_google_android_gms_internal_zzeyh);
        return com_google_android_gms_internal_zzeyh;
    }

    public /* synthetic */ zzeyn zzcwa() throws CloneNotSupportedException {
        return (zzeyh) clone();
    }

    protected int zzn() {
        int i = 0;
        if (this.zzotl == null) {
            return 0;
        }
        int i2 = 0;
        while (i < this.zzotl.size()) {
            i2 += this.zzotl.zzlf(i).zzn();
            i++;
        }
        return i2;
    }
}
