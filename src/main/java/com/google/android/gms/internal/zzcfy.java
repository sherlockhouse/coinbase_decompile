package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcfy extends zzeyh<zzcfy> {
    private static volatile zzcfy[] zzixm;
    public Integer zzixn;
    public String zzixo;
    public zzcfz[] zzixp;
    private Boolean zzixq;
    public zzcga zzixr;

    public zzcfy() {
        this.zzixn = null;
        this.zzixo = null;
        this.zzixp = zzcfz.zzbab();
        this.zzixq = null;
        this.zzixr = null;
        this.zzotl = null;
        this.zzomu = -1;
    }

    public static zzcfy[] zzbaa() {
        if (zzixm == null) {
            synchronized (zzeyl.zzott) {
                if (zzixm == null) {
                    zzixm = new zzcfy[0];
                }
            }
        }
        return zzixm;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcfy)) {
            return false;
        }
        zzcfy com_google_android_gms_internal_zzcfy = (zzcfy) obj;
        if (this.zzixn == null) {
            if (com_google_android_gms_internal_zzcfy.zzixn != null) {
                return false;
            }
        } else if (!this.zzixn.equals(com_google_android_gms_internal_zzcfy.zzixn)) {
            return false;
        }
        if (this.zzixo == null) {
            if (com_google_android_gms_internal_zzcfy.zzixo != null) {
                return false;
            }
        } else if (!this.zzixo.equals(com_google_android_gms_internal_zzcfy.zzixo)) {
            return false;
        }
        if (!zzeyl.equals(this.zzixp, com_google_android_gms_internal_zzcfy.zzixp)) {
            return false;
        }
        if (this.zzixq == null) {
            if (com_google_android_gms_internal_zzcfy.zzixq != null) {
                return false;
            }
        } else if (!this.zzixq.equals(com_google_android_gms_internal_zzcfy.zzixq)) {
            return false;
        }
        if (this.zzixr == null) {
            if (com_google_android_gms_internal_zzcfy.zzixr != null) {
                return false;
            }
        } else if (!this.zzixr.equals(com_google_android_gms_internal_zzcfy.zzixr)) {
            return false;
        }
        return (this.zzotl == null || this.zzotl.isEmpty()) ? com_google_android_gms_internal_zzcfy.zzotl == null || com_google_android_gms_internal_zzcfy.zzotl.isEmpty() : this.zzotl.equals(com_google_android_gms_internal_zzcfy.zzotl);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.zzixq == null ? 0 : this.zzixq.hashCode()) + (((((this.zzixo == null ? 0 : this.zzixo.hashCode()) + (((this.zzixn == null ? 0 : this.zzixn.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31) + zzeyl.hashCode(this.zzixp)) * 31);
        zzcga com_google_android_gms_internal_zzcga = this.zzixr;
        hashCode = ((com_google_android_gms_internal_zzcga == null ? 0 : com_google_android_gms_internal_zzcga.hashCode()) + (hashCode * 31)) * 31;
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
                    this.zzixn = Integer.valueOf(com_google_android_gms_internal_zzeye.zzctc());
                    continue;
                case 18:
                    this.zzixo = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 26:
                    int zzb = zzeyq.zzb(com_google_android_gms_internal_zzeye, 26);
                    zzcsn = this.zzixp == null ? 0 : this.zzixp.length;
                    Object obj = new zzcfz[(zzb + zzcsn)];
                    if (zzcsn != 0) {
                        System.arraycopy(this.zzixp, 0, obj, 0, zzcsn);
                    }
                    while (zzcsn < obj.length - 1) {
                        obj[zzcsn] = new zzcfz();
                        com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                        com_google_android_gms_internal_zzeye.zzcsn();
                        zzcsn++;
                    }
                    obj[zzcsn] = new zzcfz();
                    com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                    this.zzixp = obj;
                    continue;
                case 32:
                    this.zzixq = Boolean.valueOf(com_google_android_gms_internal_zzeye.zzcst());
                    continue;
                case 42:
                    if (this.zzixr == null) {
                        this.zzixr = new zzcga();
                    }
                    com_google_android_gms_internal_zzeye.zza(this.zzixr);
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
        if (this.zzixn != null) {
            com_google_android_gms_internal_zzeyf.zzx(1, this.zzixn.intValue());
        }
        if (this.zzixo != null) {
            com_google_android_gms_internal_zzeyf.zzm(2, this.zzixo);
        }
        if (this.zzixp != null && this.zzixp.length > 0) {
            for (zzeyn com_google_android_gms_internal_zzeyn : this.zzixp) {
                if (com_google_android_gms_internal_zzeyn != null) {
                    com_google_android_gms_internal_zzeyf.zza(3, com_google_android_gms_internal_zzeyn);
                }
            }
        }
        if (this.zzixq != null) {
            com_google_android_gms_internal_zzeyf.zzl(4, this.zzixq.booleanValue());
        }
        if (this.zzixr != null) {
            com_google_android_gms_internal_zzeyf.zza(5, this.zzixr);
        }
        super.zza(com_google_android_gms_internal_zzeyf);
    }

    protected final int zzn() {
        int zzn = super.zzn();
        if (this.zzixn != null) {
            zzn += zzeyf.zzaa(1, this.zzixn.intValue());
        }
        if (this.zzixo != null) {
            zzn += zzeyf.zzn(2, this.zzixo);
        }
        if (this.zzixp != null && this.zzixp.length > 0) {
            int i = zzn;
            for (zzeyn com_google_android_gms_internal_zzeyn : this.zzixp) {
                if (com_google_android_gms_internal_zzeyn != null) {
                    i += zzeyf.zzb(3, com_google_android_gms_internal_zzeyn);
                }
            }
            zzn = i;
        }
        if (this.zzixq != null) {
            this.zzixq.booleanValue();
            zzn += zzeyf.zzkb(4) + 1;
        }
        return this.zzixr != null ? zzn + zzeyf.zzb(5, this.zzixr) : zzn;
    }
}
