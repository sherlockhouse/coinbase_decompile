package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import java.util.Set;

public abstract class zzaa<T extends IInterface> extends zzd<T> implements zze, zzae {
    private final Account zzduz;
    private final Set<Scope> zzecm;
    private final zzq zzfkj;

    protected zzaa(Context context, Looper looper, int i, zzq com_google_android_gms_common_internal_zzq, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, zzaf.zzce(context), GoogleApiAvailability.getInstance(), i, com_google_android_gms_common_internal_zzq, (ConnectionCallbacks) zzbp.zzu(connectionCallbacks), (OnConnectionFailedListener) zzbp.zzu(onConnectionFailedListener));
    }

    private zzaa(Context context, Looper looper, zzaf com_google_android_gms_common_internal_zzaf, GoogleApiAvailability googleApiAvailability, int i, zzq com_google_android_gms_common_internal_zzq, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, com_google_android_gms_common_internal_zzaf, googleApiAvailability, i, connectionCallbacks == null ? null : new zzab(connectionCallbacks), onConnectionFailedListener == null ? null : new zzac(onConnectionFailedListener), com_google_android_gms_common_internal_zzq.zzajv());
        this.zzfkj = com_google_android_gms_common_internal_zzq;
        this.zzduz = com_google_android_gms_common_internal_zzq.getAccount();
        Set zzajs = com_google_android_gms_common_internal_zzq.zzajs();
        Set<Scope> zzb = zzb(zzajs);
        for (Scope contains : zzb) {
            if (!zzajs.contains(contains)) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        this.zzecm = zzb;
    }

    public final Account getAccount() {
        return this.zzduz;
    }

    public zzc[] zzajh() {
        return new zzc[0];
    }

    protected final Set<Scope> zzajl() {
        return this.zzecm;
    }

    protected final zzq zzakd() {
        return this.zzfkj;
    }

    protected Set<Scope> zzb(Set<Scope> set) {
        return set;
    }
}
