package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.coinbase.api.internal.ApiConstants;
import java.io.IOException;
import java.security.KeyPair;
import java.util.Map;

public final class zzj {
    private static Map<String, zzj> zzhtn = new ArrayMap();
    static String zzhtt;
    private static zzr zznft;
    private static zzl zznfu;
    private Context mContext;
    private KeyPair zzhtq;
    private String zzhtr = "";

    private zzj(Context context, String str, Bundle bundle) {
        this.mContext = context.getApplicationContext();
        this.zzhtr = str;
    }

    public static synchronized zzj zza(Context context, Bundle bundle) {
        zzj com_google_firebase_iid_zzj;
        synchronized (zzj.class) {
            String string = bundle == null ? "" : bundle.getString("subtype");
            String str = string == null ? "" : string;
            Context applicationContext = context.getApplicationContext();
            if (zznft == null) {
                zznft = new zzr(applicationContext);
                zznfu = new zzl(applicationContext);
            }
            zzhtt = Integer.toString(FirebaseInstanceId.zzej(applicationContext));
            com_google_firebase_iid_zzj = (zzj) zzhtn.get(str);
            if (com_google_firebase_iid_zzj == null) {
                com_google_firebase_iid_zzj = new zzj(applicationContext, str, bundle);
                zzhtn.put(str, com_google_firebase_iid_zzj);
            }
        }
        return com_google_firebase_iid_zzj;
    }

    public static zzr zzcga() {
        return zznft;
    }

    public static zzl zzcgb() {
        return zznfu;
    }

    public final String getToken(String str, String str2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        Object obj = 1;
        if (bundle.getString("ttl") != null || "jwt".equals(bundle.getString("type"))) {
            obj = null;
        } else {
            zzs zzo = zznft.zzo(this.zzhtr, str, str2);
            if (!(zzo == null || zzo.zzrg(zzhtt))) {
                return zzo.zzkoo;
            }
        }
        String zzb = zzb(str, str2, bundle);
        if (zzb == null || r0 == null) {
            return zzb;
        }
        zznft.zza(this.zzhtr, str, str2, zzb, zzhtt);
        return zzb;
    }

    public final void zza(String str, String str2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        zznft.zzf(this.zzhtr, str, str2);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("delete", "1");
        zzb(str, str2, bundle);
    }

    final KeyPair zzasq() {
        if (this.zzhtq == null) {
            this.zzhtq = zznft.zzre(this.zzhtr);
        }
        if (this.zzhtq == null) {
            this.zzhtq = zznft.zzrc(this.zzhtr);
        }
        return this.zzhtq;
    }

    public final void zzasr() {
        zznft.zzrd(this.zzhtr);
        this.zzhtq = null;
    }

    public final String zzb(String str, String str2, Bundle bundle) throws IOException {
        if (str2 != null) {
            bundle.putString(ApiConstants.SCOPE, str2);
        }
        bundle.putString("sender", str);
        if (!"".equals(this.zzhtr)) {
            str = this.zzhtr;
        }
        bundle.putString("subtype", str);
        bundle.putString("X-subtype", str);
        Intent zza = zznfu.zza(bundle, zzasq());
        if (zza == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String stringExtra = zza.getStringExtra("registration_id");
        if (stringExtra == null) {
            stringExtra = zza.getStringExtra("unregistered");
        }
        if (stringExtra != null) {
            return stringExtra;
        }
        stringExtra = zza.getStringExtra("error");
        if (stringExtra != null) {
            throw new IOException(stringExtra);
        }
        String valueOf = String.valueOf(zza.getExtras());
        Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(valueOf).length() + 29).append("Unexpected response from GCM ").append(valueOf).toString(), new Throwable());
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }
}
