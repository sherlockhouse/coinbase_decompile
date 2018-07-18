package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzcv;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public final class zzb extends AsyncTaskLoader<Void> implements zzcv {
    private Semaphore zzecq = new Semaphore(0);
    private Set<GoogleApiClient> zzecr;

    public zzb(Context context, Set<GoogleApiClient> set) {
        super(context);
        this.zzecr = set;
    }

    private final Void zzaaj() {
        int i = 0;
        for (GoogleApiClient zza : this.zzecr) {
            i = zza.zza((zzcv) this) ? i + 1 : i;
        }
        try {
            this.zzecq.tryAcquire(i, 5, TimeUnit.SECONDS);
        } catch (Throwable e) {
            Log.i("GACSignInLoader", "Unexpected InterruptedException", e);
            Thread.currentThread().interrupt();
        }
        return null;
    }

    public final /* synthetic */ Object loadInBackground() {
        return zzaaj();
    }

    protected final void onStartLoading() {
        this.zzecq.drainPermits();
        forceLoad();
    }

    public final void zzaak() {
        this.zzecq.release();
    }
}
