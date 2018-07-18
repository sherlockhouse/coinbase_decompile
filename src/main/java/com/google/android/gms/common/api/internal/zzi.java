package com.google.android.gms.common.api.internal;

import android.util.Log;
import android.util.SparseArray;
import com.coinbase.android.utils.CryptoUri;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzbp;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzi extends zzo {
    private final SparseArray<zza> zzfik = new SparseArray();

    class zza implements OnConnectionFailedListener {
        public final int zzfil;
        public final GoogleApiClient zzfim;
        public final OnConnectionFailedListener zzfin;
        private /* synthetic */ zzi zzfio;

        public zza(zzi com_google_android_gms_common_api_internal_zzi, int i, GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
            this.zzfio = com_google_android_gms_common_api_internal_zzi;
            this.zzfil = i;
            this.zzfim = googleApiClient;
            this.zzfin = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        public final void onConnectionFailed(ConnectionResult connectionResult) {
            String valueOf = String.valueOf(connectionResult);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 27).append("beginFailureResolution for ").append(valueOf).toString());
            this.zzfio.zzb(connectionResult, this.zzfil);
        }
    }

    private zzi(zzcg com_google_android_gms_common_api_internal_zzcg) {
        super(com_google_android_gms_common_api_internal_zzcg);
        this.zzfoo.zza("AutoManageHelper", (LifecycleCallback) this);
    }

    public static zzi zza(zzcf com_google_android_gms_common_api_internal_zzcf) {
        zzcg zzb = LifecycleCallback.zzb(com_google_android_gms_common_api_internal_zzcf);
        zzi com_google_android_gms_common_api_internal_zzi = (zzi) zzb.zza("AutoManageHelper", zzi.class);
        return com_google_android_gms_common_api_internal_zzi != null ? com_google_android_gms_common_api_internal_zzi : new zzi(zzb);
    }

    private final zza zzbq(int i) {
        return this.zzfik.size() <= i ? null : (zza) this.zzfik.get(this.zzfik.keyAt(i));
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < this.zzfik.size(); i++) {
            zza zzbq = zzbq(i);
            if (zzbq != null) {
                printWriter.append(str).append("GoogleApiClient #").print(zzbq.zzfil);
                printWriter.println(CryptoUri.URI_SCHEME_DELIMETER);
                zzbq.zzfim.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
            }
        }
    }

    public final void onStart() {
        super.onStart();
        boolean z = this.mStarted;
        String valueOf = String.valueOf(this.zzfik);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 14).append("onStart ").append(z).append(" ").append(valueOf).toString());
        if (this.zzfiw.get() == null) {
            for (int i = 0; i < this.zzfik.size(); i++) {
                zza zzbq = zzbq(i);
                if (zzbq != null) {
                    zzbq.zzfim.connect();
                }
            }
        }
    }

    public final void onStop() {
        super.onStop();
        for (int i = 0; i < this.zzfik.size(); i++) {
            zza zzbq = zzbq(i);
            if (zzbq != null) {
                zzbq.zzfim.disconnect();
            }
        }
    }

    public final void zza(int i, GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
        zzbp.zzb((Object) googleApiClient, (Object) "GoogleApiClient instance cannot be null");
        zzbp.zza(this.zzfik.indexOfKey(i) < 0, "Already managing a GoogleApiClient with id " + i);
        zzp com_google_android_gms_common_api_internal_zzp = (zzp) this.zzfiw.get();
        boolean z = this.mStarted;
        String valueOf = String.valueOf(com_google_android_gms_common_api_internal_zzp);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 49).append("starting AutoManage for client ").append(i).append(" ").append(z).append(" ").append(valueOf).toString());
        this.zzfik.put(i, new zza(this, i, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && com_google_android_gms_common_api_internal_zzp == null) {
            String valueOf2 = String.valueOf(googleApiClient);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf2).length() + 11).append("connecting ").append(valueOf2).toString());
            googleApiClient.connect();
        }
    }

    protected final void zza(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zza com_google_android_gms_common_api_internal_zzi_zza = (zza) this.zzfik.get(i);
        if (com_google_android_gms_common_api_internal_zzi_zza != null) {
            zzbp(i);
            OnConnectionFailedListener onConnectionFailedListener = com_google_android_gms_common_api_internal_zzi_zza.zzfin;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    protected final void zzafw() {
        for (int i = 0; i < this.zzfik.size(); i++) {
            zza zzbq = zzbq(i);
            if (zzbq != null) {
                zzbq.zzfim.connect();
            }
        }
    }

    public final void zzbp(int i) {
        zza com_google_android_gms_common_api_internal_zzi_zza = (zza) this.zzfik.get(i);
        this.zzfik.remove(i);
        if (com_google_android_gms_common_api_internal_zzi_zza != null) {
            com_google_android_gms_common_api_internal_zzi_zza.zzfim.unregisterConnectionFailedListener(com_google_android_gms_common_api_internal_zzi_zza);
            com_google_android_gms_common_api_internal_zzi_zza.zzfim.disconnect();
        }
    }
}
