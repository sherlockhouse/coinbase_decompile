package com.google.android.gms.common.api;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.internal.zzbp;
import java.util.ArrayList;

public class AvailabilityException extends Exception {
    private final ArrayMap<zzh<?>, ConnectionResult> zzfgj;

    public AvailabilityException(ArrayMap<zzh<?>, ConnectionResult> arrayMap) {
        this.zzfgj = arrayMap;
    }

    public ConnectionResult getConnectionResult(GoogleApi<? extends ApiOptions> googleApi) {
        zzh zzafk = googleApi.zzafk();
        zzbp.zzb(this.zzfgj.get(zzafk) != null, (Object) "The given API was not part of the availability request.");
        return (ConnectionResult) this.zzfgj.get(zzafk);
    }

    public String getMessage() {
        Iterable arrayList = new ArrayList();
        Object obj = 1;
        for (zzh com_google_android_gms_common_api_internal_zzh : this.zzfgj.keySet()) {
            ConnectionResult connectionResult = (ConnectionResult) this.zzfgj.get(com_google_android_gms_common_api_internal_zzh);
            if (connectionResult.isSuccess()) {
                obj = null;
            }
            String zzafv = com_google_android_gms_common_api_internal_zzh.zzafv();
            String valueOf = String.valueOf(connectionResult);
            arrayList.add(new StringBuilder((String.valueOf(zzafv).length() + 2) + String.valueOf(valueOf).length()).append(zzafv).append(": ").append(valueOf).toString());
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (obj != null) {
            stringBuilder.append("None of the queried APIs are available. ");
        } else {
            stringBuilder.append("Some of the queried APIs are unavailable. ");
        }
        stringBuilder.append(TextUtils.join("; ", arrayList));
        return stringBuilder.toString();
    }

    public final ArrayMap<zzh<?>, ConnectionResult> zzafh() {
        return this.zzfgj;
    }
}
