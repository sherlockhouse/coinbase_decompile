package com.google.android.gms.common.api.internal;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;

final class zzaf implements OnCompleteListener<Void> {
    private /* synthetic */ zzad zzfky;

    private zzaf(zzad com_google_android_gms_common_api_internal_zzad) {
        this.zzfky = com_google_android_gms_common_api_internal_zzad;
    }

    public final void onComplete(Task<Void> task) {
        this.zzfky.zzfke.lock();
        try {
            if (this.zzfky.zzfkt) {
                if (task.isSuccessful()) {
                    this.zzfky.zzfku = new ArrayMap(this.zzfky.zzfkk.size());
                    for (zzac zzafk : this.zzfky.zzfkk.values()) {
                        this.zzfky.zzfku.put(zzafk.zzafk(), ConnectionResult.zzfff);
                    }
                } else if (task.getException() instanceof AvailabilityException) {
                    AvailabilityException availabilityException = (AvailabilityException) task.getException();
                    if (this.zzfky.zzfkr) {
                        this.zzfky.zzfku = new ArrayMap(this.zzfky.zzfkk.size());
                        for (zzac com_google_android_gms_common_api_internal_zzac : this.zzfky.zzfkk.values()) {
                            zzh zzafk2 = com_google_android_gms_common_api_internal_zzac.zzafk();
                            ConnectionResult connectionResult = availabilityException.getConnectionResult(com_google_android_gms_common_api_internal_zzac);
                            if (this.zzfky.zza(com_google_android_gms_common_api_internal_zzac, connectionResult)) {
                                this.zzfky.zzfku.put(zzafk2, new ConnectionResult(16));
                            } else {
                                this.zzfky.zzfku.put(zzafk2, connectionResult);
                            }
                        }
                    } else {
                        this.zzfky.zzfku = availabilityException.zzafh();
                    }
                    this.zzfky.zzfkx = this.zzfky.zzagr();
                } else {
                    Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                    this.zzfky.zzfku = Collections.emptyMap();
                    this.zzfky.zzfkx = new ConnectionResult(8);
                }
                if (this.zzfky.zzfkv != null) {
                    this.zzfky.zzfku.putAll(this.zzfky.zzfkv);
                    this.zzfky.zzfkx = this.zzfky.zzagr();
                }
                if (this.zzfky.zzfkx == null) {
                    this.zzfky.zzagp();
                    this.zzfky.zzagq();
                } else {
                    this.zzfky.zzfkt = false;
                    this.zzfky.zzfkn.zzc(this.zzfky.zzfkx);
                }
                this.zzfky.zzfkp.signalAll();
                this.zzfky.zzfke.unlock();
            }
        } finally {
            this.zzfky.zzfke.unlock();
        }
    }
}
