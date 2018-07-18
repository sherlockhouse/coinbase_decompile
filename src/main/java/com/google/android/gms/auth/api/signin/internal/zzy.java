package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.coinbase.android.utils.CryptoUri;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.zzbp;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

public final class zzy {
    private static final Lock zzedl = new ReentrantLock();
    private static zzy zzedm;
    private final Lock zzedn = new ReentrantLock();
    private final SharedPreferences zzedo;

    private zzy(Context context) {
        this.zzedo = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static zzy zzbl(Context context) {
        zzbp.zzu(context);
        zzedl.lock();
        try {
            if (zzedm == null) {
                zzedm = new zzy(context.getApplicationContext());
            }
            zzy com_google_android_gms_auth_api_signin_internal_zzy = zzedm;
            return com_google_android_gms_auth_api_signin_internal_zzy;
        } finally {
            zzedl.unlock();
        }
    }

    private final GoogleSignInAccount zzep(String str) {
        GoogleSignInAccount googleSignInAccount = null;
        if (!TextUtils.isEmpty(str)) {
            String zzer = zzer(zzq("googleSignInAccount", str));
            if (zzer != null) {
                try {
                    googleSignInAccount = GoogleSignInAccount.zzem(zzer);
                } catch (JSONException e) {
                }
            }
        }
        return googleSignInAccount;
    }

    private final GoogleSignInOptions zzeq(String str) {
        GoogleSignInOptions googleSignInOptions = null;
        if (!TextUtils.isEmpty(str)) {
            String zzer = zzer(zzq("googleSignInOptions", str));
            if (zzer != null) {
                try {
                    googleSignInOptions = GoogleSignInOptions.zzen(zzer);
                } catch (JSONException e) {
                }
            }
        }
        return googleSignInOptions;
    }

    private final String zzer(String str) {
        this.zzedn.lock();
        try {
            String string = this.zzedo.getString(str, null);
            return string;
        } finally {
            this.zzedn.unlock();
        }
    }

    private final void zzes(String str) {
        this.zzedn.lock();
        try {
            this.zzedo.edit().remove(str).apply();
        } finally {
            this.zzedn.unlock();
        }
    }

    private final void zzp(String str, String str2) {
        this.zzedn.lock();
        try {
            this.zzedo.edit().putString(str, str2).apply();
        } finally {
            this.zzedn.unlock();
        }
    }

    private static String zzq(String str, String str2) {
        String str3 = CryptoUri.URI_SCHEME_DELIMETER;
        return new StringBuilder((String.valueOf(str).length() + String.valueOf(str3).length()) + String.valueOf(str2).length()).append(str).append(str3).append(str2).toString();
    }

    public final void zza(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        zzbp.zzu(googleSignInAccount);
        zzbp.zzu(googleSignInOptions);
        zzp("defaultGoogleSignInAccount", googleSignInAccount.zzaae());
        zzbp.zzu(googleSignInAccount);
        zzbp.zzu(googleSignInOptions);
        String zzaae = googleSignInAccount.zzaae();
        zzp(zzq("googleSignInAccount", zzaae), googleSignInAccount.zzaaf());
        zzp(zzq("googleSignInOptions", zzaae), googleSignInOptions.zzaai());
    }

    public final GoogleSignInAccount zzaas() {
        return zzep(zzer("defaultGoogleSignInAccount"));
    }

    public final GoogleSignInOptions zzaat() {
        return zzeq(zzer("defaultGoogleSignInAccount"));
    }

    public final void zzaau() {
        String zzer = zzer("defaultGoogleSignInAccount");
        zzes("defaultGoogleSignInAccount");
        if (!TextUtils.isEmpty(zzer)) {
            zzes(zzq("googleSignInAccount", zzer));
            zzes(zzq("googleSignInOptions", zzer));
        }
    }
}
