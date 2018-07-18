package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

public class SignInAccount extends zzbck implements ReflectedParcelable {
    public static final Creator<SignInAccount> CREATOR = new zze();
    @Deprecated
    private String zzdmy;
    @Deprecated
    private String zzebw;
    private GoogleSignInAccount zzecp;

    SignInAccount(String str, GoogleSignInAccount googleSignInAccount, String str2) {
        this.zzecp = googleSignInAccount;
        this.zzebw = zzbp.zzh(str, "8.3 and 8.4 SDKs require non-null email");
        this.zzdmy = zzbp.zzh(str2, "8.3 and 8.4 SDKs require non-null userId");
    }

    public final GoogleSignInAccount getGoogleSignInAccount() {
        return this.zzecp;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 4, this.zzebw, false);
        zzbcn.zza(parcel, 7, this.zzecp, i, false);
        zzbcn.zza(parcel, 8, this.zzdmy, false);
        zzbcn.zzai(parcel, zze);
    }
}
