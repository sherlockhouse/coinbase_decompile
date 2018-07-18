package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.v4.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import com.coinbase.android.utils.CryptoUri;
import com.google.firebase.FirebaseApp;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class FirebaseInstanceId {
    private static Map<String, FirebaseInstanceId> zzhtn = new ArrayMap();
    private static zzk zznfj;
    private final FirebaseApp zznfk;
    private final zzj zznfl;
    private final String zznfm;

    private FirebaseInstanceId(FirebaseApp firebaseApp, zzj com_google_firebase_iid_zzj) {
        this.zznfk = firebaseApp;
        this.zznfl = com_google_firebase_iid_zzj;
        String gcmSenderId = this.zznfk.getOptions().getGcmSenderId();
        if (gcmSenderId == null) {
            gcmSenderId = this.zznfk.getOptions().getApplicationId();
            if (gcmSenderId.startsWith("1:")) {
                String[] split = gcmSenderId.split(CryptoUri.URI_SCHEME_DELIMETER);
                if (split.length < 2) {
                    gcmSenderId = null;
                } else {
                    gcmSenderId = split[1];
                    if (gcmSenderId.isEmpty()) {
                        gcmSenderId = null;
                    }
                }
            }
        }
        this.zznfm = gcmSenderId;
        if (this.zznfm == null) {
            throw new IllegalStateException("IID failing to initialize, FirebaseApp is missing project ID");
        }
        FirebaseInstanceIdService.zza(this.zznfk.getApplicationContext(), this);
    }

    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    @Keep
    public static synchronized FirebaseInstanceId getInstance(FirebaseApp firebaseApp) {
        FirebaseInstanceId firebaseInstanceId;
        synchronized (FirebaseInstanceId.class) {
            firebaseInstanceId = (FirebaseInstanceId) zzhtn.get(firebaseApp.getOptions().getApplicationId());
            if (firebaseInstanceId == null) {
                zzj zza = zzj.zza(firebaseApp.getApplicationContext(), null);
                if (zznfj == null) {
                    zznfj = new zzk(zzj.zzcga());
                }
                firebaseInstanceId = new FirebaseInstanceId(firebaseApp, zza);
                zzhtn.put(firebaseApp.getOptions().getApplicationId(), firebaseInstanceId);
            }
        }
        return firebaseInstanceId;
    }

    static String zza(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) ((digest[0] & 15) + 112);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException e) {
            Log.w("FirebaseInstanceId", "Unexpected error, device missing required alghorithms");
            return null;
        }
    }

    static void zza(Context context, zzr com_google_firebase_iid_zzr) {
        com_google_firebase_iid_zzr.zzasv();
        Intent intent = new Intent();
        intent.putExtra("CMD", "RST");
        zzq.zzcge().zze(context, intent);
    }

    private final void zzac(Bundle bundle) {
        bundle.putString("gmp_app_id", this.zznfk.getOptions().getApplicationId());
    }

    static int zzao(Context context, String str) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Failed to find package ").append(valueOf).toString());
            return i;
        }
    }

    static zzk zzcfz() {
        return zznfj;
    }

    static String zzdd(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 38).append("Never happens: can't find own package ").append(valueOf).toString());
            return null;
        }
    }

    static int zzej(Context context) {
        return zzao(context, context.getPackageName());
    }

    static void zzek(Context context) {
        Intent intent = new Intent();
        intent.putExtra("CMD", "SYNC");
        zzq.zzcge().zze(context, intent);
    }

    static String zzn(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }

    public String getId() {
        return zza(this.zznfl.zzasq());
    }

    public String getToken() {
        zzs zzcfx = zzcfx();
        if (zzcfx == null || zzcfx.zzrg(zzj.zzhtt)) {
            FirebaseInstanceIdService.zzem(this.zznfk.getApplicationContext());
        }
        return zzcfx != null ? zzcfx.zzkoo : null;
    }

    public String getToken(String str, String str2) throws IOException {
        Bundle bundle = new Bundle();
        zzac(bundle);
        return this.zznfl.getToken(str, str2, bundle);
    }

    final zzs zzcfx() {
        return zzj.zzcga().zzo("", this.zznfm, "*");
    }

    final String zzcfy() throws IOException {
        return getToken(this.zznfm, "*");
    }

    final void zzqx(String str) throws IOException {
        zzs zzcfx = zzcfx();
        if (zzcfx == null || zzcfx.zzrg(zzj.zzhtt)) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String str2 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str);
        bundle.putString(str2, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        String str3 = zzcfx.zzkoo;
        str2 = String.valueOf("/topics/");
        valueOf2 = String.valueOf(str);
        valueOf2 = valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2);
        zzac(bundle);
        this.zznfl.zzb(str3, valueOf2, bundle);
    }

    final void zzqy(String str) throws IOException {
        zzs zzcfx = zzcfx();
        if (zzcfx == null || zzcfx.zzrg(zzj.zzhtt)) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String str2 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str);
        bundle.putString(str2, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        zzj com_google_firebase_iid_zzj = this.zznfl;
        String str3 = zzcfx.zzkoo;
        valueOf = String.valueOf("/topics/");
        valueOf2 = String.valueOf(str);
        com_google_firebase_iid_zzj.zza(str3, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), bundle);
    }
}
