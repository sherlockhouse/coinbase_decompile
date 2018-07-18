package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api.zze;
import java.util.ArrayList;

final class zzax extends zzbb {
    private /* synthetic */ zzar zzflx;
    private final ArrayList<zze> zzfmd;

    public zzax(zzar com_google_android_gms_common_api_internal_zzar, ArrayList<zze> arrayList) {
        this.zzflx = com_google_android_gms_common_api_internal_zzar;
        super(com_google_android_gms_common_api_internal_zzar);
        this.zzfmd = arrayList;
    }

    public final void zzagz() {
        this.zzflx.zzflh.zzfju.zzfmo = this.zzflx.zzahf();
        ArrayList arrayList = this.zzfmd;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((zze) obj).zza(this.zzflx.zzflt, this.zzflx.zzflh.zzfju.zzfmo);
        }
    }
}
