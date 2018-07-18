package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.internal.zzbcl;

public final class zzw implements Creator<SignInConfiguration> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        GoogleSignInOptions googleSignInOptions = null;
        int zzd = zzbcl.zzd(parcel);
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = zzbcl.zzq(parcel, readInt);
                    break;
                case 5:
                    googleSignInOptions = (GoogleSignInOptions) zzbcl.zza(parcel, readInt, GoogleSignInOptions.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, readInt);
                    break;
            }
        }
        zzbcl.zzaf(parcel, zzd);
        return new SignInConfiguration(str, googleSignInOptions);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new SignInConfiguration[i];
    }
}
