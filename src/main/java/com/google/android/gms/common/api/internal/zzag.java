package com.google.android.gms.common.api.internal;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;

final class zzag implements OnCompleteListener<Void> {
    private /* synthetic */ zzad zzfky;
    private zzcv zzfkz;

    zzag(zzad com_google_android_gms_common_api_internal_zzad, zzcv com_google_android_gms_common_api_internal_zzcv) {
        this.zzfky = com_google_android_gms_common_api_internal_zzad;
        this.zzfkz = com_google_android_gms_common_api_internal_zzcv;
    }

    final void cancel() {
        this.zzfkz.zzaak();
    }

    public final void onComplete(Task<Void> task) {
        this.zzfky.zzfke.lock();
        try {
            if (this.zzfky.zzfkt) {
                if (task.isSuccessful()) {
                    this.zzfky.zzfkv = new ArrayMap(this.zzfky.zzfkl.size());
                    for (zzac zzafk : this.zzfky.zzfkl.values()) {
                        this.zzfky.zzfkv.put(zzafk.zzafk(), ConnectionResult.zzfff);
                    }
                } else if (task.getException() instanceof AvailabilityException) {
                    AvailabilityException availabilityException = (AvailabilityException) task.getException();
                    if (this.zzfky.zzfkr) {
                        this.zzfky.zzfkv = new ArrayMap(this.zzfky.zzfkl.size());
                        for (zzac com_google_android_gms_common_api_internal_zzac : this.zzfky.zzfkl.values()) {
                            zzh zzafk2 = com_google_android_gms_common_api_internal_zzac.zzafk();
                            ConnectionResult connectionResult = availabilityException.getConnectionResult(com_google_android_gms_common_api_internal_zzac);
                            if (this.zzfky.zza(com_google_android_gms_common_api_internal_zzac, connectionResult)) {
                                this.zzfky.zzfkv.put(zzafk2, new ConnectionResult(16));
                            } else {
                                this.zzfky.zzfkv.put(zzafk2, connectionResult);
                            }
                        }
                    } else {
                        this.zzfky.zzfkv = availabilityException.zzafh();
                    }
                } else {
                    Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                    this.zzfky.zzfkv = Collections.emptyMap();
                }
                if (this.zzfky.isConnected()) {
                    this.zzfky.zzfku.putAll(this.zzfky.zzfkv);
                    if (this.zzfky.zzagr() == null) {
                        this.zzfky.zzagp();
                        this.zzfky.zzagq();
                        this.zzfky.zzfkp.signalAll();
                    }
                }
                this.zzfkz.zzaak();
                this.zzfky.zzfke.unlock();
                return;
            }
            this.zzfkz.zzaak();
        } finally {
            this.zzfky.zzfke.unlock();
        }
    }
}
