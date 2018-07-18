package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public final class IdToken extends zzbck implements ReflectedParcelable {
    public static final Creator<IdToken> CREATOR = new zzh();
    private final String zzdzr;
    private final String zzeaw;

    public IdToken(String str, String str2) {
        boolean z = true;
        zzbp.zzb(!TextUtils.isEmpty(str), (Object) "account type string cannot be null or empty");
        if (TextUtils.isEmpty(str2)) {
            z = false;
        }
        zzbp.zzb(z, (Object) "id token string cannot be null or empty");
        this.zzdzr = str;
        this.zzeaw = str2;
    }

    public final String getAccountType() {
        return this.zzdzr;
    }

    public final String getIdToken() {
        return this.zzeaw;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 1, getAccountType(), false);
        zzbcn.zza(parcel, 2, getIdToken(), false);
        zzbcn.zzai(parcel, zze);
    }
}
