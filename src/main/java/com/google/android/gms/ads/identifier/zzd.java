package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.common.zzo;

final class zzd {
    private SharedPreferences zzamc;

    zzd(Context context) {
        try {
            Context remoteContext = zzo.getRemoteContext(context);
            this.zzamc = remoteContext == null ? null : remoteContext.getSharedPreferences("google_ads_flags", 0);
        } catch (Throwable th) {
            Log.w("GmscoreFlag", "Error while getting SharedPreferences ", th);
            this.zzamc = null;
        }
    }

    final boolean getBoolean(String str, boolean z) {
        boolean z2 = false;
        try {
            if (this.zzamc != null) {
                z2 = this.zzamc.getBoolean(str, false);
            }
        } catch (Throwable th) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", th);
        }
        return z2;
    }

    final float getFloat(String str, float f) {
        float f2 = 0.0f;
        try {
            if (this.zzamc != null) {
                f2 = this.zzamc.getFloat(str, 0.0f);
            }
        } catch (Throwable th) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", th);
        }
        return f2;
    }

    final String getString(String str, String str2) {
        try {
            if (this.zzamc != null) {
                str2 = this.zzamc.getString(str, str2);
            }
        } catch (Throwable th) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", th);
        }
        return str2;
    }
}
