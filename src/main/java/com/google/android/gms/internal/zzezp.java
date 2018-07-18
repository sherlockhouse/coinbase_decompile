package com.google.android.gms.internal;

import java.io.IOException;

public final class zzezp extends zzeyh<zzezp> {
    public long zzgcc;
    public String zzoxs;
    public String zzoxt;
    public long zzoxu;
    public String zzoxv;
    public long zzoxw;
    public String zzoxx;
    public String zzoxy;
    public String zzoxz;
    public String zzoya;
    public String zzoyb;
    public int zzoyc;
    public zzezo[] zzoyd;

    public zzezp() {
        this.zzoxs = "";
        this.zzoxt = "";
        this.zzoxu = 0;
        this.zzoxv = "";
        this.zzoxw = 0;
        this.zzgcc = 0;
        this.zzoxx = "";
        this.zzoxy = "";
        this.zzoxz = "";
        this.zzoya = "";
        this.zzoyb = "";
        this.zzoyc = 0;
        this.zzoyd = zzezo.zzcwx();
        this.zzotl = null;
        this.zzomu = -1;
    }

    public static zzezp zzbi(byte[] bArr) throws zzeym {
        return (zzezp) zzeyn.zza(new zzezp(), bArr);
    }

    public final /* synthetic */ zzeyn zza(zzeye com_google_android_gms_internal_zzeye) throws IOException {
        while (true) {
            int zzcsn = com_google_android_gms_internal_zzeye.zzcsn();
            switch (zzcsn) {
                case 0:
                    break;
                case 10:
                    this.zzoxs = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 18:
                    this.zzoxt = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 24:
                    this.zzoxu = com_google_android_gms_internal_zzeye.zzcsp();
                    continue;
                case 34:
                    this.zzoxv = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 40:
                    this.zzoxw = com_google_android_gms_internal_zzeye.zzcsp();
                    continue;
                case 48:
                    this.zzgcc = com_google_android_gms_internal_zzeye.zzcsp();
                    continue;
                case 58:
                    this.zzoxx = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 66:
                    this.zzoxy = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 74:
                    this.zzoxz = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 82:
                    this.zzoya = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 90:
                    this.zzoyb = com_google_android_gms_internal_zzeye.readString();
                    continue;
                case 96:
                    this.zzoyc = com_google_android_gms_internal_zzeye.zzcsq();
                    continue;
                case 106:
                    int zzb = zzeyq.zzb(com_google_android_gms_internal_zzeye, 106);
                    zzcsn = this.zzoyd == null ? 0 : this.zzoyd.length;
                    Object obj = new zzezo[(zzb + zzcsn)];
                    if (zzcsn != 0) {
                        System.arraycopy(this.zzoyd, 0, obj, 0, zzcsn);
                    }
                    while (zzcsn < obj.length - 1) {
                        obj[zzcsn] = new zzezo();
                        com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                        com_google_android_gms_internal_zzeye.zzcsn();
                        zzcsn++;
                    }
                    obj[zzcsn] = new zzezo();
                    com_google_android_gms_internal_zzeye.zza(obj[zzcsn]);
                    this.zzoyd = obj;
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
        if (!(this.zzoxs == null || this.zzoxs.equals(""))) {
            com_google_android_gms_internal_zzeyf.zzm(1, this.zzoxs);
        }
        if (!(this.zzoxt == null || this.zzoxt.equals(""))) {
            com_google_android_gms_internal_zzeyf.zzm(2, this.zzoxt);
        }
        if (this.zzoxu != 0) {
            com_google_android_gms_internal_zzeyf.zzf(3, this.zzoxu);
        }
        if (!(this.zzoxv == null || this.zzoxv.equals(""))) {
            com_google_android_gms_internal_zzeyf.zzm(4, this.zzoxv);
        }
        if (this.zzoxw != 0) {
            com_google_android_gms_internal_zzeyf.zzf(5, this.zzoxw);
        }
        if (this.zzgcc != 0) {
            com_google_android_gms_internal_zzeyf.zzf(6, this.zzgcc);
        }
        if (!(this.zzoxx == null || this.zzoxx.equals(""))) {
            com_google_android_gms_internal_zzeyf.zzm(7, this.zzoxx);
        }
        if (!(this.zzoxy == null || this.zzoxy.equals(""))) {
            com_google_android_gms_internal_zzeyf.zzm(8, this.zzoxy);
        }
        if (!(this.zzoxz == null || this.zzoxz.equals(""))) {
            com_google_android_gms_internal_zzeyf.zzm(9, this.zzoxz);
        }
        if (!(this.zzoya == null || this.zzoya.equals(""))) {
            com_google_android_gms_internal_zzeyf.zzm(10, this.zzoya);
        }
        if (!(this.zzoyb == null || this.zzoyb.equals(""))) {
            com_google_android_gms_internal_zzeyf.zzm(11, this.zzoyb);
        }
        if (this.zzoyc != 0) {
            com_google_android_gms_internal_zzeyf.zzx(12, this.zzoyc);
        }
        if (this.zzoyd != null && this.zzoyd.length > 0) {
            for (zzeyn com_google_android_gms_internal_zzeyn : this.zzoyd) {
                if (com_google_android_gms_internal_zzeyn != null) {
                    com_google_android_gms_internal_zzeyf.zza(13, com_google_android_gms_internal_zzeyn);
                }
            }
        }
        super.zza(com_google_android_gms_internal_zzeyf);
    }

    protected final int zzn() {
        int zzn = super.zzn();
        if (!(this.zzoxs == null || this.zzoxs.equals(""))) {
            zzn += zzeyf.zzn(1, this.zzoxs);
        }
        if (!(this.zzoxt == null || this.zzoxt.equals(""))) {
            zzn += zzeyf.zzn(2, this.zzoxt);
        }
        if (this.zzoxu != 0) {
            zzn += zzeyf.zzc(3, this.zzoxu);
        }
        if (!(this.zzoxv == null || this.zzoxv.equals(""))) {
            zzn += zzeyf.zzn(4, this.zzoxv);
        }
        if (this.zzoxw != 0) {
            zzn += zzeyf.zzc(5, this.zzoxw);
        }
        if (this.zzgcc != 0) {
            zzn += zzeyf.zzc(6, this.zzgcc);
        }
        if (!(this.zzoxx == null || this.zzoxx.equals(""))) {
            zzn += zzeyf.zzn(7, this.zzoxx);
        }
        if (!(this.zzoxy == null || this.zzoxy.equals(""))) {
            zzn += zzeyf.zzn(8, this.zzoxy);
        }
        if (!(this.zzoxz == null || this.zzoxz.equals(""))) {
            zzn += zzeyf.zzn(9, this.zzoxz);
        }
        if (!(this.zzoya == null || this.zzoya.equals(""))) {
            zzn += zzeyf.zzn(10, this.zzoya);
        }
        if (!(this.zzoyb == null || this.zzoyb.equals(""))) {
            zzn += zzeyf.zzn(11, this.zzoyb);
        }
        if (this.zzoyc != 0) {
            zzn += zzeyf.zzaa(12, this.zzoyc);
        }
        if (this.zzoyd == null || this.zzoyd.length <= 0) {
            return zzn;
        }
        int i = zzn;
        for (zzeyn com_google_android_gms_internal_zzeyn : this.zzoyd) {
            if (com_google_android_gms_internal_zzeyn != null) {
                i += zzeyf.zzb(13, com_google_android_gms_internal_zzeyn);
            }
        }
        return i;
    }
}
