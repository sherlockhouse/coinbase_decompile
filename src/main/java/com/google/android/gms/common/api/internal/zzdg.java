package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzbp;
import java.lang.ref.WeakReference;

public final class zzdg<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    private final Object zzfjf;
    private final WeakReference<GoogleApiClient> zzfjh;
    private ResultTransform<? super R, ? extends Result> zzfph;
    private zzdg<? extends Result> zzfpi;
    private volatile ResultCallbacks<? super R> zzfpj;
    private PendingResult<R> zzfpk;
    private Status zzfpl;
    private final zzdi zzfpm;
    private boolean zzfpn;

    private final void zzain() {
        if (this.zzfph != null || this.zzfpj != null) {
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zzfjh.get();
            if (!(this.zzfpn || this.zzfph == null || googleApiClient == null)) {
                googleApiClient.zza(this);
                this.zzfpn = true;
            }
            if (this.zzfpl != null) {
                zzw(this.zzfpl);
            } else if (this.zzfpk != null) {
                this.zzfpk.setResultCallback(this);
            }
        }
    }

    private final boolean zzaip() {
        return (this.zzfpj == null || ((GoogleApiClient) this.zzfjh.get()) == null) ? false : true;
    }

    private static void zzd(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (Throwable e) {
                String valueOf = String.valueOf(result);
                Log.w("TransformedResultImpl", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    private final void zzd(Status status) {
        synchronized (this.zzfjf) {
            this.zzfpl = status;
            zzw(this.zzfpl);
        }
    }

    private final void zzw(Status status) {
        synchronized (this.zzfjf) {
            if (this.zzfph != null) {
                Status onFailure = this.zzfph.onFailure(status);
                zzbp.zzb((Object) onFailure, (Object) "onFailure must not return null");
                this.zzfpi.zzd(onFailure);
            } else if (zzaip()) {
                this.zzfpj.onFailure(status);
            }
        }
    }

    public final void onResult(R r) {
        synchronized (this.zzfjf) {
            if (!r.getStatus().isSuccess()) {
                zzd(r.getStatus());
                zzd((Result) r);
            } else if (this.zzfph != null) {
                zzct.zzahn().submit(new zzdh(this, r));
            } else if (zzaip()) {
                this.zzfpj.onSuccess(r);
            }
        }
    }

    public final void zza(PendingResult<?> pendingResult) {
        synchronized (this.zzfjf) {
            this.zzfpk = pendingResult;
            zzain();
        }
    }

    final void zzaio() {
        this.zzfpj = null;
    }
}
