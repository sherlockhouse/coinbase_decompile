package com.google.firebase.messaging;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.Map;

public final class RemoteMessage extends zzbck {
    public static final Creator<RemoteMessage> CREATOR = new zzf();
    Bundle mBundle;
    private Map<String, String> zzdks;
    private Notification zzngk;

    public static class Notification {
        private final String mTag;
        private final String zzbrq;
        private final String zzehk;
        private final String zzngl;
        private final String[] zzngm;
        private final String zzngn;
        private final String[] zzngo;
        private final String zzngp;
        private final String zzngq;
        private final String zzngr;
        private final String zzngs;
        private final Uri zzngt;

        private Notification(Bundle bundle) {
            this.zzehk = zza.zze(bundle, "gcm.n.title");
            this.zzngl = zza.zzh(bundle, "gcm.n.title");
            this.zzngm = zzk(bundle, "gcm.n.title");
            this.zzbrq = zza.zze(bundle, "gcm.n.body");
            this.zzngn = zza.zzh(bundle, "gcm.n.body");
            this.zzngo = zzk(bundle, "gcm.n.body");
            this.zzngp = zza.zze(bundle, "gcm.n.icon");
            this.zzngq = zza.zzaf(bundle);
            this.mTag = zza.zze(bundle, "gcm.n.tag");
            this.zzngr = zza.zze(bundle, "gcm.n.color");
            this.zzngs = zza.zze(bundle, "gcm.n.click_action");
            this.zzngt = zza.zzae(bundle);
        }

        private static String[] zzk(Bundle bundle, String str) {
            Object[] zzi = zza.zzi(bundle, str);
            if (zzi == null) {
                return null;
            }
            String[] strArr = new String[zzi.length];
            for (int i = 0; i < zzi.length; i++) {
                strArr[i] = String.valueOf(zzi[i]);
            }
            return strArr;
        }

        public String getBody() {
            return this.zzbrq;
        }
    }

    RemoteMessage(Bundle bundle) {
        this.mBundle = bundle;
    }

    public final Map<String, String> getData() {
        if (this.zzdks == null) {
            this.zzdks = new ArrayMap();
            for (String str : this.mBundle.keySet()) {
                Object obj = this.mBundle.get(str);
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (!(str.startsWith("google.") || str.startsWith("gcm.") || str.equals("from") || str.equals("message_type") || str.equals("collapse_key"))) {
                        this.zzdks.put(str, str2);
                    }
                }
            }
        }
        return this.zzdks;
    }

    public final Notification getNotification() {
        if (this.zzngk == null && zza.zzad(this.mBundle)) {
            this.zzngk = new Notification(this.mBundle);
        }
        return this.zzngk;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 2, this.mBundle, false);
        zzbcn.zzai(parcel, zze);
    }
}
