package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.util.ArraySet;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzbp implements Callback {
    private static final Object zzaqd = new Object();
    public static final Status zzfnk = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status zzfnl = new Status(4, "The user must be signed in to make this API call.");
    private static zzbp zzfnn;
    private final Context mContext;
    private final Handler mHandler;
    private final GoogleApiAvailability zzfhl;
    private final Map<zzh<?>, zzbr<?>> zzfkk = new ConcurrentHashMap(5, 0.75f, 1);
    private long zzfmj = 120000;
    private long zzfmk = 5000;
    private long zzfnm = 10000;
    private int zzfno = -1;
    private final AtomicInteger zzfnp = new AtomicInteger(1);
    private final AtomicInteger zzfnq = new AtomicInteger(0);
    private zzak zzfnr = null;
    private final Set<zzh<?>> zzfns = new ArraySet();
    private final Set<zzh<?>> zzfnt = new ArraySet();

    private zzbp(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.mContext = context;
        this.mHandler = new Handler(looper, this);
        this.zzfhl = googleApiAvailability;
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6));
    }

    public static zzbp zzaho() {
        zzbp com_google_android_gms_common_api_internal_zzbp;
        synchronized (zzaqd) {
            com.google.android.gms.common.internal.zzbp.zzb(zzfnn, (Object) "Must guarantee manager is non-null before using getInstance");
            com_google_android_gms_common_api_internal_zzbp = zzfnn;
        }
        return com_google_android_gms_common_api_internal_zzbp;
    }

    public static void zzahp() {
        synchronized (zzaqd) {
            if (zzfnn != null) {
                zzbp com_google_android_gms_common_api_internal_zzbp = zzfnn;
                com_google_android_gms_common_api_internal_zzbp.zzfnq.incrementAndGet();
                com_google_android_gms_common_api_internal_zzbp.mHandler.sendMessageAtFrontOfQueue(com_google_android_gms_common_api_internal_zzbp.mHandler.obtainMessage(10));
            }
        }
    }

    private final void zzahr() {
        for (zzh remove : this.zzfnt) {
            ((zzbr) this.zzfkk.remove(remove)).signOut();
        }
        this.zzfnt.clear();
    }

    private final void zzb(GoogleApi<?> googleApi) {
        zzh zzafk = googleApi.zzafk();
        zzbr com_google_android_gms_common_api_internal_zzbr = (zzbr) this.zzfkk.get(zzafk);
        if (com_google_android_gms_common_api_internal_zzbr == null) {
            com_google_android_gms_common_api_internal_zzbr = new zzbr(this, googleApi);
            this.zzfkk.put(zzafk, com_google_android_gms_common_api_internal_zzbr);
        }
        if (com_google_android_gms_common_api_internal_zzbr.zzaac()) {
            this.zzfnt.add(zzafk);
        }
        com_google_android_gms_common_api_internal_zzbr.connect();
    }

    public static zzbp zzca(Context context) {
        zzbp com_google_android_gms_common_api_internal_zzbp;
        synchronized (zzaqd) {
            if (zzfnn == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                zzfnn = new zzbp(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.getInstance());
            }
            com_google_android_gms_common_api_internal_zzbp = zzfnn;
        }
        return com_google_android_gms_common_api_internal_zzbp;
    }

    public final boolean handleMessage(Message message) {
        zzbr com_google_android_gms_common_api_internal_zzbr;
        switch (message.what) {
            case 1:
                this.zzfnm = ((Boolean) message.obj).booleanValue() ? 10000 : 300000;
                this.mHandler.removeMessages(12);
                for (zzh obtainMessage : this.zzfkk.keySet()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(12, obtainMessage), this.zzfnm);
                }
                break;
            case 2:
                zzj com_google_android_gms_common_api_internal_zzj = (zzj) message.obj;
                for (zzh com_google_android_gms_common_api_internal_zzh : com_google_android_gms_common_api_internal_zzj.zzafx()) {
                    zzbr com_google_android_gms_common_api_internal_zzbr2 = (zzbr) this.zzfkk.get(com_google_android_gms_common_api_internal_zzh);
                    if (com_google_android_gms_common_api_internal_zzbr2 == null) {
                        com_google_android_gms_common_api_internal_zzj.zza(com_google_android_gms_common_api_internal_zzh, new ConnectionResult(13));
                        break;
                    } else if (com_google_android_gms_common_api_internal_zzbr2.isConnected()) {
                        com_google_android_gms_common_api_internal_zzj.zza(com_google_android_gms_common_api_internal_zzh, ConnectionResult.zzfff);
                    } else if (com_google_android_gms_common_api_internal_zzbr2.zzahy() != null) {
                        com_google_android_gms_common_api_internal_zzj.zza(com_google_android_gms_common_api_internal_zzh, com_google_android_gms_common_api_internal_zzbr2.zzahy());
                    } else {
                        com_google_android_gms_common_api_internal_zzbr2.zza(com_google_android_gms_common_api_internal_zzj);
                    }
                }
                break;
            case 3:
                for (zzbr com_google_android_gms_common_api_internal_zzbr3 : this.zzfkk.values()) {
                    com_google_android_gms_common_api_internal_zzbr3.zzahx();
                    com_google_android_gms_common_api_internal_zzbr3.connect();
                }
                break;
            case 4:
            case 8:
            case 13:
                zzcq com_google_android_gms_common_api_internal_zzcq = (zzcq) message.obj;
                com_google_android_gms_common_api_internal_zzbr = (zzbr) this.zzfkk.get(com_google_android_gms_common_api_internal_zzcq.zzfpb.zzafk());
                if (com_google_android_gms_common_api_internal_zzbr == null) {
                    zzb(com_google_android_gms_common_api_internal_zzcq.zzfpb);
                    com_google_android_gms_common_api_internal_zzbr = (zzbr) this.zzfkk.get(com_google_android_gms_common_api_internal_zzcq.zzfpb.zzafk());
                }
                if (com_google_android_gms_common_api_internal_zzbr.zzaac() && this.zzfnq.get() != com_google_android_gms_common_api_internal_zzcq.zzfpa) {
                    com_google_android_gms_common_api_internal_zzcq.zzfoz.zzr(zzfnk);
                    com_google_android_gms_common_api_internal_zzbr.signOut();
                    break;
                }
                com_google_android_gms_common_api_internal_zzbr.zza(com_google_android_gms_common_api_internal_zzcq.zzfoz);
                break;
                break;
            case 5:
                String errorString;
                String errorMessage;
                int i = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                for (zzbr com_google_android_gms_common_api_internal_zzbr4 : this.zzfkk.values()) {
                    if (com_google_android_gms_common_api_internal_zzbr4.getInstanceId() == i) {
                        if (com_google_android_gms_common_api_internal_zzbr4 != null) {
                            Log.wtf("GoogleApiManager", "Could not find API instance " + i + " while trying to fail enqueued calls.", new Exception());
                            break;
                        }
                        errorString = this.zzfhl.getErrorString(connectionResult.getErrorCode());
                        errorMessage = connectionResult.getErrorMessage();
                        com_google_android_gms_common_api_internal_zzbr4.zzv(new Status(17, new StringBuilder((String.valueOf(errorString).length() + 69) + String.valueOf(errorMessage).length()).append("Error resolution was canceled by the user, original error message: ").append(errorString).append(": ").append(errorMessage).toString()));
                        break;
                    }
                }
                com_google_android_gms_common_api_internal_zzbr4 = null;
                if (com_google_android_gms_common_api_internal_zzbr4 != null) {
                    Log.wtf("GoogleApiManager", "Could not find API instance " + i + " while trying to fail enqueued calls.", new Exception());
                } else {
                    errorString = this.zzfhl.getErrorString(connectionResult.getErrorCode());
                    errorMessage = connectionResult.getErrorMessage();
                    com_google_android_gms_common_api_internal_zzbr4.zzv(new Status(17, new StringBuilder((String.valueOf(errorString).length() + 69) + String.valueOf(errorMessage).length()).append("Error resolution was canceled by the user, original error message: ").append(errorString).append(": ").append(errorMessage).toString()));
                }
            case 6:
                if (this.mContext.getApplicationContext() instanceof Application) {
                    zzk.zza((Application) this.mContext.getApplicationContext());
                    zzk.zzafz().zza(new zzbq(this));
                    if (!zzk.zzafz().zzbd(true)) {
                        this.zzfnm = 300000;
                        break;
                    }
                }
                break;
            case 7:
                zzb((GoogleApi) message.obj);
                break;
            case 9:
                if (this.zzfkk.containsKey(message.obj)) {
                    ((zzbr) this.zzfkk.get(message.obj)).resume();
                    break;
                }
                break;
            case 10:
                zzahr();
                break;
            case 11:
                if (this.zzfkk.containsKey(message.obj)) {
                    ((zzbr) this.zzfkk.get(message.obj)).zzahh();
                    break;
                }
                break;
            case 12:
                if (this.zzfkk.containsKey(message.obj)) {
                    ((zzbr) this.zzfkk.get(message.obj)).zzaib();
                    break;
                }
                break;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + message.what);
                return false;
        }
        return true;
    }

    final PendingIntent zza(zzh<?> com_google_android_gms_common_api_internal_zzh_, int i) {
        zzbr com_google_android_gms_common_api_internal_zzbr = (zzbr) this.zzfkk.get(com_google_android_gms_common_api_internal_zzh_);
        if (com_google_android_gms_common_api_internal_zzbr == null) {
            return null;
        }
        zzcps zzaic = com_google_android_gms_common_api_internal_zzbr.zzaic();
        return zzaic == null ? null : PendingIntent.getActivity(this.mContext, i, zzaic.zzaam(), 134217728);
    }

    public final Task<Void> zza(Iterable<? extends GoogleApi<?>> iterable) {
        zzj com_google_android_gms_common_api_internal_zzj = new zzj(iterable);
        for (GoogleApi zzafk : iterable) {
            zzbr com_google_android_gms_common_api_internal_zzbr = (zzbr) this.zzfkk.get(zzafk.zzafk());
            if (com_google_android_gms_common_api_internal_zzbr != null) {
                if (!com_google_android_gms_common_api_internal_zzbr.isConnected()) {
                }
            }
            this.mHandler.sendMessage(this.mHandler.obtainMessage(2, com_google_android_gms_common_api_internal_zzj));
            return com_google_android_gms_common_api_internal_zzj.getTask();
        }
        com_google_android_gms_common_api_internal_zzj.zzafy();
        return com_google_android_gms_common_api_internal_zzj.getTask();
    }

    public final void zza(ConnectionResult connectionResult, int i) {
        if (!zzc(connectionResult, i)) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(5, i, 0, connectionResult));
        }
    }

    public final void zza(GoogleApi<?> googleApi) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, googleApi));
    }

    public final <O extends ApiOptions> void zza(GoogleApi<O> googleApi, int i, zzm<? extends Result, zzb> com_google_android_gms_common_api_internal_zzm__extends_com_google_android_gms_common_api_Result__com_google_android_gms_common_api_Api_zzb) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, new zzcq(new zzc(i, com_google_android_gms_common_api_internal_zzm__extends_com_google_android_gms_common_api_Result__com_google_android_gms_common_api_Api_zzb), this.zzfnq.get(), googleApi)));
    }

    public final void zza(zzak com_google_android_gms_common_api_internal_zzak) {
        synchronized (zzaqd) {
            if (this.zzfnr != com_google_android_gms_common_api_internal_zzak) {
                this.zzfnr = com_google_android_gms_common_api_internal_zzak;
                this.zzfns.clear();
                this.zzfns.addAll(com_google_android_gms_common_api_internal_zzak.zzagv());
            }
        }
    }

    final void zzafp() {
        this.zzfnq.incrementAndGet();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(10));
    }

    public final void zzafw() {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3));
    }

    public final int zzahq() {
        return this.zzfnp.getAndIncrement();
    }

    final void zzb(zzak com_google_android_gms_common_api_internal_zzak) {
        synchronized (zzaqd) {
            if (this.zzfnr == com_google_android_gms_common_api_internal_zzak) {
                this.zzfnr = null;
                this.zzfns.clear();
            }
        }
    }

    final boolean zzc(ConnectionResult connectionResult, int i) {
        return this.zzfhl.zza(this.mContext, connectionResult, i);
    }
}
