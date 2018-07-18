package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.ApiOptions.NotRequiredOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzd;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.internal.zzbd;
import com.google.android.gms.common.api.internal.zzcf;
import com.google.android.gms.common.api.internal.zzcv;
import com.google.android.gms.common.api.internal.zzdg;
import com.google.android.gms.common.api.internal.zzi;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.api.internal.zzw;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.internal.zzcpp;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GoogleApiClient {
    private static final Set<GoogleApiClient> zzfha = Collections.newSetFromMap(new WeakHashMap());

    public interface OnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    public interface ConnectionCallbacks {
        void onConnected(Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public static final class Builder {
        private final Context mContext;
        private Looper zzakg;
        private Account zzduz;
        private String zzdxc;
        private final Set<Scope> zzfhb = new HashSet();
        private final Set<Scope> zzfhc = new HashSet();
        private int zzfhd;
        private View zzfhe;
        private String zzfhf;
        private final Map<Api<?>, zzs> zzfhg = new ArrayMap();
        private final Map<Api<?>, ApiOptions> zzfhh = new ArrayMap();
        private zzcf zzfhi;
        private int zzfhj = -1;
        private OnConnectionFailedListener zzfhk;
        private GoogleApiAvailability zzfhl = GoogleApiAvailability.getInstance();
        private zza<? extends zzcps, zzcpt> zzfhm = zzcpp.zzdwq;
        private final ArrayList<ConnectionCallbacks> zzfhn = new ArrayList();
        private final ArrayList<OnConnectionFailedListener> zzfho = new ArrayList();
        private boolean zzfhp = false;

        public Builder(Context context) {
            this.mContext = context;
            this.zzakg = context.getMainLooper();
            this.zzdxc = context.getPackageName();
            this.zzfhf = context.getClass().getName();
        }

        public final Builder addApi(Api<? extends NotRequiredOptions> api) {
            zzbp.zzb((Object) api, (Object) "Api must not be null");
            this.zzfhh.put(api, null);
            Collection zzn = api.zzafc().zzn(null);
            this.zzfhc.addAll(zzn);
            this.zzfhb.addAll(zzn);
            return this;
        }

        public final <O extends HasOptions> Builder addApi(Api<O> api, O o) {
            zzbp.zzb((Object) api, (Object) "Api must not be null");
            zzbp.zzb((Object) o, (Object) "Null options are not permitted for this Api");
            this.zzfhh.put(api, o);
            Collection zzn = api.zzafc().zzn(o);
            this.zzfhc.addAll(zzn);
            this.zzfhb.addAll(zzn);
            return this;
        }

        public final Builder addConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
            zzbp.zzb((Object) connectionCallbacks, (Object) "Listener must not be null");
            this.zzfhn.add(connectionCallbacks);
            return this;
        }

        public final Builder addOnConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
            zzbp.zzb((Object) onConnectionFailedListener, (Object) "Listener must not be null");
            this.zzfho.add(onConnectionFailedListener);
            return this;
        }

        public final GoogleApiClient build() {
            zzbp.zzb(!this.zzfhh.isEmpty(), (Object) "must call addApi() to add at least one API");
            zzq zzafr = zzafr();
            Api api = null;
            Map zzajt = zzafr.zzajt();
            Map arrayMap = new ArrayMap();
            Map arrayMap2 = new ArrayMap();
            ArrayList arrayList = new ArrayList();
            Object obj = null;
            for (Api api2 : this.zzfhh.keySet()) {
                Api api22;
                Object obj2 = this.zzfhh.get(api22);
                boolean z = zzajt.get(api22) != null;
                arrayMap.put(api22, Boolean.valueOf(z));
                ConnectionCallbacks com_google_android_gms_common_api_internal_zzw = new zzw(api22, z);
                arrayList.add(com_google_android_gms_common_api_internal_zzw);
                zzd zzafd = api22.zzafd();
                zze zza = zzafd.zza(this.mContext, this.zzakg, zzafr, obj2, com_google_android_gms_common_api_internal_zzw, com_google_android_gms_common_api_internal_zzw);
                arrayMap2.put(api22.zzafe(), zza);
                Object obj3 = zzafd.getPriority() == 1 ? obj2 != null ? 1 : null : obj;
                if (!zza.zzaal()) {
                    api22 = api;
                } else if (api != null) {
                    String name = api22.getName();
                    String name2 = api.getName();
                    throw new IllegalStateException(new StringBuilder((String.valueOf(name).length() + 21) + String.valueOf(name2).length()).append(name).append(" cannot be used with ").append(name2).toString());
                }
                obj = obj3;
                api = api22;
            }
            if (api != null) {
                if (obj != null) {
                    name = api.getName();
                    throw new IllegalStateException(new StringBuilder(String.valueOf(name).length() + 82).append("With using ").append(name).append(", GamesOptions can only be specified within GoogleSignInOptions.Builder").toString());
                }
                zzbp.zza(this.zzduz == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", api.getName());
                zzbp.zza(this.zzfhb.equals(this.zzfhc), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", api.getName());
            }
            GoogleApiClient com_google_android_gms_common_api_internal_zzbd = new zzbd(this.mContext, new ReentrantLock(), this.zzakg, zzafr, this.zzfhl, this.zzfhm, arrayMap, this.zzfhn, this.zzfho, arrayMap2, this.zzfhj, zzbd.zza(arrayMap2.values(), true), arrayList, false);
            synchronized (GoogleApiClient.zzfha) {
                GoogleApiClient.zzfha.add(com_google_android_gms_common_api_internal_zzbd);
            }
            if (this.zzfhj >= 0) {
                zzi.zza(this.zzfhi).zza(this.zzfhj, com_google_android_gms_common_api_internal_zzbd, this.zzfhk);
            }
            return com_google_android_gms_common_api_internal_zzbd;
        }

        public final Builder setHandler(Handler handler) {
            zzbp.zzb((Object) handler, (Object) "Handler must not be null");
            this.zzakg = handler.getLooper();
            return this;
        }

        public final zzq zzafr() {
            zzcpt com_google_android_gms_internal_zzcpt = zzcpt.zzjno;
            if (this.zzfhh.containsKey(zzcpp.API)) {
                com_google_android_gms_internal_zzcpt = (zzcpt) this.zzfhh.get(zzcpp.API);
            }
            return new zzq(this.zzduz, this.zzfhb, this.zzfhg, this.zzfhd, this.zzfhe, this.zzdxc, this.zzfhf, com_google_android_gms_internal_zzcpt);
        }
    }

    public static Set<GoogleApiClient> zzafo() {
        Set<GoogleApiClient> set;
        synchronized (zzfha) {
            set = zzfha;
        }
        return set;
    }

    public abstract ConnectionResult blockingConnect();

    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();

    public abstract void connect();

    public void connect(int i) {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public Context getContext() {
        throw new UnsupportedOperationException();
    }

    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean isConnected();

    public abstract void reconnect();

    public abstract void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);

    public abstract void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);

    public void zza(zzdg com_google_android_gms_common_api_internal_zzdg) {
        throw new UnsupportedOperationException();
    }

    public boolean zza(zzcv com_google_android_gms_common_api_internal_zzcv) {
        throw new UnsupportedOperationException();
    }

    public void zzafp() {
        throw new UnsupportedOperationException();
    }

    public void zzb(zzdg com_google_android_gms_common_api_internal_zzdg) {
        throw new UnsupportedOperationException();
    }

    public <A extends zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        throw new UnsupportedOperationException();
    }

    public <A extends zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        throw new UnsupportedOperationException();
    }
}
