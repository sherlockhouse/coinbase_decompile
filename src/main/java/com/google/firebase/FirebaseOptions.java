package com.google.firebase;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzbz;
import com.google.android.gms.common.util.zzt;
import java.util.Arrays;

public final class FirebaseOptions {
    private final String zzehy;
    private final String zzlir;
    private final String zzlis;
    private final String zzlit;
    private final String zzliu;
    private final String zzliv;
    private final String zzliw;

    private FirebaseOptions(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        zzbp.zza(!zzt.zzgm(str), "ApplicationId must be set.");
        this.zzehy = str;
        this.zzlir = str2;
        this.zzlis = str3;
        this.zzlit = str4;
        this.zzliu = str5;
        this.zzliv = str6;
        this.zzliw = str7;
    }

    public static FirebaseOptions fromResource(Context context) {
        zzbz com_google_android_gms_common_internal_zzbz = new zzbz(context);
        Object string = com_google_android_gms_common_internal_zzbz.getString("google_app_id");
        return TextUtils.isEmpty(string) ? null : new FirebaseOptions(string, com_google_android_gms_common_internal_zzbz.getString("google_api_key"), com_google_android_gms_common_internal_zzbz.getString("firebase_database_url"), com_google_android_gms_common_internal_zzbz.getString("ga_trackingId"), com_google_android_gms_common_internal_zzbz.getString("gcm_defaultSenderId"), com_google_android_gms_common_internal_zzbz.getString("google_storage_bucket"), com_google_android_gms_common_internal_zzbz.getString("project_id"));
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FirebaseOptions)) {
            return false;
        }
        FirebaseOptions firebaseOptions = (FirebaseOptions) obj;
        return zzbf.equal(this.zzehy, firebaseOptions.zzehy) && zzbf.equal(this.zzlir, firebaseOptions.zzlir) && zzbf.equal(this.zzlis, firebaseOptions.zzlis) && zzbf.equal(this.zzlit, firebaseOptions.zzlit) && zzbf.equal(this.zzliu, firebaseOptions.zzliu) && zzbf.equal(this.zzliv, firebaseOptions.zzliv) && zzbf.equal(this.zzliw, firebaseOptions.zzliw);
    }

    public final String getApplicationId() {
        return this.zzehy;
    }

    public final String getGcmSenderId() {
        return this.zzliu;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzehy, this.zzlir, this.zzlis, this.zzlit, this.zzliu, this.zzliv, this.zzliw});
    }

    public final String toString() {
        return zzbf.zzt(this).zzg("applicationId", this.zzehy).zzg("apiKey", this.zzlir).zzg("databaseUrl", this.zzlis).zzg("gcmSenderId", this.zzliu).zzg("storageBucket", this.zzliv).zzg("projectId", this.zzliw).toString();
    }
}
