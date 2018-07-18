package com.google.android.gms.internal;

import java.io.IOException;

public final class zzezo extends zzeyh<zzezo> {
    private static volatile zzezo[] zzoxr;
    public String zzoxs;

    public zzezo() {
        this.zzoxs = "";
        this.zzotl = null;
        this.zzomu = -1;
    }

    public static zzezo[] zzcwx() {
        if (zzoxr == null) {
            synchronized (zzeyl.zzott) {
                if (zzoxr == null) {
                    zzoxr = new zzezo[0];
                }
            }
        }
        return zzoxr;
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
        super.zza(com_google_android_gms_internal_zzeyf);
    }

    protected final int zzn() {
        int zzn = super.zzn();
        return (this.zzoxs == null || this.zzoxs.equals("")) ? zzn : zzn + zzeyf.zzn(1, this.zzoxs);
    }
}
