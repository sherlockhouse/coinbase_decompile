package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcgc extends zzeyh<zzcgc> {
    public Integer zziyf;
    public String zziyg;
    public Boolean zziyh;
    public String[] zziyi;

    public zzcgc() {
        this.zziyf = null;
        this.zziyg = null;
        this.zziyh = null;
        this.zziyi = zzeyq.EMPTY_STRING_ARRAY;
        this.zzotl = null;
        this.zzomu = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgc)) {
            return false;
        }
        zzcgc com_google_android_gms_internal_zzcgc = (zzcgc) obj;
        if (this.zziyf == null) {
            if (com_google_android_gms_internal_zzcgc.zziyf != null) {
                return false;
            }
        } else if (!this.zziyf.equals(com_google_android_gms_internal_zzcgc.zziyf)) {
            return false;
        }
        if (this.zziyg == null) {
            if (com_google_android_gms_internal_zzcgc.zziyg != null) {
                return false;
            }
        } else if (!this.zziyg.equals(com_google_android_gms_internal_zzcgc.zziyg)) {
            return false;
        }
        if (this.zziyh == null) {
            if (com_google_android_gms_internal_zzcgc.zziyh != null) {
                return false;
            }
        } else if (!this.zziyh.equals(com_google_android_gms_internal_zzcgc.zziyh)) {
            return false;
        }
        return !zzeyl.equals(this.zziyi, com_google_android_gms_internal_zzcgc.zziyi) ? false : (this.zzotl == null || this.zzotl.isEmpty()) ? com_google_android_gms_internal_zzcgc.zzotl == null || com_google_android_gms_internal_zzcgc.zzotl.isEmpty() : this.zzotl.equals(com_google_android_gms_internal_zzcgc.zzotl);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((this.zziyh == null ? 0 : this.zziyh.hashCode()) + (((this.zziyg == null ? 0 : this.zziyg.hashCode()) + (((this.zziyf == null ? 0 : this.zziyf.intValue()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31) + zzeyl.hashCode(this.zziyi)) * 31;
        if (!(this.zzotl == null || this.zzotl.isEmpty())) {
            i = this.zzotl.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ zzeyn zza(zzeye com_google_android_gms_internal_zzeye) throws IOException {
        while (true) {
            int zzcsn = com_google_android_gms_internal_zzeye.zzcsn();
            int position;
            switch (zzcsn) {
                case 0:
                    break;
                case 8:
                    position = com_google_android_gms_internal_zzeye.getPosition();
                    int zzctc = com_google_android_gms_internal_zzeye.zzctc();
                    switch (zzctc) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                            this.zziyf = Integer.valueOf(zzctc);
                            break;
                        default:
                            com_google_android_gms_internal_zzeye.zzla(position);
                            zza(com_google_android_gms_internal_zzeye, zzcsn);
                            continue;
                    }
                case 18:
                    this.zziyg = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 24:
                    this.zziyh = Boolean.valueOf(com_google_android_gms_internal_zzeye.zzcst());
                    continue;
                case 34:
                    position = zzeyq.zzb(com_google_android_gms_internal_zzeye, 34);
                    zzcsn = this.zziyi == null ? 0 : this.zziyi.length;
                    Object obj = new String[(position + zzcsn)];
                    if (zzcsn != 0) {
                        System.arraycopy(this.zziyi, 0, obj, 0, zzcsn);
                    }
                    while (zzcsn < obj.length - 1) {
                        obj[zzcsn] = com_google_android_gms_internal_zzeye.readString();
                        com_google_android_gms_internal_zzeye.zzcsn();
                        zzcsn++;
                    }
                    obj[zzcsn] = com_google_android_gms_internal_zzeye.readString();
                    this.zziyi = obj;
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
        if (this.zziyf != null) {
            com_google_android_gms_internal_zzeyf.zzx(1, this.zziyf.intValue());
        }
        if (this.zziyg != null) {
            com_google_android_gms_internal_zzeyf.zzm(2, this.zziyg);
        }
        if (this.zziyh != null) {
            com_google_android_gms_internal_zzeyf.zzl(3, this.zziyh.booleanValue());
        }
        if (this.zziyi != null && this.zziyi.length > 0) {
            for (String str : this.zziyi) {
                if (str != null) {
                    com_google_android_gms_internal_zzeyf.zzm(4, str);
                }
            }
        }
        super.zza(com_google_android_gms_internal_zzeyf);
    }

    protected final int zzn() {
        int i = 0;
        int zzn = super.zzn();
        if (this.zziyf != null) {
            zzn += zzeyf.zzaa(1, this.zziyf.intValue());
        }
        if (this.zziyg != null) {
            zzn += zzeyf.zzn(2, this.zziyg);
        }
        if (this.zziyh != null) {
            this.zziyh.booleanValue();
            zzn += zzeyf.zzkb(3) + 1;
        }
        if (this.zziyi == null || this.zziyi.length <= 0) {
            return zzn;
        }
        int i2 = 0;
        int i3 = 0;
        while (i < this.zziyi.length) {
            String str = this.zziyi[i];
            if (str != null) {
                i3++;
                i2 += zzeyf.zztk(str);
            }
            i++;
        }
        return (zzn + i2) + (i3 * 1);
    }
}
