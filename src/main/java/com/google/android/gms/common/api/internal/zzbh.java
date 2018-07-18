package com.google.android.gms.common.api.internal;

import com.google.android.gms.auth.api.signin.internal.zzy;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzbh implements ResultCallback<Status> {
    private /* synthetic */ GoogleApiClient zzems;
    private /* synthetic */ zzbd zzfmv;
    private /* synthetic */ zzda zzfmx;
    private /* synthetic */ boolean zzfmy;

    zzbh(zzbd com_google_android_gms_common_api_internal_zzbd, zzda com_google_android_gms_common_api_internal_zzda, boolean z, GoogleApiClient googleApiClient) {
        this.zzfmv = com_google_android_gms_common_api_internal_zzbd;
        this.zzfmx = com_google_android_gms_common_api_internal_zzda;
        this.zzfmy = z;
        this.zzems = googleApiClient;
    }

    public final /* synthetic */ void onResult(Result result) {
        Status status = (Status) result;
        zzy.zzbl(this.zzfmv.mContext).zzaau();
        if (status.isSuccess() && this.zzfmv.isConnected()) {
            this.zzfmv.reconnect();
        }
        this.zzfmx.setResult(status);
        if (this.zzfmy) {
            this.zzems.disconnect();
        }
    }
}
